/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect1.edd.bioinformatica;

/**
 *
 * @author Azael Zerpa
 */
public class Posiciones {
    private int[] posiciones;
    private int tamaño;

    public Posiciones() {
        posiciones = new int[10]; 
        tamaño = 0;
    }

    public void agregar(int posicion) {
        if (tamaño == posiciones.length) {
            int[] nuevo = new int[posiciones.length * 2];
            System.arraycopy(posiciones, 0, nuevo, 0, posiciones.length);
            posiciones = nuevo;
        }
        posiciones[tamaño++] = posicion;
    }

    public int[] obtenerTodas() {
        int[] resultado = new int[tamaño];
        System.arraycopy(posiciones, 0, resultado, 0, tamaño);
        return resultado;
    }

    public int getTamaño() {
        return tamaño;
    }
}
