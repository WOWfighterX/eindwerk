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
public class Gebruiker {
    
    private School school;
    private String gebruikersnaam;
    private String wachtwoord;

    public Gebruiker(School school, String gebruikersnaam, String wachtwoord) {
        this.school = school;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public School getSchool() {
        return school;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }
    
    
    
}
