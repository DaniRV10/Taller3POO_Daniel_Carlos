package dominio;

public class HechizoAgua extends Hechizo {
	
	private double cantHeal;
	private double presionAgua;

	public HechizoAgua(String nombre, String tipo, double daño,double cantHeal,double presionAgua) {
		super(nombre, tipo, daño);
		this.cantHeal = cantHeal;
		this.presionAgua = presionAgua;
	}

	public double getCantHeal() {
		return cantHeal;
	}

	public void setCantHeal(double cantHeal) {
		this.cantHeal = cantHeal;
	}

	public double getPresionAgua() {
		return presionAgua;
	}

	public void setPresionAgua(double presionAgua) {
		this.presionAgua = presionAgua;
	}

	@Override
	public double calcularPuntaje() {
        return (getDaño() + cantHeal + presionAgua) * 2.0;
	}

	@Override
	public String datosParaTXT() {
		return this.getNombre() + ";Agua;" + (int)this.getDaño() + ";" + (int)this.cantHeal + "," + (int)this.presionAgua;
	}

	
}
