package services;
/*
    * Interfaz ProductoServices
 * */

import modelos.Producto;

import java.util.List;

public interface ProductoServices {
    abstract List<Producto> listar();
}
