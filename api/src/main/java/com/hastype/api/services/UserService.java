package com.hastype.api.services;


import com.hastype.api.dtos.LoginRecordDto;
import com.hastype.api.dtos.UserRecordDto;
import com.hastype.api.exceptions.*;
import com.hastype.api.models.SessaoModel;
import com.hastype.api.models.UserModel;
import com.hastype.api.repository.SessaoRepository;
import com.hastype.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class  UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final SessaoRepository sessaoRepository;

    private final SessaoService sessaoService;

    public UserService(UserRepository userRepository, SessaoRepository sessaoRepository, SessaoService sessaoService) {
        this.userRepository = userRepository;
        this.sessaoRepository = sessaoRepository;
        this.sessaoService = sessaoService;
    }

    public ResponseEntity<Boolean> singoutUser(UUID userId){
        var sessaoModel = sessaoRepository.findByUserId(userId).orElseThrow(SessionDoesntExistException::new);

        try{
            sessaoRepository.deleteById(sessaoModel.getId());
            return ResponseEntity.status(HttpStatus.OK).body(Boolean.TRUE);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(Boolean.FALSE);

        }

    }


    public ResponseEntity<SessaoModel> loginValidation(LoginRecordDto loginRecordDto){

        var user = new UserModel();

        UserModel userValidated = userRepository
                .findByEmailAndSenha(loginRecordDto.email(), loginRecordDto.senha())
                .orElseThrow(LoginFailedException::new);

        BeanUtils.copyProperties(loginRecordDto, user);

        boolean isLoginValid = user.validateUserLogin(userValidated.getEmail(), userValidated.getSenha());

//        check email and password to login
        if(isLoginValid){

//          check if user already has activity session
            if(sessaoService.isUserAlreadyLoggedInSession(userValidated.getId())){
                throw new UserIsAlreadyLoggedException();
            }
            logger.info("Criando Sessão do Usuário!!");
            return ResponseEntity.status(HttpStatus.OK).body(sessaoService.saveSession(userValidated.getId()));

        }

        throw new LoginFailedException();

    }

    public ResponseEntity<SessaoModel> addUser(UserRecordDto userRecordDto){

            if(userRepository.findByEmail(userRecordDto.email()).isPresent()){
                logger.info("Usuário {} já existe no sistema!!!", userRecordDto.email());
                throw new EmailAlreadyExistsException();
            }

            var user = new UserModel();
            BeanUtils.copyProperties(userRecordDto, user);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(sessaoService.saveSession(user.getId()));
    }

    public ResponseEntity<List<UserModel>> findAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    public ResponseEntity<UserModel> findByEmail(String email){
        return ResponseEntity.status(HttpStatus.FOUND).body(userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));
    }
}
