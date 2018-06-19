/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;

/**
 *
 * @author aaron gevers
 */
public class Functie implements Serializable {
    
    private int FunctieID;
    private String Functienaam;

    public Functie() {
    }

    public void setFunctieID(int FunctieID) {
        this.FunctieID = FunctieID;
    }

    public void setFunctienaam(String Functienaam) {
        this.Functienaam = Functienaam;
    }

    public int getFunctieID() {
        return FunctieID;
    }

    public String getFunctienaam() {
        return Functienaam;
    }
    
}
