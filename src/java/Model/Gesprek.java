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
public class Gesprek {
    
    private School school;
    private Date datum;
    private String status;
    private String type;
    private Persoon evaluator1;
    private Persoon evaluator2;
    
    public Gesprek(School s, Date d, String st, String t, Persoon ev1, Persoon ev2){
        school = s;
        datum = d;
        status = st;
        type = t;
        evaluator1 = ev1;
        evaluator2 = ev2;
    }
    
}
