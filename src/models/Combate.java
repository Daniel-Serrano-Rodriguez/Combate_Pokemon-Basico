package models;

import java.util.ArrayList;
import java.util.Scanner;

import utils.CondArena;
import utils.CondPosiPkmn;

public class Combate {
	private Entrenador entrenador1, entrenador2;
	private ArrayList<Pokemon> pokemon1, pokemon2;
	private CondArena condArena;
	private CondPosiPkmn condPoke1, condPoke2;

	/**
	 * Constructor del objeto 'Combate'. Se crean 2 arrayList vacios que representan
	 * los Pokemon actualmente en la arena
	 */
	public Combate(Entrenador entrenador1, Entrenador entrenador2) {
		this.entrenador1 = entrenador1;
		this.entrenador2 = entrenador2;
		this.pokemon1 = new ArrayList<Pokemon>();
		this.pokemon2 = new ArrayList<Pokemon>();
		this.condArena = CondArena.Ninguno;
		this.condPoke1 = CondPosiPkmn.Ninguno;
		this.condPoke2 = CondPosiPkmn.Ninguno;
	}

	/**
	 * Constructor del objeto 'Combate'. Se crean 2 entrenadores que han de ser
	 * creados y 2 arrayList vacios que representan los Pokemon actualmente en la
	 * arena
	 */
	public Combate() {
		this.entrenador1 = new Entrenador();
		this.entrenador2 = new Entrenador();
		this.pokemon1 = new ArrayList<Pokemon>();
		this.pokemon2 = new ArrayList<Pokemon>();
		this.condArena = CondArena.Ninguno;
	}

	// Getters - Setters
	protected Entrenador getEntrenador1() {
		return entrenador1;
	}

	protected Entrenador getEntrenador2() {
		return entrenador2;
	}

	protected ArrayList<Pokemon> getPokemon1() {
		return pokemon1;
	}

	protected ArrayList<Pokemon> getPokemon2() {
		return pokemon2;
	}

	protected CondArena getCondArena() {
		return condArena;
	}

	protected void setCondArena(CondArena condArena) {
		this.condArena = condArena;
	}

	protected CondPosiPkmn getCondPoke1() {
		return condPoke1;
	}

	protected void setCondPoke1(CondPosiPkmn condPoke1) {
		this.condPoke1 = condPoke1;
	}

	protected CondPosiPkmn getCondPoke2() {
		return condPoke2;
	}

	protected void setCondPoke2(CondPosiPkmn condPoke2) {
		this.condPoke2 = condPoke2;
	}

	// Methods
	public void setEntrenadores() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.print("Nombre del primer entrenador: ");
		this.entrenador1.setNombre(sc.nextLine());
		System.out.print("Nombre del segundo entrenador: ");
		this.entrenador2.setNombre(sc.nextLine());

		setPokemon(entrenador1);
		setPokemon(entrenador2);
	}

	private void setPokemon(Entrenador entrenador) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int opc;
		boolean elegido;

		System.out.println("Elige tu Pokemon, " + entrenador.getNombre());
		do {
			utils.Almacen.mostrarPokemons();
			System.out.print("->: ");
			opc = Integer.parseInt(sc.nextLine());
			if (opc < 1 || opc > utils.Almacen.almacenPokemon.size()) {
				elegido = false;
				System.out.println("\n\nElige un numero correcto\n");
			} else {
				elegido = true;
				setPkmnMoves(utils.Almacen.almacenPokemon.get(opc));
				entrenador.meterPkmnEquipo(utils.Almacen.almacenPokemon.get(opc));
				utils.Almacen.almacenPokemon.remove(opc);
			}
		} while (!elegido);
	}

	private void setPkmnMoves(Pokemon pokemon) {
		for (int i = 0; i < 4; i++) {
			pokemon.aprenderMovimiento(utils.Almacen.almacenMovimientos
					.get((int) (Math.random() * utils.Almacen.almacenMovimientos.size())));
		}
	}
	/*
	 * TODO En el combate: si condArena != CondArena.Ninguno, aplicar el daño y las
	 * condiciones que sean Si condPoke1/2 != CondPosiPoke.Ninguno, aplicar el daño
	 * y las condiciones que sean
	 */
}
