/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlator;

import Utilities.FinderExt;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

/**
 *
 * @author andresbailen93
 */
public class SearchControlator {

    private FinderExt finde;

    public SearchControlator() throws IOException {
        finde = new FinderExt();
        HashSet<String> directories=(HashSet<String>) finde.readDirectory();
        for(String dir: directories){
            File f= new File(dir);
            directory(f);
        }
        
    }

    public void directory(File dir) throws IOException {
            if (dir.isDirectory()) {
            File s[] = dir.listFiles();
            for (int i = 0; i < s.length; i++) {
                if (s[i].isDirectory()) {
                    directory(s[i]);

                } else if (s[i].isFile()) {
                    if (finde.isImage(s[i])) {
                    }
                }
            }
        } else {
            System.out.println("No es un directorio");
        }
    }
}
