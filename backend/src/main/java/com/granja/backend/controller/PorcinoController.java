package com.granja.backend.controller;

import com.granja.backend.entity.Porcino;
import com.granja.backend.repository.PorcinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
