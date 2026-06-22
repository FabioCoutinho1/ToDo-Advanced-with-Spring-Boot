package com.estudo.todo.module.user.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.estudo.todo.module.auth.service.TokenService;
import com.estudo.todo.module.user.dto.RequestLoginDto;
import com.estudo.todo.module.user.dto.RequestRegisterDto;
import com.estudo.todo.module.user.dto.ResponseLoginDto;
import com.estudo.todo.module.user.entity.User;
import com.estudo.todo.module.user.services.UserService;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @GetMapping("/admin/{name}")
    public ResponseEntity<UserDetails> getUserByName(@PathVariable("name") String dto) {
        UserDetails user = userService.getUser(dto);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> useLogin(@Valid @RequestBody RequestLoginDto dto) {
        try {
            var userNamePassword =
                    new UsernamePasswordAuthenticationToken(
                            dto.userName(),
                            dto.password());

            var auth = authenticationManager.authenticate(userNamePassword);

            String token = tokenService.generateToken(
                    (User) auth.getPrincipal());

            return ResponseEntity.ok(new ResponseLoginDto(token));

        } catch (Exception e) {
            System.out.println(e.getClass());
            throw e;
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> useRegister(@Valid @RequestBody RequestRegisterDto dto) {
        userService.registerUser(dto);

        var userNamePassword =
                new UsernamePasswordAuthenticationToken(
                        dto.userName(),
                        dto.password());

        var auth = authenticationManager.authenticate(userNamePassword);

        String token = tokenService.generateToken(
                (User) auth.getPrincipal());

        return ResponseEntity.ok().body(Map.of("token", token));
    }
}
