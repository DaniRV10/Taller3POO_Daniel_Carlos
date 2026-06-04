package dominio;

public abstract class Hechizo implements Calculable {
	
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

	public abstract String datosParaTXT();
	
	
	

}
