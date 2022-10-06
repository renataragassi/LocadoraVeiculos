package db;

import model.Pessoa;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaDB {
    private static List<Pessoa> pessoas = new ArrayList<>();

    public Pessoa adicionaPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
        return pessoa;
    }

    public List<Pessoa> listPessoas() {
        return pessoas;
    }

    public Pessoa buscaPessoa(String cpf) {
        Optional<Pessoa> pessoa = pessoas
                .stream()
                .filter(l -> l.getCpf().equals(cpf))
                .findFirst();
        if (pessoa.isPresent()) {
            return pessoa.get();
        }
        return null;
    }

}
