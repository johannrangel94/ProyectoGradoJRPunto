package jrpunto.Venta;

import jrpunto.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Johann Rangel
 */
public class FacturaCRUD {
    
        // Método para buscar facturas por rango de fechas
    public List<Factura> buscarFacturasPorFecha(Date fechaInicio, Date fechaFin) {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT id, total, fecha FROM facturas WHERE fecha BETWEEN ? AND ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            statement.setDate(2, new java.sql.Date(fechaFin.getTime()));

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double total = resultSet.getDouble("total");
                Date fecha = resultSet.getDate("fecha");
                facturas.add(new Factura(id, total, fecha, new ArrayList<>()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facturas;
    }

    // Método para obtener los detalles de una factura por su ID
    public List<DetalleFactura> obtenerDetallesFactura(int facturaId) {
        List<DetalleFactura> detalles = new ArrayList<>();
        String sql = "SELECT producto_id, cantidad, precio FROM detalles_factura WHERE factura_id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, facturaId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int productoId = resultSet.getInt("producto_id");
                int cantidad = resultSet.getInt("cantidad");
                double precio = resultSet.getDouble("precio");
                detalles.add(new DetalleFactura(0, facturaId, productoId, cantidad, precio));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    public boolean registrarFactura(Factura factura) {
           String sqlVenta = "INSERT INTO ventas (total, fecha) VALUES (?, ?)";
           String sqlDetalle = "INSERT INTO detalle_venta (venta_id, producto_id, cantidad, precio) VALUES (?, ?, ?, ?)";

           try (Connection conexion = ConexionDB.conectar();
                PreparedStatement statementVenta = conexion.prepareStatement(sqlVenta, PreparedStatement.RETURN_GENERATED_KEYS)) {

               // Insertar en ventas
               statementVenta.setDouble(1, factura.getTotal());
               statementVenta.setDate(2, new java.sql.Date(factura.getFecha().getTime()));
               int affectedRows = statementVenta.executeUpdate();

               if (affectedRows == 0) {
                   throw new SQLException("Creating venta failed, no rows affected.");
               }

               try (ResultSet generatedKeys = statementVenta.getGeneratedKeys()) {
                   if (generatedKeys.next()) {
                       int ventaId = generatedKeys.getInt(1);
                       // Insertar detalles
                       try (PreparedStatement statementDetalle = conexion.prepareStatement(sqlDetalle)) {
                           for (DetalleFactura detalle : factura.getDetalles()) {
                               statementDetalle.setInt(1, ventaId);
                               statementDetalle.setInt(2, detalle.getProductoId());
                               statementDetalle.setInt(3, detalle.getCantidad());
                               statementDetalle.setDouble(4, detalle.getPrecio());
                               statementDetalle.addBatch();
                           }
                           statementDetalle.executeBatch();
                       }
                       return true;
                   } else {
                       throw new SQLException("Creating venta failed, no ID obtained.");
                   }
               }
           } catch (SQLException e) {
               e.printStackTrace();
               return false;
           }
       }    
    
}
