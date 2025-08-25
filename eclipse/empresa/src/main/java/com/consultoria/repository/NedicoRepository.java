package com.consultoria.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.consultaria.Medico;

public interface NedicoRepository extends JpaRepository<Medico, Integer> {
}
