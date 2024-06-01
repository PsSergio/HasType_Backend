package com.hastype.api.repository;

import com.hastype.api.models.PalavraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalavraRepository extends JpaRepository<PalavraModel, Integer> {

}
