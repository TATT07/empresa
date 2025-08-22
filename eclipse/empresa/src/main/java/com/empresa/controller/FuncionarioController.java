package com.empresa.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.empresa.entity.funcionario;
import com.empresa.service.FuncionarioService;
@RestController
@RequestMapping("api/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@PostMapping
	public funcionario guardar(@RequestBody funcionario request) {
	    return funcionarioService.guardar(request); 
	}

	@GetMapping
	public List<funcionario> listar() {
		return funcionarioService.listar();
	}
	@GetMapping("/{id}")
	public funcionario listarPorId(@PathVariable Integer id) {
		return funcionarioService.get(id);
	}
	@PutMapping("/{id}")
	public funcionario actualizar(@PathVariable Integer id, @RequestBody funcionario paciente) {
		return funcionarioService.update(id, paciente);
	}
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		funcionarioService.eliminar(id);
	}
}


