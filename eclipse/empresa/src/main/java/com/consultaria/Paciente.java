package com.consultaria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "paciente")
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @Email(message = "Debe ingresar un correo válido")
    @NotBlank(message = "El correo es obligatorio")
    @Column(nullable = false, unique = true)
    private String correo;

    @NotBlank(message = "La dirección es obligatoria")
    @Column(nullable = false)
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    @Column(nullable = false, length = 15)
    private String telefono;

    @OneToMany(mappedBy = "paciente")
    private List<Citas> citas;
}
