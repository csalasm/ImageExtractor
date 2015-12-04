/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import java.io.File;


/**
 * Clase para la extraccion de los metadatos utilizando la libreria metadata-extractor
 * @author andresbailen93
 */
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import model.Label;

public class MetadataFinder {
    private File file;
    /**
     * Constructor de la clase MetadaFinder
     * @param file Objeto de la clase File al que se le extraeran los metadatos
     */
    public MetadataFinder(File file) {
        this.file = file;
    }
/**
 * Metodo que devuelve los directorios para una imagen
 * @return ArrayList de objetos Directory con los directorios de una imagen
 * @throws ImageProcessingException
 * @throws IOException 
 */
    public ArrayList<Directory> getDirectory() throws ImageProcessingException, IOException{
        ArrayList<Directory> listDirectory = new ArrayList<>();
        Iterable<Directory> list = ImageMetadataReader.readMetadata(file).getDirectories();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            listDirectory.add((Directory)it.next());
        }
        
        return listDirectory;
    }
    /**
     * Metodo que devuelve las etiquetas dado un directorio
     * @param dir Objeto de la clase Directory
     * @return ArrayList de objetos Tag con las etiquetas de una foto
     */
    public ArrayList<Tag> getLabel(Directory dir){
        ArrayList<Tag> listTag = new ArrayList<>();
        Iterable<Tag> list = dir.getTags();
        Iterator it = list.iterator();
        while (it.hasNext()){
            listTag.add((Tag)it.next());
        }
        return listTag;
        
    }
   
    
}
