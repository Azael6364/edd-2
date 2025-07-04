/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect1.edd.bioinformatica;

/**
 *
 * @author Azael Zerpa
 */
public class Nodo {
    String clave;
     // Falta una lista
    Nodo siguiente;

    Nodo(String clave, int posicion) {
        this.clave = clave;
        this.siguiente = null;
    }
}
