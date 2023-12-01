package com.mycompany.finalpharmaplus;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Dao.ProductoDao;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modelo.Producto;

public class pocoStock implements Initializable {

    @FXML
    private TableView<Producto> tvProductos;
    
    private ProductoDao productoDao;
    
    private ContextMenu cmOpciones;
    
    private Producto productoSelect;
    @FXML
    private Button bntCategorias;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.productoDao = new ProductoDao();
    cargarProductosConStockBajo();
   
    
    }
    
   public void cargarProductosConStockBajo() {
    tvProductos.getItems().clear();
    tvProductos.getColumns().clear();

    List<Producto> productosConStockBajo = this.productoDao.listarConStockBajo();

    ObservableList<Producto> data = FXCollections.observableArrayList(productosConStockBajo);

    TableColumn<Producto, Integer> idCol = new TableColumn<>("Id");
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

    TableColumn<Producto, String> nombreCol = new TableColumn<>("Nombre");
    nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));

    TableColumn<Producto, Double> precioCol = new TableColumn<>("Precio");
    precioCol.setCellValueFactory(new PropertyValueFactory<>("precio"));

    TableColumn<Producto, Integer> stockCol = new TableColumn<>("Stock");
    stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

    TableColumn<Producto, String> categoriaCol = new TableColumn<>("Categoria");
    categoriaCol.setCellValueFactory(new PropertyValueFactory<>("categoria"));

    TableColumn<Producto, String> descripcionCol = new TableColumn<>("Descripcion");
    descripcionCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

    tvProductos.setItems(data);
    tvProductos.getColumns().addAll(idCol, nombreCol, precioCol, stockCol, categoriaCol, descripcionCol);
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
            System.err.println("Error al cargar la vista pocoStock.fxml: " + e.getMessage());
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
            System.err.println("Error al cargar la vista pocoStock.fxml: " + e.getMessage());
        }
    }
}
