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
public class Account implements Serializable{
    private int AccountID;
    private String Accountnaam;
    private String Wachtwoord;
    private int actief;

    public Account() {
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public void setAccountnaam(String Accountnaam) {
        this.Accountnaam = Accountnaam;
    }

    public void setWachtwoord(String Wachtwoord) {
        this.Wachtwoord = Wachtwoord;
    }

    public void setActief(int actief) {
        this.actief = actief;
    }

    public int getAccountID() {
        return AccountID;
    }

    public String getAccountnaam() {
        return Accountnaam;
    }

    public String getWachtwoord() {
        return Wachtwoord;
    }

    public int getActief() {
        return actief;
    }
    
}
