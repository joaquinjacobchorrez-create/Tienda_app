package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 15)
    private String telefono;

    @Column(precision = 10, scale = 2)
    private BigDecimal deudaTotal;

    public Cliente() {
    }

    public Cliente(String nombre, String telefono, BigDecimal deudaTotal) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.deudaTotal = deudaTotal;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public BigDecimal getDeudaTotal() {
        return deudaTotal;
    }

    public void setDeudaTotal(BigDecimal deudaTotal) {
        this.deudaTotal = deudaTotal;
    }
}
