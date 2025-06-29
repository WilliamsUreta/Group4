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
@Table(name = "Prod_Categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prod_Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name = "Categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "Producto", nullable = false)
    private Producto producto;
}
