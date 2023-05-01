package com.quotation.nk.quotmanager.Model;

public class Full_Java {


    String id,modelnm,frameno,engineno,datesupdate,colornm;


    public  Full_Java(){

    }

    public Full_Java(String id, String modelnm, String frameno, String engineno, String datesupdate, String colornm) {
        this.id = id;
        this.modelnm = modelnm;
        this.frameno = frameno;
        this.engineno = engineno;
        this.datesupdate = datesupdate;
        this.colornm = colornm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelnm() {
        return modelnm;
    }

    public void setModelnm(String modelnm) {
        this.modelnm = modelnm;
    }

    public String getFrameno() {
        return frameno;
    }

    public void setFrameno(String frameno) {
        this.frameno = frameno;
    }

    public String getEngineno() {
        return engineno;
    }

    public void setEngineno(String engineno) {
        this.engineno = engineno;
    }

    public String getDatesupdate() {
        return datesupdate;
    }

    public void setDatesupdate(String datesupdate) {
        this.datesupdate = datesupdate;
    }

    public String getColornm() {
        return colornm;
    }

    public void setColornm(String colornm) {
        this.colornm = colornm;
    }
}
