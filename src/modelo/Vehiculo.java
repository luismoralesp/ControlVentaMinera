/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;

/**
 *
 * @author migue
 */
public class Vehiculo implements Serializable{
    private String placa;
    private float capacidad;

    public Vehiculo() {
    }

    public Vehiculo(String placa, float capacidad) {
        this.placa = placa;
        this.capacidad = capacidad;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the capacidad
     */
    public float getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(float capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return placa + " - " + capacidad + "Ton";
    }
    
}
