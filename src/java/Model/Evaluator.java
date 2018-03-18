/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
    private List functies;
    
    public Evaluator(int nr, String vn, String fn, Date g, String e, Adres a, Functie f){
        stamboeknr = nr;
        voornaam = vn;
        familianaam = fn;
        geboorte = g;
        email = e;
        adres = a;
        functies.add(f);
    }
    
    protected void AddFunctie(Functie f){
        functies.add(f);
    }
    
}
