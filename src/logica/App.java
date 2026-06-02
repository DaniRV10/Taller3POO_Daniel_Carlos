package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import dominio.*;

public class App {
	private static SistemaImpl s = new SistemaImpl();

	public static void main(String[] args) {
		try {
			cargarHechizos();
			cargarMagos();
		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el archivo");
		}

	}

	private static void cargarHechizos() throws FileNotFoundException {
        File arch = new File("Hechizos.txt");
        Scanner lector = new Scanner(arch, "UTF-8");

        while (lector.hasNextLine()) {
            String linea = lector.nextLine();
            if (linea.isEmpty()) continue;

            String[] partes = linea.split(";");
            if (partes.length < 4) continue;

            String nombre = partes[0].trim();
            String tipo   = partes[1].trim();
            double daño  = Double.parseDouble(partes[2].trim());
            String extras = partes[3].trim();

            Hechizo h = null;

            switch (tipo) {
                case "Fuego":
                    double durQ = Double.parseDouble(extras);
                    h = new HechizoFuego(nombre,"Fuego", daño, durQ);
                    break;

                case "Tierra":
                    double mejD = Double.parseDouble(extras);
                    h = new HechizoTierra(nombre,"Tierra", daño, mejD);
                    break;

                case "Planta":
                    String[] ep = extras.split(",");
                    double durS  = Double.parseDouble(ep[0].trim());
                    double cantP = Double.parseDouble(ep[1].trim());
                    h = new HechizoPlanta(nombre,"Planta", daño, durS, cantP);
                    break;

                case "Agua":
                    String[] ea = extras.split(",");
                    double cantH  = Double.parseDouble(ea[0].trim());
                    double presion = Double.parseDouble(ea[1].trim());
                    h = new HechizoAgua(nombre,"Agua", daño, cantH, presion);
                    break;

                default:
                    System.out.println("Tipo desconocido: " + tipo);
                    break;
            }

            if (h != null) s.agregarHechizo(h);
        }

        lector.close();
    }
	

	private static void cargarMagos() throws FileNotFoundException {
		File arch = new File("Magos.txt");
        Scanner lector = new Scanner(arch);

        while (lector.hasNextLine()) {
            String linea = lector.nextLine();
            if (linea.isEmpty()) continue;

            String[] partes = linea.split(";");
            if (partes.length < 2) continue;

            Mago mago = new Mago(partes[0].trim());

            for (String nombreH : partes[1].trim().split("\\|")) {
                Hechizo h = s.buscarHechizo(nombreH.trim());
                if (h != null) mago.agregarHechizo(h);
                else System.out.println("ADVERTENCIA: '" + nombreH.trim() + "' no encontrado.");
            }

            s.agregarMago(mago);
        }
        lector.close();
	}

		
}
