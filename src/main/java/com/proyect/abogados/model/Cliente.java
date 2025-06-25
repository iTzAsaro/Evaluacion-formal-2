package com.proyect.abogados.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author PythonLovers
 */
@Getter
@Setter
public class Cliente extends Persona {

    // * ID único del cliente en Firestore (obligatorio)
    // ! Este campo se usa como clave primaria
    @NotBlank
    private String id;

    // * Dirección de residencia (2-200 caracteres, obligatorio)
    @NotBlank
    @Size(min = 2, max = 200)
    private String direccion;

    // * Teléfono del cliente (obligatorio)

    // TODO: Validar formato de número telefónico
    @NotBlank
    private String telefono;

    // * Correo electrónico del cliente (obligatorio)
    // TODO: Validar formato de email
    @NotBlank
    private String email;
}