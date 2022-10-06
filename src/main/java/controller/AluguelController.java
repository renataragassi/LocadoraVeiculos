package controller;

import model.DadoAluguel;
import model.Aluguel;
import service.AluguelService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
public class AluguelController {

    private AluguelService aluguelService = new AluguelService();

    @GetMapping("/aluguel")
    public List<Aluguel> listarAlugueis() {
        return aluguelService.listarAlugueis();
    }

    @PostMapping("/aluguel")
    public void salvarAlugueis(@RequestBody DadoAluguel aluguel) {
        aluguelService.cadastrarAluguel(aluguel.getCpf(), aluguel.getPlaca());
    }

}
