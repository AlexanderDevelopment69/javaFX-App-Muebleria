package Users.RegisterUsers.AlertRegisterUsers.ValidAlert;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ValidAlertController {
    @FXML
    private JFXButton btnContinuar;

    @FXML
    private void onDone() throws IOException {
        //ControllerRegisterUser.validDialog.close();
        try{ FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login/login.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle( StageStyle.UNDECORATED );
            stage.setScene(new Scene(root));
            stage.show();
            Stage myStage=(Stage) this.btnContinuar.getScene().getWindow();
            myStage.close();
        }catch (Exception e){
            System.out.println("error");
        }




    }

}

