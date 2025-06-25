package com.proyect.abogados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// * Clase principal para iniciar la aplicación Spring Boot de Abogados
@SpringBootApplication
public class AbogadosApplication {

	public static void main(String[] args) {
		// * Hook para mostrar mensaje al cerrar el programa
		// TODO: Personalizar mensajes de cierre si se requiere más información
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("Cerrando programa... ¡Hasta pronto!");
		}));

		// * Inicia la aplicación Spring Boot
		SpringApplication.run(AbogadosApplication.class, args);
		System.out.println("Desplegando aplicación Abogados...");
		System.out.println(" Aplicación Abogados iniciada correctamente.");
		System.out.println("Ingresar a documentacion: http://localhost:8080/doc/swagger-ui/index.html");
	}

}
