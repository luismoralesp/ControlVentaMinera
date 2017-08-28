/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persist;

import java.io.Serializable;
import java.util.ArrayList;
import modelo.Vehiculo;

/**
 *
 * @author migue
 */
public class PVehiculo implements Serializable {

    
    private static PVehiculo instacia = null;

    private PVehiculo() {
    }

    public static PVehiculo getInstacia() {
        if (instacia == null) {
            instacia = new PVehiculo();
        }
        return instacia;
    }

    public void addVehiculo(Vehiculo vehiculo) {
        Persist persist = Persist.getInstance();
        persist.getVehiculos().add(vehiculo);
        Persist.save();
    }

    public ArrayList<Vehiculo> getVehiculos() {
        Persist persist = Persist.getInstance();
        return persist.getVehiculos();
    }

}
