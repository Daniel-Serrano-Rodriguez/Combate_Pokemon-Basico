package models;

public class Entrenador {
	private Equipo equipo;
	private String nombre;

	/**
	 * Constructor del objeto 'Entrenador'. Se crea con un equipo vacio
	 * 
	 * @param nombre String que representa el nombre del Entrenador
	 */
	public Entrenador(String nombre) {
		this.nombre = nombre;
		this.equipo = new Equipo();
	}
	
	// Getters - Setters
	public String getNombre() {
		return this.nombre;
	}
	
	// Methods
	public void meterPkmnEquipo(Pokemon pokemon) {
		this.equipo.meterPokemon(pokemon);
	}
}
