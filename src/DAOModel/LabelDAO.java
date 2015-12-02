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
import model.Image_label;
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

   
     public void addMetaData(Image image, Directory dir, Label label, Image_label imageLabel) throws SQLException {
      
        proc = con.prepareCall("{call INSERTMETADATA(?,?,?,?)}");
        proc.setInt(1, image.getIdImage()); 
        proc.setString(2, dir.getName()); 
        proc.setString(3,label.getValue());
        proc.setString(4, imageLabel.getValue()); 
        proc.execute();  
        proc.close();
     
}

}