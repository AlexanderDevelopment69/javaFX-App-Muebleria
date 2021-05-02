package Users.AdministrationUsers;


import ConnectionMySQL.ConnectionMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ModelTableUsers {
    PreparedStatement pst;
    String Dni,Nombre,Contrasena,Edad,Correo,Domicilio,Celular;

    public ModelTableUsers(String dni, String nombre, String contrasena, String edad, String correo, String domicilio, String celular) {
        Dni = dni;
        Nombre = nombre;
        Contrasena = contrasena;
        Edad = edad;
        Correo = correo;
        Domicilio = domicilio;
        Celular = celular;
    }

    public ModelTableUsers() {

    }

    public String getDni() {
        return Dni;
    }



    public String getNombre() {
        return Nombre;
    }



    public String getContrasena() {
        return Contrasena;
    }


    public String getEdad() {
        return Edad;
    }



    public String getCorreo() {
        return Correo;
    }


    public String getDomicilio() {
        return Domicilio;
    }



    public String getCelular() {
        return Celular;
    }



    public ObservableList<ModelTableUsers> getUsers() throws ClassNotFoundException {
        ObservableList<ModelTableUsers> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select*from usuario ");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Dni=rs.getString("dni");
                Nombre=rs.getString("nombre");
                Contrasena=rs.getString("contrasena");
                Edad=rs.getString("edad");
                Correo=rs.getString("correo");
                Domicilio=rs.getString("domicilio");
                Celular=rs.getString("celular");
                ModelTableUsers c=new ModelTableUsers(Dni,Nombre,Contrasena,Edad,Correo,Domicilio,Celular);
                obs.add(c);
            }
            connection.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }

}
