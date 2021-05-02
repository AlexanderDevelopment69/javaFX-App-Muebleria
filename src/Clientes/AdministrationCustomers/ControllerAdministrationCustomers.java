package Clientes.AdministrationCustomers;


import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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


    //Usamos la clase de RegisterCustomers para el modelo
    @FXML
    private TableView<Clientes.RegisterCustomers.ModelTableCliente> TableClientes;
    @FXML
    private TableColumn<?, ?> tbCodigo;

    @FXML
    private TableColumn<?, ?> tbNombres;

    @FXML
    private TableColumn<?, ?> tbApellidos;

    @FXML
    private TableColumn<?, ?> tbSexo;

    @FXML
    private TableColumn<?, ?> tbEdad;

    @FXML
    private TableColumn<?, ?> tbDireccion;

    @FXML
    private TableColumn<?, ?> tbDni;

    @FXML
    private TableColumn<?, ?> tbRuc;

    @FXML
    private TableColumn<?, ?> tbCelular;

    @FXML
    private TableColumn<?, ?> tbCorreo;
    @FXML
    private TableColumn<?, ?> tbFecha;



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
        Parent fxml= FXMLLoader.load((getClass().getResource("/Clientes/RegisterCustomers/registerCustomers13.fxml")));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }



    public void MostrarClienteEnTabla(){
        try {

            Clientes.RegisterCustomers.ModelTableCliente s=new Clientes.RegisterCustomers.ModelTableCliente();
            ObservableList<Clientes.RegisterCustomers.ModelTableCliente> items=s.getCliente();
            this.TableClientes.setItems(items);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Usando el modelo de la clase ModelTableCliente
        tbCodigo.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tbNombres.setCellValueFactory(new PropertyValueFactory<>("Nombres"));
        tbApellidos.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
        tbDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        tbSexo.setCellValueFactory(new PropertyValueFactory<>("Sexo"));
        tbDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        tbRuc.setCellValueFactory(new PropertyValueFactory<>("Ruc"));
        tbCelular.setCellValueFactory(new PropertyValueFactory<>("Celular"));
//        tbEdad.setCellValueFactory(new PropertyValueFactory<>("Edad"));
        tbFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        tbCorreo.setCellValueFactory(new PropertyValueFactory<>("Correo"));
        MostrarClienteEnTabla();


    }
}
