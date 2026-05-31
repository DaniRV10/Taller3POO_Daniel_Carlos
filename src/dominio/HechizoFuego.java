package dominio;

public class HechizoFuego extends Hechizo {
	
	private double duracionQuemadura;

	public HechizoFuego(String nombre, String tipo, double daño, double duracionQuemadura) {
		super(nombre, tipo, daño);
		this.duracionQuemadura = duracionQuemadura;
	}

	public double getDuracionQuemadura() {
		return duracionQuemadura;
	}

	public void setDuracionQuemadura(double duracionQuemadura) {
		this.duracionQuemadura = duracionQuemadura;
	}

	
}
