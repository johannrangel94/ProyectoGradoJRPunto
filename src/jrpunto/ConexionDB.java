package jrpunto;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Johann Rangel
 */
public class ConexionDB {
    
    static final String URL = "jdbc:mysql://localhost:3306/servidorjrpuntocom";
    static final String USUARIO = "root";
    static final String CONTRASEÑA = "1234";
    
    
    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
