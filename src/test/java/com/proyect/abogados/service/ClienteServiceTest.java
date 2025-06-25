package com.proyect.abogados.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.proyect.abogados.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// * Pruebas unitarias para el servicio de clientes utilizando Firestore mockeado
class ClienteServiceTest {

    // * Instancia del servicio a testear
    private ClienteService clienteService;
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
        clienteService = new ClienteService() {
            @Override
            protected Firestore firestore() {
                return firestoreMock;
            }
        };
    }

    // * Test: Crear un cliente y verificar los datos asignados
    @Test
    void testCrearCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setDireccion("Dirección 1");
        cliente.setTelefono("123456789");
        cliente.setEmail("cliente@correo.com");

        when(firestoreMock.collection("clientes")).thenReturn(collectionMock);
        when(collectionMock.document()).thenReturn(documentMock);
        when(documentMock.getId()).thenReturn("cliente-mock-id-001");

        @SuppressWarnings("unchecked")
        ApiFuture<WriteResult> writeResultFuture = mock(ApiFuture.class);
        when(documentMock.set(cliente)).thenReturn(writeResultFuture);

        Cliente creado = clienteService.crearCliente(cliente);

        assertNotNull(creado.getId());
        assertEquals("cliente@correo.com", creado.getEmail());
        assertEquals("cliente-mock-id-001", creado.getId());
    }

    // * Test: Obtener un cliente por ID
    @Test
    void testObtenerClientePorId() throws Exception {
        String id = "abc123";
        Cliente clienteEsperado = new Cliente();
        clienteEsperado.setId(id);
        clienteEsperado.setEmail("ejemplo@correo.com");

        when(firestoreMock.collection("clientes")).thenReturn(collectionMock);
        when(collectionMock.document(id)).thenReturn(documentMock);

        @SuppressWarnings("unchecked")
        ApiFuture<DocumentSnapshot> futureMock = mock(ApiFuture.class);
        when(documentMock.get()).thenReturn(futureMock);
        when(futureMock.get()).thenReturn(snapshotMock);
        when(snapshotMock.exists()).thenReturn(true);
        when(snapshotMock.toObject(Cliente.class)).thenReturn(clienteEsperado);

        Cliente clienteObtenido = clienteService.obtenerCliente(id);

        assertEquals(clienteEsperado.getId(), clienteObtenido.getId());
        assertEquals(clienteEsperado.getEmail(), clienteObtenido.getEmail());
    }

    // * Test: Listar clientes cuando la colección está vacía
    @Test
    void testListarClientesVacio() throws ExecutionException, InterruptedException {
        when(firestoreMock.collection("clientes")).thenReturn(collectionMock);

        @SuppressWarnings("unchecked")
        ApiFuture<QuerySnapshot> futureMock = mock(ApiFuture.class);
        QuerySnapshot querySnapshot = mock(QuerySnapshot.class);
        when(collectionMock.get()).thenReturn(futureMock);
        when(futureMock.get()).thenReturn(querySnapshot);
        when(querySnapshot.getDocuments()).thenReturn(Collections.emptyList());

        List<Cliente> clientes = clienteService.listarClientes();

        assertNotNull(clientes);
        assertTrue(clientes.isEmpty());
    }

    // * Test: Eliminar todos los clientes de la colección
    // TODO: Probar también el caso de colección vacía
    @Test
    void testEliminarTodosClientes() throws Exception {
        when(firestoreMock.collection("clientes")).thenReturn(collectionMock);

        @SuppressWarnings("unchecked")
        ApiFuture<QuerySnapshot> futureMock = mock(ApiFuture.class);
        QuerySnapshot querySnapshot = mock(QuerySnapshot.class);
        QueryDocumentSnapshot doc1 = mock(QueryDocumentSnapshot.class);

        when(collectionMock.get()).thenReturn(futureMock);
        when(futureMock.get()).thenReturn(querySnapshot);
        when(querySnapshot.getDocuments()).thenReturn(List.of(doc1));
        when(doc1.getReference()).thenReturn(documentMock);

        @SuppressWarnings("unchecked")
        ApiFuture<WriteResult> deleteFuture = mock(ApiFuture.class);
        when(documentMock.delete()).thenReturn(deleteFuture);

        assertDoesNotThrow(() -> clienteService.eliminarTodosClientes());
    }
}
