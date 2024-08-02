package com.dona.rosa.tienda.controller;

import com.dona.rosa.tienda.model.Producto;
import com.dona.rosa.tienda.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
    }

    @Test
    public void testGetAllProductos() throws Exception {
        when(productoRepository.findAll()).thenReturn(Arrays.asList(
            new Producto(1L, "Peras", 4000, 65),
            new Producto(2L, "Limones", 1500, 25)
        ));

        mockMvc.perform(get("/productos")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nombre").value("Peras"))
            .andExpect(jsonPath("$[1].nombre").value("Limones"));
    }

    @Test
    public void testCreateProducto() throws Exception {
        Producto producto = new Producto(1L, "Peras", 4000, 65);
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/productos")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"nombre\":\"Peras\",\"precio\":4000,\"cantidad\":65}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Peras"));
    }

    @Test
    public void testUpdateProducto() throws Exception {
        Producto producto = new Producto(1L, "Peras", 4000, 65);
        when(productoRepository.findById(1L)).thenReturn(java.util.Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(put("/productos/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"nombre\":\"Peras\",\"precio\":5000,\"cantidad\":60}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.precio").value(5000))
            .andExpect(jsonPath("$.cantidad").value(60));
    }

    @Test
    public void testDeleteProducto() throws Exception {
        doNothing().when(productoRepository).deleteById(1L);

        mockMvc.perform(delete("/productos/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
