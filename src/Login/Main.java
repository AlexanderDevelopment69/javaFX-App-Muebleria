package Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
//        Parent root = FXMLLoader.load(getClass().getResource("/Almacen/Proveedores/registrarProveedor.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setOpacity(0.9);
        primaryStage.setScene(new Scene(root, 600, 550));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
