package Users.RegisterUsers.AlertRegisterUsers.InvalidAlert;

import Users.RegisterUsers.ControllerRegisterUser;

import javafx.fxml.FXML;


public class InvalidAlertController {

    @FXML
    private void onGoBack() {
        ControllerRegisterUser.invalidDialog.close();

    }

}
