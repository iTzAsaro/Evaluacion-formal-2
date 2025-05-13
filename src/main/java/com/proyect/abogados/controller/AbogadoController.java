package com.proyect.abogados.controller;

import com.proyect.abogados.model.Abogado;
import com.proyect.abogados.service.AbogadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/abogados")
@RequiredArgsConstructor
public class AbogadoController {

    private final AbogadoService abogadoService;

    @GetMapping
    public List<Abogado> listarAbogados() throws ExecutionException, InterruptedException {
        return abogadoService.listarAbogados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Abogado> obtenerAbogado(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(abogadoService.obtenerAbogadoPorId(id));
    }

    @PostMapping
    public ResponseEntity<Abogado> crearAbogado(@RequestBody Abogado abogado) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(abogadoService.crearAbogado(abogado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Abogado> actualizarAbogado(@PathVariable String id, @RequestBody Abogado abogado) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(abogadoService.actualizarAbogado(id, abogado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAbogado(@PathVariable String id) throws ExecutionException, InterruptedException {
        abogadoService.eliminarAbogado(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarTodos() throws ExecutionException, InterruptedException {
        abogadoService.eliminarTodosAbogados();
        return ResponseEntity.noContent().build();
    }
}