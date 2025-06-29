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

import java.util.Date;

@Entity
@Table(name = "Producto")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100 , nullable = false)
    private String nombre;

    @Column(nullable = true)
    private Date fechaCaducidad;

    @Column(nullable = false )
    private Integer precio;

    @Column(nullable = true)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "Categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "MetodoPago", nullable = false)
    private MetodoPago metodoPago;
}
