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
public class Adres implements Serializable {
    
    private int AdresID;
    private String Straat;
    private int Postcode;
    private String Stad;

    public Adres() {
    }

    public void setAdresID(int AdresID) {
        this.AdresID = AdresID;
    }

    public void setStraat(String Straat) {
        this.Straat = Straat;
    }

    public void setPostcode(int Postcode) {
        this.Postcode = Postcode;
    }

    public void setStad(String Stad) {
        this.Stad = Stad;
    }

    public int getAdresID() {
        return AdresID;
    }

    public String getStraat() {
        return Straat;
    }

    public int getPostcode() {
        return Postcode;
    }

    public String getStad() {
        return Stad;
    }
    
}
