package com.proyect.abogados.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Cliente extends Persona {

    @NotBlank
    private String id;

    @NotBlank
    @Size(min = 2, max = 200)
    private String direccion;

    @NotBlank
    private String telefono;

    @NotBlank
    private String email;
}
