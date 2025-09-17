package com.academiadigital.Controller;

import com.academiadigital.Entity.AvaliacaoFisica;
import com.academiadigital.Entity.Form.AvaliacaoFisicaForm;
import com.academiadigital.Entity.Form.AvaliacaoFisicaUpdateForm;
import com.academiadigital.Service.AvaliacaoFisicaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    private AvaliacaoFisicaService avaliacaoFisicaService;

    @Autowired
    public AvaliacaoFisicaController(AvaliacaoFisicaService avaliacaoFisicaService) {
        this.avaliacaoFisicaService = avaliacaoFisicaService;
    }

    @PostMapping
    public AvaliacaoFisica create(@RequestBody AvaliacaoFisicaForm avaliacaoFisicaForm) {
        return avaliacaoFisicaService.create(avaliacaoFisicaForm);
    }

    @PutMapping("/{alunoId}")
    public ResponseEntity<Object> update(@PathVariable Long alunoId, @RequestBody AvaliacaoFisicaUpdateForm avaliacaoFisicaUpdateForm) {
        if (avaliacaoFisicaService.findById(alunoId).isPresent()) {
            avaliacaoFisicaService.update(alunoId, avaliacaoFisicaUpdateForm);
            return ResponseEntity.status(HttpStatus.OK).body("ID " + alunoId + " atualizado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID " + alunoId + " não localizado");
        }
    }

    @GetMapping
    public List<AvaliacaoFisica> findAll() {
        return avaliacaoFisicaService.findAll();
    }

    @GetMapping("/{alunoId}")
    public Optional<AvaliacaoFisica> findById(@PathVariable Long alunoId) {
        if (AvaliacaoFisicaService.findById(alunoId).isPresent()) {
            return avaliacaoFisicaService.findById(alunoId);
        } else {
            return Optional.empty();
        }
    }

    @DeleteMapping("/{alunoId}")
    public ResponseEntity<String> delete(@PathVariable Long alunoId) {
        if (avaliacaoFisicaService.findById(alunoId).isPresent()) {
            avaliacaoFisicaService.delete(alunoId);
            return ResponseEntity.status(HttpStatus.OK).body("ID " + alunoId + " excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID " + alunoId + " não localizado");
        }
    }
}

