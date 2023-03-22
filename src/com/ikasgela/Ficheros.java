package com.ikasgela;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Ficheros {

    /**
     * Leer un fichero de texto byte a byte
     */
    public static void leerTextoBytes() {

        // Esta estructura es un try-with-resources (java 1.7+)
        try (FileInputStream f = new FileInputStream("README.md")) {

            // Calcular el número de bytes a leer
            int longitud = f.available();

            System.out.print(Colors.BLUE_BOLD);
            System.out.println("--- Contenido ---");
            System.out.print(Colors.YELLOW);

            // Leer byte a byte y mostrar los datos
            for (int i = 0; i < longitud; i++) {
                System.out.print((char) f.read());
            }

            System.out.print(Colors.BLUE_BOLD);
            System.out.println("-----------------");
            System.out.println("Bytes: " + longitud);
            System.out.println("-----------------");
            System.out.print(Colors.RESET);

        } catch (IOException e) {
            System.err.println("ERROR: Error de E/S");
            System.err.println(e.getMessage());
        }
    }

    /**
     * Leer un fichero de texto por líneas y con codificación UTF-8
     */
    public static void leerTextoLineas() {

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("README.md"), StandardCharsets.UTF_8));

            //BufferedReader br = new BufferedReader(new FileReader("README.md", StandardCharsets.UTF_8));  // Java 11+

            System.out.print(Colors.BLUE_BOLD);
            System.out.println("--- Contenido ---");
            System.out.print(Colors.YELLOW);

            // Leer línea a línea
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            System.out.print(Colors.BLUE_BOLD);
            System.out.println("-----------------");
            System.out.print(Colors.RESET);

        } catch (IOException e) {
            System.err.println("ERROR: Error de E/S");
            System.err.println(e.getMessage());
        }

    }

    /**
     * Leer una imagen desde un fichero PNG y mostrarla usando un JLabel
     */
    public static void leerImagenBytes() {

        // Esta estructura es un try-with-resources (java 1.7+)
        try (FileInputStream fichero = new FileInputStream("icono.png")) {

            System.out.print(Colors.YELLOW);
            System.out.println("Cargando imagen...");
            System.out.print(Colors.RESET);

            // Crear el panel
            VentanaImagen ventana = new VentanaImagen();

            // Cargar la imagen y asignarla al JLabel
            Image image = ImageIO.read(fichero);
            ventana.getEtiqueta().setIcon(new ImageIcon(image));

            // Crear el JFrame con el panel y mostrarlo
            JFrame frame = new JFrame();
            frame.setContentPane(ventana.getPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (IOException e) {
            System.err.println("ERROR: Error de E/S");
            System.err.println(e.getMessage());
        }
    }

    /**
     * Escribir un fichero byte a byte
     */
    public static void escribirFicheros() {

        String texto = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam diam nisl, vehicula sit amet convallis vel, imperdiet eget nisl. Pellentesque magna elit, rhoncus vitae diam in, vulputate pretium neque. Cras volutpat elit quis mauris gravida, sit amet condimentum enim tristique. Proin tempor a purus nec varius. Curabitur eget consectetur felis. Suspendisse et nulla orci. Nullam sit amet congue nibh, in vulputate urna. Donec est est, vulputate a dignissim eu, maximus ac nulla. Etiam gravida at tortor id ullamcorper. Nullam tincidunt sem eu metus pulvinar viverra. Pellentesque tincidunt pharetra erat. Sed placerat dui suscipit ipsum fermentum commodo. Nunc ultricies tellus in porta imperdiet.";

        byte[] buffer = texto.getBytes();

        FileOutputStream f1 = null;
        FileOutputStream f2 = null;
        FileOutputStream f3 = null;

        // Esta vez no usamos el try-with-resources
        try {
            f1 = new FileOutputStream("../f1.txt");
            f2 = new FileOutputStream("../f2.txt");
            f3 = new FileOutputStream("../f3.txt");

            // Escritura de f1
            f1.write(buffer);

            // Escritura de f2
            for (int i = 0; i < buffer.length; i += 2) {
                f2.write(buffer[i]);
            }

            // Escritura de f3
            f3.write(buffer, buffer.length - buffer.length / 4, buffer.length / 4);

            System.out.print(Colors.YELLOW);
            System.out.println("Ficheros f1.txt, f2.txt y f3.txt generados.");
            System.out.print(Colors.RESET);

        } catch (IOException e) {
            System.out.println("ERROR: Error de E/S");
        } finally {
            // Hay que cerrar los streams a mano
            try {
                if (f1 != null) {
                    f1.close();
                }
            } catch (IOException e) {
                System.out.println("ERROR: No se puede cerrar f1.txt");
            }
            try {
                if (f2 != null) {
                    f2.close();
                }
            } catch (IOException e) {
                System.out.println("ERROR: No se puede cerrar f2.txt");
            }
            try {
                if (f3 != null) {
                    f3.close();
                }
            } catch (IOException e) {
                System.out.println("ERROR: No se puede cerrar f3.txt");
            }
        }
    }
}
