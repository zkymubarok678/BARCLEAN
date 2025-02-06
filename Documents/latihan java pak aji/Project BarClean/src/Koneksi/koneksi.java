package Koneksi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi{
   private static Connection connection = null;
   public static Connection getKoneksi(){
       if(connection != null){
           return connection;
       }
       else{
           String dbUrl = "jdbc:mysql://localhost:3306/cleanbar";
           try{
               Class.forName("com.mysql.jdbc.Driver");
               connection = DriverManager.getConnection(dbUrl, "root", "");
               System.out.println("Koneksi Sukses");
           }
           catch(ClassNotFoundException | SQLException e){
               System.out.println("Koneksi Gagal: " + e);
           }
           return connection;
       } 
   }
    public static void main(String[] args) {
        getKoneksi();
    }
}
