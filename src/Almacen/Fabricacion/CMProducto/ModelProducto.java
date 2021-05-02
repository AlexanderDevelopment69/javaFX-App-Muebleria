package Almacen.Fabricacion.CMProducto;

import Almacen.Fabricacion.CMProveedor.ModelProveedor;
import ConnectionMySQL.ConnectionMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModelProducto {

    PreparedStatement pst;
    String Nombre;

    public ModelProducto(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public ModelProducto() {

    }

    @Override
    public String toString() {
        return Nombre;
    }

    public ObservableList<ModelProducto> getCMProducto()  {
        ObservableList<ModelProducto> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select productos.producto from productos");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
//                int CatId=rs.getInt("catId");

                Nombre=rs.getString("producto");
                ModelProducto e=new ModelProducto(Nombre);

                obs.add(e);
            }
            connection.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }

}
