package com.Foro.Challenge.Controller;

import com.Foro.Challenge.dto.TopicoRequest;
import com.Foro.Challenge.dto.TopicoResponse;
import com.Foro.Challenge.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/topico")
public class RegistrarController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<Void> registrarTopico(@RequestBody @Valid TopicoRequest topicoRequest) {
        topicoService.registrarTopico(topicoRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TopicoResponse>> listarTopicos() {
        List<TopicoResponse> topicos = topicoService.listarTopicos();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponse> obtenerTopicoPorId(@PathVariable Long id) {
        TopicoResponse topico = topicoService.obtenerTopicoPorId(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoRequest topicoRequest) {
        topicoService.actualizarTopico(id, topicoRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.ok().build();
    }
}
