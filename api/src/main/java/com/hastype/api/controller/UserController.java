package com.hastype.api.controller;

import com.hastype.api.dtos.LoginRecordDto;
import com.hastype.api.dtos.UserRecordDto;
import com.hastype.api.models.UserModel;
import com.hastype.api.repository.UserRepository;
import com.hastype.api.services.UserService;
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

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("add")
    public ResponseEntity<UserModel> cadastraUsuario(@RequestBody @Valid UserRecordDto userRecordDto){

        return userService.addUser(userRecordDto);

    }

    @PostMapping("validaLogin")
    public ResponseEntity<Object> validaLogin (@RequestBody @Valid LoginRecordDto loginRecordDto){

        return userService.validaLogin(loginRecordDto);

    }

    @GetMapping("all")
    public ResponseEntity<List<UserModel>> listarUsuarios(){

        return userService.findAllUsers();

    }

}