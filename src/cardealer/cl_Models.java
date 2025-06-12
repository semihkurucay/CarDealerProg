/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealer;

/**
 *
 * @author semih
 */
public class cl_Models {

    private int modelID = -1;
    private int model_brand_ID = -1;
    private String modelName = "";
    private String model_brand_Name = "";

    public cl_Models(int modelID, String modelName) {
        this.modelID = modelID;
        this.modelName = modelName;
    }

    public cl_Models() {

    }

    //GET
    public int getModelID() {
        return modelID;
    }

    public int getModel_brand_ID() {
        return model_brand_ID;
    }

    public String getModelName() {
        return modelName;
    }

    public String getModel_brand_Name() {
        return model_brand_Name;
    }

    //SET
    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public void setModel_brand_ID(int model_brand_ID) {
        this.model_brand_ID = model_brand_ID;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setModel_brand_Name(String model_brand_Name) {
        this.model_brand_Name = model_brand_Name;
    }

    @Override
    public String toString() {
        return modelName;
    }
}
