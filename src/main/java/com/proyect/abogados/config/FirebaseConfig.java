package com.proyect.abogados.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.InputStream;

/**
 * Clase de configuración encargada de inicializar la conexión con Firebase.
 * Utiliza credenciales definidas en el archivo JSON ubicado en el classpath.
 * Esta clase se ejecuta automáticamente al iniciar el contexto de Spring.
 *
 * El archivo de credenciales debe estar en:
 * src/main/resources/firebase/justiciaplv1-firebase-adminsdk-fbsvc-8375ab5102.json
 *
 * @author PythonLovers
 */
@Configuration
public class FirebaseConfig {

    /**
     * Método de inicialización que configura FirebaseApp si aún no está
     * inicializada.
     * Se ejecuta automáticamente después de construir el bean gracias a
     * {@code @PostConstruct}.
     */
    // * Inicializa la conexión con Firebase al arrancar el contexto de Spring
    // TODO: Permitir parametrizar la ruta del archivo de credenciales si se
    // requiere en otros entornos
    @PostConstruct
    public void initialize() {
        try {
            // * Cargar archivo de credenciales desde resources
            InputStream serviceAccount = getClass()
                    .getClassLoader()
                    .getResourceAsStream("firebase/justiciaplv1-firebase-adminsdk-fbsvc-8375ab5102.json");

            // ! Validar que el archivo de credenciales exista
            if (serviceAccount == null) {
                throw new IllegalStateException("No se pudo encontrar el archivo de credenciales de Firebase.");
            }

            // * Configurar opciones de Firebase con las credenciales
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            // * Inicializar FirebaseApp solo si no está inicializado
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("FirebaseApp inicializado correctamente.");
            }
        } catch (Exception e) {
            // ! Error al inicializar Firebase
            System.err.println("❌ Error al inicializar Firebase:");
            e.printStackTrace();
        }
    }
}