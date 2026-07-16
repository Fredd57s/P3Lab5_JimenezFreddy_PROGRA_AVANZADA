package com.lab.proyecto.controlador;

import com.lab.proyecto.modelo.Pedido;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PedidoRestServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void should_create_pedido_end_to_end() {
        // Arrange
        Pedido nuevo = new Pedido(null, "Fredd", 60.0, "PENDIENTE");

        // Act
        ResponseEntity<Pedido> response = restTemplate.postForEntity("/api/pedidos", nuevo, Pedido.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getCliente()).isEqualTo("Fredd");
    }
}