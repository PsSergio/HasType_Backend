package com.hastype.api.services;

import com.hastype.api.exceptions.SessionDoesntExistException;
import com.hastype.api.exceptions.SessionIsExpiredException;
import com.hastype.api.models.SessaoModel;
import com.hastype.api.repository.SessaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;

    public SessaoService(SessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    public SessaoModel saveSession(UUID userId){

        var sessaoModel = new SessaoModel();

        sessaoModel.setUserId(userId);
        sessaoModel.setInitialSession(LocalDateTime.now());
        sessaoModel.calculateFinalSession(300);

        sessaoRepository.save(sessaoModel);

        return sessaoModel;

    }

    public SessaoModel updateSession(SessaoModel sessaoModel){
        sessaoModel.setInitialSession(LocalDateTime.now());
        sessaoModel.calculateFinalSession(300);

        sessaoRepository.save(sessaoModel);
        return sessaoModel;
    }

    public SessaoModel findById(UUID sessionId){
        return sessaoRepository.findById(sessionId).orElseThrow(SessionDoesntExistException::new);
    }

    public void validateSession(UUID sessionId){
        var session = sessaoRepository.findById(sessionId);
        if(session.isPresent()){

            var sessaoModel = new SessaoModel();
            BeanUtils.copyProperties(session.get(), sessaoModel);

            boolean sessionIsValid = sessaoModel.validateSessionExpiration();

            if(sessionIsValid){
                updateSession(sessaoModel);
                return;
            }

            sessaoRepository.deleteById(sessionId);
            throw new SessionIsExpiredException();

        }

            throw new SessionDoesntExistException();

    }

}
