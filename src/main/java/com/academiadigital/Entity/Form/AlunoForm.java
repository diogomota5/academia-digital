package com.academiadigital.Entity.Form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoForm {

    @NotBlank(message = "Preencha o campo corretamente.")
    @Size(min = 3, max = 50, message = "'${validateValue}' precisa estar entre {min} e {max} caracteres.")
    private String nome;

    @NotBlank(message = "Preencha o campo corretamente.")
    private String cpf;

    @NotBlank(message = "Preencha o campo corretamente.")
    @Size(min = 3, max = 50, message = "'${validateValue}' precisa estar entre {min} e {max} caracteres.")
    private String bairro;

    @NotNull(message = "Preencha o campo corretamente.")
    @Past(message = "Data '${validateValue} è inválida.'")
    private LocalDate dataNascimento;
}
