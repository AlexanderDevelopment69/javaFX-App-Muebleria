package Almacen.AlmacenDashboard;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAlmacenDashboard implements Initializable {
    @FXML
    private JFXTextField browser;

    @FXML
    private TableView<?> TableAlmacen2;

    @FXML
    private TableColumn<?, ?> tbId2;

    @FXML
    private TableColumn<?, ?> tbProducto2;

    @FXML
    private TableColumn<?, ?> tbMarca2;

    @FXML
    private TableColumn<?, ?> tbTipo2;

    @FXML
    private TableColumn<?, ?> tbModelo2;

    @FXML
    private TableColumn<?, ?> tbPlazas2;

    @FXML
    private TableColumn<?, ?> tbStock;

    @FXML
    private TableColumn<?, ?> tbPrecio2;

    @FXML
    private TableColumn<?, ?> tbTotal;

    @FXML
    private TableColumn<?, ?> tbProveedor2;

    @FXML
    private TableColumn<?, ?> tbEstado2;

    @FXML
    private JFXTextField id1;

    @FXML
    private BorderPane stackPrincipal;

    @FXML
    void Buscar(MouseEvent event) {

    }
    @FXML
    void agregarProducto(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml= FXMLLoader.load(getClass().getResource("/Almacen/Fabricacion/registrarProducto13.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }
    @FXML
    void agregarCategorias(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml= FXMLLoader.load(getClass().getResource("/Almacen/Categorias/registrarTM.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }


    @FXML
    void agregarProveedores(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml= FXMLLoader.load(getClass().getResource("/Proveedores/registrarProveedor13.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }
    @FXML
    void Productos(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml= FXMLLoader.load(getClass().getResource("/Almacen/AgregarProducto/registerProducto13.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }





}
