package com.proyect.abogados.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Persona {

    @NotBlank
    @Size(min = 12, max = 20)
    private String rut;

    @NotBlank
    @Size(min = 2, max = 50)
    private String pNombre;

    @Size(min = 2, max = 50)
    private String sNombre;

    @NotBlank 
    @Size(min = 2, max = 50)
    private String pApellido;

    @Size(min = 2, max = 50)
    private String sApellido;

    @NotBlank 
    @Size(min = 2, max = 50)
    private String documento;
}

