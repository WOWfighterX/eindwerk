/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author aaron gevers
 */
public class Melding implements Serializable{
    
    private int MeldingID;
    private int SchoolfunctieID;
    private String type;
    private Date Datum;
    private String extra;

    public Melding() {
    }

    public void setMeldingID(int MeldingID) {
        this.MeldingID = MeldingID;
    }

    public void setSchoolfunctieID(int SchoolfunctieID) {
        this.SchoolfunctieID = SchoolfunctieID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDatum(java.util.Date Datum) {
        this.Datum = convertDate(Datum);
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getMeldingID() {
        return MeldingID;
    }

    public int getSchoolfunctieID() {
        return SchoolfunctieID;
    }

    public String getType() {
        return type;
    }

    public java.util.Date getDatum() {
        return Datum;
    }

    public String getExtra() {
        return extra;
    }
    
    private java.sql.Date convertDate(java.util.Date d){
        return new java.sql.Date(d.getTime());
    }
}
