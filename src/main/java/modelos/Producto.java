package modelos;
/*
    * Clase modelo Producto
 */
public class Producto {
    // Encapsulamos y declaramos las variables del objeto producto
    private Long idProducto;
    private String nombre;
    private String tipo;
    private double precio;

    /*
        *  Constructor vacio
     * */
    public Producto(){}

    /*
        *  Constructor con parametros
     * */
    public Producto(Long idProducto, String nombre, String tipo, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
    }

    /*
        *  Getters y Setters
     *  */
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
