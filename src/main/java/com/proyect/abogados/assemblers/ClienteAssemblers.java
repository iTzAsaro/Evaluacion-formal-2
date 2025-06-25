package com.proyect.abogados.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.proyect.abogados.controller.ClienteController;
import com.proyect.abogados.model.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

// * Ensamblador HATEOAS para la entidad Cliente
// * Convierte Cliente en EntityModel con enlaces REST
@Component
public class ClienteAssemblers implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

    @Override
    public EntityModel<Cliente> toModel(Cliente cliente) {
        try {
            // * Construye el modelo con enlaces HATEOAS
            return EntityModel.of(
                    cliente,
                    linkTo(methodOn(ClienteController.class).obtenerCliente(cliente.getId())).withSelfRel(),
                    linkTo(methodOn(ClienteController.class).listarClientes()).withRel("clientes"),
                    linkTo(methodOn(ClienteController.class).crearCliente(null)).withRel("crear"));
        } catch (Exception e) {
            // ! Error al construir los enlaces HATEOAS
            throw new RuntimeException("Error al construir los enlaces HATEOAS", e);
        }
    }
}