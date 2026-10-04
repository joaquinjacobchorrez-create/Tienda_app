package com.example.demo.controller;

import com.example.demo.dto.VentaRequest;
import com.example.demo.entity.Venta;
import com.example.demo.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public Venta registrarVenta(@RequestBody VentaRequest request) {
        return ventaService.registrarVenta(request.getVenta(), request.getDetalles());
    }
}
