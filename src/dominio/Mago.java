package dominio;

import java.util.ArrayList;

public class Mago implements Calculable {

	private String nombre;
	private ArrayList<Hechizo> hechizos;

	public Mago(String nombre) {
		this.nombre = nombre;
		this.hechizos = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Hechizo> getHechizos() {
		return hechizos;
	}

	public void agregarHechizo(Hechizo hechizo) {
		for (Hechizo h : hechizos) {
			if (h.getNombre().equalsIgnoreCase(hechizo.getNombre())) {
				return;
			}
		}
		hechizos.add(hechizo);
	}

	public Hechizo buscarHechizo(String nombreHechizo) {
		for (Hechizo h : hechizos) {
			if (h.getNombre().equalsIgnoreCase(nombreHechizo)) {
				return h;
			}
		}
		return null;
	}

	// Calcula el puntaje total del mago sumando los puntajes de cada uno de sus
	// hechizos
	@Override
	public double calcularPuntaje() {
		double total = 0;
		for (Hechizo h : hechizos) {
			total += h.calcularPuntaje();
		}
		return total;
	}

}
