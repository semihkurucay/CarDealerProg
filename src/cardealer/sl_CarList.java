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
public class sl_CarList {
    private db_SqlConnection sConn = new db_SqlConnection();
    private Connection conn = null;
    private Statement stt = null;
    private PreparedStatement pstt = null;
    private ResultSet rs = null;
    
    public void tbl_List(JTable table){
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);
        
        //int id = -1;
        String vin = "", plate = "", brn_Name = "", mdl_Name = "";
        
        try{
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select car_VIN,dtl_Plate,brn_Name,mdl_Name from tbl_Cars join tbl_CarDetial on tbl_Cars.car_VIN=tbl_CarDetial.dtl_car_VIN join tbl_Brands on tbl_Cars.car_brn_ID=tbl_Brands.brn_ID join tbl_Models on tbl_Cars.car_mdl_ID=tbl_Models.mdl_ID where car_IsActive = 1");
            
            while(rs.next()){
                vin = rs.getString("car_VIN");
                plate = rs.getString("dtl_Plate");
                brn_Name = rs.getString("brn_Name");
                mdl_Name = rs.getString("mdl_Name");
                
                mTable.addRow(new Object[]{vin,plate,brn_Name,mdl_Name});
            }
            
            conn.close();
            stt.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_CarList.tbl_List sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_CarList.tbl_List - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getCarBrandModel(String vin){
        String get = "";
        
        try{
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select car_VIN,dtl_Plate,dtl_Packet,brn_Name,mdl_Name from tbl_Cars join tbl_CarDetial on tbl_Cars.car_VIN=tbl_CarDetial.dtl_car_VIN join tbl_Brands on tbl_Cars.car_brn_ID=tbl_Brands.brn_ID join tbl_Models on tbl_Cars.car_mdl_ID=tbl_Models.mdl_ID where car_VIN='"+vin+"'");
            
            while(rs.next()){
                get = rs.getString("brn_Name") + " " + rs.getString("mdl_Name") + " - " + rs.getString("dtl_Packet") + " - " + rs.getString("dtl_Plate");
            }
            
            conn.close();
            stt.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_CarList.getCarBrandModel sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_CarList.getCarBrandModel - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return get;
    }
    
    public cl_Cars getCarInfo(String vin){
        cl_Cars car = new cl_Cars();
        
        try{
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select car_VIN,car_brn_ID,car_mdl_ID,car_Year,dtl_Packet,dtl_KM,dtl_Plate,dtl_Price,dtl_WebUrl,dtl_Report,dtl_Comment from tbl_Cars join tbl_CarDetial on tbl_Cars.car_VIN=tbl_CarDetial.dtl_car_VIN where car_IsActive=1 AND car_VIN='" + vin + "'");
            
            while(rs.next()){
                car.setCarVIN(rs.getString("car_VIN"));
                car.setCarBrands(rs.getInt("car_brn_ID"));
                car.setCarModels(rs.getInt("car_mdl_ID"));
                car.setCarYear(rs.getString("car_Year"));
                car.setCarPacket(rs.getString("dtl_Packet"));
                car.setCarKm(rs.getString("dtl_KM"));
                car.setCarPlate(rs.getString("dtl_Plate"));
                car.setCarPrice(rs.getString("dtl_Price"));
                car.setCarWebLink(rs.getString("dtl_WebUrl"));
                car.setCarReport(rs.getBoolean("dtl_Report"));
                car.setCarComment(rs.getString("dtl_Comment"));
            }
            
            conn.close();
            stt.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_CarList.qetCarInfo sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_CarList.qetCarInfo - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return car;
    }
    
    
    
    public int getCarActiveCount(){
        int totalCar = -1;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("Select count(*) as totalCar from tbl_Cars where car_IsActive=1");
            rs = pstt.executeQuery();
            
            if(rs.next()){
                totalCar = rs.getInt("totalCar");
            }
            
            conn.close();
            pstt.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_CarList.getCarActiveCount sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_CarList.getCarActiveCount - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return totalCar;
    }
    
    public int getCarAllCount(){
        int totalCar = -1;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("Select count(*) as totalCar from tbl_Cars");
            rs = pstt.executeQuery();
            
            if(rs.next()){
                totalCar = rs.getInt("totalCar");
            }
            
            conn.close();
            pstt.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_CarList.getCarAllCount sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_CarList.getCarAllCount - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return totalCar;
    }
    
    public boolean isThereVIN(String vin){
        boolean isThere = false;
        
        try{
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select * from tbl_Cars where car_VIN='" + vin + "'");
            
            while(rs.next()){
                isThere = true;
                
            }
            
            conn.close();
            stt.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_CarList.isThereVIN sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_CarList.isThereVIN - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return isThere;
    }
    
    public boolean isThereCar(String vin, int brn_ID, int mdl_ID, String year){
        boolean isThere = false;
        
        try{
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select * from tbl_Cars where car_VIN='" + vin + "' AND car_brn_ID=" + brn_ID + " AND car_mdl_ID=" + mdl_ID + " AND car_Year='" + year + "'");
            
            while(rs.next()){
                isThere = true;
                
            }
            
            conn.close();
            stt.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_CarList.isThereCar sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_CarList.isThereCar - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return isThere;
    }
    
    public boolean isThereStockCar(String vin){
        boolean isThere = false;
        
        try{
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select * from tbl_Cars where car_VIN='" + vin + "' AND car_IsActive=1");
            
            while(rs.next()){
                isThere = true;
                
            }
            
            conn.close();
            stt.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_CarList.isThereStockCar sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_CarList.isThereStockCar - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return isThere;
    }
    
    public boolean sell(cl_Cars car){
        boolean delCar = deleteCarDetail(car.getCarVIN());
        boolean setCar = carSetActive(car.getCarVIN(), false);
        
        if(delCar && setCar){
            return true;
        }else{
            if(delCar){
                addCarDetail(car);
            }
            if(setCar){
                carSetActive(car.getCarVIN(), true);
            }
            return false;
        }
    }
    
    public void cancelSell(cl_Cars car){
        addCarDetail(car);
        carSetActive(car.getCarVIN(), true);
    }
    
    public boolean buy(cl_Cars car){
        boolean addCar = addCar(car);
        boolean addCarDetail = addCarDetail(car);
        
        if(addCar && addCarDetail){
            return true;
        }else{
            if(addCar){
                deleteCar(car.getCarVIN());
            }
            if(addCarDetail){
                deleteCarDetail(car.getCarVIN());
            }
            return false;
        }
    }
    
    public void cancelBuy(cl_Cars car){
        deleteCar(car.getCarVIN());
        deleteCarDetail(car.getCarVIN());
    }
    
    public boolean setBuy(cl_Cars car){
        boolean addCarDetail = addCarDetail(car);
        boolean setCar = carSetActive(car.getCarVIN(), true);
        
        if(addCarDetail && setCar){
            return true;
        }else{
            if(addCarDetail){
                deleteCarDetail(car.getCarVIN());
            }
            if(setCar){
                carSetActive(car.getCarVIN(), false);
            }
            return false;
        }
    }
    
    public void cancelSetBuy(cl_Cars car){
        deleteCarDetail(car.getCarVIN());
        carSetActive(car.getCarVIN(), false);
    }
    
    public boolean addCarDetail(cl_Cars car){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("insert into tbl_CarDetial (dtl_car_VIN,dtl_Packet,dtl_KM,dtl_Plate,dtl_Price,dtl_WebUrl,dtl_Report,dtl_Comment) values (?,?,?,?,?,?,?,?)");
            pstt.setString(1, car.getCarVIN());
            pstt.setString(2, car.getCarPacket());
            pstt.setString(3, car.getCarKm());
            pstt.setString(4, car.getCarPlate());
            pstt.setString(5, car.getCarPrice());
            pstt.setString(6, car.getCarWebLink());
            pstt.setBoolean(7, car.getCarReport());
            pstt.setString(8, car.getCarComment());
            pstt.executeUpdate();
            
            conn.close();
            pstt.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_CarList.addCarDetail sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_CarList.addCarDetail - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
    
    public boolean updateCarDetail(cl_Cars car){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_CarDetial set dtl_Packet=?,dtl_KM=?,dtl_Plate=?,dtl_Price=?,dtl_WebUrl=?,dtl_Report=?,dtl_Comment=? where dtl_car_VIN=?");
            
            pstt.setString(1, car.getCarPacket());
            pstt.setString(2, car.getCarKm());
            pstt.setString(3, car.getCarPlate());
            pstt.setString(4, car.getCarPrice());
            pstt.setString(5, car.getCarWebLink());
            pstt.setBoolean(6, car.getCarReport());
            pstt.setString(7, car.getCarComment());
            pstt.setString(8, car.getCarVIN());
            pstt.executeUpdate();
            
            conn.close();
            pstt.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_CarList.updateCarDetail sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_CarList.updateCarDetail - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
    
    public boolean deleteCarDetail(String vin){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("delete from tbl_CarDetial where dtl_car_VIN=?");
            pstt.setString(1, vin);
            pstt.executeUpdate();
            
            conn.close();
            pstt.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_CarList.deleteCarDetail sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_CarList.deleteCarDetail - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
    
    public boolean carSetActive(String vin, boolean active){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_Cars set car_IsActive=? where car_VIN=?");
            pstt.setBoolean(1, active);
            pstt.setString(2, vin);
            pstt.executeUpdate();
            
            conn.close();
            pstt.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_CarList.carSetActive sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_CarList.carSetActive - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
    
    public boolean addCar(cl_Cars car){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("insert into tbl_Cars (car_VIN,car_brn_ID,car_mdl_ID,car_Year,car_IsActive) values (?,?,?,?,?)");
            
            pstt.setString(1, car.getCarVIN());
            pstt.setInt(2, car.getCarBrands());
            pstt.setInt(3, car.getCarModels());
            pstt.setString(4, car.getCarYear());
            pstt.setBoolean(5, true);
            pstt.executeUpdate();
            
            conn.close();
            pstt.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_CarList.addCar sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_CarList.addCar - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
    
    public boolean deleteCar(String vin){
        boolean isComplate = false;
        
        try{
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("delete from tbl_Cars where car_VIN=?");
            
            pstt.setString(1, vin);
            pstt.executeUpdate();
            
            conn.close();
            pstt.close();
            
            isComplate = true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "sl_CarList.deleteCar sınıfında hata ile karşılaşıldı. Hata Kodu : " + e.getErrorCode() + " - Hata Mesajı : " + e.getMessage(), "sl_CarList.deleteCar - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return isComplate;
    }
}
