package utils;

import java.util.ArrayList;

import models.AbstractMove;
import models.Pokemon;
import models.TipoPokemon.Tipo;

public class Almacen {
	public static ArrayList<Pokemon> almacenPokemon = new ArrayList<Pokemon>();
	public static ArrayList<AbstractMove> almacenMovimientos = new ArrayList<AbstractMove>();

	public static void mostrarPokemons() {
		for (int i = 0; i < almacenPokemon.size(); i++) {
			if (i % 3 == 0)
				System.out.println();

			if (almacenPokemon.get(i).getTipo2() == Tipo.Ninguno) {
				System.out.print((i + 1) + "." + almacenPokemon.get(i).getNombre() + "  "
						+ almacenPokemon.get(i).getTipo1() + "    \t");
			} else {
				System.out.print((i + 1) + "." + almacenPokemon.get(i).getNombre() + "  "
						+ almacenPokemon.get(i).getTipo1() + " " + almacenPokemon.get(i).getTipo2() + "  \t");
			}
		}
		System.out.println("\n");
	}

	public static void mostrarMovimientos() {
		for (int i = 0; i < almacenMovimientos.size(); i++) {
			if (i % 3 == 0)
				System.out.println();

			System.out.print((i + 1) + "." + almacenMovimientos.get(i).getNombre() + " Daño: "
					+ almacenMovimientos.get(i).getDamage() + " PP: " + almacenMovimientos.get(i).getMaxPP()
					+ " Descripcion: " + almacenMovimientos.get(i).getDescripcion());
		}
		System.out.println("\n");
	}
}
