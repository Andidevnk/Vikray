package com.quotation.nk.quotmanager.Model;

public class Model_java {


    String id, modelname,model_qunatity;

    public  Model_java(){

    }

    public Model_java(String id, String modelname, String model_qunatity) {
        this.id = id;
        this.modelname = modelname;
        this.model_qunatity = model_qunatity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getModel_qunatity() {
        return model_qunatity;
    }

    public void setModel_qunatity(String model_qunatity) {
        this.model_qunatity = model_qunatity;
    }
}
