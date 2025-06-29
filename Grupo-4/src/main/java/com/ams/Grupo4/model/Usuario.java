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

@Entity
@Table(name = "Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
  
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 40,nullable = false)
  private String nombre;

  @Column(nullable = false)
  private String correo; 

  @Column(length = 50 ,nullable = false)
  private String contraseña;

  @ManyToOne
  @JoinColumn(name = "id_Tipo", nullable = false)
  private TipoUsuario tipoUsuario;
}
