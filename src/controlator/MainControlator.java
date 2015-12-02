/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlator;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author csalas
 */
public class MainControlator {
    public static void main(String[] args) {
        try {
            SearchControlator secontr=new SearchControlator();
        } catch (IOException ex) {
            Logger.getLogger(MainControlator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
