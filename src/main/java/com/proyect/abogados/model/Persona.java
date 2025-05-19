package com.proyect.abogados.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase abstracta que representa una persona genérica.
 * Esta clase es utilizada como superclase para entidades como {@link Abogado} y {@link Cliente}.
 * Contiene atributos comunes como RUT, nombres, apellidos y documento.
 * 
 * Se aplican validaciones para asegurar la integridad de los datos.
 * 
 * @author PythonLovers
 */
@Getter
@Setter
public abstract class Persona {

    /**
     * Rol Único Tributario (RUT) del individuo, validado con tamaño mínimo y máximo.
     */
    @NotBlank
    @Size(min = 12, max = 20)
    private String rut;

    /**
     * Primer nombre de la persona.
     */
    @NotBlank
    @Size(min = 2, max = 50)
    private String pNombre;

    /**
     * Segundo nombre de la persona (opcional).
     */
    @Size(min = 2, max = 50)
    private String sNombre;

    /**
     * Primer apellido de la persona.
     */
    @NotBlank
    @Size(min = 2, max = 50)
    private String pApellido;

    /**
     * Segundo apellido de la persona (opcional).
     */
    @Size(min = 2, max = 50)
    private String sApellido;

    /**
     * Documento de identificación o referencia administrativa.
     */
    @NotBlank
    @Size(min = 2, max = 50)
    private String documento;
}
