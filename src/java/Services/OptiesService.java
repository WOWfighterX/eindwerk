/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.WriteDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aaron gevers
 */
public class OptiesService {
    
    private WriteDAO wdao;

    public OptiesService() {
        wdao = new WriteDAO();
    }

    public void veranderWachtwoord(String nieuw, int nr) {
        try {
            wdao.veranderWachtwoord(nieuw, nr);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(OptiesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
