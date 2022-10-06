package service;

import model.Pessoa;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaService {

    private static List<Pessoa> pessoas = new ArrayList<>();

    public Pessoa cadastrarPessoa(String nome, String cpf, String endereco, String telefone) {

        if (nome == null || nome.isEmpty()) {
            return null;
        } else if (cpf == null || cpf.isEmpty()) {
            return null;
        } else {
            Pessoa pessoa = new Pessoa(nome, cpf, endereco, telefone);
            pessoas.add(pessoa);
            return pessoa;
        }
    }

    public List<Pessoa> listaPessoas() {
        return pessoas;
    }

    public Pessoa buscaPessoa(String cpf) {
        Optional<Pessoa> pessoa = pessoas
                .stream()
                .filter(p -> p.getCpf().equals(cpf))
                .findFirst();

        if (pessoa.isPresent()) {
            return pessoa.get();
        }
        return null;
    }

}