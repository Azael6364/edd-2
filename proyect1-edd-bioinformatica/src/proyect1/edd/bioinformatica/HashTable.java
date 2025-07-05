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
    
    
    public String mostrarOrdenadoPorFrecuencia() {
        int total = 0;
        for (int i = 0; i < capacidad; i++) {
            Nodo actual = tabla[i];
            while (actual != null) {
                total++;
                actual = actual.siguiente;
            }
        }
        
        
        String[] claves = new String[total];
        int[] frecuencias = new int[total];
        int[][] posiciones = new int[total][];
        int index = 0;

        // Llenamos los arreglos
        for (int i = 0; i < capacidad; i++) {
            Nodo actual = tabla[i];
            while (actual != null) {
                claves[index] = actual.clave;
                frecuencias[index] = actual.posiciones.getTamaño();
                posiciones[index] = actual.posiciones.obtenerTodas();
                index++;
                actual = actual.siguiente;
            }
        }

        // Ordenamos por frecuencia (burbuja, por simplicidad)
        for (int i = 0; i < total - 1; i++) {
            for (int j = 0; j < total - i - 1; j++) {
                if (frecuencias[j] < frecuencias[j + 1]) {
                    // Intercambiar frecuencia
                    int tempF = frecuencias[j];
                    frecuencias[j] = frecuencias[j + 1];
                    frecuencias[j + 1] = tempF;

                    // Intercambiar clave
                    String tempC = claves[j];
                    claves[j] = claves[j + 1];
                    claves[j + 1] = tempC;

                    // Intercambiar posiciones
                    int[] tempP = posiciones[j];
                    posiciones[j] = posiciones[j + 1];
                    posiciones[j + 1] = tempP;
                }
            }
        }

        String resultado = "";
        for (int i = 0; i < total; i++) {
            //System.out.print("Tripleta: " + claves[i] + " Frecuencia: " + frecuencias[i] + " Posiciones: ");
            resultado += "Tripleta: " + claves[i] + "\n - Frecuencia: " + frecuencias[i] + " - Posiciones: ";
            for (int j = 0; j < posiciones[i].length; j++) {
                //System.out.print(posiciones[i][j]);
                resultado += posiciones[i][j] + ", ";
            }
            resultado += "\n\n";
            //System.out.println();
        }
        
        return resultado;
    }
    
    
    public String mostrarPatronMenosFrecuente() {
        int total = 0;
        for (int i = 0; i < capacidad; i++) {
            Nodo actual = tabla[i];
            while (actual != null) {
                total++;
                actual = actual.siguiente;
            }
        }
        
        
        String[] claves = new String[total];
        int[] frecuencias = new int[total];
        int[][] posiciones = new int[total][];
        int index = 0;

        // Llenamos los arreglos
        for (int i = 0; i < capacidad; i++) {
            Nodo actual = tabla[i];
            while (actual != null) {
                claves[index] = actual.clave;
                frecuencias[index] = actual.posiciones.getTamaño();
                posiciones[index] = actual.posiciones.obtenerTodas();
                index++;
                actual = actual.siguiente;
            }
        }

        int minFrecuencia = Integer.MAX_VALUE;
        for (int i = 0; i < total; i++) {
            if (frecuencias[i] < minFrecuencia) {
                minFrecuencia = frecuencias[i];
            }
        }

        String resultado = "Patrón(es) menos frecuente(s): \n";
        for (int i = 0; i < total; i++) {
            if (frecuencias[i] == minFrecuencia) {
                resultado += ("Tripleta: " + claves[i] + " - Frecuencia: " + frecuencias[i] + "\n");
            }
        }

        
        
        return resultado;
    }
    
    
    public String mostrarPatronMasFrecuente() {
        int total = 0;
        for (int i = 0; i < capacidad; i++) {
            Nodo actual = tabla[i];
            while (actual != null) {
                total++;
                actual = actual.siguiente;
            }
        }
        
        
        String[] claves = new String[total];
        int[] frecuencias = new int[total];
        int[][] posiciones = new int[total][];
        int index = 0;

        // Llenamos los arreglos
        for (int i = 0; i < capacidad; i++) {
            Nodo actual = tabla[i];
            while (actual != null) {
                claves[index] = actual.clave;
                frecuencias[index] = actual.posiciones.getTamaño();
                posiciones[index] = actual.posiciones.obtenerTodas();
                index++;
                actual = actual.siguiente;
            }
        }

        int maxFrecuencia = Integer.MIN_VALUE;
        for (int i = 0; i < total; i++) {
            if (frecuencias[i] > maxFrecuencia) {
                maxFrecuencia = frecuencias[i];
            }
        }

        String resultado = "Patrón(es) mas frecuente(s): \n";
        for (int i = 0; i < total; i++) {
            if (frecuencias[i] == maxFrecuencia) {
                resultado += ("Tripleta: " + claves[i] + " - Frecuencia: " + frecuencias[i] + "\n");
            }
        }

        
        
        return resultado;
    }
   
}
