package com.consultaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consultoria.Cita;
import com.consultoria.Medico;
import com.consultoria.Paciente;
import com.consultoria.repository.CitasRepository;
import com.consultoria.repository.NedicoRepository;
import com.consultoria.repository.PacienteRepository;

@Service
public class CitasService {

    @Autowired
    private CitasRepository citasRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Cita> listar() {
        return citasRepository.findAll();
    }

    public Cita get(Integer id) {
        return citasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));
    }

    public Cita guardar(Cita request) {
        Medico medico = medicoRepository.findById(request.getMedico().getId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + request.getMedico().getId()));

        Paciente paciente = pacienteRepository.findById(request.getPaciente().getId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + request.getPaciente().getId()));

        request.setMedico(medico);
        request.setPaciente(paciente);

        return citasRepository.save(request);
    }

    public Cita actualizar(Integer id, Cita request) {
        Cita cita = citasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));

        Medico medico = medicoRepository.findById(request.getMedico().getId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con ID: " + request.getMedico().getId()));

        Paciente paciente = pacienteRepository.findById(request.getPaciente().getId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + request.getPaciente().getId()));

        cita.setFecha(request.getFecha());
        cita.setHora(request.getHora());
        cita.setMotivo(request.getMotivo());
        cita.setMedico(medico);
        cita.setPaciente(paciente);

        return citasRepository.save(cita);
    }

    public void eliminar(Integer id) {
        Cita cita = citasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));
        citasRepository.delete(cita);
    }
}

