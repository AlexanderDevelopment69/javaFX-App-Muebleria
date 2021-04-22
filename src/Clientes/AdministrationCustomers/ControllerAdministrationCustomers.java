package Clientes.AdministrationCustomers;

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

public class ControllerAdministrationCustomers implements Initializable {

    @FXML
    private BorderPane stackPrincipal;

    @FXML
    private JFXTextField browser;

    @FXML
    private TableView<?> TableAlmacen2;

    @FXML
    private TableColumn<?, ?> tbCodigo;

    @FXML
    private TableColumn<?, ?> tbNombres;

    @FXML
    private TableColumn<?, ?> tbApellidos;

    @FXML
    private TableColumn<?, ?> tbSexo;

    @FXML
    private TableColumn<?, ?> tbDireccion;

    @FXML
    private TableColumn<?, ?> tbDni;

    @FXML
    private TableColumn<?, ?> tbRuc;

    @FXML
    private TableColumn<?, ?> tbCelular;

    @FXML
    void Buscar(MouseEvent event) {

    }

    @FXML
    void agregarCliente(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml= FXMLLoader.load(getClass().getResource("/Clientes/RegisterCustomers/registerCustomers13.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
