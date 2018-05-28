/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author aaron gevers
 */
public class Gesprek {
    
    private Medewerker medewerker;
    private School school;
    private Date datum;
    private String status;
    private String type;
    private Medewerker evaluator1;
    private Medewerker evaluator2;
    private String filepath;
    private int gesprekID;
    
    public Gesprek(Medewerker m, School s, Date d, String st, String t, Medewerker ev1, Medewerker ev2){
        medewerker = m;
        school = s;
        datum = d;
        status = st;
        type = t;
        evaluator1 = ev1;
        evaluator2 = ev2;
    }

    public Gesprek(Medewerker medewerker,School s, Date datum, String status, String type, Medewerker evaluator1, Medewerker evaluator2, String filepath) {
        this.medewerker = medewerker;
        this.datum = datum;
        this.status = status;
        this.type = type;
        this.evaluator1 = evaluator1;
        this.evaluator2 = evaluator2;
        this.filepath = filepath;
        this.school = s;
    }

    public String getFilepath() {
        return filepath;
    }

    public Medewerker getMedewerker() {
        return medewerker;
    }

    public School getSchool() {
        return school;
    }

    public Date getDatum() {
        return datum;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public Medewerker getEvaluator1() {
        return evaluator1;
    }

    public Medewerker getEvaluator2() {
        return evaluator2;
    }
    
    public void setGesprekID(int gesprekID) {
        this.gesprekID = gesprekID;
    }

    public int getGesprekID() {
        return gesprekID;
    }
}
