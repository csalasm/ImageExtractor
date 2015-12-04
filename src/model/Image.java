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
    
    /**
     * Constructor de la clase Image
     * @param name String asociado al nombre de la imagen
     * @param size long con el tamaño de una imagen
     * @param extension String con la extension de una imagen
     * @param idImage int identificador de la imagen
     * @param idRoute int identificador de la ruta de la imagen
     */

    public Image(String name, long size, String extension, int idImage, int idRoute) {
        this.name = name;
        this.size = size;
        this.extension = extension;
        this.idImage = idImage;
        this.idRoute = idRoute;
    }
    

    /**
     * 
     * @return String con el nombre de la imagen
     */
    public String getName() {
        return name;
    }

    /**
     * definimos el parametro name de Image para que pueda ser usado por todos los métodos
     * @param name String con el nombre de una imagen
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return long con el tamaño de la imagen
     */
    public long getSize() {
        return size;
    }

    /**
     * 
     * @return Image
     */
    @Override
    public String toString() {
        return "Image{" + "name=" + name + ", size=" + size + ", extension=" + extension + ", idImage=" + idImage + ", idRoute=" + idRoute + '}';
    }

    /**
     * definimos el parametro size de Image para que pueda ser usado por todos los métodos
     * @param size long con el tamaño de una imagen
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * 
     * @return String, extension de una imagen
     */
    public String getExtension() {
        return extension;
    }

    /**
     * definimos el parametro extension de Image para que pueda ser usado por todos los métodos
     * @param extension String con la extension de la imagen
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * 
     * @return int, idImage
     */
    public int getIdImage() {
        return idImage;
    }

    /**
     * definimos el parametro idImage de Image para que pueda ser usado por todos los métodos
     * @param idImage int identificador de una imagen
     */
    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    /**
     * 
     * @return int, idRoute de la imagen
     */
    public int getIdRoute() {
        return idRoute;
    }

    /**
     * definimos el parametro idRoute de image para que pueda ser usado por todos los métodos
     * @param idRoute int identificador de ruta de una imagen
     */
    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }
}
