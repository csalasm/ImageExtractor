/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Migration;
import model.MigrationDirectory;

/**
 *
 * @author andresbailen93
 */
public class DemoMain {
    public static void main(String[] args) {
        MigrationDAO migration = new MigrationDAO();
        try {
            ArrayList<Migration> list = migration.migrated2();
            for(int i=0; i<list.size();i++){
                   System.out.println(list.get(i));
       }
        } catch (SQLException ex) {
            Logger.getLogger(DemoMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
