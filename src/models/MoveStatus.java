package models;

import models.TipoPokemon.Tipo;
import utils.CondArena;
import utils.CondPosiPkmn;
import utils.Estado;

public class MoveStatus extends AbstractMove {

	protected MoveStatus(Estado aplicaEstado, CondArena aplicaCondArena, CondPosiPkmn aplicaCondPosiPkmn, Tipo tipo,
			String nombre, int maxPP, int actPP, int chnAtkYou, int chnAtkRiv, int chnDefYou, int chnDefRiv,
			int chnSpAtkYou, int chnSpAtkRiv, int chnSpDefYou, int chnSpDefRiv, int chnSpeYou, int chnSpeRiv) {
		super(aplicaEstado, aplicaCondArena, aplicaCondPosiPkmn, tipo, nombre, maxPP, actPP, 0, chnAtkYou, chnAtkRiv,
				chnDefYou, chnDefRiv, chnSpAtkYou, chnSpAtkRiv, chnSpDefYou, chnSpDefRiv, chnSpeYou, chnSpeRiv);

	}

}
