package com.proyect.abogados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AbogadosApplication {

	public static void main(String[] args) {
		// Hook para mostrar mensaje al cerrar el programa
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("Cerrando programa... ¡Hasta pronto!");
		}));

		SpringApplication.run(AbogadosApplication.class, args);
		System.out.println("Desplegando aplicación Abogados...");
		System.out.println(" Aplicación Abogados iniciada correctamente.");
		System.out.println("Ingresar a documentacion: http://localhost:8080/doc/swagger-ui/index.html");
	}

}
