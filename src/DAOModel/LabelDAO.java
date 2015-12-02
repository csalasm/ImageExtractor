/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import model.Directory;
import model.Image;
import model.Label;
import model.Path;

/**
 *
 * @author Asus
 */
public class LabelDAO {
   
    private Connection con = null;
    CallableStatement proc = null;

    public LabelDAO() {
        con = ConexionOrcl.connect();
    }

   
     public void addMetaData(Image image, Path path, Directory dir, Label label) throws SQLException {
      
        proc = con.prepareCall("{? = call INSERTMETADATA(?,?,?,?)}");
        this.proc.registerOutParameter(1, java.sql.Types.CHAR);
        proc.setInt(1, image.getIdImage()); //path
        proc.setString(2, dir.getName()); //name
        proc.setInt(3,label.getIdLabel()); //extension
        proc.setString(3, label.getValue()); //size
        proc.execute();  
        proc.close();
     
}

}