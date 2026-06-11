package com.Estudo.todo.module.user.services;

import com.Estudo.todo.exception.UserNotFaundException;

import com.Estudo.todo.module.user.dto.RequestRegisterDto;

import com.Estudo.todo.module.user.entity.User;
import com.Estudo.todo.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails getUser(String dto) {
        UserDetails response = userRepository.findByUserName(dto);

        if(response == null){
            throw new UserNotFaundException();
        }

        return response;
    }

    public User registerUser( RequestRegisterDto dto) {
        if( userRepository.findByUserName(dto.userName()) != null) {
            throw new RuntimeException("User already exists");
        }

        String ebcryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        User newUser = new User(dto.userName(), ebcryptedPassword, dto.role());

        return userRepository.save(newUser);
    }
}
