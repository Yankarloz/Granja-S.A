package com.granja.backend.controller;

import com.granja.backend.entity.Cliente;
import com.granja.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.granja.backend.repository.PorcinoRepository;

import java.util.List;

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
