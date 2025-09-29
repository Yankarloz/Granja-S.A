package com.granja.backend.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.granja.backend.entity.Alimentacion;
import com.granja.backend.repository.AlimentacionRepository;
import com.granja.backend.repository.PorcinoRepository;

@Controller
public class AlimentacionGraphQLController {

    @Autowired
    private AlimentacionRepository alimentacionRepository;
    
    @Autowired
    private PorcinoRepository porcinoRepository;

    @QueryMapping
    public List<Alimentacion> allAlimentacion() {
        return alimentacionRepository.findAll();
    }

    @QueryMapping
    public Alimentacion alimentacionById(@Argument Integer id) {
        return alimentacionRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public Alimentacion createAlimentacion(@Argument AlimentacionInput input) {
        Alimentacion alimentacion = new Alimentacion();
        alimentacion.setDescripcion(input.getDescripcion());
        alimentacion.setDosis(input.getDosis());
        return alimentacionRepository.save(alimentacion);
    }

    @MutationMapping
    public Alimentacion updateAlimentacion(@Argument Integer id, @Argument AlimentacionInput input) {
        Alimentacion alimentacion = new Alimentacion();
        alimentacion.setId(id);
        alimentacion.setDescripcion(input.getDescripcion());
        alimentacion.setDosis(input.getDosis());
        return alimentacionRepository.save(alimentacion);
    }

    @MutationMapping
    public Boolean deleteAlimentacion(@Argument Integer id) {
        try {
            // Desasociar de porcinos antes de eliminar
            var porcinos = porcinoRepository.findByAlimentaciones_Id(id);
            for (var porcino : porcinos) {
                porcino.getAlimentaciones().removeIf(a -> a.getId().equals(id));
                porcinoRepository.save(porcino);
            }
            alimentacionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Input class
    public static class AlimentacionInput {
        private String descripcion;
        private String dosis;

        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        public String getDosis() { return dosis; }
        public void setDosis(String dosis) { this.dosis = dosis; }
    }
}