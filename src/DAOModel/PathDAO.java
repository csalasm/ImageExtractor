/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Directory;

/**
 *
 * @author Asus
 */
public class PathDAO {
     private Connection con = null;
    PreparedStatement pstmt = null;

    public PathDAO() {
        con = ConexionOrcl.connect();
    }
    
    public void insertaDirectorio(Directory dir) throws SQLException {

    
    }    
}