package com.proyect.abogados.controller;

import com.proyect.abogados.assemblers.AbogadoAssemblers;
import com.proyect.abogados.model.Abogado;
import com.proyect.abogados.service.AbogadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Controlador REST que expone los endpoints para gestionar abogados.
 * Permite realizar operaciones CRUD sobre la entidad Abogado utilizando
 * Firestore como base de datos.
 *
 * URL base: {@code /api/abogados}
 *
 * @author PythonLovers
 */
@RestController
@RequestMapping("/api/abogados")
@RequiredArgsConstructor
public class AbogadoController {

    // * Servicio para operaciones de abogados
    private final AbogadoService abogadoService;
    // * Ensamblador para convertir Abogado a EntityModel
    private final AbogadoAssemblers abogadoAssemblers;

    // * Listar todos los abogados
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Abogado>>> listarAbogados()
            throws ExecutionException, InterruptedException {

        List<EntityModel<Abogado>> abogados = abogadoService.listarAbogados()
                .stream()
                .map(abogadoAssemblers::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Abogado>> collection = CollectionModel.of(abogados);
        collection.add(linkTo(methodOn(AbogadoController.class).listarAbogados()).withSelfRel());
        collection.add(linkTo(methodOn(AbogadoController.class).crearAbogado(null)).withRel("crear"));

        return ResponseEntity.ok(collection);
    }

    // * Obtener un abogado por ID
    // TODO: Manejar el caso en que el abogado no exista (retornar 404)
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Abogado>> obtenerAbogado(@PathVariable String id)
            throws ExecutionException, InterruptedException {

        Abogado abogado = abogadoService.obtenerAbogadoPorId(id);
        return ResponseEntity.ok(abogadoAssemblers.toModel(abogado));
    }

    // * Crear un nuevo abogado
    @PostMapping
    public ResponseEntity<EntityModel<Abogado>> crearAbogado(@RequestBody Abogado abogado)
            throws ExecutionException, InterruptedException {

        Abogado nuevoAbogado = abogadoService.crearAbogado(abogado);
        EntityModel<Abogado> resource = abogadoAssemblers.toModel(nuevoAbogado);

        // ! Retorna 201 Created con la URI del nuevo recurso
        return ResponseEntity
                .created(linkTo(methodOn(AbogadoController.class).obtenerAbogado(nuevoAbogado.getId())).toUri())
                .body(resource);
    }

    // * Actualizar un abogado existente
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Abogado>> actualizarAbogado(
            @PathVariable String id,
            @RequestBody Abogado abogado)
            throws ExecutionException, InterruptedException {

        Abogado abogadoActualizado = abogadoService.actualizarAbogado(id, abogado);
        return ResponseEntity.ok(abogadoAssemblers.toModel(abogadoActualizado));
    }

    // * Eliminar un abogado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAbogado(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        abogadoService.eliminarAbogado(id);
        return ResponseEntity.noContent().build(); // ! Retorna 204 No Content
    }

    // * Eliminar todos los abogados
    // TODO: Confirmar esta acción en el frontend antes de llamar a este endpoint
    @DeleteMapping
    public ResponseEntity<Void> eliminarTodos() throws ExecutionException, InterruptedException {
        abogadoService.eliminarTodosAbogados();
        return ResponseEntity.noContent().build(); // ! Retorna 204 No Content
    }
}