package com.academiadigital.Service;

import com.academiadigital.Entity.Aluno;
import com.academiadigital.Entity.Form.MatriculaForm;
import com.academiadigital.Entity.Matricula;
import com.academiadigital.Repository.AlunoRepository;
import com.academiadigital.Repository.MatriculaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    private MatriculaRepository matriculaRepository;

    private AlunoRepository alunoRepository;

    @Autowired
    public MatriculaService(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository){
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
    }

    public Matricula create(MatriculaForm matriculaForm){
        Matricula matricula = new Matricula();
        Aluno aluno = alunoRepository.findById(matriculaForm.getAlunoId()).get();

        matricula.setAluno(aluno);

        return matriculaRepository.save(matricula);
    }

    public Optional<Matricula> findById(Long id){
        return matriculaRepository.findById(id);
    }

    public List<Matricula> findAll(String bairro){
        if(bairro == null){
            return matriculaRepository.findAll();
        } else {
            return matriculaRepository.findAlunosMatriculadosBairro(bairro);
        }
    }

    public void delete(Long id){
        matriculaRepository.deleteById(id);
    }
}
