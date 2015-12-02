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
 *
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
    
    public MetadataFinder(File file) {
        this.file = file;
    }

    public ArrayList<Directory> getDirectory() throws ImageProcessingException, IOException{
        ArrayList<Directory> listDirectory = new ArrayList<>();
        Iterable<Directory> list = ImageMetadataReader.readMetadata(file).getDirectories();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            listDirectory.add((Directory)it.next());
        }
        
        return listDirectory;
    }
    
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
