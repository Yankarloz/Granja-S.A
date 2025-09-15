package com.granja.backend.controller;

import com.granja.backend.entity.Alimentacion;
import com.granja.backend.repository.AlimentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.granja.backend.repository.PorcinoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alimentacion")
public class AlimentacionController {
    @Autowired
    private AlimentacionRepository alimentacionRepository;

    @Autowired
    private PorcinoRepository porcinoRepository;

    @GetMapping
    public List<Alimentacion> getAll() {
        return alimentacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Alimentacion> getById(@PathVariable Integer id) {
        return alimentacionRepository.findById(id);
    }

    @PostMapping
    public Alimentacion create(@RequestBody Alimentacion alimentacion) {
        return alimentacionRepository.save(alimentacion);
    }

    @PutMapping("/{id}")
    public Alimentacion update(@PathVariable Integer id, @RequestBody Alimentacion alimentacion) {
        alimentacion.setId(id);
        return alimentacionRepository.save(alimentacion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        // Desasociar el alimento de los porcinos antes de eliminarlo
        var porcinos = porcinoRepository.findByAlimentaciones_Id(id);
        for (var porcino : porcinos) {
            porcino.getAlimentaciones().removeIf(a -> a.getId().equals(id));
            porcinoRepository.save(porcino);
        }
        alimentacionRepository.deleteById(id);
    }
}
