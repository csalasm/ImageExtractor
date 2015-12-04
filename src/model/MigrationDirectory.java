/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *Clase con los directorios de una imagen y etiquetas
 * @author andresbailen93
 */
public class MigrationDirectory {
    private String name;
    private  ArrayList<Value_Label> labels;
/**
 * Constructor de la clase MigrationDirectory
 * @param name String nombre del directorio
 * @param labels ArrayList de objetos Value_Label que almacena las variables etiqueta, valor de una imagen
 */
    public MigrationDirectory(String name, ArrayList<Value_Label> labels) {
        this.name = name;
        this.labels = labels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Value_Label> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<Value_Label> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "MigrationDirectory{" + "name=" + name + ", labels=" + labels + '}';
    }


}
