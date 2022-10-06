package service;

import model.Aluguel;
import model.Veiculo;
import model.Pessoa;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AluguelService {

    private static List<Aluguel> alugueis = new ArrayList<>();
    private PessoaService pessoaService = new PessoaService();
    private VeiculoService veiculoService = new VeiculoService();

    public Aluguel cadastrarAluguel(String cpf, String placa) {
        Pessoa pessoa = pessoaService.buscaPessoa(cpf);
        if (pessoa == null) {
            return null;
        }
        Veiculo veiculo = veiculoService.buscarVeiculos(placa);
        if (veiculo == null) {
            return null;
        }

        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucao = dataEmprestimo.plusDays(7);

        Aluguel aluguel = new Aluguel(veiculo, pessoa, dataEmprestimo, dataDevolucao);
        alugueis.add(aluguel);
        return aluguel;
    }

    public List<Aluguel> listarAlugueis() {
        return alugueis;
    }

    public boolean finalizarAluguel(String cpf) {
        Optional<Aluguel> aluguel = alugueis
                .stream()
                .filter(e -> e.getPessoa().getCpf().equals(cpf))
                .findFirst();

        if (aluguel.isPresent()) {
            Aluguel emp = aluguel.get();
            alugueis.remove(emp);
            return true;
        }
        return false;
    }

}