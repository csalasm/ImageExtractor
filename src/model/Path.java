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
public class Path {
    private String path;
    private int idRoute;
/**
 * Constructor de la clase Path
 * @param path String con la ruta de una determinada imagen
 * @param idRoute int identificador de una determinada ruta para una imagen
 */
    public Path(String path, int idRoute) {
        this.path = path;
        this.idRoute = idRoute;
    }

    /**
     * 
     * @return String, devuelve la ruta de una determinada imagen
     */
    public String getPath() {
        return path;
    }

    /**
     * definimos el parametro path para que pueda ser usado por todos los métodos
     * @param path String que determina la ruta de una imagen
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 
     * @return int, idRoute
     */
    public int getIdRoute() {
        return idRoute;
    }

    /**
     * definimos el parametro idRoute para que pueda ser usado por todos los métodos
     * @param idRoute int identificador de la ruta
     */
    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

   
    
    
}
