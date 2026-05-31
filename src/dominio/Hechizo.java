package dominio;

public abstract class Hechizo {
	
	private String nombre;
	private String tipo;
	private double daño;
	
	public Hechizo(String nombre, String tipo, double daño) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.daño = daño;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public double getDaño() {
		return daño;
	}

	@Override
	public String toString() {
		return "Hechizo [nombre=" + nombre + ", tipo=" + tipo + ", daño=" + daño + "]";
	}
	
	
	

}
