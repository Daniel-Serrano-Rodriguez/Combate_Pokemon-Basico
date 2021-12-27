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

	/**
	 * Constructor del objeto 'Entrenador'. Se crea con un nombre y un equipo vacio
	 * 
	 * @param nombre String que representa el nombre del Entrenador
	 */
	protected Entrenador() {
		this.nombre = "";
		this.equipo = new Equipo();
	}

	// Getters - Setters
	protected String getNombre() {
		return this.nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Methods
	protected void meterPkmnEquipo(Pokemon pokemon) {
		this.equipo.meterPokemon(pokemon);
	}
}
