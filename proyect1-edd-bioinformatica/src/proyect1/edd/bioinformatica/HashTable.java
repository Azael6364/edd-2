/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect1.edd.bioinformatica;

/**
 * Clase que implementa una tabla de dispersión para almacenar tripletas de ADN
 * junto con las posiciones en las que aparecen en una secuencia.
 * 
 * @author Azael Zerpa
 */
public class HashTable {

    private Nodo[] tabla;
    private int capacidad;
    
    /**
     * Constructor de la tabla hash.
     *
     * @param capacidad el tamaño inicial del arreglo de dispersión
    */
    public HashTable(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new Nodo[capacidad];
    }

    
    /**
     * Función hash que convierte una cadena en un índice para la tabla.
     *
     * @param clave la tripleta que se desea dispersar
     * @return índice correspondiente en la tabla
     */
    private int hash(String clave) {
        int hash = 0;
        for (char c : clave.toCharArray()) {
            hash = (hash * 31 + c) % capacidad;
        }
        return hash;
    }

    /**
     * Inserta una tripleta en la tabla junto con su posición dentro de la secuencia original.
     * Si la clave ya existe, se añade la nueva posición a la lista.
     *
     * @param clave    la tripleta de ADN
     * @param posicion la posición donde se encuentra dicha tripleta
     */
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
    
    /**
     * Busca una tripleta en la tabla y retorna las posiciones donde fue encontrada.
     *
     * @param clave la tripleta a buscar
     * @return estructura Posiciones con las apariciones encontradas, o null si no existe
     */
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
    
    /**
     * Genera un listado de todas las tripletas ordenadas de mayor a menor frecuencia.
     *
     * @return cadena con tripletas, frecuencia y posiciones
     */
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
    
    /**
     * Retorna las tripletas con menor frecuencia de aparición.
     *
     * @return cadena con tripletas y su frecuencia mínima
     */
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
    
    /**
     * Retorna las tripletas con mayor frecuencia de aparición.
     *
     * @return cadena con tripletas y su frecuencia máxima
     */
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
   
    /**
     * Genera un reporte de colisiones entre claves distintas que cayeron en el mismo índice de la tabla.
     *
     * @return cadena con el detalle de las colisiones encontradas
     */
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

    /**
     * Genera una lista por aminoácido, listando las tripletas que lo codifican,
     * su frecuencia de aparición, y también indica las tripletas no válidas.
     *
     * @param aminoacidos arreglo personalizado con todos los aminoácidos y sus tripletas asociadas
     * @return cadena con el detalle del reporte por aminoácido
     */
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
