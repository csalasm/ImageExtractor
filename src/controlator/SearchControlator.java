/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlator;

import DAOModel.ImageDAO;
import DAOModel.LabelDAO;
import DAOModel.MigrationDAO;
import Utilities.ExtensionFinder;
import Utilities.MetadataFinder;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import com.mongodb.MongoWriteException;
import Connections.ConexionMongoDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import model.Image;
import model.Image_label;
import model.Label;
import model.Migration;
import model.MigrationDirectory;
import model.Path;
import view.MainView;


/**
 *
 * @author andresbailen93
 */
public class SearchControlator implements ActionListener, KeyListener, ListSelectionListener {

    private ExtensionFinder finde;
    private MainView mainView;
    ConexionMongoDB mongoDB = null;

    public SearchControlator() {
      mainView = new MainView();
      mainView.btnIndex.setActionCommand("BTN_INDEX");
      mainView.btnIndex.addActionListener(this);
      mainView.btnShow.setActionCommand("SHOW");
      mainView.btnShow.addActionListener(this);
      mainView.jTextSearchImage.addKeyListener(this);
      mainView.jTableImage.getSelectionModel().addListSelectionListener(this);
      mainView.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       try { 
           switch (e.getActionCommand()) {
                case "BTN_INDEX":
                    startSearching();
                    break;
                case "SHOW":
                    getAllImages(null);
                    break;
                            
           }
       } catch (IOException|MongoWriteException ex) {
           //ex.printStackTrace();
       }
    }
    
    private void initMongoDB() {
        if (mongoDB == null)
            mongoDB = new ConexionMongoDB("images");
    }
    
    private void startSearching() throws IOException {
        finde = new ExtensionFinder();
        ArrayList<String> directories = finde.readDirectory();
        for (String dir : directories) {
            File f = new File(dir);
            directory(f);
        }
        generateMigrationData();
        
    }
    private void getAllImages(ArrayList<Migration> migrationList) {
        ArrayList<Migration> mList = null;
        // No se reciben datos filtrados por parámetro
        if (migrationList == null) {
            initMongoDB();
            mList = mongoDB.getAllImages();
        }
        else
            mList = migrationList;
        
        mainView.model.setRowCount(0);
        for (Migration m: mList) {
            Object[] row ={m.getId(),m.getName(),m.getSize(),m.getExtension(),m.getPath()};
            mainView.model.addRow(row);
        }
        mainView.jTableImage.setEnabled(true);
        mainView.jTableImage.setRowSelectionAllowed(true);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int i=0;i<mainView.jTableImage.getColumnCount();i++)
            mainView.jTableImage.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        
        
        generateMetadataGUI();
    }
    
    private void generateMigrationData() {
        MigrationDAO migrationDAO = new MigrationDAO();
        ArrayList<Migration> migrationList;
        try {
            migrationList = migrationDAO.migrate();
            initMongoDB();
            mongoDB.setMigrationData(migrationList);
            mongoDB.insertIntoMongoDB();
        } catch (SQLException ex) {
            Logger.getLogger(SearchControlator.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
    
    private void generateMetadataGUI() {
        ArrayList<MigrationDirectory> listDirectories = mongoDB.getDirectoriesFromImageID(6);
        mainView.jComboDirectorio.removeAllItems();
        for (MigrationDirectory md: listDirectories) {
            mainView.jComboDirectorio.addItem(md.getName());
        }
    }

    public void directory(File dir) throws IOException {
        StringBuilder output = new StringBuilder();
        if (dir != null) {
            if (dir.isDirectory()) {
                File s[] = dir.listFiles();
                for (int i = 0; i < s.length; i++) {
                    if (s[i].isDirectory()) {
                        directory(s[i]);

                    } else if (s[i].isFile()) {
                        if (finde.isImage(s[i])) {
                            output.append(s[i].getName()).append("\n");
                            int imageid=0;
                            Image image = new Image(finde.returnName(s[i]), s[i].length(), finde.returnExt(s[i]), 0, 0);
                            Path path= new Path(finde.returnPath(s[i]),0);
                            ImageDAO imagedao=new ImageDAO();
                            
                            try {
                                imageid=imagedao.addImage(image, path);
                                image.setIdImage(imageid);
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(SearchControlator.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            MetadataFinder mf = new MetadataFinder(s[i]);
                            try {
                                ArrayList<Directory> listDir = mf.getDirectory();
                                ArrayList<Tag> tagList;
                                LabelDAO labelDAO = new LabelDAO();
                                
                                for(Directory d: listDir) {
                                    output.append(d).append("\n");
                                    model.Directory md = new model.Directory(0, d.getName());
                                    tagList = mf.getLabel(d);
                                    for (Tag t: tagList) {
                                        Label l = new Label(t.getTagName(), 0, 0);
                                        Image_label il = new Image_label(t.getDescription(), 0, 0);
                                        labelDAO.addMetaData(image, md, l,il);
                                    }
                                    
                                }
                                mainView.jAreaConsole.setText(output.toString());
                            } catch (ImageProcessingException ex) {
                                Logger.getLogger(SearchControlator.class.getName()).log(Level.SEVERE, null, ex);
                            
                            
                        }   catch (SQLException ex) {
                                Logger.getLogger(SearchControlator.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }
            } else {
                System.out.println("No es un directorio");
            }
        }
    }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        initMongoDB();
        getAllImages(mongoDB.getImagesFilterBy(mainView.getImageFilterSelected(), mainView.jTextSearchImage.getText()));

        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (mainView.jTableImage.getSelectedRow() > -1)
            System.out.println(mainView.jTableImage.getValueAt(mainView.jTableImage.getSelectedRow(), 0));
    }
}
