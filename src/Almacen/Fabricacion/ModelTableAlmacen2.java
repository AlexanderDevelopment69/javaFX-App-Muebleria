package Almacen.Fabricacion;//package Almacen;
//
//import ConnectionMySQL.ConnectionMYSQL;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//public class ModelTableAlmacen2 extends ModelTableAlmacen {
//    PreparedStatement pst;
//    int Id;
//    String Producto,Marca,Tipo,Modelo,Plazas,Caracteristicas,Estado;
//    Double Precio,PrecioAdicional;
//
//    public ModelTableAlmacen2(int id, String producto, String marca, String tipo, String modelo, String plazas, String caracteristicas, String estado, Double precio, Double precioAdicional) {
//        Id = id;
//        Producto = producto;
//        Marca = marca;
//        Tipo = tipo;
//        Modelo = modelo;
//        Plazas = plazas;
//        Caracteristicas = caracteristicas;
//        Estado = estado;
//        Precio = precio;
//        PrecioAdicional = precioAdicional;
//    }
//
//    public ModelTableAlmacen2() {
//
//    }
//
//    public int getId() {
//        return Id;
//    }
//
//    public void setId(int id) {
//        Id = id;
//    }
//
//    public String getProducto() {
//        return Producto;
//    }
//
//    public void setProducto(String producto) {
//        Producto = producto;
//    }
//
//    public String getMarca() {
//        return Marca;
//    }
//
//    public void setMarca(String marca) {
//        Marca = marca;
//    }
//
//    public String getTipo() {
//        return Tipo;
//    }
//
//    public void setTipo(String tipo) {
//        Tipo = tipo;
//    }
//
//    public String getModelo() {
//        return Modelo;
//    }
//
//    public void setModelo(String modelo) {
//        Modelo = modelo;
//    }
//
//    public String getPlazas() {
//        return Plazas;
//    }
//
//    public void setPlazas(String plazas) {
//        Plazas = plazas;
//    }
//
//    public String getCaracteristicas() {
//        return Caracteristicas;
//    }
//
//    public void setCaracteristicas(String caracteristicas) {
//        Caracteristicas = caracteristicas;
//    }
//
//    public String getEstado() {
//        return Estado;
//    }
//
//    public void setEstado(String estado) {
//        Estado = estado;
//    }
//
//    public Double getPrecio() {
//        return Precio;
//    }
//
//    public void setPrecio(Double precio) {
//        Precio = precio;
//    }
//
//    public Double getPrecioAdicional() {
//        return PrecioAdicional;
//    }
//
//    public void setPrecioAdicional(Double precioAdicional) {
//        PrecioAdicional = precioAdicional;
//    }
//
//
//    public ObservableList<ModelTableAlmacen2> getAlmacen() throws ClassNotFoundException {
//        ObservableList<ModelTableAlmacen2> obs = FXCollections.observableArrayList();
//
//        try {
//
//            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
//            Connection connection = ConnectionClass.getConnection();
//
//            pst = connection.prepareStatement("select*from producto");
//
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                int Id=rs.getInt("Id");
////                String Id=rs.getString("Id");
//                String Producto=rs.getString("Producto");
//                String Marca=rs.getString("Marca");
//                String Tipo=rs.getString("Tipo");
//                String Modelo=rs.getString("Modelo");
//                String Plazas=rs.getString("Plazas");
//                String Caracteristicas=rs.getString("Caracteristicas");
//                Double Precio=rs.getDouble("Precio");
//                Double PrecioAdicional=rs.getDouble("PrecioAdicional");
//                String Estado=rs.getString("Estado");
//                ModelTableAlmacen2 c=new ModelTableAlmacen2(Id,Producto,Marca,Tipo,Modelo,Plazas,Caracteristicas,Estado,Precio,PrecioAdicional);
//                obs.add(c);
//            }
//        } catch (Exception ex) {
//            System.out.println("" + ex);
//        }
//        return obs;
//    }
//
//
//
//}
