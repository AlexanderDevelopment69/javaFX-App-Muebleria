package ConnectionMySQL;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMYSQL {
    Connection connection;
    public  Connection getConnection() {
        String dbName = "muebleria";
        String userName = "alexanderdeveloper";
        String password = "alexander123";
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://192.168.88.40/" + dbName,userName,password);
    }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
//            e.printStackTrace();
        }


        return connection;
    }
}
