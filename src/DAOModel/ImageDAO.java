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
 * Clase que utiliza la funcion INSERTIMAGE de oracle para las inserciones
 * @author csalas
 */
public class ImageDAO {

    private Connection con = null;
    CallableStatement proc = null;
/**
 * Constructor de la clase ImagenDAO
 */
    public ImageDAO() {
        con = ConexionOrcl.connect();
    }
/**
 * Funcion que invoca la funcion que inserta una imagen en la base de datos relacional
 * @param image Objeto de la clase Image
 * @param path Objeto de la clase Path
 * @return Devuelve el entero identificador de la imagen
 * @throws SQLException 
 */
    public int addImage(Image image, Path path) throws SQLException {
        
        proc = con.prepareCall("{? = call INSERTIMAGE(?,?,?,?)}");
        proc.registerOutParameter(1, java.sql.Types.INTEGER);
        proc.setString(2, path.getPath()); //path
        proc.setString(3, image.getName()); //name
        proc.setString(4, image.getExtension()); //extension
        proc.setLong(5, image.getSize()); //size

        proc.execute();
        int id = proc.getInt(1); //Id from the new Image or existing image
        proc.close();
        return id;
    }

}