package com.hastype.api.services;


import com.hastype.api.models.UserModel;
import com.hastype.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean usuarioPorID(UUID id){

        Optional<UserModel> user = userRepository.findById(id);

        return user.isPresent();

    }
}
