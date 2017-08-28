/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import java.util.ArrayList;
import modelo.Pedido;
import persist.PPedido;

/**
 *
 * @author migue
 */
public class CPedido {
    
    private static CPedido instacia = null;

    private CPedido() {
    }

    public static CPedido getInstacia() {
        if (instacia == null) {
            instacia = new CPedido();
        }
        return instacia;
    }

    public void addPedido(Pedido pedido) {
        PPedido.getInstacia().addPedido(pedido);
    }
    
    public void delPedido(Pedido pedido){
        PPedido.getInstacia().delPedido(pedido);
    }

    public ArrayList<Pedido> getPedidos() {
        return PPedido.getInstacia().getPedidos();
    }
}
