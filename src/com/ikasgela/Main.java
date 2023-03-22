package com.ikasgela;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int opcion = 0;

        do {

            System.out.println();
            System.out.println("FICHEROS EN JAVA");
            System.out.println("----------------");
            System.out.println("1. Lectura desde un fichero de texto (bytes)");
            System.out.println("2. Lectura desde un fichero de texto (líneas)");
            System.out.println("3. Lectura de una imagen PNG (bytes)");
            System.out.println("4. Escritura de ficheros");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(br.readLine());

                switch (opcion) {
                    case 1:
                        Ficheros.leerTextoBytes();
                        break;
                    case 2:
                        Ficheros.leerTextoLineas();
                        break;
                    case 3:
                        Ficheros.leerImagenBytes();
                        break;
                    case 4:
                        Ficheros.escribirFicheros();
                        break;
                    case 5:
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("ERROR: Opción no válida...");
            }

        } while (opcion != 5);

    }
}
