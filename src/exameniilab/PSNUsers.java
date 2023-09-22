/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exameniilab;

import exameniilab.HashTable.Trophy;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author josuc
 */
public class PSNUsers {
    RandomAccessFile raf;
    HashTable users;
    
    public PSNUsers() throws IOException {
        raf = new RandomAccessFile("psn", "rw");
        this.users = new HashTable();
        reloadHashTable();
    }
    
    private void reloadHashTable() throws IOException {
        raf.seek(0);
        while (raf.getFilePointer() < raf.length()) {
            String username = raf.readUTF();
            raf.skipBytes(9);
            long pos = raf.getFilePointer();
            users.add(username, pos);
        }
    }
    
    public void addUser(String username) throws IOException {
        raf.seek(raf.length());
        raf.writeUTF(username);
        raf.writeInt(0); 
        raf.writeInt(0);
        raf.writeBoolean(true);
        users.add(username, raf.getFilePointer());
    }

    public void deactivateUser(String username) throws IOException {
        long pos = users.Search(username);
        if (pos != -1) {
            raf.seek(pos);
            raf.writeBoolean(false);
            users.remove(username);
        }
    }
    
    public void addTrophieTo(String username, String trophyGame, String trophyName, Trophy type) throws IOException {
        long pos = users.Search(username);
        System.out.println(pos);
        if (pos != -1) {
            raf.seek(pos - 9);
            int points = raf.readInt();
            int trophies = raf.readInt();
            points += type.points;
            trophies++;            
            raf.seek(pos - 9);
            raf.writeInt(points);
            raf.writeInt(trophies);
            
            RandomAccessFile trophyFile = new RandomAccessFile("psn_trophies", "rw");
            trophyFile.seek(trophyFile.length());
            trophyFile.writeUTF(username);
            trophyFile.writeUTF(type.name());
            trophyFile.writeUTF(trophyGame);
            trophyFile.writeUTF(trophyName);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
            Date date = new Date();  
            trophyFile.writeUTF(formatter.format(date));
        }
    }

    public String playerInfo(String username) throws IOException {
        long pos = users.Search(username);
        System.out.println("posicion user " + pos);
        StringBuilder info = new StringBuilder();
        if (pos != -1) {
            raf.seek(pos - 9 - username.length() - 2);
            String user = raf.readUTF();
            info.append("Username: " + user + "\n");
            int puntos = raf.readInt();
            info.append("Points: " + puntos + "\n");
            int trophies = raf.readInt();
            info.append("Trophies: " + trophies + "\n");
            info.append("Active: " + raf.readBoolean() + "\n");

            RandomAccessFile trophyFile = new RandomAccessFile("psn_trophies", "rw");
            trophyFile.seek(0);
            while (trophyFile.getFilePointer() < trophyFile.length()) {
                if (trophyFile.readUTF().equals(username)) {
                    info.append(trophyFile.readUTF() + " - " + trophyFile.readUTF() + " - " + trophyFile.readUTF() + " - " + trophyFile.readUTF() + "\n");
                } else {
                    raf.skipBytes(9 + username.length() + 2);
                }
            }
        }
        return info.toString();
    }
}
