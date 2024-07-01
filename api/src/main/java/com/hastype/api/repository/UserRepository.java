package com.hastype.api.repository;

import com.hastype.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findByEmailAndSenha(String email, String senha);

    Optional<UserModel> findByEmail(String email);

}
