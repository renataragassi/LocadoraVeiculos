package main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.AluguelService;
import service.PessoaService;
import service.VeiculoService;
import model.Aluguel;
import model.Pessoa;
import model.Veiculo;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        PessoaService pessoaService = new PessoaService();
        VeiculoService veiculoService = new VeiculoService();
        AluguelService aluguelService = new AluguelService();

        while (true) {
            System.out.println("1) Cadastrar Pessoa");
            System.out.println("2) Listar Pessoas");
            System.out.println("3) Buscar Pessoa");
            System.out.println("4) Cadastrar Veículo");
            System.out.println("5) Listar Veículos");
            System.out.println("6) Buscar Veículo");
            System.out.println("7) Cadastrar Aluguel");
            System.out.println("8) Listar Aluguéis");
            System.out.println("9) Devolução do Veículo");

            String opcao = scanner.nextLine();
            if (opcao.equals("1")) {
                System.out.println("Digite o nome completo:");
                String nome = scanner.nextLine();

                System.out.println("Digite o CPF:");
                String cpf = scanner.nextLine();

                System.out.println("Digite o endereço completo:");
                String endereco = scanner.nextLine();

                System.out.println("Digite o telefone com DDD:");
                String telefone = scanner.nextLine();

                Pessoa pessoa = pessoaService.cadastrarPessoa(nome, cpf, endereco, telefone);
                if (pessoa != null) {
                    System.out.println("Pessoa cadastrada com sucesso!");
                } else {
                    System.out.println("Erro ao cadastrar a pessoa!");
                }
            }

            if (opcao.equals("2")) {
                List<Pessoa> pessoas = pessoaService.listaPessoas();
                pessoas.forEach(p -> System.out.println(p.getNome() + " " + p.getCpf()));
            }

            if (opcao.equals("3")) {
                System.out.println("Digite o CPF: ");
                String cpf = scanner.nextLine();

                Pessoa pessoa = pessoaService.buscaPessoa(cpf);
                if (pessoa != null) {
                    System.out.println(pessoa.getNome() + " " + pessoa.getCpf());
                } else {
                    System.out.println("Pessoa não encontrada!");
                }
            }

            if (opcao.equals("4")) {
                System.out.println("Digite o modelo do veículo:");
                String modelo = scanner.nextLine();

                System.out.println("Digite a placa do veículo:");
                String placa = scanner.nextLine();

                System.out.println("O veículo está disponível? Digite \"true\" para sim e \"false\" para não.");
                boolean disponivel = true | false;

                Veiculo veiculo = veiculoService.cadastrarVeiculo(modelo, placa, disponivel);
                if (veiculo != null) {
                    System.out.println("Veículo cadastrado com sucesso!");
                } else {
                    System.out.println("Erro ao cadastrar veiculo!");
                }
            }

            if (opcao.equals("5")) {
                List<Veiculo> veiculos = veiculoService.listarVeiculos();
                veiculos.forEach(l -> System.out.println(l.getModelo()));
            }

            if (opcao.equals("6")) {
                System.out.println("Digite a placa do veículo:");
                String placa = scanner.nextLine();

                Veiculo veiculo = veiculoService.buscarVeiculos(placa);
                if (veiculo != null) {
                    System.out.println(veiculo.getModelo());
                } else {
                    System.out.println("Veículo não encontrado!");
                }
            }

            if (opcao.equals("7")) {
                System.out.println("Digite o CPF:");
                String cpf = scanner.nextLine();

                System.out.println("Digite a placa do veículo:");
                String placa = scanner.nextLine();

                Aluguel aluguel = aluguelService.cadastrarAluguel(cpf, placa);
                if (aluguel != null) {
                    System.out.println(aluguel.getPessoa().getNome() + " " + aluguel.getDataEmprestimo() + " " + aluguel.getDataDevolucao());
                } else {
                    System.out.println("Erro ao cadastrar aluguel!");
                }
            }

            if (opcao.equals("8")) {
                List<Aluguel> aluguel = aluguelService.listarAlugueis();
                aluguel.forEach(e -> System.out.println(e.getPessoa().getNome() + " " + e.getDataEmprestimo() + " " + e.getDataDevolucao()));
            }

            if (opcao.equals("9")) {
                System.out.println("Digite o CPF:");
                String cpf = scanner.nextLine();

                boolean funcionou = aluguelService.finalizarAluguel(cpf);
                if (funcionou) {
                    System.out.println("Aluguel concluído com sucesso!");
                } else {
                    System.out.println("Erro ao concluir aluguel!");
                }
            }

        }

    }

}