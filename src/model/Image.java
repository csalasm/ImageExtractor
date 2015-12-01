/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author csalas
 */
public class Image {
    private String name;
    private long size;
    private String extension;
    private int idImage;
    private int idRoute;

    public Image(String name, long size, String extension, int idImage, int idRoute) {
        this.name = name;
        this.size = size;
        this.extension = extension;
        this.idImage = idImage;
        this.idRoute = idRoute;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Image{" + "name=" + name + ", size=" + size + ", extension=" + extension + ", idImage=" + idImage + ", idRoute=" + idRoute + '}';
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

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }
}
