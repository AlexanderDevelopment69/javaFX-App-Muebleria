package ConnectionMySQL;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMYSQL {
    Connection connection;
    public  Connection getConnection() throws SQLException {
        String dbName = "muebleria";
        String userName = "alexanderdeveloper";
        String password = "alexander123";
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://192.168.88.32/" + dbName,userName,password);
            //            String url ="jdbc:mysql://mysqledm2.mysql.database.azure.com:3306/muebleria?useSSL=true&requireSSL=false"; connection = DriverManager.getConnection(url, "alexanderAdmin@mysqledm2", "mysqlserver666#");
    }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);

//            e.printStackTrace();
        }


        return connection;
    }
}
