package com.lab.proyecto.controlador;

import com.lab.proyecto.modelo.Pedido;
import com.lab.proyecto.servicio.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    void should_return_pedidos_pendientes() throws Exception {
        // Arrange
        given(pedidoService.obtenerPorEstado("PENDIENTE"))
                .willReturn(List.of(new Pedido(1L, "Freddy", 25.0, "PENDIENTE")));

        // Act
        ResultActions response = mockMvc.perform(get("/api/pedidos").param("estado", "PENDIENTE"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cliente").value("Freddy"));
    }
}
