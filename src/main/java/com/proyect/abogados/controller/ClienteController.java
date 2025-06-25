package com.proyect.abogados.controller;

import com.proyect.abogados.assemblers.ClienteAssemblers;
import com.proyect.abogados.model.Cliente;
import com.proyect.abogados.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

// * Controlador REST para gestionar clientes
// * Permite operaciones CRUD sobre la entidad Cliente
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    // * Servicio para operaciones de clientes
    private final ClienteService clienteService;
    // * Ensamblador para convertir Cliente a EntityModel
    private final ClienteAssemblers clienteAssemblers;

    // * Listar todos los clientes
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Cliente>>> listarClientes()
            throws ExecutionException, InterruptedException {
        List<EntityModel<Cliente>> clientes = clienteService.listarClientes()
                .stream()
                .map(clienteAssemblers::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Cliente>> collection = CollectionModel.of(clientes);
        collection.add(Link.of("/api/clientes").withSelfRel());
        collection.add(Link.of("/api/clientes").withRel("crear"));

        return ResponseEntity.ok(collection);
    }

    // * Obtener un cliente por ID
    // TODO: Manejar el caso en que el cliente no exista (retornar 404)
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Cliente>> obtenerCliente(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        Cliente cliente = clienteService.obtenerCliente(id);
        return ResponseEntity.ok(clienteAssemblers.toModel(cliente));
    }

    // * Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<EntityModel<Cliente>> crearCliente(@RequestBody Cliente cliente)
            throws ExecutionException, InterruptedException {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        EntityModel<Cliente> resource = clienteAssemblers.toModel(nuevoCliente);

        // ! Retorna 201 Created con la URI del nuevo recurso
        return ResponseEntity
                .created(Link.of("/api/clientes/" + nuevoCliente.getId()).toUri())
                .body(resource);
    }

    // * Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Cliente>> actualizarCliente(@PathVariable String id, @RequestBody Cliente cliente)
            throws ExecutionException, InterruptedException {
        Cliente clienteActualizado = clienteService.actualizarCliente(id, cliente);
        return ResponseEntity.ok(clienteAssemblers.toModel(clienteActualizado));
    }

    // * Eliminar un cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build(); // ! Retorna 204 No Content
    }

    // * Eliminar todos los clientes
    // TODO: Confirmar esta acción en el frontend antes de llamar a este endpoint
    @DeleteMapping
    public ResponseEntity<Void> eliminarTodos() throws ExecutionException, InterruptedException {
        clienteService.eliminarTodosClientes();
        return ResponseEntity.noContent().build(); // ! Retorna 204 No Content
    }
}