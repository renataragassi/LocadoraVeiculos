package db;

import model.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoDB {

    private static List<Veiculo> veiculos = new ArrayList<>();

    public Veiculo adicionaVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
        return veiculo;
    }

    public List<Veiculo> listVeiculos() {
        return veiculos;
    }

    public Veiculo buscaVeiculo(String placa) {
        Optional<Veiculo> livro = veiculos
                .stream()
                .filter(l -> l.getPlaca().equals(placa))
                .findFirst();
        if (livro.isPresent()) {
            return livro.get();
        }
        return null;
    }

}