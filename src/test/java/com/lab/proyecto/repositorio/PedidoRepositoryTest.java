package com.lab.proyecto.repositorio;

import com.lab.proyecto.modelo.Pedido;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@DataJpaTest
class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Test
    void should_find_pedidos_by_estado() {
        // Arrange
        pedidoRepository.save(new Pedido(null, "Freddy", 50.0, "PENDIENTE"));
        pedidoRepository.save(new Pedido(null, "Edith", 120.0, "PENDIENTE"));
        pedidoRepository.save(new Pedido(null, "Xd", 80.0, "PAGADO"));

        // Act
        List<Pedido> pedidosPendientes = pedidoRepository.findByEstado("PENDIENTE");

        // Assert
        assertThat(pedidosPendientes).hasSize(2);
    }
}