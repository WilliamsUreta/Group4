package com.ams.Grupo4.Service;

import com.ams.Grupo4.model.MetodoPago;
import com.ams.Grupo4.model.Ventas;
import com.ams.Grupo4.repository.MetodoPagoRepository;
import com.ams.Grupo4.repository.VentasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetodoPagoService {


    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private VentasRepository ventaRepository;

    @Autowired
    private VentasService ventasService;

    //Obtener todos los métodos de pago
    public List<MetodoPago> ObtenerTodos() {  
        return metodoPagoRepository.findAll();
    }

    //Obtener un método de pago por su ID
    public MetodoPago ObtenerPorId(Long id) { 
        return metodoPagoRepository.findById(id).orElse(null);
    }
 
    //Guardar un nuevo método de pago
    public MetodoPago Guardar(MetodoPago metodoPago) {  
        return metodoPagoRepository.save(metodoPago); 
    }                                                

    //Eliminar un método de pago por su ID
    public void Eliminar(Long id) { 
        MetodoPago metodoPago = metodoPagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        
        List<Ventas> ventas = ventaRepository.findByMetodo(metodoPago);

        for(Ventas venta : ventas){
            ventasService.eliminar(Long.valueOf(venta.getId()));
        }

        metodoPagoRepository.delete(metodoPago);
    }

    //Actualizar un método de pago por su ID
    public MetodoPago Actualizar(Long id, MetodoPago metodoPago) {
        MetodoPago metodoPagoExistente = ObtenerPorId(id);
        if (metodoPagoExistente != null) {
            if (metodoPago.getMetodoPago() != null) {
                metodoPagoExistente.setMetodoPago(metodoPago.getMetodoPago());
            }
            return metodoPagoRepository.save(metodoPagoExistente);
        }
        return null;
    }
}
