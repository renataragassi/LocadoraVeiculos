package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Veiculo;
import service.VeiculoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class VeiculoControllerTest {

    @InjectMocks
    private VeiculoController veiculoController;

    @Mock
    private VeiculoService veiculoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(veiculoController).build();
    }

    @Test
    @Order(3)
    public void testListaVeiculosVazio() throws Exception {

        Mockito.when(veiculoService.listarVeiculos()).thenReturn(new ArrayList<>());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/veiculo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resposta = result.getResponse().getContentAsString();
        Assertions.assertEquals("[]", resposta);

    }

    @Test
    @Order(1)
    public void testListaVeiculos() throws Exception {

        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(new Veiculo("EBB-8465", "Ford Ka", true));
        veiculos.add(new Veiculo("EMF-2378", "Ford Fiesta", true));

        Mockito.when(veiculoService.listarVeiculos()).thenReturn(veiculos);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/veiculo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resposta = result.getResponse().getContentAsString();
        Assertions.assertEquals("[{\"placa\":\"EBB-8465\",\"modelo\":\"Ford Ka\",\"disponivel\":\"true\"},{\"placa\":\"EMF-2378\",\"modelo\":\"Ford Fiesta\",\"disponivel\":\"true\"}]", resposta);

    }

    @Test
    @Order(2)
    public void cadastrarVeiculo() throws Exception {

        Veiculo veiculo = new Veiculo("EBB-8465", "Ford Ka", true);
        String livroString = new ObjectMapper().writeValueAsString(veiculo);

        Mockito.when(veiculoService.cadastrarVeiculo("EBB-8465", "Ford Ka", true))
                .thenReturn(veiculo);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/veiculo")
                        .content(livroString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String resposta = result.getResponse().getContentAsString();
        Assertions.assertEquals("{\"placa\":\"EBB-8465\",\"modelo\":\"Ford Ka\",\"disponivel\":\"true\"}", resposta);

    }

}
