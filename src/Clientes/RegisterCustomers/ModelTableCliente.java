package Clientes.RegisterCustomers;

import Almacen.AgregarProducto.ModelTableProducto;
import ConnectionMySQL.ConnectionMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;

public class ModelTableCliente {
    PreparedStatement pst;
    String Nombres,Apellidos,Direccion,Sexo,Dni,Ruc,Celular,Edad,Correo;
    String Fecha;
    int Id;
//Introducir datos
//    public ModelTableCliente(String nombres, String apellidos, String direccion, String sexo, String dni, String ruc, String celular,String edad,String correo) {
//        Nombres = nombres;
//        Apellidos = apellidos;
//        Direccion = direccion;
//        Sexo = sexo;
//        Dni = dni;
//        Ruc = ruc;
//        Celular = celular;
//        Edad=edad;
//        Correo=correo;
//    }
    public ModelTableCliente(String nombres, String apellidos, String direccion, String sexo, String dni, String ruc, String celular,String fecha,String correo) {
        Nombres = nombres;
        Apellidos = apellidos;
        Direccion = direccion;
        Sexo = sexo;
        Dni = dni;
        Ruc = ruc;
        Celular = celular;
        Fecha=fecha;
        Correo=correo;
    }

    //Mostrar Cliente en Tabla
    public ModelTableCliente() {

    }
//Recibir datos
//    public ModelTableCliente(int id, String nombres, String apellidos, String direccion, String sexo, String dni, String ruc, String celular,String edad,String correo) {
//        Id=id;
//        Nombres = nombres;
//        Apellidos = apellidos;
//        Direccion = direccion;
//        Sexo = sexo;
//        Dni = dni;
//        Ruc = ruc;
//        Celular = celular;
//        Edad=edad;
//        Correo=correo;
//    }
    public ModelTableCliente(int id, String nombres, String apellidos, String direccion, String sexo, String dni, String ruc, String celular,String fecha,String correo) {
        Id=id;
        Nombres = nombres;
        Apellidos = apellidos;
        Direccion = direccion;
        Sexo = sexo;
        Dni = dni;
        Ruc = ruc;
        Celular = celular;
        Fecha=fecha;
        Correo=correo;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String ruc) {
        Ruc = ruc;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

//    public String getEdad() {
//        return Edad;
//    }
//
//    public void setEdad(String edad) {
//        Edad = edad;
//    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public ObservableList<ModelTableCliente> getCliente() throws ClassNotFoundException {
        ObservableList<ModelTableCliente> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select *,DATE_FORMAT(cliente.cliFecha,'%d/%m/%Y')as cliFechas from cliente");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Id=rs.getInt("cliCodigo");
                Nombres=rs.getString("cliNombres");
                Apellidos=rs.getString("cliApellidos");
                Direccion=rs.getString("cliDireccion");
                Sexo=rs.getString("cliSexo");
//                Edad=rs.getString("cliEdad");
                Fecha=rs.getString("cliFechas");
                Dni=rs.getString("cliDNI");
                Ruc=rs.getString("cliRUC");
                Celular=rs.getString("cliCelular");
                Correo=rs.getString("cliCorreo");

//                ModelTableCliente c=new ModelTableCliente(Id,Nombres,Apellidos,Direccion,Sexo,Dni,Ruc,Celular,Edad,Correo);
                ModelTableCliente c=new ModelTableCliente(Id,Nombres,Apellidos,Direccion,Sexo,Dni,Ruc,Celular,Fecha,Correo);
                obs.add(c);
            }
            connection.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }


//Buscador de CLientes

    public ObservableList<ModelTableCliente> getBuscadors (String valor) throws ClassNotFoundException {

        ObservableList<ModelTableCliente> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("select * from cliente  where cliDni like '%"+valor+"%' or cliRuc like '%"+valor+"%' or cliNombres like '%"+valor+"%' or cliApellidos like '%"+valor+"%' order by cliCodigo asc");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Id = rs.getInt("cliCodigo");
                Nombres = rs.getString("cliNombres");
                Apellidos = rs.getString("cliApellidos");
                Direccion = rs.getString("cliDireccion");
                Sexo = rs.getString("cliSexo");
//                Edad = rs.getString("cliEdad");
                Fecha=rs.getString("cliFecha");
                Dni = rs.getString("cliDni");
                Ruc = rs.getString("cliRuc");
                Celular=rs.getString("cliCelular");
                Correo=rs.getString("cliCorreo");
//                ModelTableCliente c = new ModelTableCliente(Id,Nombres,Apellidos,Direccion,Sexo,Dni,Ruc,Celular,Edad,Correo);
                ModelTableCliente c = new ModelTableCliente(Id,Nombres,Apellidos,Direccion,Sexo,Dni,Ruc,Celular,Fecha,Correo);
//                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
                obs.add(c);
            }
            connection.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }


}
