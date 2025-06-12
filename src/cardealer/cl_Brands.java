/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealer;

/**
 *
 * @author semih
 */
public class cl_Brands {

    private int brandID = -1;
    private String brandName = "";

    public cl_Brands(int brandID, String brandName) {
        this.brandID = brandID;
        this.brandName = brandName;
    }

    public cl_Brands() {
    }

    //GET
    public int getBrandID() {
        return brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    //SET
    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return brandName;
    }
}
