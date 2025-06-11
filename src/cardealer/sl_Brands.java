/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author semih
 */
public class sl_Brands {
    db_SqlConnection sConn = new db_SqlConnection();
    Connection conn = null;
    Statement stt = null;
    PreparedStatement pstt = null;
    ResultSet rs = null;
    
    public void tbl_List(JTable table){
        cl_Brands brnd = new cl_Brands();
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);
        
        try{
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Brands");
            
            while(rs.next()){
                brnd.setBrandID(rs.getInt("brn_ID"));
                brnd.setBrandName(rs.getString("brn_Name"));
                
                mTable.addRow(new Object[]{brnd.getBrandID(),brnd.getBrandName()});
            }
            
            conn.close();
            stt.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_Brands.tbl_List sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Brands.tbl_List - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<cl_Brands> get_Brands(){
        ArrayList<cl_Brands> brands = new ArrayList<cl_Brands>();
        
        try{
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Brands");
            
            while(rs.next()){
                brands.add(new cl_Brands(rs.getInt("brn_ID"), rs.getString("brn_Name")));
            }
            
            conn.close();
            stt.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_Brands.get_Brands sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Brands.get_Brands - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return brands;
    }
    
    public String get_Brand(int id){
        String brand = "";
        try{
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Brands where brn_ID=" + id);
            
            while(rs.next()){
                brand = rs.getString("brn_Name");
            }
            
            conn.close();
            stt.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_Brands.get_Brand sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Brands.get_Brand - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return brand;
    }
    
    public boolean add(cl_Brands brand){
        boolean isComplate = false;
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("insert into tbl_Brands (brn_Name) values (?) ");
            
            pstt.setString(1, brand.getBrandName());
            pstt.executeUpdate();
            
            conn.close();
            pstt.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_Brands.add sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Brands.add - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        return isComplate;
    }
    
    public boolean update(cl_Brands brand){
        boolean isComplate = false;
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_Brands set brn_Name=? where brn_ID=? ");
            
            pstt.setString(1, brand.getBrandName());
            pstt.setInt(2, brand.getBrandID());
            pstt.executeUpdate();
            
            conn.close();
            pstt.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_Brands.update sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Brands.update - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        return isComplate;
    }
    
    public boolean delete(cl_Brands brand){
        boolean isComplate = false;
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("delete from tbl_Brands where brn_ID=?");
            
            pstt.setInt(1, brand.getBrandID());
            pstt.executeUpdate();
            
            conn.close();
            pstt.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_Brands.update sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Brands.update - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        return isComplate;
    }
}
