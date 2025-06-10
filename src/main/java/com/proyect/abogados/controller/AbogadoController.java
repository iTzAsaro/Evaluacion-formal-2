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
 * Permite realizar operaciones CRUD sobre la entidad Abogado utilizando Firestore como base de datos.
 *
 * URL base: {@code /api/abogados}
 * 
 * @author PythonLovers
 */
@RestController
@RequestMapping("/api/abogados")
@RequiredArgsConstructor
public class AbogadoController {

    private final AbogadoService abogadoService;
    private final AbogadoAssemblers abogadoAssemblers;

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

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Abogado>> obtenerAbogado(@PathVariable String id)
            throws ExecutionException, InterruptedException {

        Abogado abogado = abogadoService.obtenerAbogadoPorId(id);
        return ResponseEntity.ok(abogadoAssemblers.toModel(abogado));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Abogado>> crearAbogado(@RequestBody Abogado abogado)
            throws ExecutionException, InterruptedException {

        Abogado nuevoAbogado = abogadoService.crearAbogado(abogado);
        EntityModel<Abogado> resource = abogadoAssemblers.toModel(nuevoAbogado);

        return ResponseEntity
                .created(linkTo(methodOn(AbogadoController.class).obtenerAbogado(nuevoAbogado.getId())).toUri())
                .body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Abogado>> actualizarAbogado(
            @PathVariable String id,
            @RequestBody Abogado abogado)
            throws ExecutionException, InterruptedException {

        Abogado abogadoActualizado = abogadoService.actualizarAbogado(id, abogado);
        return ResponseEntity.ok(abogadoAssemblers.toModel(abogadoActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAbogado(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        abogadoService.eliminarAbogado(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarTodos() throws ExecutionException, InterruptedException {
        abogadoService.eliminarTodosAbogados();
        return ResponseEntity.noContent().build();
    }
}