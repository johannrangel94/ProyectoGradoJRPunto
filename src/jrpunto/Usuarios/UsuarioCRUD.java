package jrpunto.Usuarios;


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
public class UsuarioCRUD {
    
     // Método que inserta un nuevo usuario en la base de datos
public boolean insertarUsuario(String nombre, String apellido, String nombreUsuario, String contraseña) {
    try (Connection conexion = ConexionDB.conectar();
         PreparedStatement statement = conexion.prepareStatement("INSERT INTO usuarios (nombre, apellido, usuario, contraseña) VALUES (?, ?, ?, ?)")) {
        
        statement.setString(1, nombre);
        statement.setString(2, apellido);
        statement.setString(3, nombreUsuario);
        statement.setString(4, contraseña);
        
        int resultadoInsercion = statement.executeUpdate();
        return resultadoInsercion > 0;
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    // Método para obtener todos los usuarios de la base de datos
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
             
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String nombreUsuario = resultSet.getString("usuario");
                String contraseña = resultSet.getString("contraseña");
                Usuario usuario = new Usuario(nombre, apellido, nombreUsuario, contraseña);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    // Método para actualizar los datos de un usuario en la base de datos
    public boolean actualizarUsuario(String nombreUsuario, String nuevaContraseña) {
        // Implementación similar a insertarUsuario
        return false;
        // Implementación similar a insertarUsuario
    }

    // Método para eliminar un usuario de la base de datos
    public boolean eliminarUsuario(String nombreUsuario) {
        // Implementación similar a insertarUsuario
        return false;
        // Implementación similar a insertarUsuario
    }
    
}
