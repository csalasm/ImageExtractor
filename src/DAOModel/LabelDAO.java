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
 * Clase que invoca el procedimiento que inserta los metadatos en la base de
 * datos relacional
 *
 * @author andresbailen93
 */
public class LabelDAO {

    private Connection con = null;
    CallableStatement proc = null;

    /**
     * Constructor de la clase LabelDAO
     */
    public LabelDAO() {
        con = ConexionOrcl.connect();
    }
/**
 * Metodo que invoca el procedimiento almacenado en la base de datos relacional que inserta un metadato para una determinada imagen
 * @param image Objeto de la clase Image
 * @param dir Objeto de la clase Directory (Directorio etiqueta)
 * @param label Objeto de la clase Label
 * @param imageLabel Objeto de la clase Image_label
 * @throws SQLException 
 */
    public void addMetaData(Image image, Directory dir, Label label, Image_label imageLabel) throws SQLException {

        proc = con.prepareCall("{call INSERTMETADATA(?,?,?,?)}");
        proc.setInt(1, image.getIdImage());
        proc.setString(2, dir.getName());
        proc.setString(3, label.getValue());
        proc.setString(4, imageLabel.getValue());
        proc.execute();
        proc.close();

    }

}
