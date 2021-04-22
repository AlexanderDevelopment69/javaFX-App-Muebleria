package Users.AdministrationUsers;

import Almacen.Producto.ModelTableAlmacen;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class ControllerAdministrationUsers implements Initializable {

    @FXML
    private BorderPane stackPrincipal;

    @FXML
    private JFXTextField browser;

    @FXML
    private TableView<ModelTableUsers> TableUsers;

    @FXML
    private TableColumn<?, ?> tbDni;

    @FXML
    private TableColumn<?, ?> tbNombres;

    @FXML
    private TableColumn<?, ?> tbPassword;

    @FXML
    private TableColumn<?, ?> tbEdad;

    @FXML
    private TableColumn<?, ?> tbCorreo;

    @FXML
    private TableColumn<?, ?> tbDomicilio;

    @FXML
    private TableColumn<?, ?> tbCelular;







    @FXML
    void Buscar(MouseEvent event) {

    }
    public void MostrarUsuarioEnTabla(){
        try {

            ModelTableUsers s=new ModelTableUsers();
            ObservableList<ModelTableUsers> items=s.getUsers();
            this.TableUsers.setItems(items);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tbDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        tbNombres.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        tbPassword.setCellValueFactory(new PropertyValueFactory<>("Contrasena"));
        tbEdad.setCellValueFactory(new PropertyValueFactory<>("Edad"));
        tbCorreo.setCellValueFactory(new PropertyValueFactory<>("Correo"));
        tbDomicilio.setCellValueFactory(new PropertyValueFactory<>("Domicilio"));
        tbCelular.setCellValueFactory(new PropertyValueFactory<>("Celular"));
        MostrarUsuarioEnTabla();
    }
}
