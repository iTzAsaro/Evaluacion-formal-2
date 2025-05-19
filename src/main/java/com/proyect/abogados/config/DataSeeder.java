package com.proyect.abogados.config;

import com.proyect.abogados.model.Abogado;
import com.proyect.abogados.service.AbogadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;

/**
 * Clase de configuración que inicializa datos predeterminados al iniciar la aplicación.
 * Inserta automáticamente tres abogados en Firestore si la colección está vacía.
 *
 * Esta clase implementa un {@link CommandLineRunner} que se ejecuta una vez al iniciar
 * el contexto de Spring Boot.
 *
 * @author PythonLovers
 */
@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    /** Servicio que permite realizar operaciones sobre la colección de abogados. */
    private final AbogadoService abogadoService;

    /**
     * Método que se ejecuta al arrancar la aplicación. Verifica si la colección
     * de abogados está vacía y, de ser así, inserta tres abogados de ejemplo.
     *
     * @return un {@link CommandLineRunner} que ejecuta la carga de datos inicial.
     */
    @Bean
    public CommandLineRunner seedData() {
        return args -> {
            try {
                if (abogadoService.listarAbogados().isEmpty()) {
                    crearAbogadoEjemplo("Derecho Civil", "Universidad de Chile", "LIC1234", 5,
                            "11.111.111-1", "Juan", "Carlos", "Pérez", "Gómez", "DOC12345");

                    crearAbogadoEjemplo("Derecho Penal", "Universidad Católica", "LIC5678", 8,
                            "22.222.222-2", "María", "Isabel", "Rodríguez", "Soto", "DOC67890");

                    crearAbogadoEjemplo("Derecho Laboral", "Universidad de Santiago", "LIC91011", 10,
                            "33.333.333-3", "Luis", "Alberto", "González", "Fuentes", "DOC11121");

                    System.out.println("Se insertaron 3 abogados automáticamente en Firestore.");
                }
            } catch (ExecutionException | InterruptedException e) {
                System.err.println("Error al insertar abogados de prueba:");
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        };
    }

    /**
     * Crea e inserta un abogado de ejemplo en la base de datos.
     *
     * @param especialidad        especialidad del abogado
     * @param universidad         universidad de egreso
     * @param licencia            número de licencia profesional
     * @param aniosExp            años de experiencia
     * @param rut                 RUT chileno
     * @param pNombre             primer nombre
     * @param sNombre             segundo nombre
     * @param pApellido           primer apellido
     * @param sApellido           segundo apellido
     * @param documento           documento de respaldo
     * @throws ExecutionException si ocurre un error al comunicarse con Firebase
     * @throws InterruptedException si la ejecución se interrumpe
     */
    private void crearAbogadoEjemplo(String especialidad, String universidad, String licencia, int aniosExp,
                                     String rut, String pNombre, String sNombre,
                                     String pApellido, String sApellido, String documento)
            throws ExecutionException, InterruptedException {

        Abogado abogado = new Abogado();
        abogado.setEspecialidad(especialidad);
        abogado.setUniversidad(universidad);
        abogado.setLicencia(licencia);
        abogado.setAniosExperiencia(aniosExp);
        abogado.setRut(rut);
        abogado.setPNombre(pNombre);
        abogado.setSNombre(sNombre);
        abogado.setPApellido(pApellido);
        abogado.setSApellido(sApellido);
        abogado.setDocumento(documento);

        abogadoService.crearAbogado(abogado);
    }
}