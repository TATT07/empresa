package com.consultoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.consultaria.Citas;

public interface CitasRepository extends JpaRepository<Citas, Integer> {
}
