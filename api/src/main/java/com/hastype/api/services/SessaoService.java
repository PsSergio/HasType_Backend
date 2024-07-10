package com.hastype.api.services;

import com.hastype.api.exceptions.SessionDoesntExistException;
import com.hastype.api.exceptions.SessionIsExpiredException;
import com.hastype.api.models.SessaoModel;
import com.hastype.api.repository.SessaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;

    public SessaoService(SessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    public boolean validateSession(UUID sessionId){
        var session = sessaoRepository.findById(sessionId);
        if(session.isPresent()){

            var sessaoModel = new SessaoModel();
            BeanUtils.copyProperties(session, sessaoModel);
            boolean sessionIsValid = sessaoModel.validateSessionExpiration();

            if(sessionIsValid){
                return true;
            }

            throw new SessionIsExpiredException();

        }

            throw new SessionDoesntExistException();

    }

}
