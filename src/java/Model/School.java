/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author aaron gevers
 */
public class School {
    
    private int instellingsnr;
    private String schoolnaam;
    private Adres adres;
    
    public School(int nr, String naam, Adres a){
        instellingsnr = nr;
        schoolnaam = naam;
        adres = a;
    }

    public int getInstellingsnr() {
        return instellingsnr;
    }

    public String getSchoolnaam() {
        return schoolnaam;
    }

    public Adres getAdres() {
        return adres;
    }
    
}
