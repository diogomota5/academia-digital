package com.academiadigital.Controller;

import com.academiadigital.Entity.Aluno;
import com.academiadigital.Entity.AvaliacaoFisica;
import com.academiadigital.Entity.Form.AlunoForm;
import com.academiadigital.Entity.Form.AlunoUpdateForm;
import com.academiadigital.Service.AlunoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

   private AlunoService alunoService;

   @Autowired
   public AlunoController(AlunoService alunoService){
       this.alunoService = alunoService;
   }

    @PostMapping
    public Aluno create(@Valid @RequestBody AlunoForm alunoForm){
       return alunoService.createAluno(alunoForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAluno(@PathVariable Long id, @RequestBody AlunoUpdateForm alunoUpdateForm){
       if(alunoService.findById(id).isPresent()){
            alunoService.updateAluno(id,alunoUpdateForm);
           return ResponseEntity.status(HttpStatus.OK).body("ID " + id + " atualizado com sucesso");
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID " + id + " não localizado" );
       }
    }

    @GetMapping
    public List<Aluno> findAll(@RequestParam(value = "dataDeNascimento", required = false) String dataDeNascimento){
       return alunoService.findAll(dataDeNascimento);
    }

    @GetMapping("/{id}")
    public Optional<Aluno> findById(@PathVariable Long id){
        if(alunoService.findById(id).isPresent()){
            return alunoService.findById(id);
        } else {
            return Optional.empty();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(alunoService.findById(id).isPresent()){
            alunoService.deleteAluno(id);
            return ResponseEntity.status(HttpStatus.OK).body("ID " + id + " excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID " + id + " não localizado" );
        }
    }

    @GetMapping("/avaliacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacaoFisica(@PathVariable Long id){
       return alunoService.getAllAvaliacaoFisica(id);
    }

}
