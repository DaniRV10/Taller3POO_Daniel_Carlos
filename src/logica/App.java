package logica;

//Carlos Alberto Montenegro Perez 22154893-0 ICCI
//Daniel Alexanders Robles Valdenegro 20738244-2 ICCI

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import dominio.*;

public class App {
	private static SistemaImpl s = new SistemaImpl();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			cargarHechizos();
			cargarMagos();
		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el archivo");
		}
		
		
		String opcion = "";
		do {
			System.out.println("=== BIENVENIDO AL MUNDO MAGICO ===");
			System.out.println("1. Menu Administrador");
			System.out.println("2. Menu Analista");
			System.out.println("3. Salir");
			System.out.print(">");
			opcion = sc.nextLine();
			
			switch (opcion) {
			case "1":
				menuAdmin();
				break;
			case "2":
				menuAnalista();
				break;
			case "3":
				System.out.println("Hasta luego!!");
				break;
			default:
				System.out.println("Ingrese una opcion valida");
				break;
			}
		} while (!opcion.equals("3"));
		
		

	}
	
	//PANEL ADMIN

	private static void menuAdmin() {
		String opcion = "";
		do {
			System.out.println("===== MENU ADMINISTRADOR =====");
			System.out.println("1. Agregar Mago");
			System.out.println("2. Modificar Mago");
			System.out.println("3. Eliminar Mago");
			System.out.println("4. Agregar Hechizo");
			System.out.println("5. Modificar Hechizo");
			System.out.println("6. Eliminar Hechizo");
			System.out.println("7. Volver al menu principal");
			System.out.print(">");
			
			opcion = sc.nextLine();
			switch (opcion) {
			case "1":
				nuevoMago();
				break;
			case "2":
				modificarMago();
				break;
			case "3":
				System.out.print("Ingrese nombre del mago a eliminar: ");
				String nombre = sc.nextLine();
				Mago m = s.buscarMago(nombre);
				if (m == null) {
			        System.out.println("Error: El mago '" + nombre + "' no existe en el sistema.");
			        break; // Corta el case aqui y vuelve a mostrar el menu
			    }
				boolean eliminado = s.eliminarMago(m);
				if (eliminado) {
			        s.actualizarDatos(); // Reescribe los archivos .txt
			        System.out.println("Mago '" + nombre + "' eliminado exitosamente de los registros!");
			    } else {
			        System.out.println("Error: No se pudo eliminar al mago del sistema.");
			    }
				break;
			case "4":
				nuevoHechizo();
				break;
			case "5":
				modificarHechizo();
				break;
			case "6":
				System.out.print("Ingrese nombre del hechizo a eliminar: ");
				String nombreH = sc.nextLine();
				Hechizo h = s.buscarHechizo(nombreH);
				if (h == null) {
			        System.out.println("Error: El hechizo '" + nombreH + "' no existe en el sistema.");
			        break; // Corta el case aqui y vuelve a mostrar el menu
			    }
				boolean eliminadoH = s.eliminarHechizo(h);
				if (eliminadoH) {
			        s.actualizarDatos(); // Reescribe los archivos .txt
			        System.out.println("Hechizo '" + nombreH + "' eliminado exitosamente de los registros!");
			    } else {
			        System.out.println("Error: No se pudo eliminar el hechizo del sistema.");
			    }
				break;
			case "7":
				System.out.println("Volviendo al menu principal....");
				break;
			default:
				System.out.println("Ingrese una opcion valida.");
				break;
			}
			
		} while (!opcion.equals("7"));
		
	}
	
	private static void nuevoHechizo() {
		// TODO Auto-generated method stub
		
	}

	private static void modificarHechizo() {
		// TODO Auto-generated method stub
		
	}

	private static void modificarMago() {
		// TODO Auto-generated method stub
		
	}

	private static void nuevoMago() {
		// TODO Auto-generated method stub
		
	}

	
	//PANEL ANALISTA
	
	private static void menuAnalista() {
		
		
	}
	
	//LECTURA ARCHIVOS

	private static void cargarHechizos() throws FileNotFoundException {
        File arch = new File("txts/Hechizos.txt");
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
		File arch = new File("txts/Magos.txt");
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
