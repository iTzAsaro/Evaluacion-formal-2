package com.proyect.abogados.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.proyect.abogados.model.Abogado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// * Pruebas unitarias para el servicio de abogados utilizando Firestore mockeado
class AbogadoServiceTest {

    // * Instancia del servicio a testear
    private AbogadoService abogadoService;
    // * Mocks de Firestore y sus componentes
    private Firestore firestoreMock;
    private CollectionReference collectionMock;
    private DocumentReference documentMock;
    private DocumentSnapshot snapshotMock;

    // * Configuración inicial antes de cada test
    @BeforeEach
    void setUp() {
        firestoreMock = mock(Firestore.class);
        collectionMock = mock(CollectionReference.class);
        documentMock = mock(DocumentReference.class);
        snapshotMock = mock(DocumentSnapshot.class);

        // * Servicio con Firestore mockeado
        abogadoService = new AbogadoService() {
            @Override
            protected Firestore firestore() {
                return firestoreMock;
            }
        };
    }

    // * Test: Crear un abogado y verificar los datos asignados
    @Test
    void testCrearAbogado() throws Exception {
        Abogado abogado = new Abogado();
        abogado.setEspecialidad("Derecho Penal");
        abogado.setUniversidad("UChile");
        abogado.setLicencia("LIC123");
        abogado.setAniosExperiencia(10);

        when(firestoreMock.collection("abogados")).thenReturn(collectionMock);
        when(collectionMock.document()).thenReturn(documentMock);
        when(documentMock.getId()).thenReturn("mock-id-123");

        @SuppressWarnings("unchecked")
        ApiFuture<WriteResult> writeResultFuture = mock(ApiFuture.class);
        when(documentMock.set(abogado)).thenReturn(writeResultFuture);

        Abogado creado = abogadoService.crearAbogado(abogado);

        assertNotNull(creado.getId());
        assertEquals("Derecho Penal", creado.getEspecialidad());
        assertEquals("mock-id-123", creado.getId());
    }

    // * Test: Obtener un abogado por ID
    @Test
    void testObtenerAbogadoPorId() throws Exception {
        String id = "abg001";
        Abogado abogadoEsperado = new Abogado();
        abogadoEsperado.setId(id);
        abogadoEsperado.setEspecialidad("Derecho Civil");

        when(firestoreMock.collection("abogados")).thenReturn(collectionMock);
        when(collectionMock.document(id)).thenReturn(documentMock);

        @SuppressWarnings("unchecked")
        ApiFuture<DocumentSnapshot> futureMock = mock(ApiFuture.class);
        when(documentMock.get()).thenReturn(futureMock);
        when(futureMock.get()).thenReturn(snapshotMock);
        when(snapshotMock.exists()).thenReturn(true);
        when(snapshotMock.toObject(Abogado.class)).thenReturn(abogadoEsperado);

        Abogado abogadoObtenido = abogadoService.obtenerAbogadoPorId(id);

        assertEquals("Derecho Civil", abogadoObtenido.getEspecialidad());
    }

    // * Test: Listar abogados cuando la colección está vacía
    @Test
    void testListarAbogadosVacio() throws ExecutionException, InterruptedException {
        when(firestoreMock.collection("abogados")).thenReturn(collectionMock);

        @SuppressWarnings("unchecked")
        ApiFuture<QuerySnapshot> futureMock = mock(ApiFuture.class);
        QuerySnapshot querySnapshot = mock(QuerySnapshot.class);
        when(collectionMock.get()).thenReturn(futureMock);
        when(futureMock.get()).thenReturn(querySnapshot);
        when(querySnapshot.getDocuments()).thenReturn(Collections.emptyList());

        List<Abogado> abogados = abogadoService.listarAbogados();

        assertNotNull(abogados);
        assertTrue(abogados.isEmpty());
    }

    // * Test: Actualizar un abogado y verificar los cambios
    @Test
    void testActualizarAbogado() throws Exception {
        String id = "abg123";
        Abogado abogado = new Abogado();
        abogado.setEspecialidad("Derecho Laboral");

        when(firestoreMock.collection("abogados")).thenReturn(collectionMock);
        when(collectionMock.document(id)).thenReturn(documentMock);

        @SuppressWarnings("unchecked")
        ApiFuture<WriteResult> futureMock = mock(ApiFuture.class);
        when(documentMock.set(abogado)).thenReturn(futureMock);

        Abogado actualizado = abogadoService.actualizarAbogado(id, abogado);

        assertEquals(id, actualizado.getId());
        assertEquals("Derecho Laboral", actualizado.getEspecialidad());
    }

    // * Test: Eliminar un abogado por ID
    @Test
    void testEliminarAbogado() throws Exception {
        String id = "abg789";

        when(firestoreMock.collection("abogados")).thenReturn(collectionMock);
        when(collectionMock.document(id)).thenReturn(documentMock);

        @SuppressWarnings("unchecked")
        ApiFuture<WriteResult> futureMock = mock(ApiFuture.class);
        when(documentMock.delete()).thenReturn(futureMock);

        assertDoesNotThrow(() -> abogadoService.eliminarAbogado(id));
    }

    // * Test: Eliminar todos los abogados de la colección
    // TODO: Probar también el caso de colección vacía
    @Test
    void testEliminarTodosAbogados() throws Exception {
        when(firestoreMock.collection("abogados")).thenReturn(collectionMock);

        QueryDocumentSnapshot doc1 = mock(QueryDocumentSnapshot.class);
        QueryDocumentSnapshot doc2 = mock(QueryDocumentSnapshot.class);
        when(doc1.getReference()).thenReturn(documentMock);
        when(doc2.getReference()).thenReturn(documentMock);

        List<QueryDocumentSnapshot> docs = Arrays.asList(doc1, doc2);

        QuerySnapshot querySnapshot = mock(QuerySnapshot.class);
        when(querySnapshot.getDocuments()).thenReturn(docs);

        @SuppressWarnings("unchecked")
        ApiFuture<QuerySnapshot> futureMock = mock(ApiFuture.class);
        when(collectionMock.get()).thenReturn(futureMock);
        when(futureMock.get()).thenReturn(querySnapshot);

        @SuppressWarnings("unchecked")
        ApiFuture<WriteResult> deleteFuture = mock(ApiFuture.class);
        when(documentMock.delete()).thenReturn(deleteFuture);

        assertDoesNotThrow(() -> abogadoService.eliminarTodosAbogados());
    }
}
