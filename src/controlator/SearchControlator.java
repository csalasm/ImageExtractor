/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlator;

import DAOModel.ImageDAO;
import Utilities.FinderExt;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Image;
import model.Path;

/**
 *
 * @author andresbailen93
 */
public class SearchControlator {

    private FinderExt finde;

    public SearchControlator() throws IOException {
        finde = new FinderExt();
        ArrayList<String> directories = finde.readDirectory();
        for (String dir : directories) {
            File f = new File(dir);
            directory(f);
        }

    }

    public void directory(File dir) throws IOException {
        if (dir != null) {
            if (dir.isDirectory()) {
                File s[] = dir.listFiles();
                for (int i = 0; i < s.length; i++) {
                    if (s[i].isDirectory()) {
                        directory(s[i]);

                    } else if (s[i].isFile()) {
                        if (finde.isImage(s[i])) {
                            System.out.println(s[i]);
                            Image image = new Image(finde.returnName(s[i]), s[i].length(), finde.returnExt(s[i]), 0, 0);
                            Path path= new Path(finde.returnPath(s[i]),0);
                            ImageDAO imagedao=new ImageDAO();
                            try {
                                imagedao.addImage(image, path);
                            } catch (SQLException ex) {
                                Logger.getLogger(SearchControlator.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.println(image); 
                        }
                    }
                }
            } else {
                System.out.println("No es un directorio");
            }
        }
    }
}
