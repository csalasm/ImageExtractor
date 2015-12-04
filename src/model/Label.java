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
public class Label {
    private String value;
    private int idLabel;
    private int idDirectory;

    /**
     * Constructor de la clase Label
     * @param value String con el valor de una determinada etiqueta a una imagen
     * @param idLabel int identificador de una determinada etiqueta para una imagen dada
     * @param idDirectory int identificador de un directorio para una etiqueta dada
     */
    public Label(String value, int idLabel, int idDirectory) {
        this.value = value;
        this.idLabel = idLabel;
        this.idDirectory = idDirectory;
    }

    /**
     * 
     * @return int, id del directorio
     */
    public int getIdDirectory() {
        return idDirectory;
    }

    /**
     * definimos el parametro directory  para que pueda ser usado por todos los métodos
     * @param idDirectory int identificador de un directorio
     */
    public void setIdDirectory(int idDirectory) {
        this.idDirectory = idDirectory;
    }

   /**
    * 
    * @return String, valor asociado a una etiqueta
    */
    
    public String getValue() {
        return value;
    }

   

    /**
     * 
     * @return int, identificador de una etiqueta
     */
    public int getIdLabel() {
        return idLabel;
    }

    /**
     * definimos el parametro idLabel de Label para que pueda ser usado por todos los métodos
     * @param idLabel int identificador de una etiqueta
     */
    public void setIdLabel(int idLabel) {
        this.idLabel = idLabel;
    }

    
    
    
}
