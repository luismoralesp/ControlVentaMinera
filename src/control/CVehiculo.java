/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import java.util.ArrayList;
import modelo.Vehiculo;
import persist.PVehiculo;

/**
 *
 * @author migue
 */
public class CVehiculo {
    
    private static CVehiculo instacia = null;

    private CVehiculo() {
    }

    public static CVehiculo getInstacia() {
        if (instacia == null) {
            instacia = new CVehiculo();
        }
        return instacia;
    }

    public void addVehiculo(Vehiculo vehiculo) {
        PVehiculo.getInstacia().addVehiculo(vehiculo);
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return PVehiculo.getInstacia().getVehiculos();
    }
}
