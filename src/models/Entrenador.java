package models;

import java.util.ArrayList;

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
	public Entrenador() {
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

	protected ArrayList<Pokemon> getEquipo() {
		return this.equipo.getEquipo();
	}

	// Methods
	protected void meterPkmnEquipo(Pokemon pokemon) {
		this.equipo.meterPokemon(pokemon);
	}

	/**
	 * Funcion que comprueba que el entrenador tiene al menos 1 pokemon capaz de
	 * combatir
	 * 
	 * @return Booleano
	 */
	protected boolean puedeCombatir() {
		int comprobarOk = 0;

		for (Pokemon pokemon : this.equipo.getEquipo()) {
			if (pokemon.getActualHp() < 0) {
				comprobarOk++;
			}
		}

		if (comprobarOk < this.equipo.getEquipo().size()) {
			return true;
		} else {
			return false;
		}
	}
}
