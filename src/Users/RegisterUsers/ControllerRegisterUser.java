package Users.RegisterUsers;

import ConnectionMySQL.ConnectionMYSQL;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerRegisterUser implements Initializable {

    @FXML
    private VBox validPane, invalidPane;
    @FXML
    public static JFXDialog validDialog, invalidDialog;
    @FXML
    private StackPane n;
    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField dni;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField validPassword;


    @FXML
    private JFXTextField edad;

    @FXML
    private JFXTextField correo;

    @FXML
    private JFXTextField celular;

    @FXML
    private JFXTextField domicilio;
    @FXML
    private JFXButton btnSalir;

    PreparedStatement pst;




    @FXML
    void registrar() {
        String Username;
        String Dni;
        String Password;
        String Edad;
        String Correo;
        String Celular;
        String Domicilio;

        Username = username.getText();
        Dni = dni.getText();
        Password = password.getText();
        Edad = edad.getText();
        Correo = correo.getText();
        Celular = celular.getText();
        Domicilio = domicilio.getText();


        if (username.getText().isBlank() ||dni.getText().isBlank() || password.getText().isBlank() || edad.getText().isBlank()||celular.getText().isBlank() ||domicilio.getText().isBlank()) {

            try {
                invalidPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Users/RegisterUsers/AlertRegisterUsers/InvalidAlert/InvalidAlert.fxml")));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            invalidDialog = new JFXDialog(n, invalidPane, JFXDialog.DialogTransition.CENTER);
            invalidDialog.show();
        }
        else if((!password.getText().equals(validPassword.getText()))){
            validPassword.setStyle("-fx-prompt-text-fill:#01c192");
            validPassword.setText("");
            validPassword.setPromptText("contraseña incorrenta");
            validPassword.requestFocus();

        }

         else {


            try {
                ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
                Connection connection = ConnectionClass.getConnection();
                pst = connection.prepareStatement("insert into usuario (nombre,contrasena,edad,correo,dni,domicilio,celular) values (?,md5(?),?,?,?,?,?)");
                pst.setString(1, Username);
                pst.setString(2, Password);
                pst.setString(3, Edad);
                pst.setString(4, Correo);
                pst.setString(5, Dni);
                pst.setString(6, Domicilio);
                pst.setString(7, Celular);
                pst.executeUpdate();
                connection.close();
                try {
                    validPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Users/RegisterUsers/AlertRegisterUsers/ValidAlert/ValidAlert.fxml")));

                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                validDialog = new JFXDialog(n, validPane, JFXDialog.DialogTransition.CENTER);
                validDialog.show();


            } catch (SQLException ex) {

                try {
                    invalidPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Users/RegisterUsers/AlertRegisterUsers/InvalidAlert/InvalidAlert.fxml")));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                invalidDialog = new JFXDialog(n, invalidPane, JFXDialog.DialogTransition.CENTER);
                invalidDialog.show();

            }
        }
    }


    @FXML
    void salir() {
        try{ FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login/login.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle( StageStyle.UNDECORATED );
            stage.setScene(new Scene(root));
            stage.show();
            Stage myStage=(Stage) this.btnSalir.getScene().getWindow();
            myStage.close();
        }catch (Exception e){
            System.out.println("error");
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
