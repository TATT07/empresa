package com.consultaria.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultaria.Medico;
import com.consultaria.Especialidad;
import com.consultoria.repository.NedicoRepository;
import com.consultoria.repository.EspecialidadRepository;

@Service
public class MedicoService {

    @Autowired
    private NedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public List<Medico> listar() {
        return medicoRepository.findAll();
    }

    public Medico get(Integer id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + id));
    }

    public Medico guardar(Medico request) {
        Especialidad especialidad = especialidadRepository.findById(request.getEspecialidad().getId())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada con ID: " + request.getEspecialidad().getId()));

        request.setEspecialidad(especialidad);
        return medicoRepository.save(request);
    }

    public Medico actualizar(Integer id, Medico request) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + id));

        Especialidad especialidad = especialidadRepository.findById(request.getEspecialidad().getId())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada con ID: " + request.getEspecialidad().getId()));

        medico.setNombre(request.getNombre());
        medico.setEspecialidad(especialidad);

        return medicoRepository.save(medico);
    }

    public void eliminar(Integer id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + id));
        medicoRepository.delete(medico);
    }
}

