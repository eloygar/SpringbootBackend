package com.example.demo.Controllers;

import com.example.demo.utils.utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//le indica a springboot que esta clase es el controlador de nuestra aplicacion 
@RestController
public class ejercicio {
    // cuando alguien entre en https://localhost :8080/ se ejecutara esto
    @GetMapping("/")
    public String greet() {
        return "bienvenido al servidor backend";
    }

    // cuando alguien entre en https://localhost :7000/aleatorio se ejecutara esto
    @GetMapping("/aleatorio")
    public String randomNumber() {
        long r = Math.round(Math.random() * 100);
        return r + "";
    }

    // cuando alguien entre en https://localhost :7000/palindromo/*** se ejecutara
    // esto
    @GetMapping("/palindromo/{name}")
    public String palindrome(@PathVariable String name) {
        boolean palindrome = utils.isPalindrome(name);
        return palindrome ? "si es palindromo" : "No es un palindromo ";
    }

}
