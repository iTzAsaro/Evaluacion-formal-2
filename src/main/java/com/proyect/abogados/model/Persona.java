package com.proyect.abogados.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase abstracta base para personas (usada por Abogado y Cliente)
 */
@Getter
@Setter
public abstract class Persona {

    /**
     * RUT del individuo (12-20 caracteres, obligatorio)
     * TODO: Validar formato específico de RUT si es necesario
     */
    @NotBlank
    @Size(min = 12, max = 20)
    private String rut;

    /**
     * Primer nombre (2-50 caracteres, obligatorio)
     */
    @NotBlank
    @Size(min = 2, max = 50)
    private String pNombre;

    /**
     * Segundo nombre (2-50 caracteres, opcional)
     */
    @Size(min = 2, max = 50)
    private String sNombre;

    /**
     * Primer apellido (2-50 caracteres, obligatorio)
     */
    @NotBlank
    @Size(min = 2, max = 50)
    private String pApellido;

    /**
     * Segundo apellido (2-50 caracteres, opcional)
     */
    @Size(min = 2, max = 50)
    private String sApellido;

    /**
     * Documento de identificación (2-50 caracteres, obligatorio)
     * ! Asegurarse de que el documento sea único si es necesario
     */
    @NotBlank
    @Size(min = 2, max = 50)
    private String documento;
}
