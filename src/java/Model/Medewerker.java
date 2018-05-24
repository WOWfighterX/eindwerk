/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aaron gevers
 */
public class Medewerker extends Persoon{
    
    private int stamboeknr;
    private String voornaam;
    private String familienaam;
    private Date geboorte;
    private String email;
    private Adres adres;
    private List functies = new ArrayList<Functie>();
    
    public Medewerker(int nr, String vn, String fn, Date d, String e, Adres a, Functie f){
        stamboeknr = nr;
        voornaam = vn;
        familienaam = fn;
        geboorte = d;
        email = e;
        adres = a;
        functies.add(f);
    }
    
    public Medewerker(int nr, String vn, String fn, Date d, String e, Functie f){
        stamboeknr = nr;
        voornaam = vn;
        familienaam = fn;
        geboorte = d;
        email = e;
        functies.add(f);
    }
    
    public Medewerker(int nr, String vn, String fn){
        stamboeknr = nr;
        voornaam = vn;
        familienaam = fn;
    }
    
    public boolean IsAanwezig(Medewerker m, Functie f) {
        
        if(m.getVoornaam().equals(voornaam)){
            if(m.getFamilienaam().equals(familienaam)){
                if(m.getGeboorte().equals(geboorte)){
                    AddFunctie(f);
                    return true;
                }
            }
            
        }
        
        return false;
        
    }
    
    public String getStringDate(){
        
        int jaar = geboorte.getYear();
        int maand = geboorte.getMonth();
        int dag = geboorte.getDay();
        String hulp = "" + jaar + maand + dag;
        return hulp;
        
    }
    
    public void AddFunctie(Functie f){
        functies.add(f);
    }

    public int getStamboeknr() {
        return stamboeknr;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public Date getGeboorte() {
        return geboorte;
    }

    public String getEmail() {
        return email;
    }

    public Adres getAdres() {
        return adres;
    }

    public List<Functie> getFuncties() {
        return functies;
    }
    
}
