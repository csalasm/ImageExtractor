/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.awt.image.BufferedImage;
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
import javax.imageio.ImageIO;

/**
 * Clase con metodos para la obtencion de informacion de una imagen
 * @author andresbailen93
 */
public class ExtensionFinder {

    String directory = "";

    public ExtensionFinder() {
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
/**
 * Metodo que recorre el archivo de configuracion y lee las posibles rutas configuradas en el archivo
 * @return ArrayList con los directorios existentes en el archivo de configuracion
 */
    public ArrayList<String> readDirectory() {
        Properties pr1 = new Properties();
        FileInputStream route = null;
        String getroute = "";
        try {
            route = new FileInputStream("./route.properties");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtensionFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pr1.load(route);
        } catch (IOException ex) {
            Logger.getLogger(ExtensionFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
        Set<String> prNames = new HashSet<>();
        prNames = pr1.stringPropertyNames();
        ArrayList<String> routes_list=new ArrayList<>();

        for(String s:prNames){
            routes_list.add(pr1.getProperty(s));
        }
        return routes_list;
        
    }

/**
 * Funcion que devuelve si un fichero es una imagen
 * @param f Objeto de la clase File
 * @return boolean que devuelve true si es una imagen, false en caso contrario
 * @throws IOException 
 */
    public boolean isImage(File f) throws IOException{
            BufferedImage Image = ImageIO.read(f);
        return Image != null;
            

    }
    /**
     * Metodo que devuelve la extension de un fichero
     * @param f Objeto de la clase File
     * @return String con la extension de un fichero
     */
    public String returnExt(File f){
        String extension = null;
        String name=f.getName();
        int i= name.lastIndexOf('.');
        if(i>0){
            extension=name.substring(i+1);
        }
        return extension.toLowerCase();
    }
    /**
     * Metodo que devuelve el nombre de un fichero
     * @param f Objeto de la clase File
     * @return String con el nombre del fichero
     */
    public String returnName(File f){
        String name=null;
        name=f.getName();
        int i = name.lastIndexOf('.');
        
        return name.substring(0, i);
    }
    /**
     * Metodo que devuelve la ruta donde esta el fichero
     * @param f Objeto de la clase File
     * @return String con la ruta del fichero
     */
    public String returnPath(File f){
        String path=null;
        path=f.toString();
        int i= path.lastIndexOf('/');
        return path.substring(0, i+1);
    }


}
