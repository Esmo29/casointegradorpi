package com.dona.rosa.tienda.controller;

import com.dona.rosa.tienda.model.Producto;
import com.dona.rosa.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventario")
public class InventarioController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/producto-mas-cercano-a-acabarse")
    public Producto getProductoMasCercanoAAcabarse() {
        return productoService.getProductoMasCercanoAAcabarse();
    }

    @GetMapping("/costo-total")
    public int getCostoTotalInventario() {
        return productoService.getCostoTotalInventario();
    }
}
