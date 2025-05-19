package com.proyect.abogados.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Modelo de dominio que representa a un cliente.
 * Hereda los datos personales desde la clase {@link Persona}, e incorpora
 * datos específicos como dirección, teléfono y correo electrónico.
 * 
 * Incluye validaciones mediante Jakarta Bean Validation.
 * 
 * @author PythonLovers
 */
@Getter
@Setter
public class Cliente extends Persona {

    /**
     * Identificador único del cliente en Firestore.
     */
    @NotBlank
    private String id;

    /**
     * Dirección de residencia del cliente.
     * Debe contener entre 2 y 200 caracteres.
     */
    @NotBlank
    @Size(min = 2, max = 200)
    private String direccion;

    /**
     * Número de teléfono del cliente.
     * Campo obligatorio.
     */
    @NotBlank
    private String telefono;

    /**
     * Correo electrónico del cliente.
     * Campo obligatorio.
     */
    @NotBlank
    private String email;
}