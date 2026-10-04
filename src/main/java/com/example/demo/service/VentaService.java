package com.example.demo.service;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.DetalleVenta;
import com.example.demo.entity.Producto;
import com.example.demo.entity.Venta;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.DetalleVentaRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Venta registrarVenta(Venta venta, List<DetalleVenta> detalles) {
        BigDecimal totalVenta = BigDecimal.ZERO;

        for (DetalleVenta detalle : detalles) {
            Producto producto = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);

            BigDecimal subtotal = producto.getPrecio().multiply(BigDecimal.valueOf(detalle.getCantidad()));
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(subtotal);

            totalVenta = totalVenta.add(subtotal);
        }

        venta.setTotal(totalVenta);
        Venta ventaGuardada = ventaRepository.save(venta);

        for (DetalleVenta detalle : detalles) {
            detalle.setVenta(ventaGuardada);
            detalleVentaRepository.save(detalle);
        }

        if (Boolean.TRUE.equals(venta.getEsCredito()) && venta.getCliente() != null) {
            Cliente cliente = clienteRepository.findById(venta.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            BigDecimal deudaActual = cliente.getDeudaTotal() != null ? cliente.getDeudaTotal() : BigDecimal.ZERO;
            cliente.setDeudaTotal(deudaActual.add(totalVenta));
            clienteRepository.save(cliente);
        }

        return ventaGuardada;
    }
}
