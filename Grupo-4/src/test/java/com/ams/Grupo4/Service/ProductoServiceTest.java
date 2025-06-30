package com.ams.Grupo4.Service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;  

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.ams.Grupo4.model.Categoria;
import com.ams.Grupo4.model.MetodoPago;
import com.ams.Grupo4.model.Producto;
import com.ams.Grupo4.repository.ProductoRepository;

@ActiveProfiles("test")
@SpringBootTest
public class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @MockBean
    private ProductoRepository productoRepository;
/* 
    private Producto CreateProducto(){
        return new Producto(
            1,
            "Pan",
            LocalDate.now(),
            300,
            20,
            new Categoria(),
            new MetodoPago()
        );
    }
*/



}
