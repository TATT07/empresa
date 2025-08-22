package com.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.repository.FuncionarioRepository;
import com.empresa.entity.funcionario;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public funcionario guardar(funcionario request) {
		return funcionarioRepository.save(request);
	}
	
	public List<funcionario> listar(){
		return funcionarioRepository.findAll();
	}
	
	public funcionario get(Integer id) {
		return funcionarioRepository.findById(id).orElse(null);
	}
	
	public funcionario actualizar(Integer id,funcionario request) {
		Optional<funcionario> f = funcionarioRepository.findById(id);
		
		if (f.isPresent() ) {
			funcionario nuevo=f.get();
			nuevo.setCedula(request.getCedula());
			nuevo.setNombre(request.getNombre());
				nuevo.setDireccion(request.getDireccion());
				nuevo.setTelefono(request.getTelefono());
				nuevo.setEmail(request.getEmail());
				
				return funcionarioRepository.save(nuevo);
		}else {
			return null;
		}
	}
	
	public void eliminar(Integer id) {
		funcionarioRepository.deleteById(id);
	}

	public funcionario update(Integer id, funcionario paciente) {
		// TODO Auto-generated method stub
		return null;
	}
}