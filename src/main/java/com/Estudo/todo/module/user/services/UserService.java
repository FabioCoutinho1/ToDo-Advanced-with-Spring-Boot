package com.Estudo.todo.module.user.services;

import com.Estudo.todo.exception.InvalidCredentialsException;
import com.Estudo.todo.exception.UserNotFaundException;

import com.Estudo.todo.module.user.dto.RequestRegisterDto;

import com.Estudo.todo.module.user.entity.User;
import com.Estudo.todo.module.user.repository.UserRepository;
import com.Estudo.todo.module.user.userRole.UserRole;
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
            throw new InvalidCredentialsException("Credenciais invalidas");
        }

        return response;
    }

    public void registerUser(RequestRegisterDto dto) {
        if( userRepository.findByUserName(dto.userName()) != null) {
            throw new InvalidCredentialsException("Usuario ja existe");
        }

        String ebcryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        User newUser = new User(dto.userName(), ebcryptedPassword, UserRole.USER);

        userRepository.save(newUser);
    }
}
