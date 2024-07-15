package com.hastype.api.controller;

import com.hastype.api.dtos.LoginRecordDto;
import com.hastype.api.dtos.UserRecordDto;
import com.hastype.api.models.SessaoModel;
import com.hastype.api.models.UserModel;
import com.hastype.api.repository.UserRepository;
import com.hastype.api.services.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("singup")
    public ResponseEntity<SessaoModel> cadastraUsuario(@RequestBody @Valid UserRecordDto userRecordDto){

        return userService.addUser(userRecordDto);

    }

    @PostMapping("singin")
    public ResponseEntity<SessaoModel> validaLogin (@RequestBody @Valid LoginRecordDto loginRecordDto){

        return userService.loginValidation(loginRecordDto);

    }

    @DeleteMapping("logout/{userId}")
    public ResponseEntity<Boolean> singout (@PathVariable(value="userId") UUID userId){

        return userService.singoutUser(userId);

    }

    @GetMapping("all")
    public ResponseEntity<List<UserModel>> listarUsuarios(){

        return userService.findAllUsers();

    }

    @GetMapping("byEmail/{email}")
    public ResponseEntity<UserModel> userByEmail(@PathVariable(value="email") String email){
        return userService.findByEmail(email);
    }

}