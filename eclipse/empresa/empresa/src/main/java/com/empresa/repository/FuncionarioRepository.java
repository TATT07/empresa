package com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.empresa.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}
