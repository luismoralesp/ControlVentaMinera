/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */
public class FileManager {

    private static final String file = "data.dat";
    private static FileManager instancia = null;

    public static FileManager getInstancia() {
        if (instancia == null){
            instancia = new FileManager();
        }
        return instancia;
    }

    public void write(Serializable object) {
        try {
            try (FileOutputStream fos = new FileOutputStream(file)) {
                ObjectOutputStream stream = new ObjectOutputStream(fos);
                stream.writeObject(object);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object read() {
        if (!new File(file).exists()) {
            return null;
        }
        try {
            Object obj;
            try (FileInputStream fos = new FileInputStream(file)) {
                ObjectInputStream stream = new ObjectInputStream(fos);
                obj = stream.readObject();
            }
            return obj;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
