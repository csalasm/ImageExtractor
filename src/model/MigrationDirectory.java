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
public class MigrationDirectory {
    private String name;
    private  ArrayList<Value_Label> labels;

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
