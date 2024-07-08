package com.Foro.Challenge.service;

import com.Foro.Challenge.dto.TopicoRequest;
import com.Foro.Challenge.dto.TopicoResponse;
import com.Foro.Challenge.model.Topico;
import com.Foro.Challenge.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public void registrarTopico(TopicoRequest topicoRequest) {
        if (topicoRepository.existsByTituloAndMensaje(topicoRequest.getTitulo(), topicoRequest.getMensaje())) {
            throw new IllegalArgumentException("Tópico duplicado");
        }

        Topico topico = new Topico();
        topico.setTitulo(topicoRequest.getTitulo());
        topico.setMensaje(topicoRequest.getMensaje());
        topico.setAutor(topicoRequest.getAutor());
        topico.setCurso(topicoRequest.getCurso());

        topicoRepository.save(topico);
    }

    public List<TopicoResponse> listarTopicos() {
        return topicoRepository.findAll().stream().map(this::convertirATopicoResponse).collect(Collectors.toList());
    }

    public TopicoResponse obtenerTopicoPorId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El tópico con ID " + id + " no existe."));
        return convertirATopicoResponse(topico);
    }

    public void actualizarTopico(Long id, TopicoRequest topicoRequest) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El tópico con ID " + id + " no existe."));

        if (topicoRepository.existsByTituloAndMensaje(topicoRequest.getTitulo(), topicoRequest.getMensaje())) {
            throw new IllegalArgumentException("El tópico con el mismo título y mensaje ya existe.");
        }

        topico.setTitulo(topicoRequest.getTitulo());
        topico.setMensaje(topicoRequest.getMensaje());
        topico.setAutor(topicoRequest.getAutor());
        topico.setCurso(topicoRequest.getCurso());

        topicoRepository.save(topico);
    }

    public void eliminarTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El tópico con ID " + id + " no existe."));
        topicoRepository.deleteById(id);
    }


    private TopicoResponse convertirATopicoResponse(Topico topico) {
        TopicoResponse response = new TopicoResponse();
        response.setTitulo(topico.getTitulo());
        response.setMensaje(topico.getMensaje());
        response.setFechaCreacion(topico.getFechaCreacion());
        response.setEstado(topico.getEstado());
        response.setAutor(topico.getAutor());
        response.setCurso(topico.getCurso());
        return response;
    }
}
