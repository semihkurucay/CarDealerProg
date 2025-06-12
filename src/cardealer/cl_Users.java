/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealer;

/**
 *
 * @author semih
 */
public class cl_Users {

    private String userID = "";
    private String userName_Surname = "";
    private String userTaxOffice = "";
    private String userPhone = "";
    private String userMail = "";
    private String userCity = "";
    private String userDistrict = "";
    private String userAddress = "";

    public cl_Users(String userID, String userName_Surname, String userTaxOffice, String userPhone, String userMail, String userCity, String userDistrict, String userAddress) {
        this.userID = userID;
        this.userName_Surname = userName_Surname;
        this.userTaxOffice = userTaxOffice;
        this.userPhone = userPhone;
        this.userMail = userMail;
        this.userCity = userCity;
        this.userDistrict = userDistrict;
        this.userAddress = userAddress;
    }

    public cl_Users() {

    }

    //GET
    public String getUserID() {
        return userID;
    }

    public String getUserName_Surname() {
        return userName_Surname;
    }

    public String getUserTaxOffice() {
        return userTaxOffice;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserMail() {
        return userMail;
    }

    public String getUserCity() {
        return userCity;
    }

    public String getUserDistrict() {
        return userDistrict;
    }

    public String getUserAddress() {
        return userAddress;
    }

    //SET
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName_Surname(String userName_Surname) {
        this.userName_Surname = userName_Surname;
    }

    public void setUserTaxOffice(String userTaxOffice) {
        this.userTaxOffice = userTaxOffice;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public void setUserDistrict(String userDistrict) {
        this.userDistrict = userDistrict;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
