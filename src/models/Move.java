package models;

import models.TipoPokemon.Tipo;
import utils.CondArena;
import utils.CondPosiPkmn;
import utils.Estado;
import utils.Moves;

public class Move implements Cloneable {
	protected enum Clase {
		Fisico, Especial, Estado
	}

	private Estado aplicaEstado;
	private CondArena aplicaCondArena;
	private CondPosiPkmn aplicaCondPosiPkmn;
	private Tipo tipo;
	private Clase clase;
	private Moves move;
	private String nombre, descripcion;
	private int maxPP, actPP, damage, precision, prioridad;
	private int golpesMin, golpesMax;
	private int turnosNecesarios, turnosCargados;
	private int chnAtkYou;
	private int chnAtkRiv;
	private int chnDefYou;
	private int chnDefRiv;
	private int chnSpAtkYou;
	private int chnSpAtkRiv;
	private int chnSpDefYou;
	private int chnSpDefRiv;
	private int chnSpeYou;
	private int chnSpeRiv;

	/**
	 * Constructor de un ataque normal
	 * 
	 * @param aplicaEstado       Enum 'Estado' que representa el estado que se
	 *                           aplica al objeto 'Pokemon'
	 * @param aplicaCondArena    Enum 'CondArena' que representa la condición
	 *                           atmosférica que se aplica al objeto 'Combate'
	 * @param aplicaCondPosiPkmn Enum 'CondPosiPkmn' que representa el estado que se
	 *                           aplica al objeto 'Pokemon' combatiendo actualmente
	 * @param tipo               Enum 'TipoPokemon.Tipo' que representa el tipo del
	 *                           movimiento
	 * @param clase              Enum 'AbstractMove.Clase' que representa si el
	 *                           movimiento es Fisico o Especial
	 * @param move               Enum que representa el movimiento
	 * @param nombre             String que representa el nombre del movimiento
	 * @param descripcion        String que representa la descripcion del
	 *                           movimiento, QUE SEA PEQUEÑA (E.g. Hace 20 puntos de
	 *                           frio)
	 * @param maxPP              Entero que representa los max PP del movimiento
	 * @param actPP              Entero que representa los PP actuales del
	 *                           movimiento
	 * @param damage             Entero que representa el daño (power) del
	 *                           movimiento
	 * @param precision          Entero que representa la precision del movimiento
	 * @param prioridad          Entero que representa la prioridad del movimiento
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
	protected Move(Estado aplicaEstado, CondPosiPkmn aplicaCondPosiPkmn, Tipo tipo,
			Clase clase, Moves move, String nombre, String descripcion, int maxPP, int damage, int precision,
			int prioridad, int chnAtkYou, int chnAtkRiv, int chnDefYou, int chnDefRiv, int chnSpAtkYou, int chnSpAtkRiv,
			int chnSpDefYou, int chnSpDefRiv, int chnSpeYou, int chnSpeRiv) {
		super();
		this.aplicaEstado = aplicaEstado;
		this.aplicaCondArena = CondArena.Ninguno;
		this.aplicaCondPosiPkmn = aplicaCondPosiPkmn;
		this.tipo = tipo;
		this.clase = clase;
		this.move = move;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.maxPP = maxPP;
		this.actPP = maxPP;
		this.damage = damage;
		this.precision = precision;
		this.prioridad = prioridad;
		this.golpesMin = 1;
		this.golpesMax = 1;
		this.turnosNecesarios = 1;
		this.turnosCargados = 1;
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

	/**
	 * Constructor de un ataque de estado
	 * 
	 * @param aplicaEstado       Enum 'Estado' que representa el estado que se
	 *                           aplica al objeto 'Pokemon'
	 * @param aplicaCondArena    Enum 'CondArena' que representa la condición
	 *                           atmosférica que se aplica al objeto 'Combate'
	 * @param aplicaCondPosiPkmn Enum 'CondPosiPkmn' que representa el estado que se
	 *                           aplica al objeto 'Pokemon' combatiendo actualmente
	 * @param tipo               Enum 'TipoPokemon.Tipo' que representa el tipo del
	 *                           movimiento
	 * @param clase              Enum 'AbstractMove.Clase' que representa si el
	 *                           movimiento es Fisico o Especial
	 * @param move               Enum que representa el movimiento
	 * @param nombre             String que representa el nombre del movimiento
	 * @param descripcion        String que representa la descripcion del
	 *                           movimiento, QUE SEA PEQUEÑA (E.g. Hace 20 puntos de
	 *                           frio)
	 * @param maxPP              Entero que representa los max PP del movimiento
	 * @param actPP              Entero que representa los PP actuales del
	 *                           movimiento
	 * @param damage             Entero que representa el daño (power) del
	 *                           movimiento
	 * @param precision          Entero que representa la precision del movimiento
	 * @param prioridad          Entero que representa la prioridad del movimiento
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
	protected Move(Estado aplicaEstado, CondArena aplicaCondArena, CondPosiPkmn aplicaCondPosiPkmn, Tipo tipo,
			Clase clase, Moves move, String nombre, String descripcion, int maxPP, int precision, int prioridad,
			int chnAtkYou, int chnAtkRiv, int chnDefYou, int chnDefRiv, int chnSpAtkYou, int chnSpAtkRiv,
			int chnSpDefYou, int chnSpDefRiv, int chnSpeYou, int chnSpeRiv) {
		this.aplicaEstado = aplicaEstado;
		this.aplicaCondArena = aplicaCondArena;
		this.aplicaCondPosiPkmn = aplicaCondPosiPkmn;
		this.tipo = tipo;
		this.clase = clase;
		this.move = move;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.maxPP = maxPP;
		this.actPP = maxPP;
		this.damage = 0;
		this.precision = precision;
		this.prioridad = prioridad;
		this.golpesMin = 1;
		this.golpesMax = 1;
		this.turnosNecesarios = 1;
		this.turnosCargados = 1;
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

	/**
	 * Constructor de un ataque de multiples golpes
	 * 
	 * @param aplicaEstado       Enum 'Estado' que representa el estado que se
	 *                           aplica al objeto 'Pokemon'
	 * @param aplicaCondArena    Enum 'CondArena' que representa la condición
	 *                           atmosférica que se aplica al objeto 'Combate'
	 * @param aplicaCondPosiPkmn Enum 'CondPosiPkmn' que representa el estado que se
	 *                           aplica al objeto 'Pokemon' combatiendo actualmente
	 * @param tipo               Enum 'TipoPokemon.Tipo' que representa el tipo del
	 *                           movimiento
	 * @param clase              Enum 'AbstractMove.Clase' que representa si el
	 *                           movimiento es Fisico o Especial
	 * @param move               Enum que representa el movimiento
	 * @param nombre             String que representa el nombre del movimiento
	 * @param descripcion        String que representa la descripcion del
	 *                           movimiento, QUE SEA PEQUEÑA (E.g. Hace 20 puntos de
	 *                           frio)
	 * @param maxPP              Entero que representa los max PP del movimiento
	 * @param actPP              Entero que representa los PP actuales del
	 *                           movimiento
	 * @param damage             Entero que representa el daño (power) del
	 *                           movimiento
	 * @param precision          Entero que representa la precision del movimiento
	 * @param prioridad          Entero que representa la prioridad del movimiento
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
	protected Move(Estado aplicaEstado, CondPosiPkmn aplicaCondPosiPkmn, Tipo tipo, Clase clase, Moves move,
			String nombre, String descripcion, int maxPP, int damage, int precision, int prioridad, int golpesMin,
			int golpesMax, int chnAtkYou, int chnAtkRiv, int chnDefYou, int chnDefRiv, int chnSpAtkYou, int chnSpAtkRiv,
			int chnSpDefYou, int chnSpDefRiv, int chnSpeYou, int chnSpeRiv) {
		super();
		this.aplicaEstado = aplicaEstado;
		this.aplicaCondArena = CondArena.Ninguno;
		this.aplicaCondPosiPkmn = aplicaCondPosiPkmn;
		this.tipo = tipo;
		this.clase = clase;
		this.move = move;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.maxPP = maxPP;
		this.actPP = maxPP;
		this.damage = damage;
		this.precision = precision;
		this.prioridad = prioridad;
		this.golpesMin = golpesMin;
		this.golpesMax = golpesMax;
		this.turnosNecesarios = 1;
		this.turnosCargados = 1;
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

	/**
	 * Constructor de un ataque que requiere de carga
	 * 
	 * @param aplicaEstado       Enum 'Estado' que representa el estado que se
	 *                           aplica al objeto 'Pokemon'
	 * @param aplicaCondArena    Enum 'CondArena' que representa la condición
	 *                           atmosférica que se aplica al objeto 'Combate'
	 * @param aplicaCondPosiPkmn Enum 'CondPosiPkmn' que representa el estado que se
	 *                           aplica al objeto 'Pokemon' combatiendo actualmente
	 * @param tipo               Enum 'TipoPokemon.Tipo' que representa el tipo del
	 *                           movimiento
	 * @param clase              Enum 'AbstractMove.Clase' que representa si el
	 *                           movimiento es Fisico o Especial
	 * @param move               Enum que representa el movimiento
	 * @param nombre             String que representa el nombre del movimiento
	 * @param descripcion        String que representa la descripcion del
	 *                           movimiento, QUE SEA PEQUEÑA (E.g. Hace 20 puntos de
	 *                           frio)
	 * @param maxPP              Entero que representa los max PP del movimiento
	 * @param actPP              Entero que representa los PP actuales del
	 *                           movimiento
	 * @param damage             Entero que representa el daño (power) del
	 *                           movimiento
	 * @param precision          Entero que representa la precision del movimiento
	 * @param prioridad          Entero que representa la prioridad del movimiento
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
	protected Move(Estado aplicaEstado, CondPosiPkmn aplicaCondPosiPkmn, Tipo tipo, Clase clase, Moves move,
			String nombre, String descripcion, int maxPP, int damage, int precision, int prioridad,
			int turnosNecesarios, int chnAtkYou, int chnAtkRiv, int chnDefYou, int chnDefRiv, int chnSpAtkYou,
			int chnSpAtkRiv, int chnSpDefYou, int chnSpDefRiv, int chnSpeYou, int chnSpeRiv) {
		super();
		this.aplicaEstado = aplicaEstado;
		this.aplicaCondArena = CondArena.Ninguno;
		this.aplicaCondPosiPkmn = aplicaCondPosiPkmn;
		this.tipo = tipo;
		this.clase = clase;
		this.move = move;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.maxPP = maxPP;
		this.actPP = maxPP;
		this.damage = damage;
		this.precision = precision;
		this.prioridad = prioridad;
		this.golpesMin = 1;
		this.golpesMax = 1;
		this.turnosNecesarios = turnosNecesarios;
		this.turnosCargados = 1;
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

	private Move() {

	}

	// Getters - Setters
	protected Estado getAplicaEstado() {
		return aplicaEstado;
	}

	protected void setAplicaEstado(Estado aplicaEstado) {
		this.aplicaEstado = aplicaEstado;
	}

	protected CondArena getAplicaCondArena() {
		return aplicaCondArena;
	}

	protected void setAplicaCondArena(CondArena aplicaCondArena) {
		this.aplicaCondArena = aplicaCondArena;
	}

	protected CondPosiPkmn getAplicaCondPosiPkmn() {
		return aplicaCondPosiPkmn;
	}

	protected void setAplicaCondPosiPkmn(CondPosiPkmn aplicaCondPosiPkmn) {
		this.aplicaCondPosiPkmn = aplicaCondPosiPkmn;
	}

	protected Tipo getTipo() {
		return tipo;
	}

	protected void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	protected Clase getClase() {
		return clase;
	}

	protected void setClase(Clase clase) {
		this.clase = clase;
	}

	protected Moves getMove() {
		return move;
	}

	protected void setMove(Moves move) {
		this.move = move;
	}

	public String getNombre() {
		return nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	protected void setDescripcion(String descripcion) {
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

	protected int getPrecision() {
		return precision;
	}

	protected void setPrecision(int precision) {
		this.precision = precision;
	}

	protected int getPrioridad() {
		return prioridad;
	}

	protected int getGolpesMin() {
		return golpesMin;
	}

	protected void setGolpesMin(int golpesMin) {
		this.golpesMin = golpesMin;
	}

	protected int getGolpesMax() {
		return golpesMax;
	}

	protected int getTurnosNecesarios() {
		return turnosNecesarios;
	}

	protected int getTurnosCargados() {
		return turnosCargados;
	}

	protected void setTurnosCargados(int turnoCargado) {
		this.turnosCargados = turnoCargado;
	}

	protected void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	protected int getChnAtkYou() {
		return chnAtkYou;
	}

	protected void setChnAtkYou(int chnAtkYou) {
		this.chnAtkYou = chnAtkYou;
	}

	protected int getChnAtkRiv() {
		return chnAtkRiv;
	}

	protected void setChnAtkRiv(int chnAtkRiv) {
		this.chnAtkRiv = chnAtkRiv;
	}

	protected int getChnDefYou() {
		return chnDefYou;
	}

	protected void setChnDefYou(int chnDefYou) {
		this.chnDefYou = chnDefYou;
	}

	protected int getChnDefRiv() {
		return chnDefRiv;
	}

	protected void setChnDefRiv(int chnDefRiv) {
		this.chnDefRiv = chnDefRiv;
	}

	protected int getChnSpAtkYou() {
		return chnSpAtkYou;
	}

	protected void setChnSpAtkYou(int chnSpAtkYou) {
		this.chnSpAtkYou = chnSpAtkYou;
	}

	protected int getChnSpAtkRiv() {
		return chnSpAtkRiv;
	}

	protected void setChnSpAtkRiv(int chnSpAtkRiv) {
		this.chnSpAtkRiv = chnSpAtkRiv;
	}

	protected int getChnSpDefYou() {
		return chnSpDefYou;
	}

	protected void setChnSpDefYou(int chnSpDefYou) {
		this.chnSpDefYou = chnSpDefYou;
	}

	protected int getChnSpDefRiv() {
		return chnSpDefRiv;
	}

	protected void setChnSpDefRiv(int chnSpDefRiv) {
		this.chnSpDefRiv = chnSpDefRiv;
	}

	protected int getChnSpeYou() {
		return chnSpeYou;
	}

	protected void setChnSpeYou(int chnSpeYou) {
		this.chnSpeYou = chnSpeYou;
	}

	protected int getChnSpeRiv() {
		return chnSpeRiv;
	}

	protected void setChnSpeRiv(int chnSpeRiv) {
		this.chnSpeRiv = chnSpeRiv;
	}

	// Methods
	protected Move copiarMove() {
		Move move = new Move();
		move.aplicaEstado = this.aplicaEstado;
		move.aplicaCondArena = this.aplicaCondArena;
		move.aplicaCondPosiPkmn = this.aplicaCondPosiPkmn;
		move.tipo = this.tipo;
		move.clase = this.clase;
		move.move = this.move;
		move.nombre = this.nombre;
		move.descripcion = this.descripcion;
		move.maxPP = this.maxPP;
		move.actPP = this.maxPP;
		move.damage = this.damage;
		move.prioridad = this.prioridad;
		move.golpesMin = this.golpesMin;
		move.golpesMax = this.golpesMax;
		move.turnosNecesarios = this.turnosNecesarios;
		move.turnosCargados = 1;
		move.chnAtkYou = this.chnAtkYou;
		move.chnAtkRiv = this.chnAtkRiv;
		move.chnDefYou = this.chnDefYou;
		move.chnDefRiv = this.chnDefRiv;
		move.chnSpAtkYou = this.chnSpAtkYou;
		move.chnSpAtkRiv = this.chnSpAtkRiv;
		move.chnSpDefYou = this.chnSpDefYou;
		move.chnSpDefRiv = this.chnSpDefRiv;
		move.chnSpeYou = this.chnSpeYou;
		move.chnSpeRiv = this.chnSpeRiv;
		return move;
	}

	/**
	 * Funcion que devuelve un multiplicador en funcion de la efectividad del
	 * movimiento
	 * 
	 * @param tipoMovimiento Tipo del ataque del objeto 'Pokemon' que ataca
	 * @param tipoRival    Tipo del objeto 'Pokemon' rival
	 * @return Doble que representa el multiplicador (0, 0.5, 1, 2)
	 */
	protected double getEfectividad(Tipo tipoMovimiento, Tipo tipoRival) {
		switch (tipoMovimiento) {
		case Normal: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Fuego:
			case Agua:
			case Electrico:
			case Planta:
			case Hielo:
			case Lucha:
			case Veneno:
			case Tierra:
			case Volador:
			case Psiquico:
			case Bicho:
			case Dragon:
			case Siniestro:
			case Hada:
				return 1;

			// Devuelven 2

			// Devuelven 0.5
			case Roca:
			case Acero:
				return 0.5;

			// Devuelven 0
			case Fantasma:
				return 0;
			}
		}

