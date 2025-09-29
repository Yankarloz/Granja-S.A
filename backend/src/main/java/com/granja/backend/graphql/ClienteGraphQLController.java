package com.granja.backend.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.granja.backend.entity.Cliente;
import com.granja.backend.repository.ClienteRepository;
import com.granja.backend.repository.PorcinoRepository;

@Controller
public class ClienteGraphQLController {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private PorcinoRepository porcinoRepository;

    @QueryMapping
    public List<Cliente> allClientes() {
        return clienteRepository.findAll();
    }

    @QueryMapping
    public Cliente clienteById(@Argument String cedula) {
        return clienteRepository.findById(cedula).orElse(null);
    }

    @MutationMapping
    public Cliente createCliente(@Argument ClienteInput input) {
        Cliente cliente = new Cliente();
        cliente.setCedula(input.getCedula());
        cliente.setNombres(input.getNombres());
        cliente.setApellidos(input.getApellidos());
        cliente.setDireccion(input.getDireccion());
        cliente.setTelefono(input.getTelefono());
        return clienteRepository.save(cliente);
    }

    @MutationMapping
    public Cliente updateCliente(@Argument String cedula, @Argument ClienteInput input) {
        Cliente cliente = new Cliente();
        cliente.setCedula(cedula);
        cliente.setNombres(input.getNombres());
        cliente.setApellidos(input.getApellidos());
        cliente.setDireccion(input.getDireccion());
        cliente.setTelefono(input.getTelefono());
        return clienteRepository.save(cliente);
    }

    @MutationMapping
    public Boolean deleteCliente(@Argument String cedula) {
        try {
            // Desasociar porcinos antes de eliminar el cliente
            var porcinos = porcinoRepository.findByCliente_Cedula(cedula);
            for (var porcino : porcinos) {
                porcino.setCliente(null);
                porcinoRepository.save(porcino);
            }
            clienteRepository.deleteById(cedula);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Input class
    public static class ClienteInput {
        private String cedula;
        private String nombres;
        private String apellidos;
        private String direccion;
        private String telefono;

        public String getCedula() { return cedula; }
        public void setCedula(String cedula) { this.cedula = cedula; }
        public String getNombres() { return nombres; }
        public void setNombres(String nombres) { this.nombres = nombres; }
        public String getApellidos() { return apellidos; }
        public void setApellidos(String apellidos) { this.apellidos = apellidos; }
        public String getDireccion() { return direccion; }
        public void setDireccion(String direccion) { this.direccion = direccion; }
        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
    }
}