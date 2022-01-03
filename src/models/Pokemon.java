package models;

import java.util.ArrayList;
import java.util.Scanner;

import models.TipoPokemon.Tipo;
import utils.CondPosiPkmn;
import utils.Estado;

public class Pokemon implements Cloneable {
	private Tipo tipo1, tipo2;
	private Estado estado;
	private ArrayList<Move> movimientos;
	private ArrayList<CondPosiPkmn> pkmnCond;
	private ArrayList<Integer> durPkmnCond;
	private Entrenador entrenador;
	private String nombre;
	private int maxHP, actualHp, numero, level, speed, idPelea, turnosEstado, cntChngAtk, cntChngSpAtk, cntChngDef,
			cntChngSpDef, cntChngSpe;
	private double attack, defence, spAttack, spDefence;
	/**
	 * posicion indica la posicón que ocupa el pokemon en el combate, 0 para 1vs1,
	 * 0-1 para 2vs2 y de 0 a 2 para 3vs3
	 * 
	 * ataca indica la posicion del atacante
	 */
	private int posicion, ataca;

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
	 * @param numero    Entero que representa el numero de la Pokedex del Pokemon
	 * @param level     Entero que representa el nivel del Pokemon
	 * @param maxHP     Entero que representa la vida maxima del Pokemon
	 * @param attack    Entero que representa la potencia de ataque del Pokemon
	 * @param defence   Entero que representa la defensa del Pokemon
	 * @param spAttack  Entero que representa la potencia de ataque especial del
	 *                  Pokemon
	 * @param spDefence Entero que representa la defense especila del Pokemon
	 * @param speed     Entero que representa la velocidad del pokemon
	 */
	public Pokemon(Tipo tipo1, Tipo tipo2, Estado estado, String nombre, int numero, int level, int maxHP, double attack,
			double defence, double spAttack, double spDefence, int speed) {
		super();
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
		this.estado = estado;
		this.movimientos = new ArrayList<Move>();
		this.pkmnCond = new ArrayList<CondPosiPkmn>();
		this.durPkmnCond = new ArrayList<Integer>();
		this.entrenador = null;
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
		this.posicion = -1;
		this.ataca = -1;
		this.idPelea = -1;
		this.turnosEstado = 0;
	}

