package com.ams.Grupo4;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.ams.Grupo4.model.Categoria;
import com.ams.Grupo4.model.EstadoDeVenta;
import com.ams.Grupo4.model.MetodoPago;
import com.ams.Grupo4.model.Prod_Categoria;
import com.ams.Grupo4.model.Producto;
import com.ams.Grupo4.model.ProductosVentas;
import com.ams.Grupo4.model.TipoUsuario;
import com.ams.Grupo4.model.Usuario;
import com.ams.Grupo4.model.Ventas;
import com.ams.Grupo4.repository.CategoriaRepository;
import com.ams.Grupo4.repository.EstadoDeVentaRepository;
import com.ams.Grupo4.repository.MetodoPagoRepository;
import com.ams.Grupo4.repository.Prod_CategoriaRepository;
import com.ams.Grupo4.repository.ProductoRepository;
import com.ams.Grupo4.repository.ProductosVentasRepository;
import com.ams.Grupo4.repository.TipoUsuarioRepository;
import com.ams.Grupo4.repository.UsuarioRepository;
import com.ams.Grupo4.repository.VentasRepository;

import net.datafaker.Faker;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private VentasRepository ventasRepository;

    @Autowired
    private EstadoDeVentaRepository estadoDeVentaRepository;

    @Autowired
    private Prod_CategoriaRepository prod_CategoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductosVentasRepository productosVentasRepository;

    @Override
    public void run(String... args) throws Exception {
 
        Faker faker = new Faker();
        Random random = new Random();
        
        for (int i = 0; i < 5; i++) {
            Categoria categoria = new Categoria();
            categoria.setCategoria(faker.cat().name());
            categoriaRepository.save(categoria);
        }
         List<Categoria> Lcategorias = categoriaRepository.findAll();

        for (int i = 0; i < 3; i++) {
            EstadoDeVenta estadoDeVenta = new EstadoDeVenta();
            estadoDeVenta.setEstado(faker.options().option("Disponible", "No disponible"));
            estadoDeVentaRepository.save(estadoDeVenta);
        }

        List<EstadoDeVenta> LestadoDeVenta = estadoDeVentaRepository.findAll();

        for (int i = 0; i < 3; i++) {
            MetodoPago metodoPago = new MetodoPago();

            metodoPago.setMetodoPago(faker.options().option("Devito","Credito","Efectivo"));
            metodoPagoRepository.save(metodoPago);
        }

        List<MetodoPago> LmetodoPago = metodoPagoRepository.findAll();

        for (int i = 0; i < 3; i++) {
            Producto producto = new Producto();

            producto.setNombre(faker.name().firstName());
            producto.setFechaCaducidad(new Date(System.currentTimeMillis() + faker.number().numberBetween(10000000, 999999999)));
            producto.setPrecio(faker.number().numberBetween(2500, 10000));
            producto.setStock(faker.number().numberBetween(1, 16));
            producto.setCategoria(Lcategorias.get(random.nextInt(Lcategorias.size())));
            producto.setMetodoPago(LmetodoPago.get(random.nextInt(LmetodoPago.size())));
            productoRepository.save(producto);
        }

        List<Producto> Lproduto = productoRepository.findAll();

        for (int i = 0; i < 3; i++) {
            Prod_Categoria prod_Categoria = new Prod_Categoria();

            prod_Categoria.setCategoria(Lcategorias.get(random.nextInt(Lcategorias.size())));
            prod_Categoria.setProducto(Lproduto.get(random.nextInt(Lproduto.size())));
            prod_CategoriaRepository.save(prod_Categoria);
        }

        for (int i = 0; i < 3; i++) {
            TipoUsuario tipoUsuario = new TipoUsuario();

            tipoUsuario.setNombre(faker.options().option("Cliente","Trabajador","Desarrollador"));
            tipoUsuarioRepository.save(tipoUsuario);
        }

        List<TipoUsuario> LtipoUsuarios = tipoUsuarioRepository.findAll();

        for (int i = 0; i < 3; i++) {
            Usuario usuario = new Usuario();
  
            usuario.setNombre(faker.name().firstName());
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setContraseña(faker.random().toString());
            usuario.setTipoUsuario(LtipoUsuarios.get(random.nextInt(LtipoUsuarios.size())));
            usuarioRepository.save(usuario);
        }

        for (int i = 0; i < 3; i++) {
            Ventas ventas = new Ventas();
            ventas.setFechaCompra(LocalDate.now());
            ventas.setHoraCompra(LocalTime.now());
            ventas.setTotalVenta(faker.number().numberBetween(7500, 30000));
            ventas.setMetodo(LmetodoPago.get(random.nextInt(LmetodoPago.size())));
            ventas.setEstadoDeVenta(LestadoDeVenta.get(random.nextInt(LestadoDeVenta.size())));
            ventasRepository.save(ventas);
        }
        
        List<Ventas> Lventas = ventasRepository.findAll();

        for (int i = 0; i < 3; i++) {
            ProductosVentas productosVentas = new ProductosVentas();
          
            productosVentas.setProducto(Lproduto.get(random.nextInt(Lproduto.size())));
            productosVentas.setVentas(Lventas.get(random.nextInt(Lventas.size())));
            productosVentasRepository.save(productosVentas);
        }
    }
}
