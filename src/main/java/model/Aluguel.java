package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluguel {

    private Veiculo veiculo;
    private model.Pessoa pessoa;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

}