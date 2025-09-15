package com.granja.backend.controller;

import com.granja.backend.entity.Prueba;
import com.granja.backend.repository.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prueba")
public class PruebaController {
    @Autowired
    private PruebaRepository pruebaRepository;

    @GetMapping
    public List<Prueba> getAll() {
        return pruebaRepository.findAll();
    }

    @PostMapping
    public Prueba create(@RequestBody Prueba prueba) {
        return pruebaRepository.save(prueba);
    }
}
