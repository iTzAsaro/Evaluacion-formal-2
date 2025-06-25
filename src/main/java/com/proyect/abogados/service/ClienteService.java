package com.proyect.abogados.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.proyect.abogados.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Servicio encargado de gestionar las operaciones de negocio relacionadas con
 * clientes.
 * Realiza operaciones CRUD sobre la colección {@code clientes} en Firebase
 * Firestore.
 * 
 * Utiliza la API de Firebase Admin SDK para acceder a la base de datos.
 * 
 * @author PythonLovers
 */
@Service
@RequiredArgsConstructor
public class ClienteService {

    /** Nombre de la colección en Firestore donde se almacenan los clientes. */
    private static final String COLLECTION_NAME = "clientes";

    /**
     * Obtiene una instancia de Firestore desde Firebase.
     *
     * @return instancia de {@link Firestore}
     */
    protected Firestore firestore() {
        return FirestoreClient.getFirestore();
    }

    /**
     * Lista todos los clientes almacenados en Firestore.
     *
     * @return lista de objetos {@link Cliente}
     * @throws ExecutionException   si ocurre un error durante la operación
     * @throws InterruptedException si la ejecución es interrumpida
     */
    public List<Cliente> listarClientes() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore().collection(COLLECTION_NAME).get();
        return future.get().getDocuments().stream()
                .map(doc -> doc.toObject(Cliente.class))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un cliente por su identificador único.
     *
     * @param id ID del cliente
     * @return objeto {@link Cliente} correspondiente al ID
     * @throws ExecutionException   si ocurre un error durante la operación
     * @throws InterruptedException si la ejecución es interrumpida
     */
    public Cliente obtenerCliente(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore().collection(COLLECTION_NAME).document(id);
        DocumentSnapshot snapshot = docRef.get().get();
        if (snapshot.exists()) {
            return snapshot.toObject(Cliente.class);
        } else {
            // ! Lanza excepción si el cliente no existe
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }
    }

    /**
     * Crea un nuevo cliente en Firestore.
     *
     * @param cliente objeto {@link Cliente} a almacenar
     * @return cliente con el ID asignado por Firestore
     * @throws ExecutionException   si ocurre un error durante la operación
     * @throws InterruptedException si la ejecución es interrumpida
     */
    public Cliente crearCliente(Cliente cliente) throws ExecutionException, InterruptedException {
        DocumentReference newDoc = firestore().collection(COLLECTION_NAME).document();
        cliente.setId(newDoc.getId());
        newDoc.set(cliente).get();
        return cliente;
    }

    /**
     * Actualiza un cliente existente en Firestore.
     *
     * @param id      ID del cliente a actualizar
     * @param cliente objeto {@link Cliente} con los nuevos datos
     * @return cliente actualizado
     * @throws ExecutionException   si ocurre un error durante la operación
     * @throws InterruptedException si la ejecución es interrumpida
     */
    public Cliente actualizarCliente(String id, Cliente cliente) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore().collection(COLLECTION_NAME).document(id);
        cliente.setId(id);
        docRef.set(cliente).get();
        return cliente;
    }

    /**
     * Elimina un cliente de la colección por su ID.
     *
     * @param id ID del cliente a eliminar
     * @throws ExecutionException   si ocurre un error durante la operación
     * @throws InterruptedException si la ejecución es interrumpida
     */
    public void eliminarCliente(String id) throws ExecutionException, InterruptedException {
        firestore().collection(COLLECTION_NAME).document(id).delete().get();
    }

    /**
     * Elimina todos los documentos (clientes) de la colección.
     *
     * @throws ExecutionException   si ocurre un error durante la operación
     * @throws InterruptedException si la ejecución es interrumpida
     */
    public void eliminarTodosClientes() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore().collection(COLLECTION_NAME).get();
        for (DocumentSnapshot doc : future.get().getDocuments()) {
            doc.getReference().delete();
        }
    }
}
