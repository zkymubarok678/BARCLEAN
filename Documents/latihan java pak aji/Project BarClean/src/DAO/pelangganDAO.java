package DAO;
import Model.Pelanggan;
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

public class pelangganDAO {
    Connection kon;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<pelangganDAO> ListPelanggan;
    Pelanggan pelanggan;
    
    public pelangganDAO(){
        kon = getKoneksi();
    }
     
     public ArrayList<pelangganDAO> getListPelanggan(){
        try{
            ListPelanggan = new ArrayList<>();
            ps = kon.prepareStatement("SELECT * FROM pelanggan ");
            rs = ps.executeQuery();
            rs.beforeFirst();
            while(rs.next()){
                pelanggan = new Pelanggan();
                pelanggan.setId_pelanggan(rs.getString("id_pelanggan"));
                pelanggan.setNama(rs.getString("nama"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setTelepon(rs.getString("alamat"));
                
               // ListPelanggan.add(pelanggan);
            }
        }
        catch (SQLException se) {
            System.out.println("Error : "+se);
        }
        return ListPelanggan;
    }
     
      public Pelanggan getSiswaByNis(String id_pelanggan){
        Pelanggan pel = new Pelanggan();
        try{
            String qry = "select * from pelanggan where id_pelanggan=?";
           ps = kon.prepareStatement(qry);
           ps.setString(1, id_pelanggan);
           rs = ps.executeQuery();
           if (rs.next()){
               pel.setId_pelanggan(rs.getString("id_pelanggan"));
               pel.setNama(rs.getString("nama"));
               pel.setAlamat(rs.getString("alamat"));
               pel.setTelepon(rs.getString("telepon"));
              
           }
           else{
               pel = new Pelanggan();
           }
        }catch(SQLException se){
            System.out.println(se);
        }
        return pel;
    }
    
    public void simpanData(Pelanggan pel){
        String qry;
        try{
            ps = kon.prepareStatement("SELECT * FROM pelanggan WHERE id_pelanggan = ?");
            ps.setString(1, pel.getId_pelanggan());
            rs = ps.executeQuery();
            if(rs.next()){
                qry = "INSERT INTO pelanggan(nama,alamat,telepon) VALUES (?,?,?)";
            }else{
                qry = "UPDATE pelanggan set nama=?, alamat=?, telepon=?, WHERE id_pelanggan=?";
            }
            ps = kon.prepareStatement(qry);
            ps.setString(1, pel.getNama());
            ps.setString(2, pel.getAlamat());
            ps.setString(3, pel.getTelepon());
            ps.setString(4, pel.getId_pelanggan());

            // Execute the INSERT or UPDATE query
            ps.executeUpdate();
            System.out.println("Data berhasil disimpan.");
           
        }
        catch(SQLException e){
            System.out.println("Error: " + e);
        } 
    }  
     public void addPelanggan(String id_pelanggan, String nama, String alamat,String telepon){
        String query = "insert into siswa (id_pelanggan, nama, alamat,telepon) values (?, ?, ?, ?)";

        try {
            ps = kon.prepareStatement(query);
            ps.setString(1, id_pelanggan);
            ps.setString(2, nama);
            ps.setString(3, alamat);
            ps.setString(4, telepon);
            ps.executeUpdate();
            System.out.println("New data has been added!");
        } catch (SQLException e) {
            System.out.println("Error : "+e);
        }
    }

    public void deletePelanggan(String id_pelanggan){
        String query = "delete from pelanggan where id_pelanggan=?";
        try {
            ps = kon.prepareStatement(query);
            ps.setString(1, id_pelanggan);
            ps.executeUpdate();
            System.out.println("Data has been removed!");
        } catch (SQLException e) {
            System.out.println("Data not found");
        }
    }

    public void updatePelanggan(String id_pelanggan, String nama, String alamat, String telepon){
        String query = "update pelanggan set nama=?, alamat=?, telepon=? where id_pelanggan=?";
        try {
            ps = kon.prepareStatement(query);
            ps.setString(1, nama);
            ps.setString(2, alamat);
            ps.setString(3, telepon);
            ps.setString(4, id_pelanggan);
            int check = ps.executeUpdate();
            if (check > 0)System.out.println("Data has been updated!");
            else System.out.println("Data not found");
        } catch (SQLException e) {
            System.out.println("Error : "+e);
        }
    }
    
}

