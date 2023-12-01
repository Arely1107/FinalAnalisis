package com.mycompany.finalpharmaplus;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Dao.CategoriaDao;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modelo.Categoria;

public class categoria implements Initializable {
  @FXML
    private TableView<Categoria> tvCategorias;
    
    private CategoriaDao categoriadao;
    @FXML
    private Button bntCategorias;
    

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.categoriadao = new CategoriaDao();
        cargarCategorias();
    }
    public void cargarCategorias(){
        tvCategorias.getItems().clear();
        tvCategorias.getColumns().clear();
        
        List<Categoria> categoria = this.categoriadao.listar();
        
        ObservableList<Categoria> data = FXCollections.observableArrayList(categoria);
        
        TableColumn idCol = new TableColumn("Id");
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        
        TableColumn descCol = new TableColumn("Descripcion");
        descCol.setCellValueFactory(new PropertyValueFactory("descripcion"));

        TableColumn nomCol = new TableColumn("Nombre");
        nomCol.setCellValueFactory(new PropertyValueFactory("nombre"));

        
        TableColumn subCol = new TableColumn("Subcategoria");
        subCol.setCellValueFactory(new PropertyValueFactory("subcategoria"));
        
        tvCategorias.setItems(data);
        tvCategorias.getColumns().addAll(idCol,descCol,nomCol,subCol);
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
