package logica;

//Carlos Alberto Montenegro Perez 22154893-0 ICCI
//Daniel Alexanders Robles Valdenegro 20738244-2 ICCI

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
			System.out.print("> ");
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
			System.out.print("> ");
			
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
		System.out.println("==== CREANDO UN NUEVO HECHIZO ====");
		System.out.print("Ingrese el nombre del nuevo hechizo: ");
	    String nombreH = sc.nextLine().trim();
	    if (s.buscarHechizo(nombreH) != null) {
	        System.out.println("Error: Ya existe un hechizo registrado con el nombre '" + nombreH + "'.");
	        return;
	    }
	    System.out.print("Ingrese el tipo de elemento (Fuego, Tierra, Planta, Agua): ");
	    String tipo = sc.nextLine().trim();
	    
	    // Normalizamos la primera letra en mayúscula y el resto en minuscula (ej: "fuego" -> "Fuego")
	    if (!tipo.isEmpty()) {
	        tipo = tipo.substring(0, 1).toUpperCase() + tipo.substring(1).toLowerCase();
	    }

	    if (!tipo.equals("Fuego") && !tipo.equals("Tierra") && !tipo.equals("Planta") && !tipo.equals("Agua")) {
	        System.out.println("Error: El tipo '" + tipo + "' no es válido. Operación cancelada.");
	        return;
	    }
	 // 3. Capturar los atributos numéricos con Try-Catch para evitar caídas
	    try {
	        System.out.print("Ingrese la cantidad de daño base: ");
	        double daño = Double.parseDouble(sc.nextLine().trim());
	        if (daño < 0) {
	            System.out.println("Error: El daño no puede ser negativo.");
	            return;
	        }
	        Hechizo nuevoHechizo = null;

	        // 4. Solicitar datos específicos según la subclase elegida
	        switch (tipo) {
	            case "Fuego":
	                System.out.print("Ingrese la duración de la quemadura: ");
	                double durQ = Double.parseDouble(sc.nextLine().trim());
	                nuevoHechizo = new HechizoFuego(nombreH, tipo, daño, durQ);
	                break;

	            case "Tierra":
	                System.out.print("Ingrese el valor de mejora de defensa: ");
	                double mejD = Double.parseDouble(sc.nextLine().trim());
	                nuevoHechizo = new HechizoTierra(nombreH, tipo, daño, mejD);
	                break;

	            case "Planta":
	                System.out.print("Ingrese la duración del stun: ");
	                double durS = Double.parseDouble(sc.nextLine().trim());
	                System.out.print("Ingrese la cantidad de plantas: ");
	                double cantP = Double.parseDouble(sc.nextLine().trim());
	                nuevoHechizo = new HechizoPlanta(nombreH, tipo, daño, durS, cantP);
	                break;

	            case "Agua":
	                System.out.print("Ingrese la cantidad de curación (Heal): ");
	                double cantH = Double.parseDouble(sc.nextLine().trim());
	                System.out.print("Ingrese el valor de la presión del agua: ");
	                double presion = Double.parseDouble(sc.nextLine().trim());
	                nuevoHechizo = new HechizoAgua(nombreH, tipo, daño, cantH, presion);
	                break;
	        }

	        // 5. Guardar en el sistema y actualizar la base de datos física (.txt)
	        if (nuevoHechizo != null) {
	            s.agregarHechizo(nuevoHechizo);
	            s.actualizarDatos(); // Reescribe el txt de hechizos automáticamente
	            System.out.println("¡El hechizo '" + nombreH + "' (" + tipo + ") fue creado y guardado exitosamente!");
	        }

	    } catch (NumberFormatException e) {
	        System.out.println("Error de formato: Ha ingresado un texto o carácter inválido en un campo numérico.");
	        System.out.println("Operación cancelada. Inténtelo nuevamente.");
	    }
	    
	    
	    
	}

	private static void modificarHechizo() {
		System.out.println("===== MODIFICANDO HECHIZO =====");
	    
	    // 1. Mostrar la lista numerada de hechizos
	    ArrayList<Hechizo> listaGlobal = s.getHechizos();
	    if (listaGlobal.isEmpty()) {
	        System.out.println("No hay hechizos registrados en el sistema.");
	        return;
	    }

	    for (int i = 0; i < listaGlobal.size(); i++) {
	        Hechizo hechizo = listaGlobal.get(i);
	        System.out.println((i + 1) + ". " + hechizo.getNombre() + " (" + hechizo.getTipo() + ")");
	    }
	    System.out.print("Seleccione el número del hechizo a modificar: ");
	    
	    Hechizo h = null;
	    try {
	        int indice = Integer.parseInt(sc.nextLine().trim()) - 1;
	        if (indice >= 0 && indice < listaGlobal.size()) {
	            h = listaGlobal.get(indice);
	        } else {
	            System.out.println("Error: Número fuera de rango.");
	            System.out.println("No se pudo realizar la modificacion. Volviendo al menu....");
	            return;
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("Error: Ingrese un número válido.");
	        System.out.println("No se pudo realizar la modificacion. Volviendo al menu....");
	        return;
	    }

	    // 2. Mostrar menú dinámico con TODAS las opciones detalladas por tipo
	    System.out.println("Modificando Hechizo: " + h.getNombre() + " (" + h.getTipo() + ")");
	    System.out.println("1. Modificar todo del hechizo");
	    System.out.println("2. Modificar SOLO Daño Base ");
	    
	    if (h instanceof HechizoFuego f) {
	        System.out.println("3. Modificar SOLO Duración Quemadura ");
	    } else if (h instanceof HechizoTierra t) {
	        System.out.println("3. Modificar SOLO Mejora de Defensa");
	    } else if (h instanceof HechizoPlanta p) {
	        System.out.println("3. Modificar EFECTOS DE PLANTA juntos (Stun y Cant. Plantas)");
	        System.out.println("4. Modificar SOLO Duración Stun");
	        System.out.println("5. Modificar SOLO Cantidad de Plantas");
	    } else if (h instanceof HechizoAgua a) {
	        System.out.println("3. Modificar EFECTOS DE AGUA juntos (Heal y Presión)");
	        System.out.println("4. Modificar SOLO Cantidad de Heal");
	        System.out.println("5. Modificar SOLO Presión de Agua");
	    }
	    
	    System.out.print("Seleccione una opción: ");
	    String opcionMod = sc.nextLine().trim();

	    // 3. Procesar los cambios con try-catch
	    try {
	        // --- OPCIÓN COMÚN 2: SOLO DAÑO BASE ---
	        if (opcionMod.equals("2")) {
	            System.out.print("Ingrese nuevo daño base: ");
	            h.setDaño(Double.parseDouble(sc.nextLine().trim()));
	            System.out.println("¡Daño base actualizado!");
	        }
	        
	        // --- PROCESAMIENTO ESPECÍFICO DE FUEGO ---
	        else if (h instanceof HechizoFuego f) {
	            if (opcionMod.equals("1")) {
	                System.out.print("Ingrese nuevo daño base: ");
	                f.setDaño(Double.parseDouble(sc.nextLine().trim()));
	                System.out.print("Ingrese nueva duración de quemadura: ");
	                f.setDuracionQuemadura(Double.parseDouble(sc.nextLine().trim()));
	                System.out.println("¡Hechizo actualizado por completo!");
	            } else if (opcionMod.equals("3")) {
	                System.out.print("Ingrese nueva duración de quemadura: ");
	                f.setDuracionQuemadura(Double.parseDouble(sc.nextLine().trim()));
	                System.out.println("¡Duración de quemadura actualizada!");
	            } else {
	                System.out.println("Opción inválida.");
	                System.out.println("No se pudo realizar la modificacion. Volviendo al menu....");
	                return;
	            }
	        }
	        
	        // --- PROCESAMIENTO ESPECÍFICO DE TIERRA ---
	        else if (h instanceof HechizoTierra t) {
	            if (opcionMod.equals("1")) {
	                System.out.print("Ingrese nuevo daño base: ");
	                t.setDaño(Double.parseDouble(sc.nextLine().trim()));
	                System.out.print("Ingrese nueva mejora de defensa: ");
	                t.setMejoraDefensa(Double.parseDouble(sc.nextLine().trim()));
	                System.out.println("¡Hechizo actualizado por completo!");
	            } else if (opcionMod.equals("3")) {
	                System.out.print("Ingrese nueva mejora de defensa: ");
	                t.setMejoraDefensa(Double.parseDouble(sc.nextLine().trim()));
	                System.out.println("¡Mejora de defensa actualizada!");
	            } else {
	                System.out.println("Opción inválida.");
	                System.out.println("No se pudo realizar la modificacion. Volviendo al menu....");
	                return;
	            }
	        }
	        
	        // --- PROCESAMIENTO ESPECÍFICO DE PLANTA ---
	        else if (h instanceof HechizoPlanta p) {
	            if (opcionMod.equals("1")) {
	                System.out.print("Ingrese nuevo daño base: ");
	                p.setDaño(Double.parseDouble(sc.nextLine().trim()));
	            }
	            
	            if (opcionMod.equals("1") || opcionMod.equals("3")) {
	                System.out.print("Ingrese nueva duración de stun: ");
	                p.setDuracionStun(Double.parseDouble(sc.nextLine().trim()));
	                System.out.print("Ingrese nueva cantidad de plantas: ");
	                p.setCantPlantas(Double.parseDouble(sc.nextLine().trim()));
	                System.out.println("¡Efectos de planta actualizados!");
	            } else if (opcionMod.equals("4")) {
	                System.out.print("Ingrese nueva duración de stun: ");
	                p.setDuracionStun(Double.parseDouble(sc.nextLine().trim()));
	                System.out.println("¡Duración de stun actualizada!");
	            } else if (opcionMod.equals("5")) {
	                System.out.print("Ingrese nueva cantidad de plantas: ");
	                p.setCantPlantas(Double.parseDouble(sc.nextLine().trim()));
	                System.out.println("¡Cantidad de plantas actualizada!");
	            } else {
	                System.out.println("Opción inválida.");
	                System.out.println("No se pudo realizar la modificacion. Volviendo al menu....");
	                return;
	            }
	        }
	        
	        // --- PROCESAMIENTO ESPECÍFICO DE AGUA ---
	        else if (h instanceof HechizoAgua a) {
	            if (opcionMod.equals("1")) {
	                System.out.print("Ingrese nuevo daño base: ");
	                a.setDaño(Double.parseDouble(sc.nextLine().trim()));
	            }
	            
	            if (opcionMod.equals("1") || opcionMod.equals("3")) {
	                System.out.print("Ingrese nueva cantidad de curación (Heal): ");
	                a.setCantHeal(Double.parseDouble(sc.nextLine().trim()));
	                System.out.print("Ingrese nueva presión de agua: ");
	                a.setPresionAgua(Double.parseDouble(sc.nextLine().trim()));
	                System.out.println("¡Efectos de agua actualizados!");
	            } else if (opcionMod.equals("4")) {
	                System.out.print("Ingrese nueva cantidad de curación (Heal): ");
	                a.setCantHeal(Double.parseDouble(sc.nextLine().trim()));
	                System.out.println("¡Cantidad de Heal actualizada!");
	            } else if (opcionMod.equals("5")) {
	                System.out.print("Ingrese nueva presión de agua: ");
	                a.setPresionAgua(Double.parseDouble(sc.nextLine().trim()));
	                System.out.println("¡Presión de agua actualizada!");
	            } else {
	                System.out.println("Opción inválida.");
	                System.out.println("No se pudo realizar la modificacion. Volviendo al menu....");
	                return;
	            }
	        }

	        // 4. Guardar los cambios directamente en el archivo
	        s.actualizarDatos();
	        
	    } catch (NumberFormatException e) {
	        System.out.println("Error: Formato numérico incorrecto. Los cambios no fueron aplicados.");
	        System.out.println("No se pudo realizar la modificacion. Volviendo al menu....");
	    }
		
	}

	private static void modificarMago() {
		System.out.println("===== MODIFICANDO MAGO =====");
	    
	    // 1. Mostrar la lista numerada de magos registrados
	    ArrayList<Mago> listaMagos = s.getMagos();
	    if (listaMagos.isEmpty()) {
	        System.out.println("No hay magos registrados en el sistema.");
	        return;
	    }

	    for (int i = 0; i < listaMagos.size(); i++) {
	        System.out.println((i + 1) + ". " + listaMagos.get(i).getNombre());
	    }
	    System.out.print("Seleccione el número del mago a modificar: ");
	    
	    Mago m = null;
	    try {
	        int indice = Integer.parseInt(sc.nextLine().trim()) - 1;
	        if (indice >= 0 && indice < listaMagos.size()) {
	            m = listaMagos.get(indice);
	        } else {
	            System.out.println("Error: Número fuera de rango.");
	            System.out.println("No se pudo realizar la modificacion. Volviendo al menu....");
	            return;
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("Error: Ingrese un número válido.");
	        System.out.println("No se pudo realizar la modificacion. Volviendo al menu....");
	        return;
	    }

	    // 2. Sub-bucle de modificación interactiva para el mago seleccionado
	    boolean finalizarModificacion = false;
	    while (!finalizarModificacion) {
	        
	        System.out.println(" ==== MODIFICANDO MAGO " + m.getNombre().toUpperCase());
	        System.out.println("Hechizos actuales: ");
	        if (m.getHechizos().isEmpty()) {
	            System.out.println("  [Ninguno]");
	        } else {
	            for (Hechizo h : m.getHechizos()) {
	                System.out.println("  - " + h.getNombre() + " (" + h.getTipo() + ")");
	            }
	        }
	       System.out.println();
	        System.out.println("1. Cambiar nombre del mago");
	        System.out.println("2. Agregarle un hechizo");
	        System.out.println("3. Eliminarle un hechizo");
	        System.out.println("4. Terminar modificacion");
	        System.out.print("Seleccione una opción: ");
	        
	        String subOpcion = sc.nextLine().trim();
	        
	        switch (subOpcion) {
	            case "1":
	                System.out.print("Ingrese el nuevo nombre para el mago: ");
	                String nuevoNombre = sc.nextLine().trim();
	                
	                if (nuevoNombre.isEmpty()) {
	                    System.out.println("Error: El nombre no puede estar vacío.");
	                } else if (s.buscarMago(nuevoNombre) != null && !nuevoNombre.equalsIgnoreCase(m.getNombre())) {
	                    System.out.println("Error: Ya existe otro mago registrado con el nombre '" + nuevoNombre + "'.");
	                } else {
	                    m.setNombre(nuevoNombre);
	                    s.actualizarDatos(); // Persistencia inmediata en el archivo txt
	                    System.out.println("¡Nombre modificado exitosamente!");
	                }
	                break;
	                
	            case "2":
	                // Utilizamos el selector que oculta los hechizos que el mago ya posee
	                Hechizo hechizoAAñadir = seleccionarHechizo(m);
	                if (hechizoAAñadir != null) {
	                    m.agregarHechizo(hechizoAAñadir);
	                    s.actualizarDatos();
	                    System.out.println("¡Hechizo '" + hechizoAAñadir.getNombre() + "' agregado con éxito!");
	                } else {
	                    System.out.println("Operación cancelada o no hay hechizos nuevos disponibles.");
	                }
	                break;
	                
	            case "3":
	                // Validar la regla de negocio: al menos 1 hechizo obligatorio
	                if (m.getHechizos().size() <= 1) {
	                    System.out.println("Error: El mago solo posee 1 hechizo. Por regla de negocio, no puede quedar sin hechizos.");
	                    break;
	                }
	                
	                System.out.println("--- SELECCIONE EL HECHIZO A ELIMINAR ---");
	                ArrayList<Hechizo> hechizosMago = m.getHechizos();
	                for (int i = 0; i < hechizosMago.size(); i++) {
	                    System.out.println((i + 1) + ". " + hechizosMago.get(i).getNombre());
	                }
	                System.out.print("Ingrese el número del hechizo que desea quitarle: ");
	                
	                try {
						int idxH = Integer.parseInt(sc.nextLine().trim()) - 1;
						if (idxH >= 0 && idxH < m.getHechizos().size()) {
							String nombreRemovido = m.getHechizos().get(idxH).getNombre();
							m.removerHechizoPorIndice(idxH); // Invocación encapsulada
							s.actualizarDatos();
							System.out.println("¡Hechizo '" + nombreRemovido + "' removido del mago exitosamente!");
						} else {
							System.out.println("Error: Número fuera de rango.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Error: Entrada inválida.");
						System.out.println("No se pudo realizar la modificacion. Volviendo al menu....");
					}
	                break;
	                
	            case "4":
	                System.out.println("Finalizando los cambios para " + m.getNombre() + "...");
	                finalizarModificacion = true;
	                break;
	                
	            default:
	                System.out.println("Opción inválida. Intente nuevamente.");
	                break;
	        }
	    }
		

	
	}

	private static void nuevoMago() {
		System.out.println("==== CREANDO UN NUEVO MAGO ====");
		System.out.print("Ingrese el nombre del mago: ");
		String nombreM = sc.nextLine().trim();
		if (s.buscarMago(nombreM)!=null) {
			System.out.println("Error: Ya existe un mago registrado con ese nombre.");
	        return;
		}
		Mago m= new Mago(nombreM);
		
		ArrayList<Hechizo> listaHechizos = s.getHechizos();
		
		while (m.getHechizos().isEmpty()) {
	        System.out.println("ADVERTENCIA: Un mago debe dominar al menos un hechizo obligatoriamente.");
	        
	        Hechizo hSelected = seleccionarHechizo(m);
	        
	        if (hSelected != null) {
	            m.agregarHechizo(hSelected);
	            System.out.println("¡'" + hSelected.getNombre() + "' asignado con éxito!");
	        } else {
	            System.out.println("Selección inválida. Intente nuevamente.");
	        }
	    }
			
		System.out.println("¿Desea asignarle más hechizos al mago? (si/no)");
	    String rpta = sc.nextLine().trim();
	    while (rpta.equalsIgnoreCase("si")) {
	        Hechizo hSelected = seleccionarHechizo(m);
	        
	        if (hSelected != null) {
	            m.agregarHechizo(hSelected);
	            System.out.println("¡Hechizo añadido!");
	        } else {
	            System.out.println("Selección inválida.");
	        }
	        System.out.println("¿Desea añadir otro hechizo? (si/no)");
	        rpta = sc.nextLine().trim();
	    }

	    s.agregarMago(m);
	    s.actualizarDatos();
	    System.out.println("¡Mago registrado y guardado exitosamente!");
		
		
		
		
	}
	
	private static Hechizo seleccionarHechizo(Mago mago) {
	    ArrayList<Hechizo> listaGlobal = s.getHechizos();
	    // Creamos una lista temporal para guardar solo los que el mago NO tiene
	    ArrayList<Hechizo> hechizosDisponibles = new ArrayList<>();

	    for (Hechizo h : listaGlobal) {
	        // Si el mago NO tiene este hechizo en su lista, lo consideramos disponible
	        if (!mago.getHechizos().contains(h)) {
	            hechizosDisponibles.add(h);
	        }
	    }

	    if (hechizosDisponibles.isEmpty()) {
	        System.out.println("Este mago ya domina todos los hechizos existentes en el sistema.");
	        return null;
	    }

	    System.out.println("\n--- HECHIZOS DISPONIBLES PARA " + mago.getNombre().toUpperCase() + " ---");
	    for (int i = 0; i < hechizosDisponibles.size(); i++) {
	        Hechizo h = hechizosDisponibles.get(i);
	        System.out.println((i + 1) + ". " + h.getNombre() + " (" + h.getTipo() + ")");
	    }
	    System.out.print("Seleccione el número del hechizo a añadir: ");

	    try {
	        int indice = Integer.parseInt(sc.nextLine().trim()) - 1;

	        if (indice >= 0 && indice < hechizosDisponibles.size()) {
	            return hechizosDisponibles.get(indice); // Retorna el objeto real filtrado
	        } else {
	            System.out.println("Error: Número fuera de rango.");
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("Error: Ingrese un número válido.");
	    }

	    return null;
	}

	
	//PANEL ANALISTA
	
	private static void menuAnalista() {
		String opcion = "";
	    do {
	        System.out.println("===== MENU ANALISTA =====");
	        System.out.println("1. Top 10 Mejores Hechizos");
	        System.out.println("2. Top 3 Mejores Magos");
	        System.out.println("3. Mostrar todos los Hechizos");
	        System.out.println("4. Mostrar todos los Magos");
	        System.out.println("5. Mostrar todos los Hechizos junto a su puntuación");
	        System.out.println("6. Mostrar todos los magos junto a su puntuación");
	        System.out.println("7. Volver al menú principal");
	        System.out.print("> ");
	        
	        opcion = sc.nextLine().trim();
	        switch (opcion) {
	            case "1":
	                analistaTop10Hechizos();
	                break;
	            case "2":
	                analistaTop3Magos();
	                break;
	            case "3":
	                analistaMostrarTodosHechizos(false); // false = sin puntaje
	                break;
	            case "4":
	                analistaMostrarTodosMagos(false); // false = sin puntaje
	                break;
	            case "5":
	                analistaMostrarTodosHechizos(true); // true = con puntaje
	                break;
	            case "6":
	                analistaMostrarTodosMagos(true); // true = con puntaje
	                break;
	            case "7":
	                System.out.println("Volviendo al menú principal....");
	                break;
	            default:
	                System.out.println("Ingrese una opción válida.");
	                break;
	        }
	    } while (!opcion.equals("7"));
		
		
	}
	
	// TOP 10 HECHIZOS
	private static void analistaTop10Hechizos() {
	    System.out.println("===== TOP 10 MEJORES HECHIZOS =====");
	    ArrayList<Hechizo> top10 = s.obtenerTop10Hechizos();
	    
	    if (top10.isEmpty()) {
	        System.out.println("No hay hechizos registrados en el sistema.");
	        return;
	    }
	    
	    for (int i = 0; i < top10.size(); i++) {
	        Hechizo h = top10.get(i);
	        System.out.println((i + 1) + ". " + h.getNombre() + " (" + h.getTipo() + ") - Puntaje: " + h.calcularPuntaje());
	    }
	}

	// TOP 3 MEJORES MAGOS
	private static void analistaTop3Magos() {
	    System.out.println("===== TOP 3 MEJORES MAGOS =====");
	    ArrayList<Mago> top3 = s.obtenerTop3Magos();
	    
	    if (top3.isEmpty()) {
	        System.out.println("No hay magos registrados en el sistema.");
	        return;
	    }
	    
	    for (int i = 0; i < top3.size(); i++) {
	        Mago m = top3.get(i);
	        System.out.println((i + 1) + ". Mago: " + m.getNombre() + " - Puntaje Total: " + m.calcularPuntaje());
	    }
	}
	
	// MOSTRAR TODOS LOS HECHIZOS 
	private static void analistaMostrarTodosHechizos(boolean mostrarPuntaje) {
		System.out.println("===== CATALOGO GENERAL DE HECHIZOS =====");
	    System.out.print(s.obtenerCatalogoHechizosTexto(mostrarPuntaje));
	}

	// MOSTRAR TODOS LOS MAGOS 
	private static void analistaMostrarTodosMagos(boolean mostrarPuntaje) {
		System.out.println("===== REGISTRO GENERAL DE MAGOS =====");
	    System.out.print(s.obtenerRegistroMagosTexto(mostrarPuntaje));
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
