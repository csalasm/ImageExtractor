/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlator;

import DAOModel.ImageDAO;
import DAOModel.LabelDAO;
import Utilities.ExtensionFinder;
import Utilities.MetadataFinder;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Image;
import model.Image_label;
import model.Label;
import model.Path;
import view.MainView;


/**
 *
 * @author andresbailen93
 */
public class SearchControlator implements ActionListener {

    private ExtensionFinder finde;
    private MainView mainView;

    public SearchControlator() {
      mainView = new MainView();
      mainView.btnIndex.setActionCommand("BTN_INDEX");
      mainView.btnIndex.addActionListener(this);
      mainView.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       try { 
           switch (e.getActionCommand()) {
                case "BTN_INDEX":
                startSearching();
           }
       } catch (IOException ex) {
           ex.printStackTrace();
       }
    }
    
    private void startSearching() throws IOException {
        finde = new ExtensionFinder();
        ArrayList<String> directories = finde.readDirectory();
        for (String dir : directories) {
            File f = new File(dir);
            directory(f);
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
}
