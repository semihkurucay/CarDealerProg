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

/**
 *
 * @author semih
 */
public class sl_Mail {

    private db_SqlConnection sConn = new db_SqlConnection();
    private Connection conn = null;
    private ResultSet rs = null;
    private Statement stt = null;
    private PreparedStatement pstt = null;

    public String[] getMailSetting() {
        String[] setting = new String[6];

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select * from tbl_MailSetting");

            while (rs.next()) {
                setting[0] = rs.getString("mal_MailAddress");
                setting[1] = rs.getString("mal_Password");
                setting[2] = rs.getString("mal_Port");
                setting[3] = rs.getString("mal_Host");
                setting[4] = String.valueOf(rs.getBoolean("mal_Auth"));
                setting[5] = String.valueOf(rs.getBoolean("mal_Starttls"));
            }

            conn.close();
            stt.close();
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Mail.getMailSetting sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Mail.getMailSetting - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return setting;
    }

    public String[] getMailMessage() {
        String[] message = new String[3];

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select * from tbl_MailMessage");

            while (rs.next()) {
                message[0] = rs.getString("mes_Title");
                message[1] = rs.getString("mes_Front");
                message[2] = rs.getString("mes_Back");
            }

            conn.close();
            stt.close();
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Mail.getMailMessage sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Mail.getMailMessage - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return message;
    }

    public boolean updateMailSetting(String mailAddres, String mailPassword, String mailPort, String mailHost, boolean mailAuth, boolean mailStarttls) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_MailSetting set mal_MailAddress=?,mal_Password=?,mal_Port=?,mal_Host=?,mal_Auth=?,mal_Starttls=?");
            pstt.setString(1, mailAddres);
            pstt.setString(2, mailPassword);
            pstt.setString(3, mailPort);
            pstt.setString(4, mailHost);
            pstt.setBoolean(5, mailAuth);
            pstt.setBoolean(6, mailStarttls);
            pstt.executeUpdate();

            conn.close();
            pstt.close();
            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Mail.updateMailSetting sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Mail.updateMailSetting - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean updateMailMessage(String title, String front, String back) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_MailMessage set mes_Title=?,mes_Front=?,mes_Back=?");
            pstt.setString(1, title);
            pstt.setString(2, front);
            pstt.setString(3, back);
            pstt.executeUpdate();

            conn.close();
            pstt.close();
            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Mail.updateMailMessage sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Mail.updateMailMessage - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }
}
