package com.granja.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.granja.backend.entity.Cliente;
import com.granja.backend.repository.ClienteRepository;
import com.granja.backend.repository.PorcinoRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PorcinoRepository porcinoRepository;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{cedula}")
    public Cliente updateCliente(@PathVariable String cedula, @RequestBody Cliente cliente) {
        cliente.setCedula(cedula);
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{cedula}")
    public void deleteCliente(@PathVariable String cedula) {
        // Desasociar porcinos antes de eliminar el cliente
        var porcinos = porcinoRepository.findByCliente_Cedula(cedula);
        for (var porcino : porcinos) {
            porcino.setCliente(null);
            porcinoRepository.save(porcino);
        }
        clienteRepository.deleteById(cedula);
    }
}
