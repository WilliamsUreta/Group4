package com.ams.Grupo4.Service;

import com.ams.Grupo4.model.EstadoDeVenta;
import com.ams.Grupo4.model.Ventas;
import com.ams.Grupo4.repository.EstadoDeVentaRepository;
import com.ams.Grupo4.repository.VentasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoDeVentaServicio {

    @Autowired
    private EstadoDeVentaRepository estadoDeVentaRepository;

    @Autowired
    private VentasService ventasService;

    @Autowired
    private VentasRepository ventasRepository;
    
    //Obtener todos los estados de venta
    public List<EstadoDeVenta> ObtenerTodos() {
        return estadoDeVentaRepository.findAll();
    }

    //Obtener un estado de venta por su ID
    public EstadoDeVenta ObtenerPorId(Long id) { 
        return estadoDeVentaRepository.findById(id).orElse(null);
    }

    //Guardar un nuevo estado de venta
    public EstadoDeVenta Guardar(EstadoDeVenta estadoDeVenta) { 
        return estadoDeVentaRepository.save(estadoDeVenta);
    }

    //Eliminar 
    /* aqui se debe de borrar por cascada debido a que si no hay un estado de la venta no puede haber una venta*/
    public void Eliminar(Long id) {
        EstadoDeVenta estadoDeVenta = estadoDeVentaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estado de venta no encontrado"));
        
        List<Ventas> ventas = ventasRepository.findByEstadoV(estadoDeVenta);

        for (Ventas venta : ventas) {
            ventasService.eliminar(Long.valueOf(venta.getId()));
        }
        
        estadoDeVentaRepository.delete(estadoDeVenta);
    }

    //Actualizar un estado de venta por su ID
    public EstadoDeVenta Actualizar(Long id, EstadoDeVenta estadoDeVenta) {
        EstadoDeVenta estadoDeVentaExistente = ObtenerPorId(id);
        if (estadoDeVentaExistente != null) {
            if (estadoDeVenta.getEstado() != null) {
                estadoDeVentaExistente.setEstado(estadoDeVenta.getEstado());
            }
            return estadoDeVentaRepository.save(estadoDeVentaExistente);
        }
        return null;
    }
}
