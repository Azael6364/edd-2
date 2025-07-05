/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect1.edd.bioinformatica;

/**
 * Clase que representa un aminoácido con su nombre y las tripletas
 * de ADN que lo codifican.
 * 
 * 
 * @author Azael Zerpa
 */
public class Aminoacido {
    String nombre;
    String[] tripletas;
    
    /**
     * Constructor que inicializa el aminoácido con su nombre y sus tripletas codificantes.
     *
     * @param nombre    el nombre del aminoácido
     * @param tripletas las tripletas que lo codifican
     */
    Aminoacido(String nombre, String[] tripletas) {
        this.nombre = nombre;
        this.tripletas = tripletas;
    }
}
