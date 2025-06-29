package com.ams.Grupo4.model;

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

@Entity
@Table(name = "Productos Ventas")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ProductosVentas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Producto",nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "Ventas",nullable = false)
    private Ventas ventas;
    
    /*
    @Column(nullable = false)
    private String producto;

    @Column(nullable = false)
    private Integer cantidad;
    */
}
