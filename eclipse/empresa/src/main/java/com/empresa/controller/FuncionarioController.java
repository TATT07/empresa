package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.empresa.entity.funcionario;
import com.empresa.service.FuncionarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;


    @PostMapping
    public ResponseEntity<Map<String, Object>> guardar(
            @Valid @RequestBody funcionario request,
            BindingResult bindingResult) {
        
        Map<String, Object> response = new HashMap<>();
        
        if (bindingResult.hasErrors()) {
            String error = bindingResult.getFieldError().getDefaultMessage();
            response.put("mensaje", "Error en la creación del funcionario");
            response.put("error", error);
            response.put("status", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
        
        try {
            funcionario creado = funcionarioService.guardar(request);
            response.put("funcionario", creado);
            response.put("mensaje", "Funcionario creado correctamente");
            response.put("status", HttpStatus.OK.value());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("mensaje", "Error en la creación del funcionario");
            response.put("error", e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping
    public ResponseEntity<List<funcionario>> listar() {
        return ResponseEntity.ok(funcionarioService.listar());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Integer id) {
        funcionario funcionario = funcionarioService.get(id);
        if (funcionario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Funcionario no encontrado"));
        }
        return ResponseEntity.ok(funcionario);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody funcionario request) {
        try {
            funcionario actualizado = funcionarioService.actualizar(id, request);
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Funcionario actualizado correctamente",
                    "funcionario", actualizado
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Error al actualizar funcionario", "error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            funcionarioService.eliminar(id);
            return ResponseEntity.ok(Map.of("mensaje", "Funcionario eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Error al eliminar funcionario", "error", e.getMessage()));
        }
    }
}
