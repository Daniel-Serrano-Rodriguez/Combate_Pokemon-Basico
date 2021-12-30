package models;

import models.TipoPokemon.Tipo;
import utils.Estado;
import utils.Moves;

public class MoveAtkCarga extends MoveAtk {

	private int turnosCarga, turnoCargado;

	/**
	 * Constructor del objeto 'MoveAtk', que son ataques con un daño específico
	 * 
	 * @param aplicaEstado Enum 'Estado' que representa el estado que se aplica al
	 *                     objeto 'Pokemon'
	 * @param tipo         Enum 'TipoPokemon.Tipo' que representa el tipo del
	 *                     movimiento
	 * @param clase        Enum 'AbstractMove.Clase' que representa si el movimiento
	 *                     es Fisico o Especial
	 * @param move         Enum que representa el movimiento
	 * @param nombre       String que representa el nombre del movimiento
	 * @param descripcion  String que representa la descripcion del movimiento, QUE
	 *                     SEA PEQUEÑA (E.g. Hace 20 puntos de frio)
	 * @param maxPP        Entero que representa los max PP del movimiento
	 * @param damage       Entero que representa el daño (power) del movimiento
	 * @param precision    Entero que representa la precision del movimiento
	 * @param turnosCarga  Entero que representa cuantos turnos tarda en cargar el
	 *                     ataque
	 * @param chnAtkYou    Entero que representa cuantos niveles cambia a tu ataque
	 *                     (0, 1, 2, 3)
	 * @param chnAtkRiv    Entero que representa cuantos niveles cambia al ataque
	 *                     del rival (0, 1, 2, 3)
	 * @param chnDefYou    Entero que representa cuantos niveles cambia a tu defensa
	 *                     (0, 1, 2, 3)
	 * @param chnDefRiv    Entero que representa cuantos niveles cambia a la defensa
	 *                     del rival (0, 1, 2, 3)
	 * @param chnSpAtkYou  Entero que representa cuantos niveles cambia a tu ataque
	 *                     especial (0, 1, 2, 3)
	 * @param chnSpAtkRiv  Entero que representa cuantos niveles cambia al ataque
	 *                     especial del rival (0, 1, 2, 3)
	 * @param chnSpDefYou  Entero que representa cuantos niveles cambia a tu defensa
	 *                     especial (0, 1, 2, 3)
	 * @param chnSpDefRiv  Entero que representa cuantos niveles cambia a la defensa
	 *                     especial del rival (0, 1, 2, 3)
	 * @param chnSpeYou    Entero que representa cuantos niveles cambia a tu
	 *                     velocidad (0, 1, 2, 3)
	 * @param chnSpeRiv    Entero que representa cuantos niveles cambia a la
	 *                     velocidad del rival (0, 1, 2, 3)
	 */
	public MoveAtkCarga(Estado aplicaEstado, Tipo tipo, Clase clase, Moves move, String nombre, String descripcion,
			int maxPP, int damage, int precision, int turnosCarga, int chnAtkYou, int chnAtkRiv, int chnDefYou,
			int chnDefRiv, int chnSpAtkYou, int chnSpAtkRiv, int chnSpDefYou, int chnSpDefRiv, int chnSpeYou,
			int chnSpeRiv) {
		super(aplicaEstado, tipo, clase, move, nombre, descripcion, maxPP, damage, precision, chnAtkYou, chnAtkRiv,
				chnDefYou, chnDefRiv, chnSpAtkYou, chnSpAtkRiv, chnSpDefYou, chnSpDefRiv, chnSpeYou, chnSpeRiv);
		this.turnoCargado = 1;
		this.turnosCarga = turnosCarga;
	}

	protected int getTurnosCarga() {
		return turnosCarga;
	}

	protected int getTurnoCargado() {
		return turnoCargado;
	}

	protected void setTurnoCargado(int turnoCargado) {
		this.turnoCargado = turnoCargado;
	}

}
