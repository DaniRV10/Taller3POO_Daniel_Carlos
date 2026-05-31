package dominio;

public class HechizoTierra extends Hechizo {
	
	private double mejoraDefensa;

	public HechizoTierra(String nombre, String tipo, double daño, double mejoraDefensa) {
		super(nombre, tipo, daño);
		this.mejoraDefensa = mejoraDefensa;
		
	}

	public double getMejoraDefensa() {
		return mejoraDefensa;
	}

	public void setMejoraDefensa(double mejoraDefensa) {
		this.mejoraDefensa = mejoraDefensa;
	}

	@Override
	public double calcularPuntaje() {
		return (getDaño()*mejoraDefensa) / 2.0;
	}

}
