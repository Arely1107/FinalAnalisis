package com.mycompany.finalpharmaplus;


import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import Dao.ProductoDao;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Producto;


public class productos implements Initializable {

@FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField doublePrecio;


    @FXML
    private TextField numStock;

    @FXML
    private TextArea txtAreaDescripcion;

    private TextField txtCategoria;

    @FXML
    private TextField txtNombre;
    
    @FXML
    private TableView<Producto> tvProductos;
    
    private ProductoDao productoDao;
    
    private ContextMenu cmOpciones;
    
    private Producto productoSelect;
    
    
    @FXML
    private ComboBox<String> comboB;

    @FXML
    private Button bntCategorias;


    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.productoDao = new ProductoDao();
        cargarProductos();
        comboBox(new ActionEvent());    

        cmOpciones = new ContextMenu();
        MenuItem miEditar = new MenuItem("Editar");
        MenuItem miEliminar = new MenuItem("Eliminar");

        cmOpciones.getItems().addAll(miEditar, miEliminar);

        miEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tvProductos.getSelectionModel().getSelectedIndex();
                productoSelect = tvProductos.getItems().get(index);

                System.out.println(productoSelect);

                txtNombre.setText(productoSelect.getNombre());
                doublePrecio.setText(String.valueOf(productoSelect.getPrecio()));
                numStock.setText(String.valueOf(productoSelect.getStock()));
                txtCategoria.setText(comboB.getValue());      
                txtAreaDescripcion.setText(productoSelect.getDescripcion());

                btnCancelar.setDisable(false);
            }
        });

        miEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tvProductos.getSelectionModel().getSelectedIndex();

                Producto productoEliminar = tvProductos.getItems().get(index);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                alert.setTitle("Confirmacion");
                alert.setHeaderText(null);
                alert.setContentText("¿Desea eliminar Producto ?" + productoEliminar.getNombre() + "?");
                alert.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    boolean rsp = productoDao.eliminar(productoEliminar.getId());
                    if (rsp) {
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Exito");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Se elimino Correctamente el Producto");
                        alert2.initStyle(StageStyle.UTILITY);
                        alert2.showAndWait();

                        cargarProductos();

                    } else {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);

                        alert2.setTitle("Error");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Hubo un Error al eliminar el Producto");
                        alert2.initStyle(StageStyle.UTILITY);
                        alert2.showAndWait();
                    }

                }
            }

        });
        tvProductos.setContextMenu(cmOpciones);

    }

    @FXML
    void btnGuardarOnAction(ActionEvent event) {
 if (productoSelect == null) {
        Producto producto = new Producto();
        producto.setNombre(txtNombre.getText());

        try {
            double precio = Double.parseDouble(doublePrecio.getText());
            if (precio > 0) {
                producto.setPrecio(precio);
            } else {
                mostrarAlertaError("El precio debe ser mayor que $0.00");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarAlertaError("Error al convertir el precio a double: " + e.getMessage());
            return;
        }

        try {
            int stock = Integer.parseInt(numStock.getText());
            if (stock > 0) {
                producto.setStock(stock);
            } else {
                mostrarAlertaError("El Stock debe ser Mayor a 0 Piezas");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarAlertaError("Error al convertir el stock a int: " + e.getMessage());
            return;
        }

        String categoriaSeleccionada = comboB.getValue();
        if (categoriaSeleccionada == null || categoriaSeleccionada.isEmpty()) {
            mostrarAlertaError("Por favor, seleccione una categoría");
            return;
        }
        producto.setCategoria(categoriaSeleccionada);

        producto.setDescripcion(txtAreaDescripcion.getText());

        System.out.println(producto.toString());

        boolean rsp = this.productoDao.registrar(producto);
            if (rsp) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Exito");
                alert.setHeaderText(null);
                alert.setContentText("Se registro Correctamente el Producto");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();

                limpiarCampos();
                cargarProductos();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Hubo un Error al registrar el Producto");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
        } else {
            productoSelect.setNombre(txtNombre.getText());
            productoSelect.setPrecio(Double.parseDouble(doublePrecio.getText()));
            productoSelect.setStock(Integer.parseInt(numStock.getText()));
            productoSelect.setCategoria(comboB.getValue());
            productoSelect.setDescripcion(txtAreaDescripcion.getText());

            boolean rsp = this.productoDao.editar(productoSelect);

            if (rsp) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Exito");
                alert.setHeaderText(null);
                alert.setContentText("Se guardo Correctamente el Producto");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();

                limpiarCampos();
                cargarProductos();

                productoSelect = null;
                btnCancelar.setDisable(true);

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Hubo al EDITAR al guardar el Producto");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
        }

    }
    private void limpiarCampos(){
        txtNombre.setText("");
        doublePrecio.clear();
        numStock.clear();
        txtCategoria.setText("");
        txtAreaDescripcion.setText("");
    }
    
    public void cargarProductos(){
        tvProductos.getItems().clear();
        tvProductos.getColumns().clear();
        
        List<Producto> producto = this.productoDao.listar();
        
        ObservableList<Producto> data = FXCollections.observableArrayList(producto);
        
        TableColumn idCol = new TableColumn("Id");
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        
        TableColumn nombreCol = new TableColumn("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory("nombre"));

        TableColumn precioCol = new TableColumn("Precio");
        precioCol.setCellValueFactory(new PropertyValueFactory("precio"));

        TableColumn stockCol = new TableColumn("Stock");
        stockCol.setCellValueFactory(new PropertyValueFactory("stock"));
        
        TableColumn categoriaCol = new TableColumn("Categoria");
        categoriaCol.setCellValueFactory(new PropertyValueFactory("categoria"));
        
        TableColumn descrpcionCol = new TableColumn("Descripcion");
        descrpcionCol.setCellValueFactory(new PropertyValueFactory("descripcion"));
        
        tvProductos.setItems(data);
        tvProductos.getColumns().addAll(idCol,nombreCol,precioCol,stockCol,categoriaCol,descrpcionCol);
    }
    
    private void mostrarAlertaError(String mensaje) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(mensaje);
    alert.initStyle(StageStyle.UTILITY);
    alert.showAndWait();
}

private void mostrarAlertaInformacion(String mensaje) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Éxito");
    alert.setHeaderText(null);
    alert.setContentText(mensaje);
    alert.initStyle(StageStyle.UTILITY);
    alert.showAndWait();
}
    
    @FXML
    void btnCancelarOnAction(ActionEvent event) {
        productoSelect = null;
        limpiarCampos();
        btnCancelar.setDisable(true);
        
    } @FXML
void comboBox(ActionEvent event){
    List<String> categorias = productoDao.obtenerCategorias();
    if (categorias != null && !categorias.isEmpty()) {
        ObservableList<String> listData = FXCollections.observableArrayList(categorias);
        if (comboB.getItems().isEmpty()) {
            comboB.setItems(listData);
        } else {
  
            comboB.getItems().addAll(listData);
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Hubo un error al listar las categorías");
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }
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
            Stage newStage = new Stage();
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
}
