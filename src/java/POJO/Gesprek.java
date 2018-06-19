/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author aaron gevers
 */
public class Gesprek implements Serializable{
    
    private int GesprekID;
    private int SchoolfunctieID;
    private Date Datum;
    private String Status;
    private String GesprekType;
    private int Evaluator1ID;
    private int Evaluator2ID;
    private String Filepath;

    public Gesprek() {
    }

    public void setGesprekID(int GesprekID) {
        this.GesprekID = GesprekID;
    }

    public void setSchoolfunctieID(int SchoolfunctieID) {
        this.SchoolfunctieID = SchoolfunctieID;
    }

    public void setDatum(java.util.Date Datum) {
        this.Datum = convertDate(Datum);
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setGesprekType(String GesprekType) {
        this.GesprekType = GesprekType;
    }

    public void setEvaluator1ID(int Evaluator1ID) {
        this.Evaluator1ID = Evaluator1ID;
    }

    public void setEvaluator2ID(int Evaluator2ID) {
        this.Evaluator2ID = Evaluator2ID;
    }

    public void setFilepath(String Filepath) {
        this.Filepath = Filepath;
    }

    public int getGesprekID() {
        return GesprekID;
    }

    public int getSchoolfunctieID() {
        return SchoolfunctieID;
    }

    public java.util.Date getDatum() {
        return Datum;
    }

    public String getStatus() {
        return Status;
    }

    public String getGesprekType() {
        return GesprekType;
    }

    public int getEvaluator1ID() {
        return Evaluator1ID;
    }

    public int getEvaluator2ID() {
        return Evaluator2ID;
    }

    public String getFilepath() {
        return Filepath;
    }
    
    private java.sql.Date convertDate(java.util.Date d){
        return new java.sql.Date(d.getTime());
    }
}
