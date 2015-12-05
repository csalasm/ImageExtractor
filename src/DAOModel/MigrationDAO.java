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
 * Clase que utilizamos para la Migracion de datos entre OracleBD y MongoDB
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
/**
 * Funcion que recorre la base de datos relacional y extrae la informacion 
 * @return ArrayList de objetos Migration con la informacion de la base de datos
 * @throws SQLException 
 */
    public ArrayList<Migration> migrate() throws SQLException {
        Migration migration = null;
        ArrayList<Value_Label> vl_list = new ArrayList<>();
        ArrayList<Migration> migration_list = new ArrayList<>();
        ArrayList<MigrationDirectory> md_list = new ArrayList<>();
        String nameDirectory = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        
        ps1 = con.prepareStatement("SELECT ID_IMAGEN as idimage, NOMBRE as name, EXTENSION as extension, TAMANO as sizes, RUTA.PATH as pat FROM IMAGEN,RUTA WHERE IMAGEN.ID_RUTA=RUTA.ID_RUTA");
        rs = ps1.executeQuery();
        
        while (rs.next()) { // Para cada imagen
            md_list = new ArrayList<>();
            ps2 = con.prepareStatement("SELECT DIRECTORIO.NOMBRE AS DIRECTORIO, IMAGEN_ETIQUETA.VALOR as valor, ETIQUETA.NOMBRE AS name, ETIQUETA.ID_ETIQUETA as idEtiqueta FROM IMAGEN_ETIQUETA, ETIQUETA, DIRECTORIO WHERE ID_IMAGEN=? AND ETIQUETA.ID_ETIQUETA=IMAGEN_ETIQUETA.ID_ETIQUETA AND ETIQUETA.ID_DIRECTORIO = DIRECTORIO.ID_DIRECTORIO ORDER BY DIRECTORIO");
            ps2.clearParameters();
            ps2.setInt(1, rs.getInt("idimage"));
            rs2 = ps2.executeQuery();
            vl_list = new ArrayList<>();
            nameDirectory = null;
            while (rs2.next()) { // Para cada etiqueta de la imagen
                Value_Label valueLabel = new Value_Label(rs2.getString("valor"), rs2.getString("name"));                
                if (nameDirectory == null) {
                    nameDirectory = rs2.getString("directorio");
                    vl_list.add(valueLabel);
                } else {
                    if (nameDirectory.equals(rs2.getString("directorio")))
                        vl_list.add(valueLabel);
                    else {
                        MigrationDirectory mdobj = new MigrationDirectory(nameDirectory, vl_list);
                        md_list.add(mdobj);
                        vl_list = new ArrayList<>();
                        nameDirectory = rs2.getString("directorio");
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
