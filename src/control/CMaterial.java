/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import java.util.ArrayList;
import modelo.Material;
import persist.PMaterial;

/**
 *
 * @author migue
 */
public class CMaterial {
    
    private static CMaterial instacia = null;

    private CMaterial() {
    }
    
    public static CMaterial getInstacia() {
        if (instacia == null){
            instacia = new CMaterial();
        }
        return instacia;
    }
    
    public void addMaterial(Material material) {
        PMaterial.getInstacia().addMaterial(material);
    }

    public ArrayList<Material> getMateriales() {
        return PMaterial.getInstacia().getMateriales();
    }
}
