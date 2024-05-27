package jrpunto.Venta;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Johann Rangel
 */
public class Factura {
    
    private int id;
    private double total;
    private Date fecha;
    private List<DetalleFactura> detalles;

    public Factura() {}

    public Factura(int id, double total, Date fecha, List<DetalleFactura> detalles) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.detalles = detalles;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }
    
    
    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }
}
