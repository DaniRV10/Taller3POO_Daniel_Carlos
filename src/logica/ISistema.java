package logica;
import dominio.*;

public interface ISistema {
	
	void agregarHechizo(Hechizo h);
    void agregarMago(Mago m);
    Hechizo buscarHechizo(String nombre);
    Mago buscarMago(String nombre);
}
