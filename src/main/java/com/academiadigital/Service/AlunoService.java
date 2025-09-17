package com.academiadigital.Service;

import com.academiadigital.Entity.Aluno;
import com.academiadigital.Entity.AvaliacaoFisica;
import com.academiadigital.Entity.Form.AlunoForm;
import com.academiadigital.Entity.Form.AlunoUpdateForm;
import com.academiadigital.Repository.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno createAluno(AlunoForm alunoForm) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoForm.getNome());
        aluno.setCpf(alunoForm.getCpf());
        aluno.setBairro(alunoForm.getBairro());
        aluno.setDataDeNascimento(alunoForm.getDataNascimento());

        return alunoRepository.save(aluno);
    }

    public Optional<Aluno> findById(Long id){
        return alunoRepository.findById(id);
    }

    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    public Aluno updateAluno(Long id, AlunoUpdateForm updateForm){
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if(aluno.isPresent()){
            Aluno alunoUpdate = aluno.get();
            alunoUpdate.setNome(updateForm.getNome());
            alunoUpdate.setBairro(updateForm.getBairro());
            alunoUpdate.setDataDeNascimento(updateForm.getDataDeNascimento());
            return alunoRepository.save(alunoUpdate);
        } else {
            throw new RuntimeException("Objeto com o ID " + id + " não foi encontrado");
        }

    }

    public void deleteAluno(Long id) {
        if (alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
        }
    }

    @GetMapping
    public List<AvaliacaoFisica> getAllAvaliacaoFisica(Long id){
        Aluno aluno = alunoRepository.findById(id).get();
        return aluno.getAvaliacoes();
    }

}
