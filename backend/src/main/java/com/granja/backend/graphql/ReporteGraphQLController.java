package com.granja.backend.graphql;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.granja.backend.dto.ClientePorcinoDTO;
import com.granja.backend.repository.PorcinoRepository;

@Controller
public class ReporteGraphQLController {

    @Autowired
    private PorcinoRepository porcinoRepository;

    @QueryMapping
    public List<ClientePorcinoDTO> reporteClientesPorcinos() {
        return porcinoRepository.findAll().stream()
            .filter(p -> p.getCliente() != null)
            .map(p -> new ClientePorcinoDTO(
                p.getCliente().getCedula(),
                p.getCliente().getNombres(),
                p.getCliente().getApellidos(),
                p.getIdentificacion(),
                p.getRaza(),
                p.getEdad(),
                p.getPeso()
            ))
            .collect(Collectors.toList());
    }
}