package com.proyect.abogados.controller;

import com.proyect.abogados.model.Cliente;
import com.proyect.abogados.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes() throws ExecutionException, InterruptedException {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(clienteService.obtenerCliente(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(clienteService.crearCliente(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable String id, @RequestBody Cliente cliente) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String id) throws ExecutionException, InterruptedException {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarTodos() throws ExecutionException, InterruptedException {
        clienteService.eliminarTodosClientes();
        return ResponseEntity.noContent().build();
    }
}
