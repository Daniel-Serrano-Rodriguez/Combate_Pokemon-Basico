package models;

import models.TipoPokemon.Tipo;
import utils.Estado;

public class MoveAtk extends AbstractMove {

	public MoveAtk(Estado aplicaEstado, Tipo tipo, String nombre, int maxPP, int actPP, int damage, int chnAtkYou,
			int chnAtkRiv, int chnDefYou, int chnDefRiv, int chnSpAtkYou, int chnSpAtkRiv, int chnSpDefYou,
			int chnSpDefRiv, int chnSpeYou, int chnSpeRiv) {
		super(aplicaEstado, tipo, nombre, maxPP, actPP, damage, chnAtkYou, chnAtkRiv, chnDefYou, chnDefRiv, chnSpAtkYou,
				chnSpAtkRiv, chnSpDefYou, chnSpDefRiv, chnSpeYou, chnSpeRiv);
	}

}
