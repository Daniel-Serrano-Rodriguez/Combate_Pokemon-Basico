package models;

import java.util.ArrayList;
import java.util.Scanner;

import models.TipoPokemon.Tipo;
import utils.Estado;

public class Pokemon {
	private Tipo tipo1, tipo2;
	private Estado estado;
	private ArrayList<AbstractMove> movimientos;
	private String nombre;
	private int maxHP, actualHp, numero, level, attack, defence, spAttack, spDefence, speed;

	/**
	 * Constructor de objeto 'Pokemon'
	 * 
	 * @param tipo1     Enum 'TipoPokemon.Tipo' que reresenta el primer tipo del
	 *                  Pokemon
	 * @param tipo2     Enum 'TipoPokemon.Tipo' que reresenta el segundo tipo del
	 *                  Pokemon
	 * @param estado    Enum 'Estado' que representa el estado que tiene aplicado el
	 *                  Pokemon
	 * @param nombre    String que representa el nombre del Pokemon
	 * @param maxHP     Entero que representa la vida maxima del Pokemon
	 * @param numero    Entero que representa el numero de la Pokedex del Pokemon
	 * @param level     Entero que representa el nivel del Pokemon
	 * @param attack    Entero que representa la potencia de ataque del Pokemon
	 * @param defence   Entero que representa la defensa del Pokemon
	 * @param spAttack  Entero que representa la potencia de ataque especial del
	 *                  Pokemon
	 * @param spDefence Entero que representa la defense especila del Pokemon
	 * @param speed     Entero que representa la velocidad del pokemon
	 */
	public Pokemon(Tipo tipo1, Tipo tipo2, Estado estado, String nombre, int maxHP, int numero, int level, int attack,
			int defence, int spAttack, int spDefence, int speed) {
		super();
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
		this.estado = estado;
		this.movimientos = new ArrayList<AbstractMove>();
		this.nombre = nombre;
		this.maxHP = maxHP;
		this.actualHp = maxHP;
		this.numero = numero;
		this.level = level;
		this.attack = attack;
		this.defence = defence;
		this.spAttack = spAttack;
		this.spDefence = spDefence;
		this.speed = speed;
	}

	// Getters - Setters
	public Tipo getTipo1() {
		return tipo1;
	}

	public void setTipo1(Tipo tipo1) {
		this.tipo1 = tipo1;
	}

	public Tipo getTipo2() {
		return tipo2;
	}

	public void setTipo2(Tipo tipo2) {
		this.tipo2 = tipo2;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public ArrayList<AbstractMove> getMovimientos() {
		return movimientos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getActualHp() {
		return actualHp;
	}

	public void setActualHp(int actualHp) {
		this.actualHp = actualHp;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getSpAttack() {
		return spAttack;
	}

	public void setSpAttack(int spAttack) {
		this.spAttack = spAttack;
	}

	public int getSpDefence() {
		return spDefence;
	}

	public void setSpDefence(int spDefence) {
		this.spDefence = spDefence;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	// Methods
	public void mostrarMovimientos() {
		for (int i = 0; i < this.movimientos.size(); i++) {
			if (i % 2 == 0) {
				System.out.println();
			}
			System.out.print((i + 1) + ". " + this.movimientos.get(i).getNombre() + "   "
					+ this.movimientos.get(i).getActPP() + "/" + this.movimientos.get(i).getMaxPP() + " PP    \t");
		}
		System.out.println("\n");
	}

	public AbstractMove elegirMovimiento() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int move;
		boolean elegido;

		do {
			mostrarMovimientos();
			System.out.print("->: ");
			move = Integer.parseInt(sc.next());

			if (move > this.movimientos.size() || move < 1) {
				elegido = false;
				System.out.println("\n\nElige un numero valido\n");
			} else {
				elegido = true;
				return this.movimientos.get(move);
			}
		} while (!elegido);
		return null;
	}

	public void aprenderMovimiento(AbstractMove aprender) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int move, opc;
		boolean elegido;

		if (this.movimientos.size() == 4) {
			System.out.println(this.nombre + " ya sabe 4 movimientos. ¿Cual deseas que " + this.nombre + " olvide?");

			do {
				mostrarMovimientos();
				System.out.print("->: ");
				move = Integer.parseInt(sc.next());

				if (move > this.movimientos.size() || move < 1) {
					elegido = false;
					System.out.println("\n\nElige un numero valido\n");
				} else {
					System.out.println("\n\n¿Olvidar " + this.movimientos.get(move).getNombre() + "?");
					System.out.println("\n1.Si\n2.No\n");
					System.out.print("->: ");
					opc = Integer.parseInt(sc.next());

					if (opc < 1 || opc > 2) {
						elegido = false;
						System.out.println("\n\nElige un numero valido\n");
					} else if (opc == 1) {
						elegido = true;
						this.movimientos.add(move, aprender);
						this.movimientos.remove(move-1);
					} else {
						elegido = false;
					}
				}
			} while (!elegido);
		} else {
			this.movimientos.add(aprender);
		}
	}
}
