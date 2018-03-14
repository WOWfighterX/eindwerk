/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author aaron gevers
 */
public class Melding {
    
    private Date datum;
    private String info;
    private Persoon medewerker;
    private Functie functie;
    
    public Melding(Date d, String i, Persoon m, Functie f){
        datum = d;
        info = i;
        medewerker = m;
        functie = f;
    }
    
}
