package com.example.apiimobiliaria.controllers;

import com.example.apiimobiliaria.models.UserModel;
import com.example.apiimobiliaria.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value="/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<UserModel> listarUsers() {
        return userRepository.findAll();
    }

    //@ExceptionHandler
    @PostMapping("/criar-user")
    public ResponseEntity<UserModel> criarUser(@RequestBody UserModel user) {
        UserModel novoUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUser);
    }

}