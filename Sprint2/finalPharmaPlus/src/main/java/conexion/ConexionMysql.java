package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionMysql {
 private Connection connection;
    private String usuario = "root";
    private String password = "";
    private String servidor = "localhost";
    private String puerto = "3306";
    private String nombreBD = "productos";
    
    private String url = "jdbc:mysql://" + servidor + ":" + puerto + "/" + nombreBD + "?serverTimezone=UTC";

    private String driver = "com.mysql.cj.jdbc.Driver";
    
    public ConexionMysql() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, usuario, password);
            if (connection != null) {
                System.out.println("Conexión realizada correctamente");
            }
        } catch (Exception e) {
            System.err.println("Ocurrió un error en la conexión");
            System.err.println("Mensaje del error: " + e.getMessage());
            System.err.println("Detalles del error:");
            e.printStackTrace();
        }
    }

public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, usuario, password);
    }

    public void closeConnection(Connection connection) {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
