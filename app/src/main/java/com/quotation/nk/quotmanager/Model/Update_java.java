package com.quotation.nk.quotmanager.Model;

public class Update_java {


    String id;
    String l_from;
    String l_to;
    String from;
    String to;
    String modell;
    String modelframe;
    String modelengine;
    String date;


    public  Update_java(){

    }

    public Update_java(String id, String l_from, String l_to, String from, String to, String modell, String modelframe, String modelengine, String date) {
        this.id = id;
        this.l_from = l_from;
        this.l_to = l_to;
        this.from = from;
        this.to = to;
        this.modell = modell;
        this.modelframe = modelframe;
        this.modelengine = modelengine;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getL_from() {
        return l_from;
    }

    public void setL_from(String l_from) {
        this.l_from = l_from;
    }

    public String getL_to() {
        return l_to;
    }

    public void setL_to(String l_to) {
        this.l_to = l_to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getModelframe() {
        return modelframe;
    }

    public void setModelframe(String modelframe) {
        this.modelframe = modelframe;
    }

    public String getModelengine() {
        return modelengine;
    }

    public void setModelengine(String modelengine) {
        this.modelengine = modelengine;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
