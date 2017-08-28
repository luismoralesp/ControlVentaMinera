/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persist;

import java.util.ArrayList;
import modelo.Pedido;

/**
 *
 * @author migue
 */
public class PPedido {

    private static PPedido instacia = null;

    private PPedido() {
    }

    public static PPedido getInstacia() {
        if (instacia == null) {
            instacia = new PPedido();
        }
        return instacia;
    }

    public void addPedido(Pedido pedido) {
        Persist persist = Persist.getInstance();
        persist.getPedidos().add(pedido);
        Persist.save();
    }
    
    public void delPedido(Pedido pedido){
        Persist persist = Persist.getInstance();
        persist.getPedidos().remove(pedido);
        Persist.save();
    }

    public ArrayList<Pedido> getPedidos() {
        Persist persist = Persist.getInstance();
        return persist.getPedidos();
    }
}
