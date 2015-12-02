/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;

/**
 *
 * @author andresbailen93
 */
public class FinderExt {

    String directory = "";

    public FinderExt() {
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public ArrayList<String> readDirectory() {
        Properties pr1 = new Properties();
        FileInputStream route = null;
        String getroute = "";
        try {
            route = new FileInputStream("./route.properties");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FinderExt.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pr1.load(route);
        } catch (IOException ex) {
            Logger.getLogger(FinderExt.class.getName()).log(Level.SEVERE, null, ex);
        }
        Set<String> prNames = new HashSet<>();
        prNames = pr1.stringPropertyNames();
        ArrayList<String> routes_list=new ArrayList<>();

        for(String s:prNames){
            routes_list.add(pr1.getProperty(s));
        }
        return routes_list;
        
    }


    public boolean isImage(File f){
       String mimetype = new MimetypesFileTypeMap().getContentType(f);
       String type=mimetype.split("/")[0];
       if(type.equals("image")){
           return true;
       }
        return false;
    }
    
    public String returnExt(File f){
        String extension = null;
        String name=f.getName();
        int i= name.lastIndexOf('.');
        if(i>0){
            extension=name.substring(i+1);
        }
        return extension.toLowerCase();
    }
    public String returnName(File f){
        String name=null;
        name=f.getName();
        int i = name.lastIndexOf('.');
    
        return name.substring(0, i-1);

        
    }


}
