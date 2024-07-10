package com.hastype.api.services;


import com.hastype.api.dtos.LoginRecordDto;
import com.hastype.api.dtos.UserRecordDto;
import com.hastype.api.exceptions.EmailAlreadyExistsException;
import com.hastype.api.exceptions.LoginFailedException;
import com.hastype.api.exceptions.UserNotFoundException;
import com.hastype.api.models.SessaoModel;
import com.hastype.api.models.UserModel;
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
    private final SessaoService sessaoService;

    public UserService(UserRepository userRepository, SessaoService sessaoService) {
        this.userRepository = userRepository;
        this.sessaoService = sessaoService;
    }

    public ResponseEntity<SessaoModel> validaLogin(LoginRecordDto loginRecordDto){
        var user = new UserModel();
        BeanUtils.copyProperties(loginRecordDto, user);

        boolean isLoginValid = user.validateUserLogin(loginRecordDto.email(), loginRecordDto.senha());
        System.out.println(isLoginValid);
        if(isLoginValid){

            UserModel userValidated = userRepository.findByEmailAndSenha(loginRecordDto.email(), loginRecordDto.senha()).orElseThrow(LoginFailedException::new);
            return ResponseEntity.status(HttpStatus.FOUND).body(sessaoService.saveSession(userValidated.getId()));

        }

        throw new LoginFailedException();

        // TODO: criar método para gerar um token falso
        // 1 - Usar lib do java para gerar um hash
        // 2 - criar uma tabela no banco de dados com o nome de Sessão
        // essa tabela vai ter o id do usuário, o hash e um time em segundos de validade do token

    }

    public ResponseEntity<UserModel> addUser(UserRecordDto userRecordDto){

            if(userRepository.findByEmail(userRecordDto.email()).isPresent()){
                logger.info("Usuário {} já existe no sistema!!!", userRecordDto.email());
                throw new EmailAlreadyExistsException();
            }

            var user = new UserModel();
            BeanUtils.copyProperties(userRecordDto, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    public ResponseEntity<List<UserModel>> findAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    public ResponseEntity<UserModel> findByEmail(String email){
        return ResponseEntity.status(HttpStatus.FOUND).body(userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));
    }
}
