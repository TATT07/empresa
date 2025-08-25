package com.consultaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultaria.Especialidad;
import com.consultoria.repository.EspecialidadRepository;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public List<Especialidad> listar() {
        return especialidadRepository.findAll();
    }

    public Especialidad get(Integer id) {
        return especialidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada con ID: " + id));
    }

    public Especialidad guardar(Especialidad request) {
        return especialidadRepository.save(request);
    }

    public Especialidad actualizar(Integer id, Especialidad request) {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada con ID: " + id));

        especialidad.setNombre(request.getNombre());

        return especialidadRepository.save(especialidad);
    }

    public void eliminar(Integer id) {
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada con ID: " + id));
        especialidadRepository.delete(especialidad);
    }
}

