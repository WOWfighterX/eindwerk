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
    private String email;
    private Adres adres;
    private List functies = new ArrayList<Functie>();
    
    public Medewerker(int nr, String vn, String fn, String e, Adres a, Functie f){
        stamboeknr = nr;
        voornaam = vn;
        familienaam = fn;
        email = e;
        adres = a;
        functies.add(f);
    }
    
    public Medewerker(int nr, String vn, String fn, String e, Functie f){
        stamboeknr = nr;
        voornaam = vn;
        familienaam = fn;
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
                    AddFunctie(f);
                    return true;
            }
            
        }
        
        return false;
        
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
