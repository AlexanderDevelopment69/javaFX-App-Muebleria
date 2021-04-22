package Clientes.AlertRegisterCustomers.InvalidAlertBlank;

import Clientes.RegisterCustomers.ControllerRegisterCustomers;

import javafx.fxml.FXML;


public class InvalidBlankController  {


    @FXML
    private void onGoBack() {
        ControllerRegisterCustomers.invalidDialog.close();

    }

}
