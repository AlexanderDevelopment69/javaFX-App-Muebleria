package DashboardPrincipal;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPrincipalMenu implements Initializable {

    @FXML
    private AnchorPane stackPrincipal;
    @FXML
    private JFXButton btnSalir;

    @FXML
    void salir(MouseEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login/login.fxml"));
            Parent root = (Parent) fxmlLoader.load();
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
    @FXML
    void Principal(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml=FXMLLoader.load(getClass().getResource("/DashboardPrincipal/dashboard.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }
    @FXML
    void Administracion(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml=FXMLLoader.load(getClass().getResource("/Users/AdministrationUsers/administracionUsers.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }


    @FXML
    void Almacen(MouseEvent event) throws IOException {


        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml=FXMLLoader.load(getClass().getResource("/Almacen/AlmacenDashboard/almacenDashboard.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);



//        try {
//            Parent fxml= FXMLLoader.load(getClass().getResource("/Almacen/Producto/registrarProducto2.fxml"));
//            stackPrincipal.getChildren().removeAll();
//            stackPrincipal.getChildren().setAll(fxml);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }




    @FXML
    void Clientes(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml=FXMLLoader.load(getClass().getResource("/Clientes/AdministrationCustomers/administracionCustomers13.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }



    @FXML
    void Proveedores(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml=FXMLLoader.load(getClass().getResource("/Proveedores/registrarProveedor.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }

    @FXML
    void Ventas(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml=FXMLLoader.load(getClass().getResource("/Ventas/VentasDashboard/ventasDashboard.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        try {
//            Parent fxml= FXMLLoader.load(getClass().getResource("/DashboardPrincipal/dashboard.fxml"));
//            stackPrincipal.getChildren().removeAll();
//            stackPrincipal.getChildren().setAll(fxml);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
