package com.quotation.nk.quotmanager.Model;

/**
 * Created by Nk on 10/31/2018.
 */

public class CallDetails {

    String id,customer_name,model_name,phone_number,Email;
    public  CallDetails(){

    }

    public CallDetails(String id, String customer_name, String model_name, String phone_number, String email) {
        this.id = id;
        this.customer_name = customer_name;
        this.model_name = model_name;
        this.phone_number = phone_number;
        this.Email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
