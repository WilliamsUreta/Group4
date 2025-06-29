package com.ams.Grupo4.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.Grupo4.model.ProductosVentas;
import com.ams.Grupo4.model.Ventas;
import com.ams.Grupo4.repository.ProductosVentasRepository;
import com.ams.Grupo4.repository.VentasRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentasService {
    
    @Autowired
    private VentasRepository ventasRepository;

    @Autowired
    private ProductoVentasService productoVentasService; 

    @Autowired
    private ProductosVentasRepository productosVentasRepository;
    //Obtener todas las ventas
    public List<Ventas> obtenerTodos() {
        return ventasRepository.findAll();
    }

    //Obtener una venta por su ID
    public Ventas obtenerPorId(Long id) {
        return ventasRepository.findById(id).orElse(null);
    }

    //Guardar una nueva venta
    public Ventas guardar(Ventas venta) {
        return ventasRepository.save(venta);
    }

    //Eliminar 
    /* se debe eliminar los productos de la venta ya que si no hay una venta no deberian haber productos*/
    public void eliminar(Long id) {
        Ventas venta = ventasRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        
        ventasRepository.delete(venta);
    }

    //Buscar ventas por fecha
    public List<Ventas> buscarPorFecha(LocalDate fecha) {
        return ventasRepository.findByFechaCompra(fecha);
    }

    //Buscar ventas por rango de fechas
    public List<Ventas> buscarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return ventasRepository.findByFechaCompraBetween(fechaInicio, fechaFin);
    }

    //Buscar ventas por monto 
    public List<Ventas> buscarPorMontoMayorA(Integer totalVenta) {
        return ventasRepository.findBytotalVenta(totalVenta);
    }

    //Buscar ventas por método de pago
    public List<Ventas> buscarPorMetodoPago(Long metodoPagoId) {
        return ventasRepository.findByMetodoId(metodoPagoId);
    }

    //Patch para ventas
    public Ventas actualizar(Long id, Ventas venta) {
        Ventas ventaE = obtenerPorId(id);
        if (ventaE != null) {
            if(venta.getEstadoDeVenta() != null){
                ventaE.setEstadoDeVenta(venta.getEstadoDeVenta());
            }
            if(venta.getFechaCompra() != null){
                ventaE.setFechaCompra(venta.getFechaCompra());
            }
            if(venta.getHoraCompra() != null){
                ventaE.setHoraCompra(venta.getHoraCompra());
            }
            if(venta.getMetodo() != null){
                ventaE.setMetodo(venta.getMetodo());
            }
            if(venta.getTotalVenta() != null){
                ventaE.setTotalVenta(venta.getTotalVenta());
            }
            return guardar(ventaE);
        }
        return null;
    }
}
