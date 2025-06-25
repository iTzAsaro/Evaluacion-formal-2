package com.proyect.abogados.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.proyect.abogados.controller.AbogadoController;
import com.proyect.abogados.model.Abogado;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

// * Ensamblador HATEOAS para la entidad Abogado
// * Convierte Abogado en EntityModel con enlaces REST
@Component
public class AbogadoAssemblers implements RepresentationModelAssembler<Abogado, EntityModel<Abogado>> {

    @Override
    public EntityModel<Abogado> toModel(Abogado abogado) {
        try {
            // * Construye el modelo con enlaces HATEOAS
            return EntityModel.of(
                    abogado,
                    linkTo(methodOn(AbogadoController.class).obtenerAbogado(abogado.getId())).withSelfRel(),
                    linkTo(methodOn(AbogadoController.class).listarAbogados()).withRel("abogados"),
                    linkTo(methodOn(AbogadoController.class).crearAbogado(null)).withRel("crear"));
        } catch (Exception e) {
            // ! Error al construir los enlaces HATEOAS
            throw new RuntimeException("Error al construir los enlaces HATEOAS", e);
        }
    }
}