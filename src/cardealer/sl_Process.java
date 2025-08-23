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
public class sl_Process {

    private db_SqlConnection sConn = new db_SqlConnection();
    private Connection conn = null;
    private Statement stt = null;
    private PreparedStatement pstt = null;
    private ResultSet rs = null;
    private String _id = "!";

    public void tbl_List(JTable table) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select * from tbl_Process ORDER BY prc_ID DESC");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getInt("prc_ID"), rs.getString("prc_hst_ID"), rs.getString("prc_usr_ID"), rs.getString("prc_Price"), rs.getString("prc_Title")});
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Process.tbl_List sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Process.tbl_List - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void tbl_SearchList(JTable table, String _ref, String _user, String _vin) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("select prc_ID,prc_hst_ID,prc_usr_ID,prc_Title,prc_Price from tbl_Process join tbl_HistoryCar on tbl_Process.prc_hst_ID=tbl_HistoryCar.hst_ID join tbl_Users on tbl_Process.prc_usr_ID=tbl_Users.usr_ID join tbl_Cars on tbl_HistoryCar.hst_VIN=tbl_Cars.car_VIN join tbl_Brands on tbl_Cars.car_brn_ID=tbl_Brands.brn_ID join tbl_Models on tbl_Cars.car_mdl_ID=tbl_Models.mdl_ID where prc_hst_ID LIKE ? AND prc_usr_ID LIKE ? AND hst_VIN LIKE ? ORDER BY prc_ID DESC");
            pstt.setString(1, "%"+_ref+"%");
            pstt.setString(2, "%"+_user+"%");
            pstt.setString(3, "%"+_vin+"%");
            rs = pstt.executeQuery();

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getInt("prc_ID"), rs.getString("prc_hst_ID"), rs.getString("prc_usr_ID"), rs.getString("prc_Price"), rs.getString("prc_Title")});
            }

            conn.close();
            pstt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Process.tbl_SearchList sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Process.tbl_SearchList - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isThereID(String id) {
        boolean isThere = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("select * from tbl_HistoryCar where hst_ID=?");
            pstt.setString(1, id);
            rs = pstt.executeQuery();

            while (rs.next()) {
                isThere = true;
            }

            conn.close();
            pstt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Process.isThereID sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Process.isThereID - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isThere;
    }

    public String getID() {
        return _id;
    }

    public String getCarBrandModel(String vin) {
        String get = "";

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("select brn_Name,mdl_Name,hst_Packet,hst_Plate from tbl_Process join tbl_HistoryCar on tbl_Process.prc_hst_ID=tbl_HistoryCar.hst_ID join tbl_Users on tbl_Process.prc_usr_ID=tbl_Users.usr_ID join tbl_Cars on tbl_HistoryCar.hst_VIN=tbl_Cars.car_VIN join tbl_Brands on tbl_Cars.car_brn_ID=tbl_Brands.brn_ID join tbl_Models on tbl_Cars.car_mdl_ID=tbl_Models.mdl_ID where car_VIN=?");
            pstt.setString(1, vin);
            rs = pstt.executeQuery();

            while (rs.next()) {
                get = rs.getString("brn_Name") + " " + rs.getString("mdl_Name") + " - " + rs.getString("hst_Packet") + " - " + rs.getString("hst_Plate");
            }

            conn.close();
            pstt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Process.getCarBrandModel sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Process.getCarBrandModel - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return get;
    }

    public String[] getInformation(String vin) {
        String[] info = new String[15];

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("select prc_hst_ID,prc_Title,prc_Price,usr_ID,usr_NameSurname,car_VIN,brn_Name,mdl_Name,hst_Packet,car_Year,hst_Km,hst_Plate,hst_WebUrl,hst_Report,hst_Comment from tbl_Process join tbl_HistoryCar on tbl_Process.prc_hst_ID=tbl_HistoryCar.hst_ID join tbl_Users on tbl_Process.prc_usr_ID=tbl_Users.usr_ID join tbl_Cars on tbl_HistoryCar.hst_VIN=tbl_Cars.car_VIN join tbl_Brands on tbl_Cars.car_brn_ID=tbl_Brands.brn_ID join tbl_Models on tbl_Cars.car_mdl_ID=tbl_Models.mdl_ID where prc_hst_ID=?");
            pstt.setString(1, vin);
            rs = pstt.executeQuery();

            while (rs.next()) {
                info[0] = rs.getString("prc_hst_ID");
                info[1] = rs.getString("prc_Title");
                info[2] = rs.getString("usr_ID");
                info[3] = rs.getString("usr_NameSurname");
                info[4] = rs.getString("car_VIN");
                info[5] = rs.getString("brn_Name");
                info[6] = rs.getString("mdl_Name");
                info[7] = rs.getString("hst_Packet");
                info[8] = rs.getString("car_Year");
                info[9] = rs.getString("hst_Km");
                info[10] = rs.getString("hst_Plate");
                info[11] = rs.getString("prc_Price");
                info[12] = rs.getString("hst_WebUrl");
                info[13] = rs.getString("hst_Comment");
                if (rs.getBoolean("hst_Report")) {
                    info[14] = "VAR";
                } else {
                    info[14] = "YOK";
                }
            }

            conn.close();
            pstt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Process.isThereID sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Process.isThereID - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return info;
    }

    public String[] search(String ref, String user, String vin) {
        String[] info = new String[15];

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("select prc_hst_ID,prc_Title,prc_Price,usr_ID,usr_NameSurname,car_VIN,brn_Name,mdl_Name,hst_Packet,car_Year,hst_Km,hst_Plate,hst_WebUrl,hst_Report,hst_Comment from tbl_Process join tbl_HistoryCar on tbl_Process.prc_hst_ID=tbl_HistoryCar.hst_ID join tbl_Users on tbl_Process.prc_usr_ID=tbl_Users.usr_ID join tbl_Cars on tbl_HistoryCar.hst_VIN=tbl_Cars.car_VIN join tbl_Brands on tbl_Cars.car_brn_ID=tbl_Brands.brn_ID join tbl_Models on tbl_Cars.car_mdl_ID=tbl_Models.mdl_ID where prc_hst_ID LIKE ? AND prc_usr_ID LIKE ? AND hst_VIN LIKE ?");
            pstt.setString(1, ref);
            pstt.setString(2, user);
            pstt.setString(3, vin);
            rs = pstt.executeQuery();

            while (rs.next()) {
                info[0] = rs.getString("prc_hst_ID");
                info[1] = rs.getString("prc_Title");
                info[2] = rs.getString("usr_ID");
                info[3] = rs.getString("usr_NameSurname");
                info[4] = rs.getString("car_VIN");
                info[5] = rs.getString("brn_Name");
                info[6] = rs.getString("mdl_Name");
                info[7] = rs.getString("hst_Packet");
                info[8] = rs.getString("car_Year");
                info[9] = rs.getString("hst_Km");
                info[10] = rs.getString("hst_Plate");
                info[11] = rs.getString("prc_Price");
                info[12] = rs.getString("hst_WebUrl");
                info[13] = rs.getString("hst_Comment");
                if (rs.getBoolean("hst_Report")) {
                    info[14] = "VAR";
                } else {
                    info[14] = "YOK";
                }
            }

            conn.close();
            pstt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Process.isThereID sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Process.isThereID - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return info;
    }

    public boolean process(cl_Cars car, String user, String price, String title) {
        while (true) {
            if (!isThereID(_id = randomID.id())) {
                break;
            }
        }

        boolean addHistory = addHistoryCar(_id, car);
        boolean addProc = addProcess(_id, user, price, title);

        if (addHistory && addProc) {
            return true;
        } else {
            if (addHistory) {
                deleteHistoryCar(_id);
            }
            if (addProc) {
                deleteProcess(_id);
            }
            return false;
        }
    }

    public void cancelProcess(String id) {
        deleteProcess(id);
        deleteHistoryCar(id);
    }

    public boolean addHistoryCar(String id, cl_Cars car) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();

            pstt = conn.prepareStatement("insert into tbl_HistoryCar (hst_ID,hst_VIN,hst_Packet,hst_Km,hst_Plate,hst_Price,hst_WebUrl,hst_Report,hst_Comment) values (?,?,?,?,?,?,?,?,?)");
            pstt.setString(1, id);
            pstt.setString(2, car.getCarVIN());
            pstt.setString(3, car.getCarPacket());
            pstt.setString(4, car.getCarKm());
            pstt.setString(5, car.getCarPlate());
            pstt.setString(6, car.getCarPrice());
            pstt.setString(7, car.getCarWebLink());
            pstt.setBoolean(8, car.getCarReport());
            pstt.setString(9, car.getCarComment());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
            _id = id;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Process.addHistoryCar sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Process.addHistoryCar - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean addProcess(String id, String user, String price, String title) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();

            pstt = conn.prepareStatement("insert into tbl_Process (prc_hst_ID,prc_usr_ID,prc_Price,prc_Title) values (?,?,?,?)");
            pstt.setString(1, id);
            pstt.setString(2, user);
            pstt.setString(3, price);
            pstt.setString(4, title);
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
            _id = id;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Process.addProcess sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Process.addProcess - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public void deleteHistoryCar(String id) {
        try {
            conn = sConn.getConnection();

            pstt = conn.prepareStatement("delete from tbl_HistoryCar where hst_ID=?");
            pstt.setString(1, id);
            pstt.executeUpdate();

            conn.close();
            pstt.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Process.deleteHistoryCar sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Process.deleteHistoryCar - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteProcess(String id) {
        try {
            conn = sConn.getConnection();

            pstt = conn.prepareStatement("delete from tbl_Process where prc_hst_ID=?");
            pstt.setString(1, id);
            pstt.executeUpdate();

            conn.close();
            pstt.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Process.deleteProcess sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_Process.deleteProcess - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
