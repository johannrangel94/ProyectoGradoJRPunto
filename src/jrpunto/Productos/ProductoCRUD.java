package jrpunto.Productos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jrpunto.ConexionDB;

/**
 *
 * @author Johann Rangel
 */
public class ProductoCRUD {

    public boolean insertarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getCantidad());

            int resultadoInsercion = statement.executeUpdate();
            return resultadoInsercion > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Producto producto = new Producto(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"),
                    resultSet.getInt("cantidad")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, cantidad = ? WHERE id = ?";
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getCantidad());
            statement.setInt(5, producto.getId());

            int resultadoActualizacion = statement.executeUpdate();
            return resultadoActualizacion > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
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
