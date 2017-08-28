/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persist;

import java.io.Serializable;
import java.util.ArrayList;
import modelo.Cliente;

/**
 *
 * @author migue
 */
public class PCliente implements Serializable{
    
    private static PCliente instacia = null;

    private PCliente() {
    }
    
    public static PCliente getInstacia() {
        if (instacia == null){
            instacia = new PCliente();
        }
        return instacia;
    }
    
    public void addCliete(Cliente cliente) {
        Persist persist = Persist.getInstance();
        persist.getClientes().add(cliente);
        Persist.save();
    }

    public ArrayList<Cliente> getClientes() {
        Persist persist = Persist.getInstance();
        return persist.getClientes();
    }
}
