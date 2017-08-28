/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import java.util.ArrayList;
import modelo.Cliente;
import persist.PCliente;

/**
 *
 * @author migue
 */
public class CCliente {
        
    private static CCliente instacia = null;

    private CCliente() {
    }
    
    public static CCliente getInstacia() {
        if (instacia == null){
            instacia = new CCliente();
        }
        return instacia;
    }
    
    public void addCliete(Cliente cliente) {
        PCliente.getInstacia().addCliete(cliente);
    }

    public ArrayList<Cliente> getClientes() {
        return PCliente.getInstacia().getClientes();
    }
}
