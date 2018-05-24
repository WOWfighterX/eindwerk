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
public class Adres {
    
    private String straat;
    private String stad;
    private int postcode;
    
    public Adres(String str, String st, int p){
        straat = str;
        stad = st;
        postcode = p;
    }

    public String getStraat() {
        return straat;
    }

    public String getStad() {
        return stad;
    }

    public int getPostcode() {
        return postcode;
    }
    
}
