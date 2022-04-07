package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.demo.models.Person;
import com.example.demo.sevices.RickAndMortyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @Autowired
    RickAndMortyService rickAndMortyService;

    @RequestMapping("/rickandMorty/ListView")
    public String getRickAndMortuListView(Model model) {
        ArrayList<Person> persons = rickAndMortyService.getCharactersFromAPI();
        model.addAttribute("persons", persons);
        return "rickandmorty";

    }

}