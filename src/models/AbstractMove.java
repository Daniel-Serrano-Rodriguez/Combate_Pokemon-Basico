package models;

import models.TipoPokemon.Tipo;
import utils.CondArena;
import utils.CondPosiPkmn;
import utils.Estado;

public abstract class AbstractMove {
	private Estado aplicaEstado;
	private CondArena aplicaCondArena;
	private CondPosiPkmn aplicaCondPosiPkmn;
	private Tipo tipo;
	private String nombre, descripcion;
	private int maxPP, actPP, damage, chnAtkYou, chnAtkRiv, chnDefYou, chnDefRiv, chnSpAtkYou, chnSpAtkRiv, chnSpDefYou,
			chnSpDefRiv, chnSpeYou, chnSpeRiv;

	/**
	 * Constructor del objeto 'AbstractMove'
	 * 
	 * @param aplicaEstado       Enum 'Estado' que representa el estado que se
	 *                           aplica al objeto 'Pokemon'
	 * @param aplicaCondArena    Enum 'CondArena' que representa la condición
	 *                           atmosférica que se aplica al objeto 'Combate'
	 * @param aplicaCondPosiPkmn Enum 'CondPosiPkmn' que representa el estado que se
	 *                           aplica al objeto 'Pokemon' combatiendo actualmente
	 * @param tipo               Enum 'TipoPokemon.Tipo' que representa el tipo del
	 *                           movimiento
	 * @param nombre             String que representa el nombre del movimiento
	 * @param descripcion        String que representa la descripcion del
	 *                           movimiento, QUE SEA PEQUEÑA (E.g. Hace 20 puntos de
	 *                           frio)
	 * @param maxPP              Entero que representa los max PP del movimiento
	 * @param actPP              Entero que representa los PP actuales del
	 *                           movimiento
	 * @param damage             Entero que representa el daño (power) del
	 *                           movimiento
	 * @param chnAtkYou          Entero que representa cuantos niveles cambia a tu
	 *                           ataque (0, 1, 2, 3)
	 * @param chnAtkRiv          Entero que representa cuantos niveles cambia al
	 *                           ataque del rival (0, 1, 2, 3)
	 * @param chnDefYou          Entero que representa cuantos niveles cambia a tu
	 *                           defensa (0, 1, 2, 3)
	 * @param chnDefRiv          Entero que representa cuantos niveles cambia a la
	 *                           defensa del rival (0, 1, 2, 3)
	 * @param chnSpAtkYou        Entero que representa cuantos niveles cambia a tu
	 *                           ataque especial (0, 1, 2, 3)
	 * @param chnSpAtkRiv        Entero que representa cuantos niveles cambia al
	 *                           ataque especial del rival (0, 1, 2, 3)
	 * @param chnSpDefYou        Entero que representa cuantos niveles cambia a tu
	 *                           defensa especial (0, 1, 2, 3)
	 * @param chnSpDefRiv        Entero que representa cuantos niveles cambia a la
	 *                           defensa especial del rival (0, 1, 2, 3)
	 * @param chnSpeYou          Entero que representa cuantos niveles cambia a tu
	 *                           velocidad (0, 1, 2, 3)
	 * @param chnSpeRiv          Entero que representa cuantos niveles cambia a la
	 *                           velocidad del rival (0, 1, 2, 3)
	 */
	protected AbstractMove(Estado aplicaEstado, CondArena aplicaCondArena, CondPosiPkmn aplicaCondPosiPkmn, Tipo tipo,
			String nombre, String descripcion, int maxPP, int damage, int chnAtkYou, int chnAtkRiv, int chnDefYou,
			int chnDefRiv, int chnSpAtkYou, int chnSpAtkRiv, int chnSpDefYou, int chnSpDefRiv, int chnSpeYou,
			int chnSpeRiv) {
		super();
		this.aplicaEstado = aplicaEstado;
		this.aplicaCondArena = aplicaCondArena;
		this.aplicaCondPosiPkmn = aplicaCondPosiPkmn;
		this.tipo = tipo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.maxPP = maxPP;
		this.actPP = maxPP;
		this.damage = damage;
		this.chnAtkYou = chnAtkYou;
		this.chnAtkRiv = chnAtkRiv;
		this.chnDefYou = chnDefYou;
		this.chnDefRiv = chnDefRiv;
		this.chnSpAtkYou = chnSpAtkYou;
		this.chnSpAtkRiv = chnSpAtkRiv;
		this.chnSpDefYou = chnSpDefYou;
		this.chnSpDefRiv = chnSpDefRiv;
		this.chnSpeYou = chnSpeYou;
		this.chnSpeRiv = chnSpeRiv;
	}

	// Getters - Setters
	protected Estado getAplicaEstado() {
		return aplicaEstado;
	}

	protected CondArena getAplicaCondArena() {
		return aplicaCondArena;
	}

	protected CondPosiPkmn getAplicaCondPosiPkmn() {
		return aplicaCondPosiPkmn;
	}

	protected Tipo getTipo() {
		return tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescricpion() {
		return descripcion;
	}

	public void setDescricpion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getMaxPP() {
		return maxPP;
	}

	protected void setMaxPP(int maxPP) {
		this.maxPP = maxPP;
	}

	protected int getActPP() {
		return actPP;
	}

	protected void setActPP(int actPP) {
		this.actPP = actPP;
	}

	public int getDamage() {
		return damage;
	}

	protected void setDamage(int damage) {
		this.damage = damage;
	}

	protected int getChnAtkYou() {
		return chnAtkYou;
	}

	protected int getChnAtkRiv() {
		return chnAtkRiv;
	}

	protected int getChnDefYou() {
		return chnDefYou;
	}

	protected int getChnDefRiv() {
		return chnDefRiv;
	}

	protected int getChnSpAtkYou() {
		return chnSpAtkYou;
	}

	protected int getChnSpAtkRiv() {
		return chnSpAtkRiv;
	}

	protected int getChnSpDefYou() {
		return chnSpDefYou;
	}

	protected int getChnSpDefRiv() {
		return chnSpDefRiv;
	}

	protected int getChnSpeYou() {
		return chnSpeYou;
	}

	protected int getChnSpeRiv() {
		return chnSpeRiv;
	}

}
