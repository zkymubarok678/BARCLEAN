package DAO;
import Model.user;
import static Koneksi.koneksi.getKoneksi;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;

/**
 *
 * @author Ojan
 */
public class userDAO {
    Connection kon;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<userDAO> ListUser;
    user u;
    
    public userDAO(){
        kon = getKoneksi();
    }
     public void simpanData(user u){
        String qry;
        try{
            ps = kon.prepareStatement("SELECT * FROM user WHERE nama = ?");
            ps.setString(1, u.getNama());
            rs = ps.executeQuery();
            if(rs.next()){
                qry = "INSERT INTO user (password,level) VALUES (?,?)";
            }else{
                qry = "UPDATE user set Password=?, level=? WHERE nama=?";
            }
            ps = kon.prepareStatement(qry);
            ps.setString(1, u.getNama());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getLevel());

            // Execute the INSERT or UPDATE query
            ps.executeUpdate();
            System.out.println("Data berhasil disimpan.");
           
        }
        catch(SQLException e){
            System.out.println("Error: " + e);
        } 
    }  
    
    
}
