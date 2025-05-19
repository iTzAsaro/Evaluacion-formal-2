package com.proyect.abogados.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Modelo de dominio que representa a un abogado.
 * Hereda los atributos personales desde la clase {@link Persona} y añade
 * información profesional como especialidad, universidad, licencia y experiencia.
 * 
 * Incluye validaciones con Jakarta Bean Validation.
 * 
 * @author PythonLovers
 */
@Getter
@Setter
public class Abogado extends Persona {

    /**
     * Identificador único del abogado en Firestore.
     */
    @NotBlank
    private String id;

    /**
     * Especialidad del abogado (Ej: Derecho Penal, Derecho Civil, etc.).
     * Debe contener entre 2 y 100 caracteres.
     */
    @NotBlank
    @Size(min = 2, max = 100)
    private String especialidad;

    /**
     * Nombre de la universidad donde se graduó el abogado.
     * Debe contener entre 2 y 100 caracteres.
     */
    @NotBlank
    @Size(min = 2, max = 100)
    private String universidad;

    /**
     * Número o código de licencia profesional del abogado.
     * Debe contener entre 2 y 50 caracteres.
     */
    @NotBlank
    @Size(min = 2, max = 50)
    private String licencia;

    /**
     * Años de experiencia laboral del abogado en el ejercicio de su profesión.
     */
    @NotBlank
    private int aniosExperiencia;
}