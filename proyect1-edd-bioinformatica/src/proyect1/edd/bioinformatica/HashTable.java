/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect1.edd.bioinformatica;

/**
 *
 * @author Azael Zerpa
 */
public class HashTable {

    private Nodo[] tabla;
    private int capacidad;
    
    public HashTable(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new Nodo[capacidad];
    }

    private int hash(String clave) {
        int hash = 0;
        for (char c : clave.toCharArray()) {
            hash = (hash * 31 + c) % capacidad;
        }
        return hash;
    }

    public void insertar(String clave, int posicion) {
        int indice = hash(clave);
        Nodo actual = tabla[indice];

        while (actual != null) {
            if (actual.clave.equals(clave)) {
                actual.posiciones.agregar(posicion);
                return;
            }
            actual = actual.siguiente;
        }

        Nodo nuevo = new Nodo(clave, posicion);
        nuevo.siguiente = tabla[indice];
        tabla[indice] = nuevo;
    }

    public Posiciones obtener(String clave) {
        int indice = hash(clave);
        Nodo actual = tabla[indice];

        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual.posiciones;
            }
            actual = actual.siguiente;
        }

        return null;
    }

   
}
