package models;

import java.util.ArrayList;
import java.util.Scanner;

import models.TipoPokemon.Tipo;
import utils.Estado;

public class Equipo {
	private ArrayList<Pokemon> equipo;

	/**
	 * Constructor del objeto 'Equipo'. Se crea vacío
	 */
	protected Equipo() {
		this.equipo = new ArrayList<Pokemon>();
	}

	// Getters - Setters
	public ArrayList<Pokemon> getEquipo() {
		return this.equipo;
	}

	// Methods
	public void mostrarEquipo() {
		for (int i = 0; i < this.equipo.size(); i++) {
			if (i % 2 == 0)
				System.out.println();

			if (this.equipo.get(i).getTipo2() == Tipo.Ninguno) {
				if (this.equipo.get(i).getEstado() == Estado.Ninguno) {
					System.out.print(
							(i + 1) + "." + this.equipo.get(i).getNombre() + "  " + this.equipo.get(i).getTipo1() + "  "
									+ this.equipo.get(i).getActualHp() + "/" + this.equipo.get(i).getMaxHP() + "   \t");
				} else {
					System.out.print((i + 1) + "." + this.equipo.get(i).getNombre() + "  "
							+ this.equipo.get(i).getTipo1() + "  " + this.equipo.get(i).getActualHp() + "/"
							+ this.equipo.get(i).getMaxHP() + " (" + this.equipo.get(i).getEstado() + ")   \t");
				}
			} else {
				if (this.equipo.get(i).getEstado() == Estado.Ninguno) {
					System.out.print((i + 1) + "." + this.equipo.get(i).getNombre() + "  "
							+ this.equipo.get(i).getTipo1() + " " + this.equipo.get(i).getTipo2() + "  "
							+ this.equipo.get(i).getActualHp() + "/" + this.equipo.get(i).getMaxHP() + "   \t");
				} else {
					System.out.print(
							(i + 1) + "." + this.equipo.get(i).getNombre() + "  " + this.equipo.get(i).getTipo1() + " "
									+ this.equipo.get(i).getTipo2() + "  " + this.equipo.get(i).getActualHp() + "/"
									+ this.equipo.get(i).getMaxHP() + " (" + this.equipo.get(i).getEstado() + ")   \t");
				}
			}
		}
		System.out.println();
	}

	public void meterPokemon(Pokemon pokemon) {
		if (this.equipo.size() == 6) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			int poke, opc;
			boolean elegido;

			if (this.equipo.size() == 6) {
				System.out.println("Equipo completo. ¿Cual deseas quitar?");

				do {
					mostrarEquipo();
					System.out.print("\n->: ");
					poke = Integer.parseInt(sc.next());

					if (poke > this.equipo.size() || poke < 1) {
						elegido = false;
						System.out.println("\n\nElige un numero valido\n");
					} else {
						System.out.println("\n\n¿Quitar " + this.equipo.get(poke).getNombre() + "?");
						System.out.println("\n1.Si\n2.No\n");
						System.out.print("->: ");
						opc = Integer.parseInt(sc.next());

						if (opc < 1 || opc > 2) {
							elegido = false;
							System.out.println("\n\nElige un numero valido\n");
						} else if (opc == 1) {
							elegido = true;
							this.equipo.add(poke, pokemon);
							this.equipo.remove(poke - 1);
						} else {
							elegido = false;
						}
					}
				} while (!elegido);
			}
		} else {
			this.equipo.add(pokemon);
		}
	}
}
