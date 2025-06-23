package com.proyect.abogados.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.proyect.abogados.model.Abogado;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Servicio encargado de gestionar las operaciones de negocio relacionadas con abogados,
 * interactuando directamente con la base de datos Firestore.
 *
 * Permite realizar operaciones CRUD sobre la colección {@code abogados}.
 */
@Service
public class AbogadoService {

    /** Nombre de la colección en Firestore donde se almacenan los abogados. */
    private static final String COLLECTION_NAME = "abogados";

    /**
     * Obtiene una instancia de Firestore desde Firebase.
     *
     * @return instancia de {@link Firestore}
     */
    protected Firestore firestore() {
        return FirestoreClient.getFirestore();
    }

    /**
     * Lista todos los abogados almacenados en Firestore.
     *
     * @return lista de objetos {@link Abogado}
     * @throws ExecutionException si ocurre un error durante la operación
     * @throws InterruptedException si la ejecución es interrumpida
     */
    public List<Abogado> listarAbogados() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore().collection(COLLECTION_NAME).get();
        return future.get().getDocuments().stream()
                .map(doc -> doc.toObject(Abogado.class))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un abogado por su identificador único.
     *
     * @param id ID del abogado
     * @return objeto {@link Abogado} correspondiente al ID
     * @throws ExecutionException si ocurre un error durante la operación
     * @throws InterruptedException si la ejecución es interrumpida
     */
    public Abogado obtenerAbogadoPorId(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore().collection(COLLECTION_NAME).document(id);
        DocumentSnapshot snapshot = docRef.get().get();
        if (snapshot.exists()) {
            return snapshot.toObject(Abogado.class);
        } else {
            throw new RuntimeException("Abogado no encontrado con id: " + id);
        }
    }

    /**
     * Crea un nuevo abogado en Firestore.
     *
     * @param abogado objeto {@link Abogado} a almacenar
     * @return abogado con el ID asignado por Firestore
     * @throws ExecutionException si ocurre un error durante la operación
     * @throws InterruptedException si la ejecución es interrumpida
     */
    public Abogado crearAbogado(Abogado abogado) throws ExecutionException, InterruptedException {
        DocumentReference newDoc = firestore().collection(COLLECTION_NAME).document();
        abogado.setId(newDoc.getId());
        newDoc.set(abogado).get();
        return abogado;
    }

    /**
     * Actualiza un abogado existente en Firestore.
     *
     * @param id ID del abogado a actualizar
     * @param abogado objeto {@link Abogado} con los nuevos datos
     * @return abogado actualizado
     * @throws ExecutionException si ocurre un error durante la operación
     * @throws InterruptedException si la ejecución es interrumpida
     */
    public Abogado actualizarAbogado(String id, Abogado abogado) throws ExecutionException, InterruptedException {
        abogado.setId(id);
        firestore().collection(COLLECTION_NAME).document(id).set(abogado).get();
        return abogado;
    }

    /**
     * Elimina un abogado de la colección por su ID.
     *
     * @param id ID del abogado a eliminar
     * @throws ExecutionException si ocurre un error durante la operación
     * @throws InterruptedException si la ejecución es interrumpida
     */
    public void eliminarAbogado(String id) throws ExecutionException, InterruptedException {
        firestore().collection(COLLECTION_NAME).document(id).delete().get();
    }

    /**
     * Elimina todos los documentos (abogados) de la colección.
     *
     * @throws ExecutionException si ocurre un error durante la operación
     * @throws InterruptedException si la ejecución es interrumpida
     */
    public void eliminarTodosAbogados() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore().collection(COLLECTION_NAME).get();
        for (DocumentSnapshot doc : future.get().getDocuments()) {
            doc.getReference().delete();
        }
    }
}
