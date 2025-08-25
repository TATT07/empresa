package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import com.empresa.entity.Funcionario;
import com.empresa.service.FuncionarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> guardar(@Valid @RequestBody Funcionario request, BindingResult bindingResult ) {
		Map<String, Object> response = new HashMap<>();
		
		if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            response.put("mensaje", "Error en la creación del funcionario.");
            response.put("error", errorMsg);
            response.put("status", HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(response);
        }
		

		try {
       	        	            
            Funcionario creado = funcionarioService.guardar(request);
             
            response.put("funcionario", creado);
            response.put("mensaje", "Funcionario creado correctamente.");
            response.put("status", HttpStatus.OK.value());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
           
            response.put("mensaje", "Error en la creación del funcionario.");
            response.put("error", e.getMessage());
            response.put("status", HttpStatus.NOT_FOUND.value());
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
	}
	
	@GetMapping
	public ResponseEntity<Map<String, Object>>  listar() {
    	Map<String, Object> response = new HashMap<>();
    		
        
        try {
        	List<Funcionario> funcionario = funcionarioService.listar();
        	
	        if (!funcionario.isEmpty()) {
	        	response.put("funcionario", funcionario );   
	        	response.put("mensage", "Lista de funcionario." );
	        }else {
	        	response.put("mensage", "No hay funcionario creados." );
	        }
	        
	        response.put("status", HttpStatus.OK.value());
	        return ResponseEntity.ok(response);
        } catch (Exception e) {
        	response.put("mensaje", "Error en la busqueda de funcionarios.");
        	response.put("error",  e.getMessage());
        	response.put("status", HttpStatus.NOT_FOUND.value());
 		   
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }    
    }
	
	@GetMapping("/{id}")
	 public ResponseEntity<Map<String, Object>>  get(@PathVariable Integer id) {
    	Map<String, Object> response = new HashMap<>();
    	    	
    	try {	
    		Funcionario funcionario = funcionarioService.get(id);
            if(funcionario != null) {
            	response.put("funcionario", funcionario);
            	
            }else {
            	response.put("mensaje", "Funcionario no encontrado."+id);
            }
            
            response.put("status", HttpStatus.OK.value());        
		    return ResponseEntity.ok(response);
        
        } catch (Exception e) {
		    response.put("mensaje", "Error en la busqueda del funcionario.");
		    response.put("error",  e.getMessage());
		    response.put("status", HttpStatus.NOT_FOUND.value());
		   
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
       }
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> actualizar(@PathVariable Integer id,@Valid  @RequestBody Funcionario request, BindingResult bindingResult) {
        Map<String, Object> response= new HashMap<>();
        
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            response.put("mensaje", "Error en la creación del funcionario.");
            response.put("error", errorMsg);
            response.put("status", HttpStatus.BAD_REQUEST.value());

            return ResponseEntity.badRequest().body(response);
        }
        
        Funcionario funcionario = funcionarioService.actualizar(id, request);
        try {
        	if(funcionario!=null) {
        		response.put("funcionario", funcionario);
        		response.put("mensaje", "Funcionario actualizado");
        	}else {
        		response.put("error", "Funcionario no encontrado. ");
        	}
        	
        	response.put("status", HttpStatus.OK.value());        
		    return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("mensaje", "Error en la busqueda del funcionario.");
		    response.put("error",  e.getMessage());
		    response.put("status", HttpStatus.NOT_FOUND.value());
		   
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
	    
    	boolean eliminado =  funcionarioService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.ok("Funcionario eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario no encontrado");
        }
       
    }
	

}
