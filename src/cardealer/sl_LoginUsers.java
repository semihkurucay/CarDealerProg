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
public class sl_LoginUsers {
    db_SqlConnection sConn = new db_SqlConnection();
    Connection conn = null;
    Statement stt = null;
    PreparedStatement pstt = null;
    ResultSet rs = null;
    
    public void tbl_List(JTable table){
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);
        
        int id = -1;
        String userName = "";
        String password= "";
        
        try{
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select lgn_ID,lgn_Username,lgn_Password from tbl_Login");
            
            while(rs.next()){
                id = rs.getInt("lgn_ID");
                userName = rs.getString("lgn_Username");
                password = rs.getString("lgn_Password");
                
                mTable.addRow(new Object[]{id,userName,password});
            }
            
            conn.close();
            stt.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_LoginUsers.tbl_List sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_LoginUsers.tbl_List - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public int getLoginUserCount(){
        int count = -1;
        
        try{
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select count(*) as userCount from tbl_Login");
            
            while(rs.next()){
                count = rs.getInt("userCount");
            }
            
            conn.close();
            stt.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_LoginUsers.getLoginUserCount sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_LoginUsers.getLoginUserCount - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return count;
    }
    
    public boolean add(String userName, String password){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("insert into tbl_Login (lgn_Username,lgn_Password) values (?,?)");
            
            pstt.setString(1, userName);
            pstt.setString(2, password);
            pstt.executeUpdate();
            
            conn.close();
            pstt.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_LoginUsers.add sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_LoginUsers.add - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
    
    public boolean update(int id, String userName, String password){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_Login set lgn_Username=?,lgn_Password=? where lgn_ID=?");
            
            pstt.setString(1, userName);
            pstt.setString(2, password);
            pstt.setInt(3, id);
            pstt.executeUpdate();
            
            conn.close();
            pstt.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_LoginUsers.update sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_LoginUsers.update - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
    
    public boolean delete(int id){
        boolean isComplate = false;
        
        if(getLoginUserCount() > 1){
            try{
                conn = sConn.getConnection();
                pstt = conn.prepareStatement("delete from tbl_Login where lgn_ID=?");
            
                pstt.setInt(1, id);
                pstt.executeUpdate();
            
                conn.close();
                pstt.close();
            
                isComplate = true;
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "sl_LoginUsers.delete sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_LoginUsers.delete - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "En az 1 kullanıcı kalmalıdır sistemde", "Geçersiz Silim", JOptionPane.ERROR_MESSAGE);
        }
        return isComplate;
    }
}
