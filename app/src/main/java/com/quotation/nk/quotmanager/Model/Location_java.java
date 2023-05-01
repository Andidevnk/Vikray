package com.quotation.nk.quotmanager.Model;

public class Location_java {


    String id, name_godown,historydate;



    public  Location_java(){
    }

    public Location_java(String id, String name_godown, String historydate) {
        this.id = id;
        this.name_godown = name_godown;
        this.historydate = historydate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_godown() {
        return name_godown;
    }

    public void setName_godown(String name_godown) {
        this.name_godown = name_godown;
    }

    public String getHistorydate() {
        return historydate;
    }

    public void setHistorydate(String historydate) {
        this.historydate = historydate;
    }
}
