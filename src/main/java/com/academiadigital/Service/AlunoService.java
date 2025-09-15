package com.academiadigital.Service;

import com.academiadigital.Entity.Aluno;
import com.academiadigital.Entity.Form.AlunoForm;
import com.academiadigital.Entity.Form.AlunoUpdateForm;
import com.academiadigital.Infra.Utils.JavaTimeUtils;
import com.academiadigital.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<Aluno> findAll(String dataDeNascimento){
        if(dataDeNascimento == null){
            return alunoRepository.findAll();
        } else {
            LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
            return alunoRepository.findByDataDeNascimento(String.valueOf(localDate));
        }

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

    public void deleteAluno(Long id){
        alunoRepository.deleteById(id);
    }
}
