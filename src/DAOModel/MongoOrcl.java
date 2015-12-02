/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOModel;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author csalas
 */
public class MongoOrcl {
    
    private static MongoClient con;
    final private static String url = "127.0.0.1";
    final private static int port = 27017;
    
    public static MongoClient connection() {
        if (con != null)
            return con;
        
        try {
            con = new MongoClient(url, port);
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(MongoOrcl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public void disconnect() {
        con.close();
        con = null;
    }
    
}
