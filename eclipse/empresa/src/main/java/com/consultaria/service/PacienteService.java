package com.consultaria.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultaria.Paciente;
import com.consultoria.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    public Paciente get(Integer id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
    }

    public Paciente guardar(Paciente request) {
        return pacienteRepository.save(request);
    }

    public Paciente actualizar(Integer id, Paciente request) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        paciente.setNombre(request.getNombre());
        paciente.setCedula(request.getCedula());
        paciente.setTelefono(request.getTelefono());
        paciente.setEmail(request.getEmail());

        return pacienteRepository.save(paciente);
    }

    public void eliminar(Integer id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));
        pacienteRepository.delete(paciente);
    }
}


