package Clientes.AlertRegisterCustomers.InvalidAlert;

import Clientes.RegisterCustomers.ControllerRegisterCustomers;

import javafx.fxml.FXML;


public class InvalidAlertController {

    @FXML
    private void onGoBack() {
        ControllerRegisterCustomers.invalidDialog.close();

    }

}
