package com.dona.rosa.tienda.service;

import com.dona.rosa.tienda.model.Producto;
import com.dona.rosa.tienda.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductoMasCercanoAAcabarse() {
        Producto producto1 = new Producto(1L, "Peras", 4000, 5);
        Producto producto2 = new Producto(2L, "Limones", 1500, 25);

        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));

        Producto result = productoService.getProductoMasCercanoAAcabarse();

        assertEquals(producto1, result);
    }

    @Test
    public void testGetCostoTotalInventario() {
        Producto producto1 = new Producto(1L, "Peras", 4000, 5);
        Producto producto2 = new Producto(2L, "Limones", 1500, 25);

        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));

        int totalCosto = productoService.getCostoTotalInventario();

        assertEquals(4000 * 5 + 1500 * 25, totalCosto);
    }
}
