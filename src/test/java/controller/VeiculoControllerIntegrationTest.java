package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Veiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class VeiculoControllerIntegrationTest {

    @InjectMocks
    private VeiculoController veiculoController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(veiculoController).build();
    }

    @Test
    public void testCadastrarVeiculo() throws Exception {
        Veiculo veiculo = new Veiculo("EBB-8465", "Ford Ka", true);
        String veiculoString = new ObjectMapper().writeValueAsString(veiculo);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/veiculo")
                        .content(veiculoString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String resposta = result.getResponse().getContentAsString();
        Assertions.assertEquals("{\"placa\":\"EBB-8465\",\"modelo\":\"Ford Ka\",\"disponivel\":\"true\"}", resposta);
    }

    @Test
    public void testListaVeiculos() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/veiculo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resposta = result.getResponse().getContentAsString();
        Assertions.assertEquals("[{\"placa\":\"EBB-8465\",\"modelo\":\"Ford Ka\",\"disponivel\":\"true\"}]", resposta);
    }


}