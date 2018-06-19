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
public class Schoolfunctie implements Serializable{
    
    private int SchoolfunctieID;
    private int FunctieID;
    private int SchoolID;
    private int MedewerkerID;
    private int evaluator;
    private int actief;

    public Schoolfunctie() {
    }

    public void setSchoolfunctieID(int SchoolfunctieID) {
        this.SchoolfunctieID = SchoolfunctieID;
    }

    public void setFunctieID(int FunctieID) {
        this.FunctieID = FunctieID;
    }

    public void setSchoolID(int SchoolID) {
        this.SchoolID = SchoolID;
    }

    public void setMedewerkerID(int MedewerkerID) {
        this.MedewerkerID = MedewerkerID;
    }

    public void setEvaluator(int evaluator) {
        this.evaluator = evaluator;
    }

    public void setActief(int actief) {
        this.actief = actief;
    }

    public int getSchoolfunctieID() {
        return SchoolfunctieID;
    }

    public int getFunctieID() {
        return FunctieID;
    }

    public int getSchoolID() {
        return SchoolID;
    }

    public int getMedewerkerID() {
        return MedewerkerID;
    }

    public int getEvaluator() {
        return evaluator;
    }

    public int getActief() {
        return actief;
    }
    
    
}
