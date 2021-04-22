package Clientes.RegisterCustomers;

import ConnectionMySQL.ConnectionMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModelTableCliente {
    PreparedStatement pst;
    String Nombres,Apellidos,Direccion,Sexo,Dni,Ruc,Celular,Edad,Correo;
    int Id;
//Introducir datos
    public ModelTableCliente(String nombres, String apellidos, String direccion, String sexo, String dni, String ruc, String celular,String edad,String correo) {
        Nombres = nombres;
        Apellidos = apellidos;
        Direccion = direccion;
        Sexo = sexo;
        Dni = dni;
        Ruc = ruc;
        Celular = celular;
        Edad=edad;
        Correo=correo;
    }

    //Mostrar Cliente en Tabla
    public ModelTableCliente() {

    }
//Recibir datos
    public ModelTableCliente(int id, String nombres, String apellidos, String direccion, String sexo, String dni, String ruc, String celular,String edad,String correo) {
        Id=id;
        Nombres = nombres;
        Apellidos = apellidos;
        Direccion = direccion;
        Sexo = sexo;
        Dni = dni;
        Ruc = ruc;
        Celular = celular;
        Edad=edad;
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

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

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

    public ObservableList<ModelTableCliente> getCliente() throws ClassNotFoundException {
        ObservableList<ModelTableCliente> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select *from cliente");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Id=rs.getInt("cliCodigo");
                Nombres=rs.getString("cliNombres");
                Apellidos=rs.getString("cliApellidos");
                Direccion=rs.getString("cliDireccion");
                Sexo=rs.getString("cliSexo");
                Edad=rs.getString("cliEdad");
                Dni=rs.getString("cliDNI");
                Ruc=rs.getString("cliRUC");
                Celular=rs.getString("cliCelular");
                Correo=rs.getString("cliCorreo");

                ModelTableCliente c=new ModelTableCliente(Id,Nombres,Apellidos,Direccion,Sexo,Dni,Ruc,Celular,Edad,Correo);

                obs.add(c);
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }




}
