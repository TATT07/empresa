package com.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.repository.FuncionarioRepository;
import com.empresa.entity.funcionario;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	private funcionario guardar(funcionario request) {
		
		return funcionarioRepository.save(request);
		
	}
	public List<funcionario> listar(){
		return funcionarioRepository.save(request);
	}
}
