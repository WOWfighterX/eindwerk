/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author aaron gevers
 */
public class Melding {
    
    private int meldingID;
    private Date datum;
    private String info;
    private Medewerker medewerker;
    private Functie functie;
    private String type;
    
    public Melding(Date d, String i, Medewerker m, Functie f, String t, int id){
        datum = d;
        info = i;
        medewerker = m;
        functie = f;
        type = t;
        meldingID = id;
    }

    public Date getDatum() {
        return datum;
    }

    public String getInfo() {
        return info;
    }

    public Medewerker getMedewerker() {
        return medewerker;
    }

    public Functie getFunctie() {
        return functie;
    }

    public String getType() {
        return type;
    }

    public int getMeldingID() {
        return meldingID;
    }
    
}
