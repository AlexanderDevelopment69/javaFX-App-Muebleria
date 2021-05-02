package DashboardPrincipal;

import ConnectionMySQL.ConnectionMYSQL;
import Login.Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerPrincipalMenu implements Initializable {

    @FXML
    private AnchorPane stackPrincipal;
    @FXML
    private JFXButton btnSalir;

    @FXML
    private SplitMenuButton SplitUsuario;

    @FXML
    private BarChart<?, ?> VentasChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    PreparedStatement pst;
    @FXML
    void salir(MouseEvent event) throws IOException {


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login/login.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle( StageStyle.UNDECORATED );
            stage.setScene(new Scene(root));
            stage.show();
            Stage myStage=(Stage) this.btnSalir.getScene().getWindow();
            myStage.close();

    }
    @FXML
    void Principal(MouseEvent event) throws IOException {

            FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
            ft.setFromValue(0);
            ft.setToValue(1);
            SequentialTransition pt = new SequentialTransition(ft);
            pt.play();
            Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/DashboardPrincipal/dashboard.fxml")));
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

        Parent fxml=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Users/AdministrationUsers/administracionUsers.fxml")));
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
        Parent fxml=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Almacen/AlmacenDashboard/almacenDashboard.fxml")));
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
        Parent fxml=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Clientes/AdministrationCustomers/administracionCustomers13.fxml")));
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
        Parent fxml=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Proveedores/registrarProveedor13.fxml")));
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
        Parent fxml=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Ventas/VentasDashboard/ventasDashboard.fxml")));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }

    @FXML
    void exit(ActionEvent event) {

    }
//    public void CompletarInfoSplitUsuario(){
//        Controller a=new Controller();
//
//        try {
//
//            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
//            Connection connection = ConnectionClass.getConnection();
//
//
//            pst = connection.prepareStatement("select usuario.nombre from usuario where dni=?");
////            pst.setString(1,a.username.getText());
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                SplitUsuario.setText(rs.getString("nombre"));
//            }
////            codProducto.setStyle("-fx-text-fill:#644EFF");
//
//
//        } catch (Exception ex) {
//            System.out.println("" + ex);
//        }
//
//
//    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Parent fxml= FXMLLoader.load(getClass().getResource("/DashboardPrincipal/dashboard.fxml"));
            stackPrincipal.getChildren().removeAll();
            stackPrincipal.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }



        XYChart.Series set1=new XYChart.Series<>();
        set1.getData().add(new XYChart.Data<>("Enero",10));
        set1.getData().add(new XYChart.Data<>("Febrero",20));
        set1.getData().add(new XYChart.Data<>("Marzo",30));
        set1.getData().add(new XYChart.Data<>("Abril",40));
        set1.getData().add(new XYChart.Data<>("Mayo",50));
        VentasChart.getData().addAll(set1);

    }
}
