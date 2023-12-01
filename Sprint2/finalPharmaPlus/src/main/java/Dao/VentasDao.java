package Dao;

import conexion.ConexionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class VentasDao {
    private ConexionMysql fabricaConexion;

    public VentasDao() {
        this.fabricaConexion = new ConexionMysql();
    }

    public Map<String, Map<LocalDate, Integer>> obtenerVentasEnRango(LocalDate fechaInicial, LocalDate fechaFinal) {
        Map<String, Map<LocalDate, Integer>> ventasPorProducto = new HashMap<>();

        String consultaSQL = "SELECT pv.fecha_Venta, p.nombre as nombreProducto, pv.piezas as totalPiezas "
                + "FROM productosvendidos pv "
                + "JOIN producto p ON pv.id_Producto = p.id "
                + "WHERE pv.fecha_Venta BETWEEN ? AND ? ";

        try (Connection conexion = this.fabricaConexion.getConnection();
             PreparedStatement preparedStatement = conexion.prepareStatement(consultaSQL)) {

            preparedStatement.setDate(1, java.sql.Date.valueOf(fechaInicial));
            preparedStatement.setDate(2, java.sql.Date.valueOf(fechaFinal));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    LocalDate fechaVenta = resultSet.getDate("fecha_Venta").toLocalDate();
                    String nombreProducto = resultSet.getString("nombreProducto");
                    int totalPiezas = resultSet.getInt("totalPiezas");

                    ventasPorProducto.computeIfAbsent(nombreProducto, k -> new HashMap<>());
                    ventasPorProducto.get(nombreProducto).put(fechaVenta, totalPiezas);
                }
            }
        } catch (SQLException e) {
            // Manejo de la excepción
            System.err.println("Ocurrió un error al realizar la nueva consulta para actualizar datos en la tabla:");
            e.printStackTrace();
        } finally {
            // No es necesario cerrar la conexión aquí
            // this.fabricaConexion.closeConnection();
        }

        return ventasPorProducto;
    }
}
