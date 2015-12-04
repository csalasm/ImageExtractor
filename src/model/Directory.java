/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Paloma
 */
public class Directory {
    private int idDirectory;
    private String name;
    
    /**
     * Constructor de la clase Directory
     * @param idDirectory int identificador de un directorio dado
     * @param name String con el nombre del directorio
     */

    public Directory(int idDirectory, String name) {
        this.idDirectory = idDirectory;
        this.name = name;
    }

    /**
     * 
     * @return int, idDirectory
     */
    public int getIdDirectory() {
        return idDirectory;
    }

    /**
     * definimos el parametro idDirectory de Directory para que pueda ser usado por todos los métodos
     * @param idDirectory int identificador de directorio
     */
    public void setIdDirectory(int idDirectory) {
        this.idDirectory = idDirectory;
    }

    /**
     * 
     * @return String, nombre de la imagen
     */
    public String getName() {
        return name;
    }

    /**
     * definimos el parametro name de Directory para que pueda ser usado por todos los métodos
     * @param name String con el nombre del directorio
     */
    public void setName(String name) {
        this.name = name;
    }

    
    
    
}
