/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author andresbailen93
 */
public class Value_Label {
    private String value;
    private String label;
/**
 * Constructor de la clae Value_Label 
 * @param value String con el valor de una determinada etiqueta a una imagen
 * @param label String con el nombre de una etiqueta de una imagen
 */
    public Value_Label(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Value_Label{" + "value=" + value + ", label=" + label + '}';
    }
    
}
