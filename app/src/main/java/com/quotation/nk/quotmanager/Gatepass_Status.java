package com.quotation.nk.quotmanager;

/**
 * Created by Nk on 22-Nov-18.
 */

public class Gatepass_Status {

    String id;
    String delivered_name;
    String delivered_date;
    String total_quantity_send;
    String delivered_name_from;
    String delivered_date_to;
    String location_from;
    String location_to;



    public  Gatepass_Status(){

    }

    public Gatepass_Status(String id, String delivered_name, String delivered_date, String total_quantity_send, String delivered_name_from, String delivered_date_to, String location_from, String location_to) {
        this.id = id;
        this.delivered_name = delivered_name;
        this.delivered_date = delivered_date;
        this.total_quantity_send = total_quantity_send;
        this.delivered_name_from = delivered_name_from;
        this.delivered_date_to = delivered_date_to;
        this.location_from = location_from;
        this.location_to = location_to;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelivered_name() {
        return delivered_name;
    }

    public void setDelivered_name(String delivered_name) {
        this.delivered_name = delivered_name;
    }

    public String getDelivered_date() {
        return delivered_date;
    }

    public void setDelivered_date(String delivered_date) {
        this.delivered_date = delivered_date;
    }

    public String getTotal_quantity_send() {
        return total_quantity_send;
    }

    public void setTotal_quantity_send(String total_quantity_send) {
        this.total_quantity_send = total_quantity_send;
    }

    public String getDelivered_name_from() {
        return delivered_name_from;
    }

    public void setDelivered_name_from(String delivered_name_from) {
        this.delivered_name_from = delivered_name_from;
    }

    public String getDelivered_date_to() {
        return delivered_date_to;
    }

    public void setDelivered_date_to(String delivered_date_to) {
        this.delivered_date_to = delivered_date_to;
    }
    public String getLocation_from() {
        return location_from;
    }

    public void setLocation_from(String location_from) {
        this.location_from = location_from;
    }

    public String getLocation_to() {
        return location_to;
    }

    public void setLocation_to(String location_to) {
        this.location_to = location_to;
    }
}
