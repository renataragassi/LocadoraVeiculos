package service;

import db.VeiculoDB;
import model.Veiculo;
import java.util.List;

public class VeiculoService {

    private VeiculoDB veiculoDB = new VeiculoDB();

    public Veiculo cadastrarVeiculo(String placa, String modelo, boolean disponivel) {
        if (placa == null || placa.isEmpty()) {
            return null;
        }
        if (modelo == null || modelo.isEmpty()) {
            return null;
        }
        if (disponivel == false) {
            return null;
        }

        Veiculo veiculo = new Veiculo(placa, modelo, disponivel);
        veiculoDB.adicionaVeiculo(veiculo);
        return veiculo;
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoDB.listVeiculos();
    }

    public Veiculo buscarVeiculos(String placa) {
        return veiculoDB.buscaVeiculo(placa);
    }

}