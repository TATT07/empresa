package com.consultaria;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "citas")
@Data
public class Citas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false, length = 20)
    private String estado;

    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día actual")
    @Column(nullable = false)
    private LocalDate fecha;

    @NotNull(message = "La fecha de asignación es obligatoria")
    @FutureOrPresent(message = "La fecha de asignación no puede ser anterior al día actual")
    @Column(nullable = false, name = "fecha_asignacion")
    private LocalDate fechaAsignacion;

    @NotNull(message = "La hora es obligatoria")
    @Column(nullable = false)
    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "medico_id", referencedColumnName = "id", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id", nullable = false)
    private Paciente paciente;
}
