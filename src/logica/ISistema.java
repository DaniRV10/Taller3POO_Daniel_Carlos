package logica;
import dominio.*;

public interface ISistema {
	
	void actualizarDatos();
	
	// MAGOS
    void agregarMago(Mago m);
    Mago buscarMago(String nombre);
    boolean eliminarMago(Mago m);
    
    
    // HECHIZOS
    void agregarHechizo(Hechizo h);
    Hechizo buscarHechizo(String nombre);
    boolean eliminarHechizo(Hechizo h);
    
    
}