	private Pokemon() {

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

	public ArrayList<Move> getMovimientos() {
		return movimientos;
	}

	protected ArrayList<CondPosiPkmn> getPkmnCond() {
		return pkmnCond;
	}

	protected void addPkmnCond(CondPosiPkmn pkmnCond) {
		this.pkmnCond.add(pkmnCond);
		this.durPkmnCond.add(-1);
	}

	protected void removePkmnCond(CondPosiPkmn pkmnCond) {
		this.pkmnCond.remove(pkmnCond);
	}

	protected boolean hasCond(CondPosiPkmn pkmnCond) {
		for (int i = 0; i < this.pkmnCond.size(); i++) {
			if (this.pkmnCond.get(i) == pkmnCond) {
				return true;
			}
		}
		return false;
	}

	protected int getPosiCond(CondPosiPkmn pkmnCond) {
		for (int i = 0; i < this.pkmnCond.size(); i++) {
			if (this.pkmnCond.get(i) == pkmnCond) {
				return i;
			}
		}
		return -1;
	}

	protected ArrayList<Integer> getDurPkmnCond() {
		return durPkmnCond;
	}

	protected void setDurPkmnCond(int posi, int durPkmnCond) {
		this.durPkmnCond.set(posi, durPkmnCond);
	}

	protected int getDurPosiPkmnCond(int posi) {
		return this.durPkmnCond.get(posi);
	}

	protected void removeDurPkmnCond(int posi) {
		this.durPkmnCond.remove(posi);
	}

	protected Entrenador getEntrenador() {
		return entrenador;
	}

	protected void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public String getNombre() {
		return nombre;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public double getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public double getSpAttack() {
		return spAttack;
	}

	public void setSpAttack(int spAttack) {
		this.spAttack = spAttack;
	}

	public double getSpDefence() {
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

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public int getAtaca() {
		return ataca;
	}

	public void setAtaca(int ataca) {
		this.ataca = ataca;
	}

	public int getIdPelea() {
		return idPelea;
	}

	public void setIdPelea(int idPelea) {
		this.idPelea = idPelea;
	}

	public int getTurnosEstado() {
		return turnosEstado;
	}

	public void setTurnosEstado(int turnosEstado) {
		this.turnosEstado = turnosEstado;
	}

	protected int getCntChngAtk() {
		return cntChngAtk;
	}

	protected void setCntChngAtk(int cntChngAtk) {
		this.cntChngAtk = cntChngAtk;
	}

	protected int getCntChngSpAtk() {
		return cntChngSpAtk;
	}

	protected void setCntChngSpAtk(int cntChngSpAtk) {
		this.cntChngSpAtk = cntChngSpAtk;
	}

	protected int getCntChngDef() {
		return cntChngDef;
	}

	protected void setCntChngDef(int cntChngDef) {
		this.cntChngDef = cntChngDef;
	}

	protected int getCntChngSpDef() {
		return cntChngSpDef;
	}

	protected void setCntChngSpDef(int cntChngSpDef) {
		this.cntChngSpDef = cntChngSpDef;
	}

	protected int getCntChngSpe() {
		return cntChngSpe;
	}

	protected void setCntChngSpe(int cntChngSpe) {
		this.cntChngSpe = cntChngSpe;
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

	public Move elegirMovimiento() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int move;
		boolean elegido;

		do {
			System.out.println("¿Qué hará " + this.nombre + "?\n");
			mostrarMovimientos();
			System.out.print("->: ");
			move = Integer.parseInt(sc.nextLine());

			if (move > this.movimientos.size() || move < 1) {
				elegido = false;
				System.out.println("\n\nElige un numero valido\n");
			} else {
				elegido = true;
				return this.movimientos.get(move - 1);
			}
		} while (!elegido);
		return null;
	}

	public void aprenderMovimiento(Move movimiento) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int move, opc;
		boolean elegido;

		if (this.movimientos.size() == 4) {
			System.out.println(this.nombre + " ya sabe 4 movimientos. ¿Cual deseas que " + this.nombre + " olvide?");

			do {
				mostrarMovimientos();
				System.out.print("\n->: ");
				move = Integer.parseInt(sc.next());

				if (move > this.movimientos.size() || move < 1) {
					elegido = false;
					System.out.println("\n\nElige un numero valido\n");
				} else {
					System.out.println("\n\n¿Olvidar " + this.movimientos.get(move - 1).getNombre() + "?");
					System.out.println("\n1.Si\n2.No\n");
					System.out.print("->: ");
					opc = Integer.parseInt(sc.nextLine());

					if (opc < 1 || opc > 2) {
						elegido = false;
						System.out.println("\n\nElige un numero valido\n");
					} else if (opc == 1) {
						elegido = true;
						this.movimientos.add(move, movimiento);
						this.movimientos.remove(move - 1);
					} else {
						elegido = false;
					}
				}
			} while (!elegido);
		} else {
			this.movimientos.add(movimiento);
		}
	}

	@SuppressWarnings("unchecked")
	public Pokemon copiarPokemon() {
		Pokemon poke = new Pokemon();
		poke.tipo1 = this.tipo1;
		poke.tipo2 = this.tipo2;
		poke.estado = this.estado;
		poke.movimientos = (ArrayList<Move>) this.movimientos.clone();
		poke.pkmnCond = (ArrayList<CondPosiPkmn>) this.pkmnCond.clone();
		poke.durPkmnCond = (ArrayList<Integer>) this.durPkmnCond.clone();
		poke.entrenador = null;
		poke.nombre = this.nombre;
		poke.maxHP = this.maxHP;
		poke.actualHp = this.maxHP;
		poke.numero = this.numero;
		poke.level = this.level;
		poke.attack = this.attack;
		poke.defence = this.defence;
		poke.spAttack = this.spAttack;
		poke.spDefence = this.spDefence;
		poke.speed = this.speed;
		poke.posicion = -1;
		poke.ataca = -1;
		poke.idPelea = -1;

		return poke;
	}

	@Override
	public String toString() {
		return "Pokemon [tipo1=" + tipo1 + ", tipo2=" + tipo2 + ", estado=" + estado + ", entrenador="
				+ entrenador.getNombre() + ", nombre=" + nombre + ", maxHP=" + maxHP + ", actualHp=" + actualHp
				+ ", level=" + level + "]";
	}

}
