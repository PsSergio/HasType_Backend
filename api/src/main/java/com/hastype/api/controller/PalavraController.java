package com.hastype.api.controller;

import com.hastype.api.dtos.PalavraRecordDto;
import com.hastype.api.models.PalavraModel;
import com.hastype.api.repository.PalavraRepository;
import com.hastype.api.services.PalavraService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("palavra/")
public class PalavraController {


    private final PalavraService palavraService;

    @Autowired
    public PalavraController(PalavraService palavraService) {
        this.palavraService = palavraService;
    }

    @PostMapping("add")
    public ResponseEntity<PalavraModel> adicionaPalavra(@RequestBody @Valid PalavraRecordDto palavraRecordDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(palavraService.adicionaPalavra(palavraRecordDto));

    }

    @GetMapping("all")
    public ResponseEntity<List<PalavraModel>> listaPalavras(){
        return ResponseEntity.status(HttpStatus.OK).body(palavraService.listarPalavras());
    }

}
