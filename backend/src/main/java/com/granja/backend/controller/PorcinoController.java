package com.granja.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.granja.backend.entity.Porcino;
import com.granja.backend.repository.PorcinoRepository;

@RestController
@RequestMapping("/porcinos")
public class PorcinoController {
    @Autowired
    private PorcinoRepository porcinoRepository;

    @GetMapping
    public List<Porcino> getAll() {
        return porcinoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Porcino> getById(@PathVariable Integer id) {
        return porcinoRepository.findById(id);
    }

    @PostMapping
    public Porcino create(@RequestBody Porcino porcino) {
        return porcinoRepository.save(porcino);
    }

    @PutMapping("/{id}")
    public Porcino update(@PathVariable Integer id, @RequestBody Porcino porcino) {
        porcino.setId(id);
        return porcinoRepository.save(porcino);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        porcinoRepository.deleteById(id);
    }
}
