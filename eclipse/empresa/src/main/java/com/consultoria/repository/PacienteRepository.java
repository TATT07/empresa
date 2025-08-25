package com.consultoria.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.consultaria.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}

