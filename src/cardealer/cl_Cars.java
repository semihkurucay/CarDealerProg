/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealer;

/**
 *
 * @author semih
 */
public class cl_Cars {

    //private int carID = -1;
    private String carVIN = "";
    private int carBrands = -1;
    private int carModels = -1;
    private String carPacket = "";
    private String carYear = "";
    private String carKm = "";
    private String carPlate = "";
    private String carPrice = "";
    private String carWebLink = "";
    private boolean carReport = false;
    private String carComment = "";

    //GET
    public String getCarVIN() {
        return carVIN;
    }

    public int getCarBrands() {
        return carBrands;
    }

    public int getCarModels() {
        return carModels;
    }

    public String getCarPacket() {
        return carPacket;
    }

    public String getCarYear() {
        return carYear;
    }

    public String getCarKm() {
        return carKm;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public String getCarWebLink() {
        return carWebLink;
    }

    public boolean getCarReport() {
        return carReport;
    }

    public String getCarComment() {
        return carComment;
    }

    //SET
    public void setCarVIN(String carVIN) {
        this.carVIN = carVIN;
    }

    public void setCarBrands(int carBrands) {
        this.carBrands = carBrands;
    }

    public void setCarModels(int carModels) {
        this.carModels = carModels;
    }

    public void setCarPacket(String carPacket) {
        this.carPacket = carPacket;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public void setCarKm(String carKm) {
        this.carKm = carKm;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public void setCarWebLink(String carWebLink) {
        this.carWebLink = carWebLink;
    }

    public void setCarReport(boolean carReport) {
        this.carReport = carReport;
    }

    public void setCarComment(String carComment) {
        this.carComment = carComment;
    }
}
