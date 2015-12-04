/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Migration;
import model.MigrationDirectory;
import model.Value_Label;

/**
 *
 * @author andresbailen93
 */
public class MigrationDAO {

    private Connection con = null;
    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    PreparedStatement ps3 = null;

    /**
     * Constructor por defecto que instancia la conexion con la base de datos.
     */
    public MigrationDAO() {

        con = ConexionOrcl.connect();
    }

    public ArrayList<Migration> migrate() throws SQLException {
        Migration migration = null;
        ArrayList<Value_Label> vl_list = new ArrayList<>();
        ArrayList<Migration> migration_list = new ArrayList<>();
        ArrayList<MigrationDirectory> md_list = new ArrayList<>();
        String nameDirectory = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        
        ps1 = con.prepareStatement("SELECT ID_IMAGEN as idimage, NOMBRE as name, EXTENSION as extension, TAMANO as sizes, RUTA.PATH as pat FROM IMAGEN,RUTA WHERE IMAGEN.ID_RUTA=RUTA.ID_RUTA");
        rs = ps1.executeQuery();
        
        while (rs.next()) { // Para cada imagen
            md_list = new ArrayList<>();
            ps2 = con.prepareStatement("SELECT IMAGEN_ETIQUETA.VALOR as valor, ETIQUETA.NOMBRE AS name, ETIQUETA.ID_ETIQUETA as idEtiqueta FROM IMAGEN_ETIQUETA, ETIQUETA WHERE ID_IMAGEN=? AND ETIQUETA.ID_ETIQUETA=IMAGEN_ETIQUETA.ID_ETIQUETA");
            ps2.clearParameters();
            ps2.setInt(1, rs.getInt("idimage"));
            rs2 = ps2.executeQuery();
            vl_list = new ArrayList<>();
            nameDirectory = null;
            while (rs2.next()) { // Para cada etiqueta de la imagen
                Value_Label valueLabel = new Value_Label(rs2.getString("valor"), rs2.getString("name"));
                int directoriId = rs2.getInt("idEtiqueta");
                ps3 = con.prepareStatement("SELECT DIRECTORIO.NOMBRE as name FROM DIRECTORIO, ETIQUETA WHERE DIRECTORIO.ID_DIRECTORIO=ETIQUETA.ID_DIRECTORIO AND ETIQUETA.ID_ETIQUETA=?");
                ps3.clearParameters();
                ps3.setInt(1, directoriId);
                rs3 = ps3.executeQuery();
                rs3.next();
                if (nameDirectory == null) {
                    nameDirectory = rs3.getString("name");
                    vl_list.add(valueLabel);
                } else {
                    if (nameDirectory.equals(rs3.getString("name")))
                        vl_list.add(valueLabel);
                    else {
                        MigrationDirectory mdobj = new MigrationDirectory(nameDirectory, vl_list);
                        md_list.add(mdobj);
                        vl_list = new ArrayList<>();
                        nameDirectory = rs3.getString("name");
                    }    
                }
                
                // Agregamos directorios y etiquetas de cada imagen
            }
            MigrationDirectory mdobj = new MigrationDirectory(nameDirectory, vl_list);
            md_list.add(mdobj);
            // Agregamos migración para cada imagen
            migration = new Migration(rs.getInt("idimage"), rs.getString("name"), rs.getInt("sizes"), rs.getString("extension"), rs.getString("pat"), md_list);
            migration_list.add(migration);
        }
        
        return migration_list;
    }
}
