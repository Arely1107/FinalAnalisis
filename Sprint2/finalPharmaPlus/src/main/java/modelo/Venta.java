package modelo;

import java.time.LocalDate;

public class Venta {
    private int idVenta;
    private Integer idProducto;
    private LocalDate fechaVenta;
    private int numPiezasVendidas;
    private String nombreProducto;
    
 
    public Venta(int idVenta, int idProducto, String nombreProducto, LocalDate fechaVenta, int numPiezasVendidas) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.fechaVenta = fechaVenta;
        this.numPiezasVendidas = numPiezasVendidas;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getNumPiezasVendidas() {
        return numPiezasVendidas;
    }

    public void setNumPiezasVendidas(int numPiezasVendidas) {
        this.numPiezasVendidas = numPiezasVendidas;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
}
