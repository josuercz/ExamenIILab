/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exameniilab;

/**
 *
 * @author josuc
 */
public class Entry {
    String username;
    long posicion;
    Entry siguiente;
    
    public Entry(long posicion, String username) {
        this.posicion = posicion;
        this.username = username;
        siguiente = null;
    }
    
    
}
