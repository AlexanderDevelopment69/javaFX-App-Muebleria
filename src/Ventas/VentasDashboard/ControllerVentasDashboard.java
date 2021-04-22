package Ventas.VentasDashboard;

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

public class ControllerVentasDashboard implements Initializable {
    @FXML
    private BorderPane stackPrincipal;

    @FXML
    private JFXTextField browser;

    @FXML
    private TableView<?> TableVentas;

    @FXML
    private TableColumn<?, ?> tbId;

    @FXML
    private TableColumn<?, ?> tbProducto;

    @FXML
    private TableColumn<?, ?> tbMarca;

    @FXML
    private TableColumn<?, ?> tbModelo;

    @FXML
    private TableColumn<?, ?> tbPlazas;

    @FXML
    private TableColumn<?, ?> tbPrecio;

    @FXML
    private TableColumn<?, ?> tbCantidad;

    @FXML
    private TableColumn<?, ?> tbTotal;

    @FXML
    private TableColumn<?, ?> tbVendedor;

    @FXML
    private TableColumn<?, ?> tbFecha;

    @FXML
    private JFXTextField id1;

    @FXML
    void NuevaVenta(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml= FXMLLoader.load(getClass().getResource("/Ventas/NuevaVenta/registrarVenta13.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }

    @FXML
    void ReporteVentas(MouseEvent event) {

    }

    @FXML
    void VerClientes(MouseEvent event) {

    }
    @FXML
    void Buscar(MouseEvent event) {

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }





}
