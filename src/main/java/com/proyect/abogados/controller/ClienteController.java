package com.proyect.abogados.controller;

import com.proyect.abogados.model.Cliente;
import com.proyect.abogados.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Controlador REST que gestiona las operaciones CRUD para clientes.
 * Expone los endpoints bajo la ruta base {@code /api/clientes}.
 * Utiliza Firestore como base de datos para persistencia.
 * 
 * @author PythonLovers
 */
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    /** Servicio que encapsula la lógica de negocio para clientes. */
    private final ClienteService clienteService;

    /**
     * Lista todos los clientes almacenados en la base de datos.
     *
     * @return lista de clientes
     * @throws ExecutionException si ocurre un error en la ejecución de la consulta
     * @throws InterruptedException si la operación es interrumpida
     */
    @GetMapping
    public List<Cliente> listarClientes() throws ExecutionException, InterruptedException {
        return clienteService.listarClientes();
    }

    /**
     * Obtiene un cliente específico por su ID.
     *
     * @param id identificador único del cliente
     * @return cliente encontrado
     * @throws ExecutionException si ocurre un error en la ejecución de la consulta
     * @throws InterruptedException si la operación es interrumpida
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(clienteService.obtenerCliente(id));
    }

    /**
     * Crea un nuevo cliente en la base de datos.
     *
     * @param cliente objeto cliente a guardar
     * @return cliente creado
     * @throws ExecutionException si ocurre un error en la ejecución
     * @throws InterruptedException si la operación es interrumpida
     */
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente)
            throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(clienteService.crearCliente(cliente));
    }

    /**
     * Actualiza un cliente existente según su ID.
     *
     * @param id identificador del cliente a actualizar
     * @param cliente nuevos datos del cliente
     * @return cliente actualizado
     * @throws ExecutionException si ocurre un error en la ejecución
     * @throws InterruptedException si la operación es interrumpida
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable String id, @RequestBody Cliente cliente)
            throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, cliente));
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param id identificador del cliente a eliminar
     * @return respuesta sin contenido
     * @throws ExecutionException si ocurre un error en la ejecución
     * @throws InterruptedException si la operación es interrumpida
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Elimina todos los clientes almacenados en Firestore.
     *
     * @return respuesta sin contenido
     * @throws ExecutionException si ocurre un error en la ejecución
     * @throws InterruptedException si la operación es interrumpida
     */
    @DeleteMapping
    public ResponseEntity<Void> eliminarTodos() throws ExecutionException, InterruptedException {
        clienteService.eliminarTodosClientes();
        return ResponseEntity.noContent().build();
    }
}
