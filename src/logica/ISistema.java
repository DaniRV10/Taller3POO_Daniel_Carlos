package logica;
import java.util.ArrayList;

import dominio.*;

public interface ISistema {
	
	void actualizarDatos();
	
	// MAGOS
    void agregarMago(Mago m);
    Mago buscarMago(String nombre);
    boolean eliminarMago(Mago m);
    ArrayList<Mago> obtenerTop3Magos();
    String obtenerRegistroMagosTexto(boolean mostrarPuntaje);

    
    
    // HECHIZOS
    void agregarHechizo(Hechizo h);
    Hechizo buscarHechizo(String nombre);
    boolean eliminarHechizo(Hechizo h);
    ArrayList<Hechizo> obtenerTop10Hechizos();
    String obtenerCatalogoHechizosTexto(boolean mostrarPuntaje);
    
    
}
