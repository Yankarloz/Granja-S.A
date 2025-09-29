package com.granja.backend.graphql;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.granja.backend.entity.Porcino;
import com.granja.backend.entity.Cliente;
import com.granja.backend.entity.Alimentacion;
import com.granja.backend.repository.PorcinoRepository;
import com.granja.backend.repository.ClienteRepository;
import com.granja.backend.repository.AlimentacionRepository;

@Controller
public class PorcinoGraphQLController {

    @Autowired
    private PorcinoRepository porcinoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private AlimentacionRepository alimentacionRepository;

    @QueryMapping
    public List<Porcino> allPorcinos() {
        return porcinoRepository.findAll();
    }

    @QueryMapping
    public Porcino porcinoById(@Argument Integer id) {
        return porcinoRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Porcino> porcinosByCliente(@Argument String cedula) {
        return porcinoRepository.findByCliente_Cedula(cedula);
    }

    @MutationMapping
    public Porcino createPorcino(@Argument PorcinoInput input) {
        Porcino porcino = new Porcino();
        porcino.setIdentificacion(input.getIdentificacion());
        porcino.setRaza(input.getRaza());
        porcino.setEdad(input.getEdad());
        porcino.setPeso(input.getPeso());
        
        if (input.getClienteCedula() != null) {
            Cliente cliente = clienteRepository.findById(input.getClienteCedula()).orElse(null);
            porcino.setCliente(cliente);
        }
        
        Set<Alimentacion> alimentaciones = new HashSet<>();
        if (input.getAlimentacionIds() != null) {
            for (Integer alimentacionId : input.getAlimentacionIds()) {
                alimentacionRepository.findById(alimentacionId).ifPresent(alimentaciones::add);
            }
        }
        porcino.setAlimentaciones(alimentaciones);
        
        return porcinoRepository.save(porcino);
    }

    @MutationMapping
    public Porcino updatePorcino(@Argument Integer id, @Argument PorcinoInput input) {
        Porcino porcino = porcinoRepository.findById(id).orElse(new Porcino());
        porcino.setId(id);
        porcino.setIdentificacion(input.getIdentificacion());
        porcino.setRaza(input.getRaza());
        porcino.setEdad(input.getEdad());
        porcino.setPeso(input.getPeso());
        
        if (input.getClienteCedula() != null) {
            Cliente cliente = clienteRepository.findById(input.getClienteCedula()).orElse(null);
            porcino.setCliente(cliente);
        }
        
        Set<Alimentacion> alimentaciones = new HashSet<>();
        if (input.getAlimentacionIds() != null) {
            for (Integer alimentacionId : input.getAlimentacionIds()) {
                alimentacionRepository.findById(alimentacionId).ifPresent(alimentaciones::add);
            }
        }
        porcino.setAlimentaciones(alimentaciones);
        
        return porcinoRepository.save(porcino);
    }

    @MutationMapping
    public Boolean deletePorcino(@Argument Integer id) {
        try {
            porcinoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Input class
    public static class PorcinoInput {
        private String identificacion;
        private String raza;
        private Integer edad;
        private BigDecimal peso;
        private String clienteCedula;
        private List<Integer> alimentacionIds;

        public String getIdentificacion() { return identificacion; }
        public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }
        public String getRaza() { return raza; }
        public void setRaza(String raza) { this.raza = raza; }
        public Integer getEdad() { return edad; }
        public void setEdad(Integer edad) { this.edad = edad; }
        public BigDecimal getPeso() { return peso; }
        public void setPeso(BigDecimal peso) { this.peso = peso; }
        public String getClienteCedula() { return clienteCedula; }
        public void setClienteCedula(String clienteCedula) { this.clienteCedula = clienteCedula; }
        public List<Integer> getAlimentacionIds() { return alimentacionIds; }
        public void setAlimentacionIds(List<Integer> alimentacionIds) { this.alimentacionIds = alimentacionIds; }
    }
}