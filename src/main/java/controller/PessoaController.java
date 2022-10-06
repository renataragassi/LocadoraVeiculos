package controller;

import model.Pessoa;
import service.PessoaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
public class PessoaController {

    private PessoaService pessoaService = new PessoaService();

    @GetMapping("/pessoa")
    public List<Pessoa> listarPessoas() {
        return pessoaService.listaPessoas();
    }

    @PostMapping("/pessoa")
    public void salvarPessoa(@RequestBody Pessoa pessoa) {
        pessoaService.cadastrarPessoa(pessoa.getNome(), pessoa.getCpf(), pessoa.getEndereco(), pessoa.getTelefone());
    }

}