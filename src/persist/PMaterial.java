/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persist;

import java.util.ArrayList;
import modelo.Material;

/**
 *
 * @author migue
 */
public class PMaterial {
    
    private static PMaterial instacia = null;

    private PMaterial() {
    }
    
    public static PMaterial getInstacia() {
        if (instacia == null){
            instacia = new PMaterial();
        }
        return instacia;
    }
    
    public void addMaterial(Material material) {
        Persist persist = Persist.getInstance();
        persist.getMateriales().add(material);
        Persist.save();
    }

    public ArrayList<Material> getMateriales() {
        Persist persist = Persist.getInstance();
        return persist.getMateriales();
    }
}
