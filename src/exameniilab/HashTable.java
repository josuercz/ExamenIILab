/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exameniilab;

/**
 *
 * @author josuc
 */
public class HashTable {
    Entry head;
    
    public void add(String username, long pos){
        Entry nuevaEntrada = new Entry(pos, username);
        if (head == null) {
            head = nuevaEntrada;
        } else {
            Entry temp = head;
            while(temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevaEntrada;
        }
    }
    
    public void remove(String username) {
        if (head == null) 
            return;
        if(head.username.equals(username)) {
            head = head.siguiente;
            return;
        }
        Entry temp = head;
        while (temp.siguiente != null) {
            if (temp.siguiente.username.equals(username)){
                temp.siguiente = temp.siguiente.siguiente;
                return;
            }
            temp = temp.siguiente;
        }
    }
    
    public long Search(String username) {
        Entry temp = head;
        while (temp != null) {
            if (temp.username.equals(username)) {
                return temp.posicion;
            }
            temp = temp.siguiente;
        }
        return -1;
    }
    
    public enum Trophy {
        PLATINO(5),
        ORO(3),
        PLATA(2),
        BRONCE(1);

        public final int points;

        Trophy(int points) {
            this.points = points;
        }
    }
}
