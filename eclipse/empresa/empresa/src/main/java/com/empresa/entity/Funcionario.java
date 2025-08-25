package com.empresa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "funcionario")
@Data
public class Funcionario {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	private String cedula;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Size(max=10, min= 2, message = "El nombre debe tener entre 2 y 10 caractéres")
	private String nombre;
	
	@Email(message = "El formato de email no es correcto")
	@NotBlank(message = "El email es obligatorio")
	private String email;

}
