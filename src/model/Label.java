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

    public Label(String value, int idLabel, int idDirectory) {
        this.value = value;
        this.idLabel = idLabel;
        this.idDirectory = idDirectory;
    }

    public int getIdDirectory() {
        return idDirectory;
    }

    public void setIdDirectory(int idDirectory) {
        this.idDirectory = idDirectory;
    }

   
    
    public String getValue() {
        return value;
    }

   

    public int getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(int idLabel) {
        this.idLabel = idLabel;
    }

    
    
    
}
