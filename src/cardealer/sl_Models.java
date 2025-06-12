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
public class sl_Models {

    private db_SqlConnection sConn = new db_SqlConnection();
    private Connection conn = null;
    private Statement stt = null;
    private PreparedStatement pstt = null;
    private ResultSet rs = null;

    public void tbl_List(JTable table) {
        cl_Models model = new cl_Models();
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select mdl_ID,brn_Name,mdl_brn_ID,mdl_Name from tbl_Models join tbl_Brands on tbl_Models.mdl_brn_ID=tbl_Brands.brn_ID");

            while (rs.next()) {
                model.setModel_brand_Name(rs.getString("brn_Name"));
                model.setModelID(rs.getInt("mdl_ID"));
                model.setModelName(rs.getString("mdl_Name"));
                model.setModel_brand_ID(rs.getInt("mdl_brn_ID"));

                mTable.addRow(new Object[]{model.getModelID(), model.getModel_brand_Name(), model.getModelName(), model.getModel_brand_ID()});
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Models.tbl_List sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Models.tbl_List - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<cl_Models> get_Models(int brn_ID) {
        ArrayList<cl_Models> models = new ArrayList<cl_Models>();

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Models where mdl_brn_ID=" + brn_ID);

            while (rs.next()) {
                models.add(new cl_Models(rs.getInt("mdl_ID"), rs.getString("mdl_Name")));
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Models.get_Models sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Models.get_Models - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return models;
    }

    public String get_Model(int id) {
        String model = "";
        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Models where mdl_ID=" + id);

            while (rs.next()) {
                model = rs.getString("mdl_Name");
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Models.get_Model sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Models.get_Model - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return model;
    }

    public boolean add(cl_Models model) {
        boolean isComplate = false;
        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("insert into tbl_Models (mdl_brn_ID,mdl_Name) values (?,?)");

            pstt.setInt(1, model.getModel_brand_ID());
            pstt.setString(2, model.getModelName());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Models.add sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Models.add - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        return isComplate;
    }

    public boolean update(cl_Models model) {
        boolean isComplate = false;
        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_Models set mdl_brn_ID=?,mdl_Name=? where mdl_ID=?");

            pstt.setInt(1, model.getModel_brand_ID());
            pstt.setString(2, model.getModelName());
            pstt.setInt(3, model.getModelID());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Models.add sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Models.add - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        return isComplate;
    }

    public boolean delete(cl_Models model) {
        boolean isComplate = false;
        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("delete from tbl_Models where mdl_ID=?");

            pstt.setInt(1, model.getModelID());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Models.delete sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Models.delete - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        return isComplate;
    }
}
