package com.hastype.api.services;


import com.hastype.api.dtos.LoginRecordDto;
import com.hastype.api.dtos.UserRecordDto;
import com.hastype.api.models.UserModel;
import com.hastype.api.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class  UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validaLogin(LoginRecordDto loginRecordDto){
        var user = new UserModel();
        BeanUtils.copyProperties(user, loginRecordDto);

        Optional<UserModel> userVal = userRepository.findByEmailAndSenha(loginRecordDto.email(), loginRecordDto.senha());

        return userVal.isPresent();
    }

    public ResponseEntity<UserModel> addUser(UserRecordDto userRecordDto){
        var user = new UserModel();
        BeanUtils.copyProperties(userRecordDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    public ResponseEntity<List<UserModel>> findAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }
}
