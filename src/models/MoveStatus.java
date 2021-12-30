package models;

import models.TipoPokemon.Tipo;
import utils.CondArena;
import utils.CondPosiPkmn;
import utils.Estado;
import utils.Moves;

public class MoveStatus extends AbstractMove {

	/**
	 * Constructor del objeto 'MoveStatus', que son movimientos que aplican estados
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
	 * @param precision          Entero que representa la precision del movimiento
	 * @param maxPP              Entero que representa los max PP del movimiento
	 * @param actPP              Entero que representa los PP actuales del
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
	public MoveStatus(Estado aplicaEstado, CondArena aplicaCondArena, CondPosiPkmn aplicaCondPosiPkmn, Tipo tipo,
			Clase clase, Moves move, String nombre, String descripcion, int maxPP, int precision, int chnAtkYou,
			int chnAtkRiv, int chnDefYou, int chnDefRiv, int chnSpAtkYou, int chnSpAtkRiv, int chnSpDefYou,
			int chnSpDefRiv, int chnSpeYou, int chnSpeRiv) {
		super(aplicaEstado, aplicaCondArena, aplicaCondPosiPkmn, tipo, clase, move, nombre, descripcion, maxPP, 0,
				precision, 1, 1, chnAtkYou, chnAtkRiv, chnDefYou, chnDefRiv, chnSpAtkYou, chnSpAtkRiv, chnSpDefYou,
				chnSpDefRiv, chnSpeYou, chnSpeRiv);
	}

}
