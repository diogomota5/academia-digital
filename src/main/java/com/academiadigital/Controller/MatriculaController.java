package com.academiadigital.Controller;

import com.academiadigital.Entity.Form.MatriculaForm;
import com.academiadigital.Entity.Matricula;
import com.academiadigital.Service.MatriculaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private MatriculaService matriculaService;

    @Autowired
    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping
    public Matricula create(@RequestBody MatriculaForm matriculaForm){
        return matriculaService.create(matriculaForm);
    }

    @GetMapping
    public List<Matricula> findAll() {
        return matriculaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Matricula> findById(@PathVariable Long id){
        if(matriculaService.findById(id).isPresent()){
            return matriculaService.findById(id);
        } else {
            return Optional.empty();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(matriculaService.findById(id).isPresent()){
            matriculaService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("ID " + id + " excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID " + id + " não localizado" );
        }
    }

}
