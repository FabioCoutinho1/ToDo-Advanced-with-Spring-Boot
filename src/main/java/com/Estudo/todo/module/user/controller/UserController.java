package com.Estudo.todo.module.user.controller;

import com.Estudo.todo.module.auth.service.TokenService;
import com.Estudo.todo.module.user.dto.RequestLoginDto;
import com.Estudo.todo.module.user.dto.RequestRegisterDto;

import com.Estudo.todo.module.user.dto.ResponseLoginDto;
import com.Estudo.todo.module.user.entity.User;
import com.Estudo.todo.module.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @GetMapping("/{name}")
    public ResponseEntity<UserDetails> getUserByName ( @PathVariable("name") String dto){
      UserDetails user = userService.getUser(dto);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> useLogin (@Valid @RequestBody RequestLoginDto dto){
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.userName(), dto.password());

        var auth = this.authenticationManager.authenticate(userNamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new ResponseLoginDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> useRegister(@Valid @RequestBody RequestRegisterDto dto){
        userService.registerUser(dto);
        return ResponseEntity.ok().body("User created");
    }
}
