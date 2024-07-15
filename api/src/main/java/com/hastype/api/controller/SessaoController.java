package com.hastype.api.controller;

import com.hastype.api.services.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("sessao/")
public class SessaoController {

    private final SessaoService sessaoService;

    @Autowired
    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping("validar/{sessionId}")
    public ResponseEntity<Boolean> validateSession(@PathVariable(value="sessionId") UUID sessionId){
        sessaoService.validateSession(sessionId);
        return ResponseEntity.status(HttpStatus.OK).body(Boolean.TRUE);
    }

}
