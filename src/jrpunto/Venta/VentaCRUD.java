/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jrpunto.Venta;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jrpunto.ConexionDB;

/**
 *
 * @author Johann Rangel
 */
public class VentaCRUD {

    public boolean registrarVenta(Venta venta) {
        String sql = "INSERT INTO ventas (producto_id, cantidad, total, fecha) VALUES (?, ?, ?, ?)";
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, venta.getProductoId());
            statement.setInt(2, venta.getCantidad());
            statement.setDouble(3, venta.getTotal());
            statement.setDate(4, new java.sql.Date(venta.getFecha().getTime()));

            int resultadoInsercion = statement.executeUpdate();
            return resultadoInsercion > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Venta> obtenerTodasLasVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Venta venta = new Venta(
                    resultSet.getInt("id"),
                    resultSet.getInt("producto_id"),
                    resultSet.getInt("cantidad"),
                    resultSet.getDouble("total"),
                    resultSet.getDate("fecha")
                );
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    public boolean actualizarVenta(Venta venta) {
        String sql = "UPDATE ventas SET producto_id = ?, cantidad = ?, total = ?, fecha = ? WHERE id = ?";
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, venta.getProductoId());
            statement.setInt(2, venta.getCantidad());
            statement.setDouble(3, venta.getTotal());
            statement.setDate(4, new java.sql.Date(venta.getFecha().getTime()));
            statement.setInt(5, venta.getId());

            int resultadoActualizacion = statement.executeUpdate();
            return resultadoActualizacion > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarVenta(int id) {
        String sql = "DELETE FROM ventas WHERE id = ?";
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, id);

            int resultadoEliminacion = statement.executeUpdate();
            return resultadoEliminacion > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}