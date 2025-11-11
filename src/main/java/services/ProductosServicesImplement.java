package services;
/*
    * Implementación de la interfaz ProductoServices
 * */
import modelos.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductosServicesImplement implements ProductoServices {
    @Override
    public List<Producto> listar() {
        // Agregamos productos
        return Arrays.asList(
                new Producto(1L, "Laptop", "Tecnología", 1200.00),
                new Producto(2L, "Mouse", "Tecnología", 25.50),
                new Producto(3L, "Teclado", "Tecnología", 45.00),
                new Producto(4L, "Monitor", "Tecnología", 300.00),
                new Producto(5L, "Silla", "Oficina", 150.00)
        );
    }
}
