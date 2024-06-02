package com.hastype.api.services;

import com.hastype.api.dtos.PalavraRecordDto;
import com.hastype.api.models.PalavraModel;
import com.hastype.api.repository.PalavraRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalavraService {

    private PalavraRepository palavraRepository;

    public PalavraService(PalavraRepository palavraRepository) {
        this.palavraRepository = palavraRepository;
    }

    public PalavraModel adicionaPalavra(PalavraRecordDto palavraRecordDto){
        var palavraModel = new PalavraModel();
        BeanUtils.copyProperties(palavraRecordDto, palavraModel);
        return palavraRepository.save(palavraModel);
    }

    public List<PalavraModel> listarPalavras(){
        return palavraRepository.findAll();
    }
}
