/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect1.edd.bioinformatica;

/**
 * Clase que gestiona un arreglo dinámico de posiciones enteras.
 * Se utiliza para almacenar todas las posiciones donde aparece una
 * tripleta específica dentro de una secuencia de ADN.
 * 
 * @author Azael Zerpa
 */
public class Posiciones {
    private int[] posiciones;
    private int tamaño;

    /**
     * Constructor por defecto. Inicializa el arreglo con capacidad 10.
     */
    public Posiciones() {
        posiciones = new int[10]; 
        tamaño = 0;
    }
    
    /**
     * Agrega una nueva posición al arreglo. Si el arreglo está lleno,
     * lo duplica de manera dinámica.
     *
     * @param posicion la nueva posición a registrar
     */
    public void agregar(int posicion) {
        if (tamaño == posiciones.length) {
            int[] nuevo = new int[posiciones.length * 2];
            System.arraycopy(posiciones, 0, nuevo, 0, posiciones.length);
            posiciones = nuevo;
        }
        posiciones[tamaño++] = posicion;
    }
    
    /**
     * Devuelve un nuevo arreglo con todas las posiciones registradas
     * hasta el momento.
     *
     * @return arreglo con las posiciones almacenadas
     */
    public int[] obtenerTodas() {
        int[] resultado = new int[tamaño];
        System.arraycopy(posiciones, 0, resultado, 0, tamaño);
        return resultado;
    }

    public int getTamaño() {
        return tamaño;
    }
}
