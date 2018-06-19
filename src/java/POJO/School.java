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
public class School implements Serializable{
    
    private int Instellingsnr;
    private String Schoolnaam;
    private int AdresID;
    private int actief;

    public School() {
    }

    public void setInstellingsnr(int Instellingsnr) {
        this.Instellingsnr = Instellingsnr;
    }

    public void setSchoolnaam(String Schoolnaam) {
        this.Schoolnaam = Schoolnaam;
    }

    public void setAdresID(int AdresID) {
        this.AdresID = AdresID;
    }

    public void setActief(int actief) {
        this.actief = actief;
    }

    public int getInstellingsnr() {
        return Instellingsnr;
    }

    public String getSchoolnaam() {
        return Schoolnaam;
    }

    public int getAdresID() {
        return AdresID;
    }

    public int getActief() {
        return actief;
    }
    
}
