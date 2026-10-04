package com.example.demo.dto;

import com.example.demo.entity.DetalleVenta;
import com.example.demo.entity.Venta;
import java.util.List;

public class VentaRequest {

    private Venta venta;
    private List<DetalleVenta> detalles;

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
}
