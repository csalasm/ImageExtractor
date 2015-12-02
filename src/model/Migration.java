/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author andresbailen93
 */
public class Migration {
    private int id;
    private String name;
    private int size;
    private String extension;
    private String path;
    private ArrayList<MigrationDirectory> directoryName;

    public Migration(int id, String name, int size, String extension, String path, ArrayList<MigrationDirectory> directoryName) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.extension = extension;
        this.path = path;
        this.directoryName = directoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<MigrationDirectory> getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(ArrayList<MigrationDirectory> directoryName) {
        this.directoryName = directoryName;
    }

    @Override
    public String toString() {
        return "Migration{" + "id=" + id + ", name=" + name + ", size=" + size + ", extension=" + extension + ", path=" + path + ", directoryName=" + directoryName + '}';
    }

 
    
}
