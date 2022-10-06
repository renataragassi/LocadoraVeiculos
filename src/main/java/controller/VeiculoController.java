package controller;

import model.Veiculo;
import service.VeiculoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
public class VeiculoController {

    private VeiculoService veiculoService = new VeiculoService();

    @GetMapping("/veiculo")
    public List<Veiculo> getVeiculo() {
        return veiculoService.listarVeiculos();
    }

    @PostMapping("/veiculo")
    public void enviaVeiculo(@RequestBody Veiculo veiculo) {
        veiculoService.cadastrarVeiculo(veiculo.getModelo(), veiculo.getPlaca(), veiculo.isDisponivel());
    }

}