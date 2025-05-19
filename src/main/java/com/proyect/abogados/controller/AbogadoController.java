package com.proyect.abogados.controller;

import com.proyect.abogados.model.Abogado;
import com.proyect.abogados.service.AbogadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    /** Servicio que encapsula la lógica de negocio relacionada a Abogado. */
    private final AbogadoService abogadoService;

    /**
     * Obtiene una lista de todos los abogados.
     *
     * @return lista de abogados almacenados en Firestore
     * @throws ExecutionException si ocurre un error en la ejecución de la consulta
     * @throws InterruptedException si la operación es interrumpida
     */
    @GetMapping
    public List<Abogado> listarAbogados() throws ExecutionException, InterruptedException {
        return abogadoService.listarAbogados();
    }

    /**
     * Obtiene un abogado por su ID.
     *
     * @param id identificador del abogado
     * @return abogado correspondiente al ID dado
     * @throws ExecutionException si ocurre un error en la ejecución de la consulta
     * @throws InterruptedException si la operación es interrumpida
     */
    @GetMapping("/{id}")
    public ResponseEntity<Abogado> obtenerAbogado(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(abogadoService.obtenerAbogadoPorId(id));
    }

    /**
     * Crea un nuevo abogado en Firestore.
     *
     * @param abogado datos del abogado a crear
     * @return abogado creado
     * @throws ExecutionException si ocurre un error en la ejecución de la operación
     * @throws InterruptedException si la operación es interrumpida
     */
    @PostMapping
    public ResponseEntity<Abogado> crearAbogado(@RequestBody Abogado abogado)
            throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(abogadoService.crearAbogado(abogado));
    }

    /**
     * Actualiza los datos de un abogado existente.
     *
     * @param id identificador del abogado a actualizar
     * @param abogado nuevos datos del abogado
     * @return abogado actualizado
     * @throws ExecutionException si ocurre un error en la ejecución de la operación
     * @throws InterruptedException si la operación es interrumpida
     */
    @PutMapping("/{id}")
    public ResponseEntity<Abogado> actualizarAbogado(@PathVariable String id, @RequestBody Abogado abogado)
            throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(abogadoService.actualizarAbogado(id, abogado));
    }

    /**
     * Elimina un abogado por su ID.
     *
     * @param id identificador del abogado a eliminar
     * @return respuesta sin contenido si se eliminó correctamente
     * @throws ExecutionException si ocurre un error en la ejecución de la operación
     * @throws InterruptedException si la operación es interrumpida
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAbogado(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        abogadoService.eliminarAbogado(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Elimina todos los abogados almacenados en Firestore.
     *
     * @return respuesta sin contenido si se eliminaron correctamente
     * @throws ExecutionException si ocurre un error en la ejecución de la operación
     * @throws InterruptedException si la operación es interrumpida
     */
    @DeleteMapping
    public ResponseEntity<Void> eliminarTodos() throws ExecutionException, InterruptedException {
        abogadoService.eliminarTodosAbogados();
        return ResponseEntity.noContent().build();
    }
}