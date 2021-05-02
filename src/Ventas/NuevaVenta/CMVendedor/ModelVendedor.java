package Ventas.NuevaVenta.CMVendedor;


import ConnectionMySQL.ConnectionMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModelVendedor {

    PreparedStatement pst;
    String Vendedor;

    public ModelVendedor(String vendedor) {
        Vendedor = vendedor;
    }

    public ModelVendedor() {

    }

    public String getVendedor() {
        return Vendedor;
    }

    public void setVendedor(String vendedor) {
        Vendedor = vendedor;
    }

    @Override
    public String toString() {
        return Vendedor;
    }

    public ObservableList<ModelVendedor> getCMVendedor()  {
        ObservableList<ModelVendedor> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select usuario.dni from usuario");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
//                int CatId=rs.getInt("catId");

                Vendedor=rs.getString("dni");
                ModelVendedor e=new ModelVendedor(Vendedor);


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
