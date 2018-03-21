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
public class Evaluator extends Persoon{
    
    private int stamboeknr;
    private String voornaam;
    private String familianaam;
    private Date geboorte;
    private String email;
    private Adres adres;
    private List functies = new ArrayList();
    
    public Evaluator(int nr, String vn, String fn, Date g, String e, Adres a, Functie f){
        stamboeknr = nr;
        voornaam = vn;
        familianaam = fn;
        geboorte = g;
        email = e;
        adres = a;
        functies.add(f);
    }

    public int getStamboeknr() {
        return stamboeknr;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilianaam() {
        return familianaam;
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

    public List getFuncties() {
        return functies;
    }
    
    protected void AddFunctie(Functie f){
        functies.add(f);
    }
    
}
