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

@Service
@RequiredArgsConstructor
public class ClienteService {

    private static final String COLLECTION_NAME = "clientes";

    private Firestore firestore() {
        return FirestoreClient.getFirestore();
    }

    public List<Cliente> listarClientes() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore().collection(COLLECTION_NAME).get();
        return future.get().getDocuments().stream()
                .map(doc -> doc.toObject(Cliente.class))
                .collect(Collectors.toList());
    }

    public Cliente obtenerCliente(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore().collection(COLLECTION_NAME).document(id);
        DocumentSnapshot snapshot = docRef.get().get();
        if (snapshot.exists()) {
            return snapshot.toObject(Cliente.class);
        } else {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }
    }

    public Cliente crearCliente(Cliente cliente) throws ExecutionException, InterruptedException {
        DocumentReference newDoc = firestore().collection(COLLECTION_NAME).document();
        cliente.setId(newDoc.getId());
        newDoc.set(cliente).get();
        return cliente;
    }

    public Cliente actualizarCliente(String id, Cliente cliente) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore().collection(COLLECTION_NAME).document(id);
        cliente.setId(id);
        docRef.set(cliente).get();
        return cliente;
    }

    public void eliminarCliente(String id) throws ExecutionException, InterruptedException {
        firestore().collection(COLLECTION_NAME).document(id).delete().get();
    }

    public void eliminarTodosClientes() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore().collection(COLLECTION_NAME).get();
        for (DocumentSnapshot doc : future.get().getDocuments()) {
            doc.getReference().delete();
        }
    }
}