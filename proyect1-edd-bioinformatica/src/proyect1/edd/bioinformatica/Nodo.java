/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect1.edd.bioinformatica;

/**
 * Nodo que representa una entrada en la tabla hash.
 * Cada nodo contiene una clave, las posiciones donde aparece,
 * y una referencia al siguiente nodo en caso de colisiones (encadenamiento).
 * 
 * @author Azael Zerpa
 */
public class Nodo {
    String clave;
    Posiciones posiciones;
    Nodo siguiente;

    /**
     * Constructor del nodo que inicializa la clave y agrega la primera posición.
     *
     * @param clave    tripleta de ADN que actúa como identificador
     * @param posicion primera posición donde fue encontrada la tripleta
     */
    Nodo(String clave, int posicion) {
        this.clave = clave;
        this.posiciones = new Posiciones();
        this.posiciones.agregar(posicion);
    }
}
