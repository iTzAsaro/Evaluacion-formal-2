package com.proyect.abogados.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.proyect.abogados.model.Abogado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AbogadoService {

    private static final String COLLECTION_NAME = "abogados";

    private Firestore firestore() {
        return FirestoreClient.getFirestore();
    }

    public List<Abogado> listarAbogados() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore().collection(COLLECTION_NAME).get();
        return future.get().getDocuments().stream()
                .map(doc -> doc.toObject(Abogado.class))
                .collect(Collectors.toList());
    }

    public Abogado obtenerAbogadoPorId(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore().collection(COLLECTION_NAME).document(id);
        DocumentSnapshot snapshot = docRef.get().get();
        if (snapshot.exists()) {
            return snapshot.toObject(Abogado.class);
        } else {
            throw new RuntimeException("Abogado no encontrado con id: " + id);
        }
    }

    public Abogado crearAbogado(Abogado abogado) throws ExecutionException, InterruptedException {
        DocumentReference newDoc = firestore().collection(COLLECTION_NAME).document();
        abogado.setId(newDoc.getId());
        newDoc.set(abogado).get();
        return abogado;
    }

    public Abogado actualizarAbogado(String id, Abogado abogado) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore().collection(COLLECTION_NAME).document(id);
        abogado.setId(id);
        docRef.set(abogado).get();
        return abogado;
    }

    public void eliminarAbogado(String id) throws ExecutionException, InterruptedException {
        firestore().collection(COLLECTION_NAME).document(id).delete().get();
    }

    public void eliminarTodosAbogados() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore().collection(COLLECTION_NAME).get();
        for (DocumentSnapshot doc : future.get().getDocuments()) {
            doc.getReference().delete();
        }
    }
}