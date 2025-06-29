package com.ams.Grupo4.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ams.Grupo4.model.EstadoDeVenta;
import com.ams.Grupo4.model.MetodoPago;
import com.ams.Grupo4.model.Ventas;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Long>{
        // Buscar ventas por fecha
        List<Ventas> findByFechaCompra(LocalDate fecha);
    
        // Buscar ventas por rango de fechas
        List<Ventas> findByFechaCompraBetween(LocalDate fechaInicio, LocalDate fechaFin);
        
        // Buscar ventas por monto total
        List<Ventas> findBytotalVenta(Integer totalVenta);

        // Buscar ventas por metodo de pago
        List<Ventas> findByMetodoId(Long metodoId);

        @Query("SELECT v FROM Ventas v WHERE v.estadoDeVenta = :estado")
        List<Ventas> findByEstadoV(@Param("estadoDeVenta") EstadoDeVenta estadoDeVenta);

        @Query("SELECT v FROM Ventas v WHERE v.metodo = :metodo")
        List<Ventas> findByMetodo(@Param("metodo") MetodoPago metodo);

        /*List<Ventas> CreateVenta(Integer id, LocalDate fechaCompra, LocalTime horaCompra, Integer totalVenta, MetodoPago metodo, EstadoDeVenta estadoDeVenta);
        
        List<Ventas> UpdateVenta(Integer id, LocalDate fechaCompra, LocalTime horaCompra, Integer totalVenta, MetodoPago metodo, EstadoDeVenta estadoDeVenta);
        
        List<Ventas> DeleteVenta(Integer id, LocalDate fechaCompra, LocalTime horaCompra, Integer totalVenta, MetodoPago metodo, EstadoDeVenta estadoDeVenta);
        
        List<Ventas> ExistsVenta(Integer id, LocalDate fechaCompra, LocalTime horaCompra, Integer totalVenta, MetodoPago metodo, EstadoDeVenta estadoDeVenta);*/

}
