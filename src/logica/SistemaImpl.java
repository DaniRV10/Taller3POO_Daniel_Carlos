package logica;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dominio.*;

public class SistemaImpl implements ISistema{
	
	private ArrayList<Hechizo> hechizos;
	private ArrayList<Mago> magos;

	

	public SistemaImpl() {
		this.hechizos = new ArrayList<>();
		this.magos = new ArrayList<>();
	}
	
	
	public ArrayList<Hechizo> getHechizos() {
		return hechizos;
	}
	public ArrayList<Mago> getMagos() {
		return magos;
	}

	
	// HECHIZOS

	public Hechizo buscarHechizo(String nombre) {
        for (Hechizo h : hechizos) {
            if (h.getNombre().equalsIgnoreCase(nombre)) return h;
        }
        return null;
    } 

	public void agregarHechizo(Hechizo h) {
		hechizos.add(h);
	}
	
	@Override
	public boolean eliminarHechizo(Hechizo h) {
		if (h == null) return false;

	    // 1. Primero removemos el hechizo de todos los magos buscando por NOMBRE
	    for (Mago m : this.magos) {
	        // Recorremos los hechizos del mago al revés para evitar errores al remover elementos de la lista
	        ArrayList<Hechizo> hechizosMago = m.getHechizos();
	        for (int i = hechizosMago.size() - 1; i >= 0; i--) {
	            if (hechizosMago.get(i).getNombre().equalsIgnoreCase(h.getNombre())) {
	                hechizosMago.remove(i); // Lo borra de la lista del mago de forma segura
	            }
	        }
	    }

	    // 2. Ahora lo removemos de la lista global del catálogo
	    return this.hechizos.remove(h);
	}


// MAGOS
	@Override
	public void agregarMago(Mago m) {
		magos.add(m);
	}
	
	@Override
	public boolean eliminarMago(Mago m) {
		return this.magos.remove(m);
	}



	@Override
	public Mago buscarMago(String nombre) {
		for (Mago m : magos) {
            if (m.getNombre().equalsIgnoreCase(nombre)) return m;
        }
        return null;
	}
	


	public void actualizarDatos() {
	    try {
	        // 1. GUARDAR HECHIZOS
	    	BufferedWriter escritorH = new BufferedWriter(new FileWriter("txts/Hechizos.txt"));
	        for (Hechizo h : this.hechizos) {
	            escritorH.write(h.datosParaTXT());
	            escritorH.newLine();
	        }
	        escritorH.close();

	        // 2. GUARDAR MAGOS
	        BufferedWriter escritorM = new BufferedWriter(new FileWriter("txts/Magos.txt"));
	        for (Mago m : this.magos) {
	            String linea = m.getNombre() + ";";
	            
	            // Construimos la cadena de hechizos de forma tradicional
	            ArrayList<Hechizo> hechizosMago = m.getHechizos();
	            for (int i = 0; i < hechizosMago.size(); i++) {
	                linea += hechizosMago.get(i).getNombre();
	                if (i < hechizosMago.size() - 1) {
	                    linea += "|";
	                }
	            }
	            
	            escritorM.write(linea);
	            escritorM.newLine();
	        }
	        escritorM.close();

	    } catch (IOException e) {
	        System.out.println("Error al guardar los archivos");
	    }
	}


	


	

	


	

	
	
}
