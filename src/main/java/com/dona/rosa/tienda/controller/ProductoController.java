package com.dona.rosa.tienda.controller;

import com.dona.rosa.tienda.model.Producto;
import com.dona.rosa.tienda.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
        Producto producto = productoRepository.findById(id).orElseThrow();
        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setCantidad(productoDetails.getCantidad());
        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }
}
