package com.academiadigital.Entity.Form;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoFisicaForm {

    @Positive(message = "O Id do aluno precisa ser positivo.")
    private Long alunoId;

    @NotNull(message = "Preencha o campo corretamente.")
    @Positive(message = "'${validateValue}' precisa ser positivo")
    private double peso;

    @NotNull(message = "Preencha o campo corretamente.")
    @Positive(message = "'${validateValue}' precisa ser positivo")
    @DecimalMin(value = "150", message = "'${validateValue}' precisa ser no mínimo {value}.")
    private double altura;
}