		case Fuego: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Electrico:
			case Lucha:
			case Veneno:
			case Tierra:
			case Volador:
			case Psiquico:
			case Fantasma:
			case Siniestro:
			case Hada:
				return 1;

			// Devuelven 2
			case Planta:
			case Hielo:
			case Bicho:
			case Acero:
				return 2;

			// Devuelven 0.5
			case Fuego:
			case Agua:
			case Roca:
			case Dragon:
				return 0.5;

			// Devuelven 0

			}
		}

		case Agua: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Electrico:
			case Hielo:
			case Lucha:
			case Veneno:
			case Volador:
			case Psiquico:
			case Bicho:
			case Fantasma:
			case Siniestro:
			case Acero:
			case Hada:
				return 1;

			// Devuelven 2
			case Fuego:
			case Tierra:
			case Roca:
				return 2;

			// Devuelven 0.5
			case Agua:
			case Planta:
			case Dragon:
				return 0.5;

			// Devuelven 0

			}
		}

		case Electrico: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Fuego:
			case Hielo:
			case Lucha:
			case Veneno:
			case Psiquico:
			case Bicho:
			case Roca:
			case Fantasma:
			case Siniestro:
			case Acero:
			case Hada:
				return 1;

			// Devuelven 2
			case Agua:
			case Volador:
				return 2;

			// Devuelven 0.5
			case Electrico:
			case Planta:
			case Dragon:
				return 0.5;

			// Devuelven 0
			case Tierra:
				return 0;

			}
		}

		case Planta: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Electrico:
			case Hielo:
			case Lucha:
			case Psiquico:
			case Fantasma:
			case Siniestro:
			case Hada:
				return 1;

			// Devuelven 2
			case Agua:
			case Tierra:
			case Roca:
				return 2;

			// Devuelven 0.5
			case Fuego:
			case Planta:
			case Veneno:
			case Volador:
			case Bicho:
			case Dragon:
			case Acero:
				return 0.5;

			// Devuelven 0

			}
		}

		case Hielo: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Electrico:
			case Lucha:
			case Veneno:
			case Psiquico:
			case Bicho:
			case Roca:
			case Fantasma:
			case Siniestro:
			case Hada:
				return 1;

			// Devuelven 2
			case Planta:
			case Tierra:
			case Volador:
			case Dragon:

				return 2;

			// Devuelven 0.5
			case Fuego:
			case Agua:
			case Hielo:
			case Acero:
				return 0.5;

			// Devuelven 0

			}
		}

		case Lucha: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Fuego:
			case Agua:
			case Electrico:
			case Planta:
			case Lucha:
			case Tierra:
			case Dragon:
				return 1;

			// Devuelven 2
			case Normal:
			case Hielo:
			case Roca:
			case Siniestro:
			case Acero:
				return 2;

			// Devuelven 0.5
			case Veneno:
			case Volador:
			case Psiquico:
			case Bicho:
			case Hada:
				return 0.5;

			// Devuelven 0
			case Fantasma:
				return 0;
			}
		}

		case Veneno: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Fuego:
			case Agua:
			case Electrico:
			case Hielo:
			case Lucha:
			case Volador:
			case Psiquico:
			case Bicho:
			case Dragon:
			case Siniestro:
				return 1;

			// Devuelven 2
			case Planta:
			case Hada:
				return 2;

			// Devuelven 0.5
			case Veneno:
			case Tierra:
			case Roca:
			case Fantasma:
				return 0.5;

			// Devuelven 0
			case Acero:
				return 0;
			}
		}

		case Tierra: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Agua:
			case Hielo:
			case Lucha:
			case Tierra:
			case Psiquico:
			case Fantasma:
			case Dragon:
			case Siniestro:
			case Hada:
				return 1;

			// Devuelven 2
			case Fuego:
			case Electrico:
			case Veneno:
			case Roca:
			case Acero:
				return 2;

			// Devuelven 0.5
			case Planta:
			case Bicho:
				return 0.5;

			// Devuelven 0
			case Volador:
				return 0;
			}
		}

		case Volador: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Fuego:
			case Agua:
			case Hielo:
			case Veneno:
			case Tierra:
			case Volador:
			case Psiquico:
			case Fantasma:
			case Dragon:
			case Siniestro:
			case Hada:
				return 1;

			// Devuelven 2
			case Planta:
			case Lucha:
			case Bicho:
				return 2;

			// Devuelven 0.5
			case Electrico:
			case Roca:
			case Acero:
				return 0.5;

			// Devuelven 0
			}
		}

		case Psiquico: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Fuego:
			case Agua:
			case Electrico:
			case Planta:
			case Hielo:
			case Tierra:
			case Volador:
			case Bicho:
			case Roca:
			case Fantasma:
			case Dragon:
				return 1;

			// Devuelven 2
			case Lucha:
			case Veneno:
				return 2;

			// Devuelven 0.5
			case Psiquico:
			case Acero:
			case Hada:
				return 0.5;

			// Devuelven 0
			case Siniestro:
				return 0;
			}
		}

		case Bicho: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Agua:
			case Electrico:
			case Hielo:
			case Tierra:
			case Bicho:
			case Roca:
			case Dragon:
				return 1;

			// Devuelven 2
			case Planta:
			case Psiquico:
			case Siniestro:
				return 2;

			// Devuelven 0.5
			case Fuego:
			case Lucha:
			case Veneno:
			case Volador:
			case Fantasma:
			case Acero:
			case Hada:
				return 0.5;

			// Devuelven 0
			}
		}

		case Roca: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Agua:
			case Electrico:
			case Planta:
			case Veneno:
			case Psiquico:
			case Roca:
			case Fantasma:
			case Dragon:
			case Siniestro:
			case Hada:
				return 1;

			// Devuelven 2
			case Fuego:
			case Hielo:
			case Volador:
			case Bicho:
				return 2;

			// Devuelven 0.5
			case Lucha:
			case Tierra:
			case Acero:
				return 0.5;

			// Devuelven 0
			}
		}

		case Fantasma: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Fuego:
			case Agua:
			case Electrico:
			case Planta:
			case Hielo:
			case Lucha:
			case Veneno:
			case Tierra:
			case Volador:
			case Bicho:
			case Roca:
			case Dragon:
			case Acero:
			case Hada:
				return 1;

			// Devuelven 2
			case Psiquico:
			case Fantasma:
				return 2;

			// Devuelven 0.5
			case Siniestro:
				return 0.5;

			// Devuelven 0
			case Normal:
				return 0;
			}
		}

		case Dragon: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Fuego:
			case Agua:
			case Electrico:
			case Planta:
			case Hielo:
			case Lucha:
			case Veneno:
			case Tierra:
			case Volador:
			case Psiquico:
			case Bicho:
			case Roca:
			case Fantasma:
			case Siniestro:
				return 1;

			// Devuelven 2
			case Dragon:
				return 2;

			// Devuelven 0.5
			case Acero:
				return 0.5;

			// Devuelven 0
			case Hada:
				return 0;
			}
		}

		case Siniestro: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Fuego:
			case Agua:
			case Electrico:
			case Planta:
			case Hielo:
			case Veneno:
			case Tierra:
			case Volador:
			case Bicho:
			case Roca:
			case Dragon:
			case Acero:
				return 1;

			// Devuelven 2
			case Psiquico:
			case Fantasma:
				return 2;

			// Devuelven 0.5
			case Lucha:
			case Siniestro:
			case Hada:
				return 0.5;

			// Devuelven 0
			}
		}

		case Acero: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Planta:
			case Lucha:
			case Veneno:
			case Tierra:
			case Volador:
			case Psiquico:
			case Bicho:
			case Fantasma:
			case Dragon:
			case Siniestro:
				return 1;

			// Devuelven 2
			case Hielo:
			case Roca:
			case Hada:
				return 2;

			// Devuelven 0.5
			case Fuego:
			case Agua:
			case Electrico:
			case Acero:
				return 0.5;

			// Devuelven 0
			}
		}

		case Hada: {
			switch (tipoRival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Agua:
			case Electrico:
			case Planta:
			case Hielo:
			case Tierra:
			case Volador:
			case Psiquico:
			case Bicho:
			case Roca:
			case Fantasma:
			case Hada:
				return 1;

			// Devuelven 2
			case Lucha:
			case Dragon:
			case Siniestro:
				return 2;

			// Devuelven 0.5
			case Fuego:
			case Veneno:
			case Acero:
				return 0.5;

			// Devuelven 0
			}
		}

		case Ninguno:
			return 1;

		default:
			return 1;
		}
	}
}
