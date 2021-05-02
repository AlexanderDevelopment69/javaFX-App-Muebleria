package Users.AdministrationUsers;

import Almacen.AgregarProducto.ModelTableProducto;
import Almacen.Producto.ModelTableAlmacen;
import ConnectionMySQL.ConnectionMYSQL;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
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


    PreparedStatement pst;




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

    @FXML
    void Eliminar(MouseEvent event) {
        ModelTableUsers p = TableUsers.getSelectionModel().getSelectedItem();

        if(p==null) {

        }else {

            try {

                ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
                Connection connection = ConnectionClass.getConnection();
                pst = connection.prepareStatement("delete from usuario where dni=?");
                pst.setString(1, p.getDni());
                pst.executeUpdate();
                connection.close();
//                MostrarValirAlertDeleted();
                MostrarUsuarioEnTabla();
//                Nuevo();
                this.TableUsers.refresh();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Estas iniciando sesion con este usuario no puede ser eliminado");
                System.out.println(e);
            }
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
