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
public class Pedido implements Serializable {

    private Cliente cliente;
    private Vehiculo vehiculo;
    private Material material;
    private float ton;

    public Pedido() {
    }

    public Pedido(Cliente cliente, Vehiculo vehiculo, float ton) {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.ton = ton;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public float getTon() {
        return ton;
    }

    public void setTon(float ton) {
        this.ton = ton;
    }

    @Override
    public String toString() {
        return "COD" + vehiculo.getPlaca().toUpperCase() + cliente.toString().toUpperCase() + material.toString().substring(0, 1).toUpperCase() + ton;
    }

}
