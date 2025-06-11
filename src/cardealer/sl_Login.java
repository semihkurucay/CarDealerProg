/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author semih
 */
public class sl_Login {
    db_SqlConnection sConn = new db_SqlConnection();
    Connection conn = null;
    Statement stt = null;
    ResultSet rs = null;
    
    public boolean isLogin(String username, String password){
        boolean login = false;
        try{
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select lgn_Username,lgn_Password from tbl_Login where lgn_Username COLLATE Latin1_General_CS_AS='"+username+"' and lgn_Password COLLATE Latin1_General_CS_AS='"+password+"'");
            
            while(rs.next()){
                login = true;
            }
            
            conn.close();
            stt.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_Login.isLogin sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Login.isLogin - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        return login;
    }
}
