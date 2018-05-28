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
    private boolean admin;
    private boolean evaluator;
    private int Stamboeknr;

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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isEvaluator() {
        return evaluator;
    }

    public void setEvaluator(boolean evaluator) {
        this.evaluator = evaluator;
    }

    public int getStamboeknr() {
        return Stamboeknr;
    }

    public void setStamboeknr(int Stamboeknr) {
        this.Stamboeknr = Stamboeknr;
    }
    
    
    
}
