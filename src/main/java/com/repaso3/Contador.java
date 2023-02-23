package com.repaso3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Contador {

    /**
     * Método que cuenta cuántas veces aparece la palabra en cada archivo de la carpeta.
     * @param ruta Ruta de la carpeta donde se encuentran los archivos.
     * @param palabra Palabra a buscar en los archivos.
     * @return La cantidad de veces que aparece la palabra en cada archivo dentro de un arreglo.
     * @throws Exception En caso de que la carpeta especificada por la ruta no exista, se propaga una excepción. 
     */
    public int[] contarEnArchivos(String ruta, String palabra) throws Exception {

        String[] archivos = this.extraerArchivos(ruta);

        int[] contador = new int[archivos.length];

        String[] extensiones = new String[archivos.length];
        for (int i = 0; i < extensiones.length; i++) {
            extensiones[i] = archivos[i].substring(archivos[i].lastIndexOf("."));   
        }

        String linea = "";

        for (int i = 0; i < archivos.length; i++) {
            
            BufferedReader reader = new BufferedReader(new FileReader(ruta + "\\" + archivos[i]));
            

            while ((linea = reader.readLine()) != null) {
                
                contador[i] += contarPalabrasLinea(linea, palabra, extensiones[i]);
            }

            reader.close();
        }
 
        return contador;
    }

    /**
     * Método que cuenta la cantidad de veces que aparece la palabra en determinada línea/renglón de cada archivo.
     * @param linea Línea/renglón del archivo.
     * @param palabra Palabra a buscar.
     * @param extension Extensión del archivo, de eso depende la forma en la que se busca la palabra.
     * @return La cantidad de veces que aparece la palabra a buscar en determinada línea del archivo.
     */
    public int contarPalabrasLinea(String linea, String palabra, String extension){

        String[] palabrasLinea;
        int contador = 0;

        switch (extension) {
            case ".txt":

                palabrasLinea = linea.split(" ");
                for (int i = 0; i < palabrasLinea.length; i++) {
                    if (palabrasLinea[i].replaceAll("[^a-zA-Z]", "").toLowerCase().equals(palabra)) {
                        contador++;
                    }
                }
                
                break;

            case ".csv":

                palabrasLinea = linea.split("[,\\s*\\;]");
                for (int i = 0; i < palabrasLinea.length; i++) {
                    if (palabrasLinea[i].replaceAll("[^a-zA-Z]", "").toLowerCase().equals(palabra)) {
                        contador++;
                    }
                }
                
                break;
        
            case ".xml":

                palabrasLinea = linea.split("[,\\s*]");
                for (int i = 0; i < palabrasLinea.length; i++) {
                    if (palabrasLinea[i].replaceAll("[^a-zA-Z]", "").toLowerCase().equals(palabra)) {
                        contador++;
                    }
                }
                
                break;

            case ".json":
                palabrasLinea = linea.split(" ");
                for (int i = 0; i < palabrasLinea.length; i++) {
                
                    if (palabrasLinea[i].replaceAll("[^a-zA-Z]", "").toLowerCase().equals(palabra)) {
                        contador++;
                    }
                }
                break;
            default:
                throw new IllegalArgumentException();
        }

        return contador;
    }

    /**
     * Método que obtiene la carpeta que especifica la ruta y extrae los archivos que posee.
     * @param ruta Es la ruta específica de la carpeta.
     * @return Lista de los archivos que posee la carpeta.
     * @throws Exception En caso de que la carpeta especificada por la ruta no exista, se propaga una excepción.
     */
    public String[] extraerArchivos(String ruta) throws Exception{

        File carpeta = new File(ruta);
        return carpeta.list();
    }
    
}
