package com.repaso3;

import java.util.Scanner;

public class App 
{
    /*
     * Método para pedir al usuario la ruta de la carpeta y la palabra a buscar.
     */
    public static void main( String[] args ) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\u001B[32mPrograma para contar la aparición de palabras en un archivo.\u001B[0m");
        System.out.println("\nRecuerde que: \n" +
                            " \u001B[32mI.\u001B[0m Debe especificar correctamente la ruta de la carpeta donde se encuentran los archivos.\n" +
                            "\u001B[32mII.\u001B[0m Los archivos deben ser de texto, es decir, con extensiones: .txt, .csv, .xml o .json");

        System.out.print("\n\u001B[34mIngrese la ruta de la carpeta que contiene los archivos: \u001B[0m");
        String ruta = scanner.nextLine().trim();

        System.out.print("\u001B[34mIngrese la palabra a buscar: \u001B[0m");
        String palabra = scanner.nextLine().trim().toLowerCase();
        
        contar(ruta, palabra);

        scanner.close();
    }

    /**
     * Método que a partir de los métodos de Contador, cuenta el total de veces que aparece la palabra en cada
     * archivo y el total y lo muestra al usuario por consola.
     * @param ruta Ruta especificada de la carpeta donde se encuentran los archivos
     * @param palabra Palabra que se debe buscar en los archivos
     */
    private static void contar(String ruta, String palabra) {
        try {
            int total = 0;
            Contador contador = new Contador();
            String[] archivos = contador.extraerArchivos(ruta);
            int[] cantidades = contador.contarEnArchivos(ruta, palabra);

            System.out.println("\nPalabra buscada: " + "\u001B[32m" + palabra + "\u001B[0m");
            System.out.println("Resultado: \n");
            for (int i = 0; i < cantidades.length; i++) {
                total += cantidades[i];
                System.out.printf("%-11s %d veces%n", archivos[i], cantidades[i]);
                
            }

            System.out.printf("\u001B[32mTotal:      \u001B[0m" + total + " veces\n");

        } catch (IllegalArgumentException e) {
           System.out.println("\n\u001B[31m¡ERROR! La carpeta no contiene ningún archivo de texto.\u001B[0m");
        } catch (Exception e1) {
            System.out.println("\n\u001B[31m¡ERROR! La ruta de la carpeta indicada no existe.\u001B[0m");
        }

    }
}
