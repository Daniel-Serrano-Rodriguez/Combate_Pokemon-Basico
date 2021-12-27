package models;

import models.TipoPokemon.Tipo;
import utils.Estado;

public class MoveAtkPerc extends MoveAtk {

	public MoveAtkPerc(Estado aplicaEstado, Tipo tipo, String nombre, int maxPP, int actPP, int chnAtkYou,
			int chnAtkRiv, int chnDefYou, int chnDefRiv, int chnSpAtkYou, int chnSpAtkRiv, int chnSpDefYou,
			int chnSpDefRiv, int chnSpeYou, int chnSpeRiv) {
		super(aplicaEstado, tipo, nombre, maxPP, actPP, 0, chnAtkYou, chnAtkRiv, chnDefYou, chnDefRiv, chnSpAtkYou,
				chnSpAtkRiv, chnSpDefYou, chnSpDefRiv, chnSpeYou, chnSpeRiv);
	}

	// Getters - Setters
	public void setDmgPerc(int damage) {
		this.setDamage(damage);
	}

}
