package com.quotation.nk.quotmanager.Model;

/**
 * Created by Nk on 10/30/2018.
 */

public class Reports  {

    String id,customer_name,qua_date,nxt_folllow_date,book_date,model,notintrested,convertdate;




    public  Reports(){

    }

    public Reports( String id,String customer_name, String qua_date, String nxt_folllow_date, String book_date) {
        this.customer_name = customer_name;
        this.qua_date = qua_date;
        this.nxt_folllow_date = nxt_folllow_date;
        this.book_date = book_date;
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

    public String getQua_date() {
        return qua_date;
    }

    public void setQua_date(String qua_date) {
        this.qua_date = qua_date;
    }

    public String getNxt_folllow_date() {
        return nxt_folllow_date;
    }

    public void setNxt_folllow_date(String nxt_folllow_date) {
        this.nxt_folllow_date = nxt_folllow_date;
    }

    public String getBook_date() {
        return book_date;
    }

    public void setBook_date(String book_date) {
        this.book_date = book_date;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public String getNotintrested() {
        return notintrested;
    }

    public void setNotintrested(String notintrested) {
        this.notintrested = notintrested;
    }

    public String getConvertdate() {
        return convertdate;
    }

    public void setConvertdate(String convertdate) {
        this.convertdate = convertdate;
    }



}
