package com.ams.Grupo4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Venta")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private LocalDate fechaCompra;

    @Column(nullable = false)
    private LocalTime horaCompra;

    @Column(nullable = false)
    private Integer totalVenta;

    @ManyToOne
    @JoinColumn(name = "Metodo de pago", nullable = false)
    private MetodoPago metodo;

    @ManyToOne
    @JoinColumn(name = "Estado de Venta", nullable = false)
    private EstadoDeVenta estadoDeVenta;
}
