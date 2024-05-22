package jrpunto.Usuarios;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jrpunto.ConexionDB;

/**
 *
 * @author Johann Rangel
 */
public class Usuario {
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String contraseña;
    
    
    // Constructor sin parámetros
    public Usuario() {
    }
    
    // Constructor con parametros
    public Usuario(String nombre, String apellido, String nombreUsuario, String contraseña) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
    /*
    public static Usuario cargarUsuario(String nombreUsuario) {
        Connection conexion = ConexionDB.conectar();
        Usuario usuario = null;
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM usuarios WHERE usuario = ?");
            statement.setString(1, nombreUsuario);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                usuario = new Usuario(resultSet.getString("usuario"), resultSet.getString("contraseña"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }
    */
    



    
}
