/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import model.Image;
import model.Path;

/**
 *
 * @author csalas
 */
public class ImageDAO {

    private Connection con = null;
    CallableStatement proc = null;

    public ImageDAO() {
        con = ConexionOrcl.connect();
    }

    public int addImage(Image image, Path path) throws SQLException {
        
        proc = con.prepareCall("{? = call INSERTIMAGE(?,?,?,?)}");
        proc.registerOutParameter(1, java.sql.Types.INTEGER);
        proc.setString(1, path.getPath()); //path
        proc.setString(2, image.getName()); //name
        proc.setString(3, image.getExtension()); //extension
        proc.setInt(3, image.getSize()); //size

        proc.execute();
        int id = proc.getInt(1); //Id from the new Image or existing image
        proc.close();
        return id;
    }

}
