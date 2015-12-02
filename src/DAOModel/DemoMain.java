/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOModel;

/**
 *
 * @author andresbailen93
 */
public class DemoMain {
    public static void main(String[] args) {
        MigrationDAO migration = new MigrationDAO();
        migration.migrated();
    }
}
