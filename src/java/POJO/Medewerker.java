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
public class Medewerker implements Serializable{
    
    private int Stamboeknr;
    private String Voornaam;
    private String Familienaam;
    private String email;
    private int AccountID;
    private int AdresID;
    private int admin;

    public Medewerker() {
    }

    public void setStamboeknr(int Stamboeknr) {
        this.Stamboeknr = Stamboeknr;
    }

    public void setVoornaam(String Voornaam) {
        this.Voornaam = Voornaam;
    }

    public void setFamilienaam(String Familienaam) {
        this.Familienaam = Familienaam;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public void setAdresID(int AdresID) {
        this.AdresID = AdresID;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getStamboeknr() {
        return Stamboeknr;
    }

    public String getVoornaam() {
        return Voornaam;
    }

    public String getFamilienaam() {
        return Familienaam;
    }

    public String getEmail() {
        return email;
    }

    public int getAccountID() {
        return AccountID;
    }

    public int getAdresID() {
        return AdresID;
    }

    public int getAdmin() {
        return admin;
    }
    
}
