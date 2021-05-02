package Login;

import ConnectionMySQL.ConnectionMYSQL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private JFXButton btnlogin;

    @FXML
    public JFXTextField username;

    @FXML
    private JFXPasswordField password;
    PreparedStatement pst;
    @FXML
    public void ingresar(MouseEvent event) throws IOException, SQLException {
            String Username;
            String Password;
            Username=username.getText();
            Password=password.getText();
            ConnectionMYSQL ConnectionClass =new ConnectionMYSQL();
            Connection connection=ConnectionClass.getConnection();

      pst=connection.prepareStatement("select*from usuario where dni=? and contrasena=md5(?)");
      pst.setString(1,Username);
      pst.setString(2,Password);
      ResultSet rs=pst.executeQuery();
        if(rs.next()) {

            try {

//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Almacen/Producto/registrarProducto2.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/DashboardPrincipal/principalMenu13.fxml"));

                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
//                stage.setFullScreen(true);
                stage.setScene(new Scene(root));
                stage.show();
                Stage myStage = (Stage) this.btnlogin.getScene().getWindow();
                myStage.close();
                connection.close();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,e);
                }

            }


      else{


            //username.setStyle("-fx-text-fill:white");
            password.setPromptText("Introduce una contraseña");
            password.setStyle("-fx-prompt-text-fill:#01c192");
            password.setText("");
            password.requestFocus();



        }


        }
    @FXML
    void RegisterCount(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Users/RegisterUsers/registerUser.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        Stage myStage = (Stage) this.btnlogin.getScene().getWindow();
        myStage.close();


    }

    @FXML
    void salir(MouseEvent event) {
    System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void Minimize(MouseEvent event) {

    }
}
