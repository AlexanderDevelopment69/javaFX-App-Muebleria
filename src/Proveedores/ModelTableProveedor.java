package Proveedores;

import Almacen.AgregarProducto.ModelTableProducto;
import ConnectionMySQL.ConnectionMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModelTableProveedor {
    String Ruc,Empresa,Direccion,Celular,Correo;
    PreparedStatement pst;

    public ModelTableProveedor(String ruc, String empresa, String direccion, String celular,String correo) {
        Ruc = ruc;
        Empresa = empresa;
        Direccion = direccion;
        Celular = celular;
        Correo=correo;
    }

    public ModelTableProveedor() {

    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String ruc) {
        Ruc = ruc;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public ObservableList<ModelTableProveedor> getModelProveedor() throws ClassNotFoundException {
        ObservableList<ModelTableProveedor> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select *from proveedor");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Ruc=rs.getString("proRuc");
                Empresa=rs.getString("proNombre");
                Direccion=rs.getString("proDireccion");
                Celular=rs.getString("proCelular");
                Correo=rs.getString("proCorreo");

                ModelTableProveedor c=new ModelTableProveedor(Ruc,Empresa,Direccion,Celular,Correo);
                obs.add(c);
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }
    public ObservableList<ModelTableProveedor> getBuscadors (String valor) throws ClassNotFoundException {

        ObservableList<ModelTableProveedor> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("select proveedor.proRuc,proveedor.proNombre,proveedor.proDireccion,proveedor.proCelular from proveedor  where proRuc like '%"+valor+"%' or proNombre like '%"+valor+"%' or proCelular like '%"+valor+"%' order by proRuc asc");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Ruc= rs.getString("proRuc");
                 Empresa = rs.getString("proNombre");
                 Direccion = rs.getString("proDireccion");
                 Celular = rs.getString("proCelular");
                 Correo=rs.getString("proCorreo");




                ModelTableProveedor c = new ModelTableProveedor(Ruc,Empresa,Direccion,Celular,Correo);

//                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
                obs.add(c);
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }
}
