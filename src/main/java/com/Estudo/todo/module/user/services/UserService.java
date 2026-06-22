package com.estudo.todo.module.user.services;

import com.estudo.todo.exception.InvalidCredentialsException;
import com.estudo.todo.module.user.dto.RequestRegisterDto;
import com.estudo.todo.module.user.entity.User;
import com.estudo.todo.module.user.repository.UserRepository;
import com.estudo.todo.module.user.userRole.UserRole;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private long getCurrentUserId() {
        User user = (User) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        if (user == null) {
            throw new RuntimeException("User not found in security context");
        }
        return user.getId();
    }

    public UserDetails getUser(String dto) {
        UserDetails response = userRepository.findByUserName(dto);

        if (response == null) {
            throw new InvalidCredentialsException("Credenciais invalidas");
        }

        return response;
    }

    public UserDetails userInfos() {
        return userRepository.findById(getCurrentUserId());
    }

    public void registerUser(RequestRegisterDto dto) {
        if (userRepository.findByUserName(dto.userName()) != null) {
            throw new InvalidCredentialsException("Usuario ja existe");
        }

        String ebcryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        User newUser = new User(dto.userName(), ebcryptedPassword, UserRole.USER);

        userRepository.save(newUser);
    }
}
