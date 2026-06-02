package logica;
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
	public void agregarMago(Mago m) {
		magos.add(m);
	}



	@Override
	public Mago buscarMago(String nombre) {
		for (Mago m : magos) {
            if (m.getNombre().equalsIgnoreCase(nombre)) return m;
        }
        return null;
	}

	
	
}
