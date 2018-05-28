/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.*;
import Model.*;
import java.sql.SQLException;
import java.util.Date;
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

    public void addMedewerker(Medewerker medewerker, String ww, String account, int sid, Adres a) {
        
        int sfid = rdao.getSchoolFunctieID();
        int accid = rdao.getAccountID();
        int fid = rdao.getFunctieID();
        int aid = rdao.getAdresID();
        
        try {
            wdao.addMedewerker(medewerker, ww, account, sfid, accid, fid, sid, a, aid);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
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
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void removeFunctie(int mid, String fnaam){
        
        
        try {
            wdao.removeFunctie(mid, fnaam);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void veranderEvaluator(int eid, String medewerker) {
        
        
        int hulp = medewerker.indexOf(" ");
        String mvn = medewerker.substring(0,hulp);
        String mfn = medewerker.substring(hulp+1);
        
        try {
            wdao.evaluatorVeranderen(eid, mvn, mfn);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
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
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void addSchool(School s) {
        
        int aid = rdao.getAdresID();
        
        try {
            wdao.schoolToevoegen(s, aid);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int mid = rdao.getMedewerkerID();
        Date d = new Date(200,1,1);
        
        Adres a = new Adres("straat", "stad", 0000);
        Functie f = new Functie("admin");
        Medewerker medewerker = new Medewerker(mid, "admin", "", d, "admin@admin.com", a, f);
        
        String account = "Admin";
        String ww = "Admin123!";
        
        addMedewerker(medewerker, ww, account, aid, a);
        
    }

    public void addMedewerker(int sid) {
        
        try {
            wdao.schoolVerwijderen(sid);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void veranderAcount(String mnaam, String status){
        
        int hulp = mnaam.indexOf(" ");
        String mvn = mnaam.substring(0,hulp);
        String mfn = mnaam.substring(hulp+1);
        
        int mid = rdao.getMedewerkerID(mvn, mfn);
        
        Medewerker m = new Medewerker(mid, mvn, mfn);
        
        String str = " = NULL ";
        if(status.equals("Actief")){
            str = " = 1 ";
        }
        
        try {
            wdao.VeranderAccountStatus(m, str);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AdminWriteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
