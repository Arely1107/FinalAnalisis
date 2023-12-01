package com.mycompany.finalpharmaplus;

import Dao.VentasDao;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Venta;

public class VentasController implements Initializable {

    @FXML
    private DatePicker fechaInicialPicker;

    @FXML
    private DatePicker fechaFinalPicker;

    @FXML
    private TableView<Venta> ventasTableView;

    private final VentasDao ventasDao = new VentasDao(); // Asumo que tienes una instancia de VentasDao
    @FXML
    private Button bntCategorias;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar fecha final como el día de hoy
        fechaFinalPicker.setValue(LocalDate.now());

        // Configurar fecha inicial como 5 días atrás
        fechaInicialPicker.setValue(LocalDate.now().minusDays(4));

        cargarDatosPredeterminados();
    }

    private void cargarDatosPredeterminados() {
        LocalDate fechaFinal = fechaFinalPicker.getValue();
        LocalDate fechaInicial = fechaInicialPicker.getValue();
        cargarDatos(fechaInicial, fechaFinal);
    }

    private void cargarDatos(LocalDate fechaInicial, LocalDate fechaFinal) {
        limpiarTabla();
        Map<String, Map<LocalDate, Integer>> ventasPorProducto = ventasDao.obtenerVentasEnRango(fechaInicial, fechaFinal);

        // Configurar columna fija para el nombre del producto
        TableColumn<Venta, String> nombreProductoColumn = new TableColumn<>("Nombre del Producto");
        nombreProductoColumn.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        ventasTableView.getColumns().add(nombreProductoColumn);

        LocalDate fecha = fechaInicial;
        while (!fecha.isAfter(fechaFinal)) {
            final LocalDate fechaActual = fecha; // Variable final para usar en la lambda

            TableColumn<Venta, String> columnaFecha = new TableColumn<>(fecha.toString());
            columnaFecha.setCellValueFactory(cellData -> {
                Venta venta = cellData.getValue();
                String nombreProducto = venta.getNombreProducto();
                return new SimpleStringProperty(Integer.toString(ventasPorProducto.getOrDefault(nombreProducto, new HashMap<>()).getOrDefault(fechaActual, 0)));
            });

            ventasTableView.getColumns().add(columnaFecha);
            fecha = fecha.plusDays(1);
        }

        // Configurar columna para la suma total
        TableColumn<Venta, String> sumaTotalColumn = new TableColumn<>("Suma total");
        sumaTotalColumn.setCellValueFactory(cellData -> {
            Venta venta = cellData.getValue();
            String nombreProducto = venta.getNombreProducto();
            int sumaTotal = ventasPorProducto.getOrDefault(nombreProducto, new HashMap<>()).values().stream().mapToInt(Integer::intValue).sum();
            return new SimpleStringProperty(Integer.toString(sumaTotal));
        });

        ventasTableView.getColumns().add(sumaTotalColumn);
        
                // Configurar columna para el stock mínimo
TableColumn<Venta, String> stockMinimoColumn = new TableColumn<>("Stock Mínimo");
stockMinimoColumn.setCellValueFactory(cellData -> {
    Venta venta = cellData.getValue();
    String nombreProducto = venta.getNombreProducto();
    int totalPiezas = ventasPorProducto.getOrDefault(nombreProducto, new HashMap<>()).values().stream().mapToInt(Integer::intValue).sum();
    int stockMinimo = totalPiezas / 4; // Calcular stock mínimo
    return new SimpleStringProperty(Integer.toString(stockMinimo));
});

ventasTableView.getColumns().add(stockMinimoColumn);

// Configurar columna para el stock máximo
TableColumn<Venta, String> stockMaximoColumn = new TableColumn<>("Stock Máximo");
stockMaximoColumn.setCellValueFactory(cellData -> {
    Venta venta = cellData.getValue();
    String nombreProducto = venta.getNombreProducto();
    int stockMaximo = ventasPorProducto.getOrDefault(nombreProducto, new HashMap<>()).values().stream().mapToInt(Integer::intValue).max().orElse(0); // Calcular stock máximo
    return new SimpleStringProperty(Integer.toString(stockMaximo));
});

ventasTableView.getColumns().add(stockMaximoColumn);


        List<Venta> nuevasVentas = construirListaVentas(ventasPorProducto);
        ventasTableView.getItems().addAll(nuevasVentas);
    }

    private List<Venta> construirListaVentas(Map<String, Map<LocalDate, Integer>> ventasPorProducto) {
        List<Venta> ventas = new ArrayList<>();

        ventasPorProducto.forEach((nombreProducto, ventasPorFecha) -> {
            Venta venta = new Venta(0, 0, nombreProducto, LocalDate.now(), 0); // Dummy Venta con fecha y numPiezasVendidas en cero

            ventasPorFecha.forEach((fecha, numPiezasVendidas) -> {
                // Actualizar los valores correspondientes de la venta
                venta.setFechaVenta(fecha);
                venta.setNumPiezasVendidas(numPiezasVendidas);
            });

            ventas.add(venta);
        });

        return ventas;
    }

    @FXML
    private void btnPocoStockOnAccion(ActionEvent event) throws IOException {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("pocoStock.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene scene = new Scene(root);
            newStage.setScene(scene);

            newStage.show();

            currentStage.close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista pocoStock.fxml: " + e.getMessage());
        }
    }

    @FXML
    private void btnProductosOnAccion(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("productos.fxml"));
            Parent root = loader.load();
            Stage newStage = newStage = new Stage();
            Scene scene = new Scene(root);
            newStage.setScene(scene);

            newStage.show();

            currentStage.close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista productos.fxml: " + e.getMessage());
        }
    }

    @FXML
    private void btnCategoriasOnAccion(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("categorias.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene scene = new Scene(root);
            newStage.setScene(scene);

            newStage.show();

            currentStage.close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista categorias.fxml: " + e.getMessage());
        }
    }

    @FXML
    private void btnVendidosOnAccion(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ventas.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene scene = new Scene(root);
            newStage.setScene(scene);

            newStage.show();

            currentStage.close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista ventas.fxml: " + e.getMessage());
        }
    }

    @FXML
    private void btnAceptarOnAccion(ActionEvent event) {
        LocalDate fechaInicial = fechaInicialPicker.getValue();
        LocalDate fechaFinal = fechaFinalPicker.getValue();

        if (fechaInicial != null && fechaFinal != null) {
            // Llamado al método con los parámetros necesarios
            limpiarTabla();
            cargarDatos(fechaInicial, fechaFinal);
        }
    }

    @FXML
    private void exportarATxt(ActionEvent event) {
        try {
            String rutaArchivo = "C:/Users/arely/OneDrive/Escritorio/Ventas.txt";

            BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo));

            ObservableList<TableColumn<Venta, ?>> columnas = ventasTableView.getColumns();
            for (TableColumn<Venta, ?> columna : columnas) {
                writer.write(columna.getText() + "\t");
            }
            writer.newLine();
            ObservableList<Venta> filas = ventasTableView.getItems();

            for (Venta venta : filas) {
                for (TableColumn<Venta, ?> columna : columnas) {
                    Object value = columna.getCellObservableValue(venta).getValue();

                    writer.write((value != null ? value.toString() : "0") + "\t");
                }

                writer.newLine();
            }

            writer.close();

            System.out.println("Exportación a archivo de texto exitosa.");
        } catch (IOException e) {
            System.err.println("Error al exportar a archivo de texto: " + e.getMessage());
        }
    }

    private void limpiarTabla() {
        // Limpiar la lista de ítems y las columnas
        ventasTableView.getItems().clear();
        ventasTableView.getColumns().clear();
    }
}