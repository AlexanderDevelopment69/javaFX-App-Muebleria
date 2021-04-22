package Ventas.NuevaVenta.AlertVenta.InvalidAlertBlank;

import Clientes.RegisterCustomers.ControllerRegisterCustomers;
import javafx.fxml.FXML;


public class InvalidBlankController  {


    @FXML
    private void onGoBack() {
        ControllerRegisterCustomers.invalidDialog.close();

    }

}
