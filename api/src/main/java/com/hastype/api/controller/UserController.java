package com.hastype.api.controller;

import com.hastype.api.dtos.LoginRecordDto;
import com.hastype.api.dtos.UserRecordDto;
import com.hastype.api.models.UserModel;
import com.hastype.api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("add")
    public ResponseEntity<UserModel> cadastraUsuario(@RequestBody @Valid UserRecordDto userRecordDto){

        var user = new UserModel();
        BeanUtils.copyProperties(userRecordDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @PostMapping("validaLogin")
    public Boolean validaLogin (@RequestBody @Valid LoginRecordDto loginRecordDto){

        var user = new UserModel();
        BeanUtils.copyProperties(user, loginRecordDto);

        Optional<UserModel> userVal = userRepository.findByEmailAndSenha(loginRecordDto.email(), loginRecordDto.senha());

        return userVal.isPresent();

    }

    @GetMapping("all")
    public ResponseEntity<List<UserModel>> listarUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

}