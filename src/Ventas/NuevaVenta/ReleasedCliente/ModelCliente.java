package Ventas.NuevaVenta.ReleasedCliente;

import Almacen.AgregarProducto.ModelTableProducto;
import ConnectionMySQL.ConnectionMYSQL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModelCliente {


    PreparedStatement pst;
    String clienteNombre;
    String clienteApellido;

    public ModelCliente(String clienteNombre, String clienteApellido) {
        this.clienteNombre = clienteNombre;
        this.clienteApellido = clienteApellido;
    }

    public ModelCliente() {

    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteApellido() {
        return clienteApellido;
    }

    public void setClienteApellido(String clienteApellido) {
        this.clienteApellido = clienteApellido;
    }

    @Override
    public String toString() {
        return  clienteNombre+ " "+ clienteApellido;
    }

    public ObservableList<ModelCliente> getReleasedCliente(String valor)  {
        ObservableList<ModelCliente> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select cliente.cliApellidos,cliente.cliNombres from cliente  where cliDni like '%"+valor+"%'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
//                int CatId=rs.getInt("catId");
                clienteApellido=rs.getString("cliApellidos");
                clienteNombre=rs.getString("cliNombres");

                ModelCliente e=new ModelCliente(clienteApellido,clienteNombre);



//                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
                obs.add(e);
            }
            connection.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }




}
