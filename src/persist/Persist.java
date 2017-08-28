/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persist;

import java.io.Serializable;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Material;
import modelo.Pedido;
import modelo.Vehiculo;

/**
 *
 * @author migue
 */
public class Persist implements Serializable {

    private final ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    private final ArrayList<Cliente> clientes = new ArrayList<>();
    private final ArrayList<Material> materiales = new ArrayList<>();
    private final ArrayList<Pedido> pedidos = new ArrayList<>();
    private static Persist instance = null;

    private Persist() {
    }

    public static Persist getInstance() {
        if (instance == null) {
            if (!read()) {
                instance = new Persist();
            }
        }
        return instance;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public ArrayList<Material> getMateriales() {
        return materiales;
    }

    public static void save() {
        FileManager manager = FileManager.getInstancia();
        manager.write(instance);
    }

    public static boolean read() {
        FileManager manager = FileManager.getInstancia();
        Object read = manager.read();
        if (read != null) {
            instance = (Persist) read;
            return true;
        }
        return false;
    }
}
