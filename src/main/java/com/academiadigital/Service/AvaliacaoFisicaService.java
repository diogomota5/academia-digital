package com.academiadigital.Service;

import com.academiadigital.Entity.Aluno;
import com.academiadigital.Entity.AvaliacaoFisica;
import com.academiadigital.Entity.Form.AvaliacaoFisicaForm;
import com.academiadigital.Entity.Form.AvaliacaoFisicaUpdateForm;
import com.academiadigital.Repository.AlunoRepository;
import com.academiadigital.Repository.AvaliacaoFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoFisicaService {

    private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    private AlunoRepository alunoRepository;

    @Autowired
    public AvaliacaoFisicaService(AvaliacaoFisicaRepository avaliacaoFisicaRepository, AlunoRepository alunoRepository) {
        this.avaliacaoFisicaRepository = avaliacaoFisicaRepository;
        this.alunoRepository = alunoRepository;
    }

    public AvaliacaoFisica create(AvaliacaoFisicaForm avaliacaoFisicaForm){
        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
        Aluno aluno = alunoRepository.findById(avaliacaoFisicaForm.getAlunoId()).get();

        avaliacaoFisica.setAluno(aluno);
        avaliacaoFisica.setPeso(avaliacaoFisicaForm.getPeso());
        avaliacaoFisica.setAltura(avaliacaoFisicaForm.getAltura());

        return avaliacaoFisicaRepository.save(avaliacaoFisica);
    }

    public Optional<AvaliacaoFisica> findById(Long id){
        return avaliacaoFisicaRepository.findById(id);
    }

    public List<AvaliacaoFisica> findAll(){
        return avaliacaoFisicaRepository.findAll();
    }

    public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm avaliacaoFisicaUpdateForm){
        Optional<AvaliacaoFisica> avaliacaoFisica = avaliacaoFisicaRepository.findById(id);
        if(avaliacaoFisica.isPresent()){
            AvaliacaoFisica avaliacaoFisicaUpdate = avaliacaoFisica.get();
            avaliacaoFisicaUpdate.setPeso(avaliacaoFisicaUpdateForm.getPeso());
            avaliacaoFisicaUpdate.setAltura(avaliacaoFisicaUpdateForm.getAltura());

            return avaliacaoFisicaRepository.save(avaliacaoFisicaUpdate);
        } else {
            throw new RuntimeException("Objeto com o ID " + id + " não foi encontrado");
        }
    }

    public void delete(Long id){
        avaliacaoFisicaRepository.deleteById(id);
    }

}
