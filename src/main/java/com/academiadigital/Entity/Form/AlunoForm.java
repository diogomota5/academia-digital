package com.academiadigital.Entity.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoForm {

    private String nome;

    private String cpf;

    private String bairro;

    private LocalDate dataNascimento;
}
