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

    public ArrayList<Migration> migrated() {
        Migration migration = null;
        ArrayList<Value_Label> vl_list = new ArrayList<>();
        ArrayList<Migration> migration_list = new ArrayList<>();
        ArrayList<MigrationDirectory> md_list = new ArrayList<>();
        String nameDirectory = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        if (ps1 == null) {
            try {
                ps1 = con.prepareStatement("SELECT ID_IMAGEN as idimage, NOMBRE as name, EXTENSION as extension, TAMANO as sizes, RUTA.PATH as pat FROM IMAGEN,RUTA WHERE IMAGEN.ID_RUTA=RUTA.ID_RUTA");
                rs = ps1.executeQuery();

            } catch (SQLException ex) {
                Logger.getLogger(MigrationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                while (rs.next()) {
                    if (ps2 == null) {
                        ps2 = con.prepareStatement("SELECT IMAGEN_ETIQUETA.VALOR as valor, ETIQUETA.NOMBRE AS name, ETIQUETA.ID_ETIQUETA as idEtiqueta FROM IMAGEN_ETIQUETA, ETIQUETA WHERE ID_IMAGEN=? AND ETIQUETA.ID_ETIQUETA=IMAGEN_ETIQUETA.ID_ETIQUETA");
                        ps2.clearParameters();
                        ps2.setInt(1, rs.getInt("idimage"));
                        rs2 = ps2.executeQuery();
                    }
                    while (rs2.next()) {
                        Value_Label valueLabel = new Value_Label(rs2.getString("valor"), rs2.getString("name"));
                        int directoriId = rs2.getInt("idEtiqueta");

                        if (ps3 == null) {
                            ps3 = con.prepareStatement("SELECT DIRECTORIO.NOMBRE as name FROM DIRECTORIO, ETIQUETA WHERE DIRECTORIO.ID_DIRECTORIO=ETIQUETA.ID_DIRECTORIO AND ETIQUETA.ID_ETIQUETA=?");
                            ps3.clearParameters();
                            ps3.setInt(1, directoriId);
                            rs3 = ps3.executeQuery();
                            ps3=null;
                        }
                        rs3.next();
                        nameDirectory = rs3.getString("name");
                        vl_list.add(valueLabel);
                    }
                    MigrationDirectory mdobj = new MigrationDirectory(nameDirectory, vl_list);
                    md_list.add(mdobj);
                    ps2=null;
                migration = new Migration(rs.getInt("idimage"), rs.getString("name"), rs.getInt("sizes"), rs.getString("extension"), rs.getString("pat"), md_list);
                migration_list.add(migration);
                    
                }


            } catch (SQLException ex) {
                Logger.getLogger(MigrationDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ps1 = null;
                ps2 = null;
                ps3 = null;
            }
        }
        return migration_list;
    }
}
