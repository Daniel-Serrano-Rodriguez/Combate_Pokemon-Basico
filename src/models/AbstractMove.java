package models;

import models.TipoPokemon.Tipo;
import utils.Estado;

public abstract class AbstractMove {
	private Estado aplicaEstado;
	private Tipo tipo;
	private String nombre;
	private int maxPP, actPP, damage, chnAtkYou, chnAtkRiv, chnDefYou, chnDefRiv, chnSpAtkYou, chnSpAtkRiv, chnSpDefYou,
			chnSpDefRiv, chnSpeYou, chnSpeRiv;

	// Const
	protected AbstractMove(Estado aplicaEstado, Tipo tipo, String nombre, int maxPP, int actPP) {
		super();
		this.aplicaEstado = aplicaEstado;
		this.tipo = tipo;
		this.nombre = nombre;
		this.maxPP = maxPP;
		this.actPP = actPP;
	}

	// Getters - Setters
	public Estado getAplicaEstado() {
		return aplicaEstado;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public int getMaxPP() {
		return maxPP;
	}

	public void setMaxPP(int maxPP) {
		this.maxPP = maxPP;
	}

	public int getActPP() {
		return actPP;
	}

	public void setActPP(int actPP) {
		this.actPP = actPP;
	}

	public int getDamage() {
		return damage;
	}

	public int getChnAtkYou() {
		return chnAtkYou;
	}

	public int getChnAtkRiv() {
		return chnAtkRiv;
	}

	public int getChnDefYou() {
		return chnDefYou;
	}

	public int getChnDefRiv() {
		return chnDefRiv;
	}

	public int getChnSpAtkYou() {
		return chnSpAtkYou;
	}

	public int getChnSpAtkRiv() {
		return chnSpAtkRiv;
	}

	public int getChnSpDefYou() {
		return chnSpDefYou;
	}

	public int getChnSpDefRiv() {
		return chnSpDefRiv;
	}

	public int getChnSpeYou() {
		return chnSpeYou;
	}

	public int getChnSpeRiv() {
		return chnSpeRiv;
	}

}
