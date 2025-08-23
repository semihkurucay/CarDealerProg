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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author semih
 */
public class sl_Users {

    private db_SqlConnection sConn = new db_SqlConnection();
    private Connection conn = null;
    private Statement stt = null;
    private PreparedStatement pstt = null;
    private ResultSet rs = null;

    public void tbl_List(JTable table) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select usr_ID,usr_NameSurname from tbl_Users");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getString("usr_ID"), rs.getString("usr_NameSurname")});
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Users.tbl_List sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Users.tbl_List - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public cl_Users getUserInfo(String id) {
        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("select * from tbl_Users where usr_ID=?");
            pstt.setString(1, id);
            rs = pstt.executeQuery();

            while (rs.next()) {
                return new cl_Users(rs.getString("usr_ID"), rs.getString("usr_NameSurname"), rs.getString("usr_TaxOffice"), rs.getString("usr_Phone"), rs.getString("usr_Mail"), rs.getString("usr_City"), rs.getString("usr_District"), rs.getString("usr_Address"));
            }

            conn.close();
            pstt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Users.isThereUser sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Users.isThereUser - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        return new cl_Users();
    }

    public boolean isThereUser(String id) {
        boolean isThere = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("select * from tbl_Users where usr_ID=?");
            pstt.setString(1, id);
            rs = pstt.executeQuery();

            while (rs.next()) {
                isThere = true;
            }

            conn.close();
            pstt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Users.isThereUser sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Users.isThereUser - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        return isThere;
    }
    
    public boolean isThereMail(String id){
        boolean isThere = false;
        
        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("select * from tbl_Users where usr_ID=?");
            pstt.setString(1, id);
            rs = pstt.executeQuery();

            while (rs.next()) {
                if(rs.getString("usr_Mail").length() > 0){
                    isThere = true;
                }
            }

            conn.close();
            pstt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Users.isThereMail sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Users.isThereMail - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return isThere;
    }

    public boolean add(cl_Users user) {
        boolean isComplate = false;
        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("insert into tbl_Users (usr_ID,usr_NameSurname,usr_TaxOffice,usr_Phone,usr_Mail,usr_City,usr_District,usr_Address) values (?,?,?,?,?,?,?,?)");

            pstt.setString(1, user.getUserID());
            pstt.setString(2, user.getUserName_Surname());
            pstt.setString(3, user.getUserTaxOffice());
            pstt.setString(4, user.getUserPhone());
            pstt.setString(5, user.getUserMail());
            pstt.setString(6, user.getUserCity());
            pstt.setString(7, user.getUserDistrict());
            pstt.setString(8, user.getUserAddress());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Users.add sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Users.add - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        return isComplate;
    }

    public boolean update(cl_Users user) {
        boolean isComplate = false;
        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_Users set usr_NameSurname=?,usr_TaxOffice=?,usr_Phone=?,usr_Mail=?,usr_City=?,usr_District=?,usr_Address=? where usr_ID=?");

            pstt.setString(1, user.getUserName_Surname());
            pstt.setString(2, user.getUserTaxOffice());
            pstt.setString(3, user.getUserPhone());
            pstt.setString(4, user.getUserMail());
            pstt.setString(5, user.getUserCity());
            pstt.setString(6, user.getUserDistrict());
            pstt.setString(7, user.getUserAddress());
            pstt.setString(8, user.getUserID());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Users.update sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Users.update - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        return isComplate;
    }

    public boolean delete(cl_Users user) {
        boolean isComplate = false;
        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("delete from tbl_Users where usr_ID=?)");

            pstt.setString(1, user.getUserID());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Users.delete sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Users.delete - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        return isComplate;
    }
}
