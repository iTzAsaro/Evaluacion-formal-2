package com.proyect.abogados.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Abogado extends Persona {

    @NotBlank
    private String id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String especialidad;

    @NotBlank 
    @Size(min = 2, max = 100)
    private String universidad;

    @NotBlank 
    @Size(min = 2, max = 50)
    private String licencia;

    @NotBlank 
    private int aniosExperiencia;
}

