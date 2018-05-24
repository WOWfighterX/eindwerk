/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.*;
import Model.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aaron gevers
 */
public class AdminWriteService {
    
    private ReadDAO rdao;
    private WriteDAO wdao;

    public AdminWriteService() {
        rdao = new ReadDAO();
        wdao = new WriteDAO();
    }

    public void addMedewerker(Medewerker medewerker, String ww, String account, int sid) {
        
        int hulp;
        List sf = rdao.getSchoolFunctie();
        int sfid = 1;
        for(int i=0;i<sf.size();i++){
            hulp = Integer.parseInt((String) sf.get(i));
            if(sfid==hulp){
                sfid++;
                i=0;
            }
        }
        
        int accid = rdao.getAccountID();
        int fid = rdao.getFunctieID();
        
        try {
            wdao.addMedewerker(medewerker, ww, account, sfid, accid, fid, sid);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void addFunctie(String m, String f, int sid){
    
        int hulp = m.indexOf(" ");
        String vn = m.substring(0,hulp);
        String fn = m.substring(hulp+1);
        int mid = rdao.getMedewerkerID(vn, fn);
        
        int sfid = 1;
        List sf = rdao.getSchoolFunctie();
        for(int i=0;i<sf.size();i++){
            hulp = Integer.parseInt((String) sf.get(i));
            if(sfid==hulp){
                sfid++;
                i=0;
            }
        }
        
        Functie functie = new Functie(f);
        Medewerker medewerker = new Medewerker(mid, vn, fn);
        
        int fid = rdao.getFunctieID();
        
        try {
            wdao.addFunctie(functie, medewerker, sfid, fid, sid);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void removeFunctie(String naam, String fnaam){
        
        int hulp = naam.indexOf(" ");
        String vn = naam.substring(0,hulp);
        String fn = naam.substring(hulp+1);
        
        try {
            wdao.removeFunctie(vn, fn, fnaam);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void veranderEvaluator(int eid, String medewerker) {
        
        
        int hulp = medewerker.indexOf(" ");
        String mvn = medewerker.substring(0,hulp);
        String mfn = medewerker.substring(hulp+1);
        
        try {
            wdao.evaluatorVeranderen(eid, mvn, mfn);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void veranderRecht(String medewerker, String recht) {
        
        int hulp = medewerker.indexOf(" ");
        String mvn = medewerker.substring(0,hulp);
        String mfn = medewerker.substring(hulp+1);
        
        String r = " IS NULL";
        if(recht.equals("Admin")){
            r = " = 1";
        }
        
        try {
            wdao.rechtenVeranderen(mvn, mfn, r);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void addSchool(School s) {
        
        int aid = 1;
        List ad = rdao.getAdressen();
        for(int i=0;i<ad.size();i++){
            int hulp = Integer.parseInt((String) ad.get(i));
            if(aid==hulp){
                aid++;
                i=0;
            }
        }
        
        try {
            wdao.schoolToevoegen(s, aid);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void addMedewerker(int sid) {
        
        try {
            wdao.schoolVerwijderen(sid);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
