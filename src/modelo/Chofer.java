package modelo;

import java.util.ArrayList;


public abstract class Chofer {
	protected String dni;
	protected String nombre;
	private int puntaje;
	ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	
	//private int calificacion;

	public Chofer(String dni, String nombre) {
		this.dni = dni;
		this.nombre = nombre;
	}
	
	public abstract double getSueldo();

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Viaje> getViajes() {
		return viajes;
	}

	public void agregarViaje(Viaje viaje) {
		this.viajes.add(viaje);
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	/*getCalificacion()- setCalificacion()*/
}
