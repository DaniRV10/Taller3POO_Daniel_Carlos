package dominio;

public class HechizoPlanta extends Hechizo {
	
	private double duracionStun;
	private double cantPlantas;
	

	public HechizoPlanta(String nombre, String tipo, double daño, double duracionStun, double cantPlantas) {
		super(nombre, tipo, daño);
		this.cantPlantas = cantPlantas;
		this.duracionStun = duracionStun;
	}


	public double getDuracionStun() {
		return duracionStun;
	}


	public void setDuracionStun(double duracionStun) {
		this.duracionStun = duracionStun;
	}


	public double getCantPlantas() {
		return cantPlantas;
	}


	public void setCantPlantas(double cantPlantas) {
		this.cantPlantas = cantPlantas;
	}

}
