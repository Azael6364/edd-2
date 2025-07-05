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
   
    public String reporteColisionesPorClaveDistinta() {
        String resultado = "Reporte de colisiones entre tripletas distintas: \n";
        int totalColisiones = 0;

        for (int i = 0; i < capacidad; i++) {
            Nodo actual = tabla[i];

            // Si hay más de un nodo en esta posición, puede haber colisión
            if (actual != null && actual.siguiente != null) {
                Nodo externo = actual;
                while (externo != null) {
                    Nodo interno = externo.siguiente;
                    while (interno != null) {
                        if (!externo.clave.equals(interno.clave)) {
                            resultado += "Índice " + i + ": \"" + externo.clave + "\" colisionó con \"" + interno.clave + "\" \n";
                            totalColisiones++;
                        }
                        interno = interno.siguiente;
                    }
                    externo = externo.siguiente;
                }
            }
        }

        if (totalColisiones == 0) {
            resultado += "\n No se detectaron colisiones entre tripletas distintas.";
        } else {
            resultado += "\n Total de colisiones entre claves distintas: " + totalColisiones;
        }
        
        return resultado;
    }

    public String mostrarReporteAminoacidos(Aminoacido[] aminoacidos) {
        String resultado = "Reporte por aminoácido:\n";

        for (int i = 0; i < aminoacidos.length; i++) {
            Aminoacido a = aminoacidos[i];
            resultado += "Aminoácido: " + a.nombre;

            for (int j = 0; j < a.tripletas.length; j++) {
                String tripleta = a.tripletas[j];
                Posiciones p = this.obtener(tripleta);
                int frecuencia = (p != null) ? p.getTamaño() : 0;
                resultado += "  Tripleta: " + tripleta + " - Frecuencia: " + frecuencia + "\n";
            }

            
        }

        resultado += "\n\n\nTripletas no válidas:";
        for (int i = 0; i < this.capacidad; i++) {
            Nodo actual = this.tabla[i];
            while (actual != null) {
                boolean valida = false;

                for (int x = 0; x < aminoacidos.length; x++) {
                    for (int y = 0; y < aminoacidos[x].tripletas.length; y++) {
                        if (actual.clave.equals(aminoacidos[x].tripletas[y])) {
                            valida = true;
                            break;
                        }
                    }
                    if (valida){
                        break;
                    }
                }

                if (!valida) {
                    resultado += "  Tripleta: " + actual.clave + " - Frecuencia: " + actual.posiciones.getTamaño();
                }

                actual = actual.siguiente;
            }
        }
        
        return resultado;
    }


}
