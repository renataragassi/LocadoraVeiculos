package db;

import model.Aluguel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AluguelDB {

    private static List<Aluguel> alugueis = new ArrayList<>();

    public Aluguel adicionaAluguel(Aluguel aluguel) {
        alugueis.add(aluguel);
        return aluguel;
    }

    public List<Aluguel> listAlugueis() {
        return alugueis;
    }

    public Aluguel buscaAluguel(String Veiculo) {
        Optional<Aluguel> aluguel = alugueis
                .stream()
                .filter(l -> l.getVeiculo().equals(Veiculo))
                .findFirst();
        if (aluguel.isPresent()) {
            return aluguel.get();
        }
        return null;
    }

}
