package dominio;
/**
 * Interfaz que define el comportamiento del calculo de la puntuacion.
 * Todas las clases que implementen esta interfaz deben definir como calcular su puntaje.
 */
public interface Calculable {
	
	/**
     * Calcula y retorna la puntuacion.
     * @return puntuacion calculada segun las reglas de cada tipo.
     */
	double calcularPuntaje();
}
