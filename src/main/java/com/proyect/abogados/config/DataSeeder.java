package com.proyect.abogados.config;

import com.proyect.abogados.model.Abogado;
import com.proyect.abogados.service.AbogadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    private final AbogadoService abogadoService;

    @Bean
    public CommandLineRunner seedData() {
        return args -> {
            if (abogadoService.listarAbogados().isEmpty()) {

                Abogado abogado1 = new Abogado();
                abogado1.setEspecialidad("Derecho Civil");
                abogado1.setUniversidad("Universidad de Chile");
                abogado1.setLicencia("LIC1234");
                abogado1.setAniosExperiencia(5);
                abogado1.setRut("11.111.111-1");
                abogado1.setPNombre("Juan");
                abogado1.setSNombre("Carlos");
                abogado1.setPApellido("Pérez");
                abogado1.setSApellido("Gómez");
                abogado1.setDocumento("DOC12345");

                Abogado abogado2 = new Abogado();
                abogado2.setEspecialidad("Derecho Penal");
                abogado2.setUniversidad("Universidad Católica");
                abogado2.setLicencia("LIC5678");
                abogado2.setAniosExperiencia(8);
                abogado2.setRut("22.222.222-2");
                abogado2.setPNombre("María");
                abogado2.setSNombre("Isabel");
                abogado2.setPApellido("Rodríguez");
                abogado2.setSApellido("Soto");
                abogado2.setDocumento("DOC67890");

                Abogado abogado3 = new Abogado();
                abogado3.setEspecialidad("Derecho Laboral");
                abogado3.setUniversidad("Universidad de Santiago");
                abogado3.setLicencia("LIC91011");
                abogado3.setAniosExperiencia(10);
                abogado3.setRut("33.333.333-3");
                abogado3.setPNombre("Luis");
                abogado3.setSNombre("Alberto");
                abogado3.setPApellido("González");
                abogado3.setSApellido("Fuentes");
                abogado3.setDocumento("DOC11121");

                try {
                    abogadoService.crearAbogado(abogado1);
                    abogadoService.crearAbogado(abogado2);
                    abogadoService.crearAbogado(abogado3);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Se insertaron 3 abogados automáticamente en Firestore.");
            }
        };
    }
}