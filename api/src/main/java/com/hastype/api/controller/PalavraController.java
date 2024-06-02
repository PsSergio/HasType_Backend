package com.hastype.api.controller;

import com.hastype.api.dtos.PalavraRecordDto;
import com.hastype.api.models.PalavraModel;
import com.hastype.api.repository.PalavraRepository;
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

    @Autowired
    private PalavraRepository palavraRepository;

    @PostMapping("add")
    public ResponseEntity<PalavraModel> adicionaPalavra(@RequestBody @Valid PalavraRecordDto palavraRecordDto){

        var palavraModel = new PalavraModel();
        BeanUtils.copyProperties(palavraRecordDto, palavraModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(palavraRepository.save(palavraModel));

    }

    @GetMapping("all")
    public ResponseEntity<List<PalavraModel>> listaPalavras(){
        return ResponseEntity.status(HttpStatus.OK).body(palavraRepository.findAll());
    }

    @GetMapping("sorteiaPalavras/{qtdPalavras}")
    public ResponseEntity<List<PalavraModel>> sorteaiaPalavras(@PathVariable(name="qtdPalavras") int qtdPalavras){

        var listaId = new PalavraModel().sorteiaNumeros((int)palavraRepository.count(), qtdPalavras);

        return ResponseEntity.status(HttpStatus.OK).body(palavraRepository.findAllById(listaId));
    }

}
