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

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteAssemblers clienteAssemblers;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Cliente>>> listarClientes() throws ExecutionException, InterruptedException {
        List<EntityModel<Cliente>> clientes = clienteService.listarClientes()
                .stream()
                .map(clienteAssemblers::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Cliente>> collection = CollectionModel.of(clientes);
        collection.add(Link.of("/api/clientes").withSelfRel());
        collection.add(Link.of("/api/clientes").withRel("crear"));

        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Cliente>> obtenerCliente(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        Cliente cliente = clienteService.obtenerCliente(id);
        return ResponseEntity.ok(clienteAssemblers.toModel(cliente));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Cliente>> crearCliente(@RequestBody Cliente cliente)
            throws ExecutionException, InterruptedException {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        EntityModel<Cliente> resource = clienteAssemblers.toModel(nuevoCliente);

        return ResponseEntity
                .created(Link.of("/api/clientes/" + nuevoCliente.getId()).toUri())
                .body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Cliente>> actualizarCliente(@PathVariable String id, @RequestBody Cliente cliente)
            throws ExecutionException, InterruptedException {
        Cliente clienteActualizado = clienteService.actualizarCliente(id, cliente);
        return ResponseEntity.ok(clienteAssemblers.toModel(clienteActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarTodos() throws ExecutionException, InterruptedException {
        clienteService.eliminarTodosClientes();
        return ResponseEntity.noContent().build();
    }
}