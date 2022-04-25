package com.example.demo.Controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.security.Provider.Service;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.plaf.InsetsUIResource;

import com.example.demo.models.Joke;
import com.example.demo.models.Person;
import com.example.demo.models.Persons;
import com.example.demo.services.JokeService;
import com.example.demo.services.RickAndMortyService;
import com.example.demo.utils.utils;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.pattern.Util;

//le indica a springboot que esta clase es el controlador de nuestra aplicacion 
@RestController
public class ejercicio {

    @Autowired
    RickAndMortyService RickAndMortyService;

    @Autowired
    JokeService jokeService;

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

    // htps://localhost.7000/sumar?num1=5&num2=2
    @GetMapping("/sumar")
    public String add(@RequestParam String num1, @RequestParam String num2) {
        int resultado = Integer.parseInt(num1) + Integer.parseInt(num2);
        Object param[] = { num1, num2, resultado };
        return MessageFormat.format("la suma de {0} y {1} es {2}", param);
    }

    // mapas o diccionario
    @PostMapping("/saveProductOnDisk")
    public String saveProductOnDisk(@RequestParam Map<String, String> body) {

        String articleValue = body.get("article");
        String priceValue = body.get("price");

        if (articleValue.equals("") || priceValue.equals("")) {
            return " eror datos incorrectos";
        }
        if (Integer.parseInt(priceValue) < 0) {
            return "error preecio es negativo";
        }

        // guarda en el disco duro esa informacion
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            utils.save("datos.txt", articleValue + "," + articleValue + "/n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "erroe el guardar en disco";
        }
        // devulve un msg al cliente " product guardado disco "

        return "product guardado correct ";

    }

    @DeleteMapping("/removeFile")
    public String removeFile() {
        boolean result = utils.remove("datos.txt");
        return result ? "borrado corecto" : "borrado incorrecto";

    }

    @Autowired
    RickAndMortyService rickAndMortyService;

    @GetMapping("/rickandmorty/random")
    public String getRickAndMorty() {
        Person c = rickAndMortyService.getCharacterFromAPI();
        return "<img src='" + c.image + "'/>";

    }

    @GetMapping("/rickandmorty/list")
    public String getRickAndMortyList() {
        String web = "<h1>Lista de personas</h1>";
        ArrayList<Person> persons = rickAndMortyService.getCharactersFromAPI();
        for (Person person : persons) {
            web += "<img src='" + person.image + "'/>";

        }
        return web;
    }

    @GetMapping("/chiste")
    public String addJoke(@RequestParam String text) {
        // Insert into joke(text) values ('xx');

        return "";

    }

    // listar chistes
    @GetMapping("/listarchistes")
    public String jokeList() {
        ArrayList<Joke> jokes = jokeService.getAllJokes();
        String listado = "";
        for (Joke joke : jokes) {
            listado += joke.getText();

            listado += "<br/>";
        }
        return listado;
    }

    @PostMapping("/insertarchiste")
    public String addJoke(@RequestParam Map<String, String> body) {
        String jokeText = body.get("text");
        jokeText.replaceAll("<", "");
        jokeText.replaceAll(">", "");
        Joke joke = new Joke();
        joke.setText(jokeText);
        jokeService.saveJoke(joke);
        return "Chiste creado correctamente";
    }

}
