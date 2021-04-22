package Clientes.AlertRegisterCustomers.ValidAlert;

import Clientes.RegisterCustomers.ControllerRegisterCustomers;

import javafx.fxml.FXML;

import java.io.IOException;

public class ValidAlertController {

    @FXML
    private void onDone() throws IOException {
        ControllerRegisterCustomers.validDialog.close();



    }

}

