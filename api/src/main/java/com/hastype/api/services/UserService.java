package com.hastype.api.services;


import com.hastype.api.dtos.LoginRecordDto;
import com.hastype.api.dtos.UserRecordDto;
import com.hastype.api.exceptions.EmailAlreadyExistsException;
import com.hastype.api.exceptions.LoginFailedException;
import com.hastype.api.exceptions.UserNotFoundException;
import com.hastype.api.models.UserModel;
import com.hastype.api.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class  UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<UserModel> validaLogin(LoginRecordDto loginRecordDto){
        var user = new UserModel();
        BeanUtils.copyProperties(user, loginRecordDto);

        UserModel userVal = userRepository.findByEmailAndSenha(loginRecordDto.email(), loginRecordDto.senha()).orElseThrow(LoginFailedException::new);

        return ResponseEntity.status(HttpStatus.FOUND).body(userVal);

    }

    public ResponseEntity<UserModel> addUser(UserRecordDto userRecordDto){

            if(userRepository.findByEmail(userRecordDto.email()).isPresent()){
                throw new EmailAlreadyExistsException();
            }

            var user = new UserModel();
            BeanUtils.copyProperties(userRecordDto, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    public ResponseEntity<List<UserModel>> findAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }
}
