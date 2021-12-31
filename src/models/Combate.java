package models;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import models.AbstractMove.Clase;
import models.TipoPokemon.Tipo;
import utils.CondArena;
import utils.CondPosiPkmn;
import utils.Estado;
import utils.Moves;

public class Combate {
	private Entrenador entrenador1, entrenador2;
	private ArrayList<Pokemon> pokemon1, pokemon2, combatientes;
	private ArrayList<AbstractMove> chargedMoves = new ArrayList<AbstractMove>(), moves = new ArrayList<AbstractMove>();
	private CondArena condArena;
	private CondPosiPkmn condPoke1, condPoke2;

	/**
	 * Constructor del objeto 'Combate'. Se crean 2 arrayList vacios que representan
	 * los Pokemon actualmente en la arena
	 */
	public Combate(Entrenador entrenador1, Entrenador entrenador2) {
		this.entrenador1 = entrenador1;
		this.entrenador2 = entrenador2;
		this.pokemon1 = new ArrayList<Pokemon>();
		this.pokemon2 = new ArrayList<Pokemon>();
		this.combatientes = new ArrayList<Pokemon>();
		this.condArena = CondArena.Ninguno;
		this.condPoke1 = CondPosiPkmn.Ninguno;
		this.condPoke2 = CondPosiPkmn.Ninguno;
	}

	/**
	 * Constructor del objeto 'Combate'. Se crean 2 entrenadores que han de ser
	 * creados y 2 arrayList vacios que representan los Pokemon actualmente en la
	 * arena
	 */
	public Combate() {
		this.entrenador1 = new Entrenador();
		this.entrenador2 = new Entrenador();
		this.pokemon1 = new ArrayList<Pokemon>();
		this.pokemon2 = new ArrayList<Pokemon>();
		this.combatientes = new ArrayList<Pokemon>();
		this.condArena = CondArena.Ninguno;
		this.condPoke1 = CondPosiPkmn.Ninguno;
		this.condPoke2 = CondPosiPkmn.Ninguno;
	}

	// Getters - Setters
	protected Entrenador getEntrenador1() {
		return entrenador1;
	}

	protected Entrenador getEntrenador2() {
		return entrenador2;
	}

	protected ArrayList<Pokemon> getPokemon1() {
		return pokemon1;
	}

	protected ArrayList<Pokemon> getPokemon2() {
		return pokemon2;
	}

	protected ArrayList<Pokemon> getCombatientes() {
		return combatientes;
	}

	protected CondArena getCondArena() {
		return condArena;
	}

	protected void setCondArena(CondArena condArena) {
		this.condArena = condArena;
	}

	protected CondPosiPkmn getCondPoke1() {
		return condPoke1;
	}

	protected void setCondPoke1(CondPosiPkmn condPoke1) {
		this.condPoke1 = condPoke1;
	}

	protected CondPosiPkmn getCondPoke2() {
		return condPoke2;
	}

	protected void setCondPoke2(CondPosiPkmn condPoke2) {
		this.condPoke2 = condPoke2;
	}

	// Methods

	// Se implementara mas unas funciones para poder hacer combates 1vs1, 2vs2 o
	// 3vs3
	public void prepararCombateTerminal(int entrenadores) {
		switch (entrenadores) {
		case 1:
			setEntrenadores();
			combateTerminal();
			break;

		default:
			System.out.println("\n\nSolo hay combates 1vs1 xD\n");
			setEntrenadores();
			combateTerminal();
		}
	}

	private void combateTerminal() {
		Pokemon atacante;

		while (!isFinished()) {
			for (Pokemon pokemon : this.pokemon1) {
				this.combatientes.add(pokemon);
			}
			for (Pokemon pokemon : this.pokemon2) {
				this.combatientes.add(pokemon);
			}

			setPkmnId(this.combatientes);

			System.out.println("\nTurno de " + this.entrenador1.getNombre());
			for (Pokemon pkmn : this.entrenador1.getEquipo()) {
				if (pkmn.getEstado() != Estado.Cargando) {
					System.out.println(pkmn);
					while (this.moves.size() < pkmn.getIdPelea()) {
						this.moves.add(null);
					}
					this.moves.add(pkmn.getIdPelea(), pkmn.elegirMovimiento());
					if (this.combatientes.size() == 2) {
						pkmn.setAtaca(0);
					}
					// WIP expansión para combates 2vs2, 3vs3
				} else {
					for (int i = 0; i < this.chargedMoves.size(); i++) {
						if (pkmn.getIdPelea() == i) {
							for (AbstractMove pkmnMove : pkmn.getMovimientos()) {
								if (pkmnMove.getMove() == this.chargedMoves.get(i).getMove()) {
									this.moves.add(pkmn.getIdPelea(), pkmnMove);
								}
							}
						}
					}
				}
			}

			System.out.println("\nTurno de " + this.entrenador2.getNombre());
			for (Pokemon pkmn : this.entrenador2.getEquipo()) {
				if (pkmn.getEstado() != Estado.Cargando) {
					System.out.println(pkmn);
					while (this.moves.size() < pkmn.getIdPelea()) {
						this.moves.add(null);
					}
					this.moves.add(pkmn.getIdPelea(), pkmn.elegirMovimiento());
					if (this.combatientes.size() == 2) {
						pkmn.setAtaca(0);
					}
					// WIP expansión para combates 2vs2, 3vs3
				} else {
					for (int i = 0; i < this.chargedMoves.size(); i++) {
						if (pkmn.getIdPelea() == i) {
							for (AbstractMove pkmnMove : pkmn.getMovimientos()) {
								if (pkmnMove.getMove() == this.chargedMoves.get(i).getMove()) {
									this.moves.add(pkmn.getIdPelea(), pkmnMove);
								}
							}
						}
					}
				}
			}

			ListIterator<Pokemon> combate = this.combatientes.listIterator();

			ArrayList<Pokemon> priori = new ArrayList<Pokemon>();

			while (combate.hasNext()) {
				int prioridad = Integer.MIN_VALUE;

				for (AbstractMove move : this.moves) {
					if (move.getPrioridad() > prioridad) {
						prioridad = move.getPrioridad();
					}
				}

				for (int i = 0; i < this.moves.size(); i++) {
					if (moves.get(i).getPrioridad() == prioridad) {
						priori.add(this.combatientes.get(i));
					}
				}

				atacante = quienEmpiezaTurno(priori);

				combatientes.remove(atacante);
				if (atacante.getEntrenador() == this.entrenador1) {
					aplicarMovimiento(this.entrenador1.getEquipo(), atacante, this.moves.get(atacante.getIdPelea()),
							this.pokemon2.get(atacante.getAtaca()));
				} else {
					aplicarMovimiento(this.entrenador2.getEquipo(), atacante, this.moves.get(atacante.getIdPelea()),
							this.pokemon1.get(atacante.getAtaca()));
				}
			}
		}

		System.out.println(this.entrenador1.getEquipo().get(0));
		System.out.println(this.entrenador2.getEquipo().get(0));

//		for (Pokemon poke : combatientes) {
//			System.out.println(poke);
//		}
	}

	private Pokemon quienEmpiezaTurno(ArrayList<Pokemon> pokemons) {
		int fastest = Integer.MIN_VALUE;
		Pokemon pkmn = null;

		for (Pokemon pokemon : pokemons) {
			if (pokemon.getSpeed() > fastest) {
				pkmn = pokemon;
			}
		}
		return pkmn;
	}

	private void aplicarMovimiento(ArrayList<Pokemon> equipo, Pokemon atacante, AbstractMove movimiento,
			Pokemon rival) {
		if (atacante.getActualHp() > 0) {
			// TODO aplicar formulas de daño
			if (movimiento.getActPP() > 0) {
				removePkmnStatus(atacante);
				if ((movimiento.getAplicaEstado() == Estado.Cargando) && (atacante.getTurnosEstado() > 0)) {
					atacante.setEstado(Estado.Cargando);
					atacante.setTurnosEstado(1);
					for (int i = 0; i < atacante.getIdPelea(); i++) {
						this.chargedMoves.add(null);
					}
					this.chargedMoves.set(atacante.getIdPelea(), movimiento);
				}

				switch (atacante.getEstado()) {
				case Paralisis: {
					if ((int) (Math.random() * 101) > 25) {
						doMove(equipo, atacante, movimiento, rival);
					} else {
						System.out.println(atacante.getNombre() + " está paralizado");
					}
					break;
				}

				case Retroceder:
					System.out.println(atacante.getNombre() + " ha retrocedido");
					break;

				case Recarga:
					System.out.println(atacante.getNombre() + " esta recargandose");
					break;

				case Cargando:
					System.out.println(atacante.getNombre() + " esta cargando el ataque");
					break;

				default: {
					doMove(equipo, atacante, movimiento, rival);
				}
				}

			} else {
				int damage = calcDamage(atacante, rival, utils.Almacen.almacenMovimientos.get(0));

				rival.setActualHp(rival.getActualHp() - damage);

				atacante.setActualHp(atacante.getActualHp() - ((int) (atacante.getMaxHP() / 4)));
			}
			applyPkmnStatus(atacante, rival);
			this.moves.remove(movimiento);
		}
	}

	public void doMove(ArrayList<Pokemon> equipo, Pokemon atacante, AbstractMove movimiento, Pokemon rival) {
		if (movimiento.getPrecision() > 0) {
			if (((int) (Math.random() * 101)) < movimiento.getPrecision()) {
				condMove(equipo, atacante, movimiento, rival);
			} else {
				System.out.println("Ha fallado!");
			}
		} else {
			condMove(equipo, atacante, movimiento, rival);
		}
	}

	public void condMove(ArrayList<Pokemon> equipo, Pokemon atacante, AbstractMove movimiento, Pokemon rival) {
		if (movimiento instanceof MoveAtkMulti) {
			int chance = (int) (Math.random() * (((MoveAtkMulti) movimiento).getGolpesMax() + 1));
			if (chance < ((MoveAtkMulti) movimiento).getGolpesMin()) {
				chance = ((MoveAtkMulti) movimiento).getGolpesMin();
			}
			for (int i = 0; i < chance; i++) {
				ataque(equipo, atacante, movimiento, rival);
			}
			System.out.println(movimiento.getNombre() + " ha golpeado " + chance + " veces");
		} else if (movimiento instanceof MoveAtkCarga) {
			if (((MoveAtkCarga) movimiento).getTurnosCargados() < ((MoveAtkCarga) movimiento).getTurnosNecesarios()) {
				System.out.println(atacante.getNombre() + " está cargando el ataque");
				((MoveAtkCarga) movimiento).setTurnoCargado(((MoveAtkCarga) movimiento).getTurnosCargados() + 1);
			} else {
				ataque(equipo, atacante, movimiento, rival);
				((MoveAtkCarga) movimiento).setTurnoCargado(1);
			}
		} else {
			ataque(equipo, atacante, movimiento, rival);
		}
	}

	public void ataque(ArrayList<Pokemon> equipo, Pokemon atacante, AbstractMove movimiento, Pokemon rival) {
		int damage;

		AbstractMove move = strangeMove(equipo, movimiento);
		AbstractMove apoyo = (AbstractMove) movimiento.copiarMove();
		movimiento = (AbstractMove) move.copiarMove();
		statusAttack(atacante, rival, movimiento);
		chargeMove(movimiento);
		condArenaBeforeCalc(movimiento);

		damage = calcDamage(atacante, rival, movimiento);

		applyDamage(atacante, rival, move, damage);
		applyStatus(atacante, rival, movimiento);
		applyCondPosiPkmnBeforeCalc(equipo, atacante, movimiento, rival);

		condArenaAfterCalc(this.combatientes);

		recoilMove(atacante, movimiento, damage);

		movimiento = (AbstractMove) apoyo.copiarMove();

		if (movimiento.getActPP() > 0)
			movimiento.setActPP(movimiento.getActPP() - 1);
	}

	private int calcDamage(Pokemon atacante, Pokemon rival, AbstractMove movimiento) {
		switch (movimiento.getMove()) {
		case Endeavor:
			return rival.getActualHp() - (rival.getActualHp() - atacante.getActualHp());

		case Super_Fang:
			return (int) (rival.getActualHp() / 2);

		default: {
			double modTiempo = 1, random = 1, stab = 1, quemadura = 1, otro = 1;
			double getRandom, efectividad;
			int crit;

			switch (condArena) {
			case Soleado:
				if (movimiento.getTipo() == Tipo.Fuego) {
					modTiempo = 1.5;
				} else if (movimiento.getTipo() == Tipo.Agua) {
					modTiempo = 0.5;
				}
				break;

			case Lluvia:
				if (movimiento.getTipo() == Tipo.Agua) {
					modTiempo = 1.5;
				} else if (movimiento.getTipo() == Tipo.Fuego) {
					modTiempo = 0.5;
				}
				break;

			default:
				modTiempo = 1;
			}

			switch ((int) (Math.random() * 11)) {
			case 10:
				crit = 2;
				break;

			default:
				crit = 1;
			}

			getRandom = (int) (Math.random() * 101);

			if (getRandom <= 85) {
				random = 85;
			}

			random /= 100;

			if (movimiento.getTipo() == atacante.getTipo1()) {
				stab = 1.5;
			}

			if (atacante.getTipo2() != Tipo.Ninguno) {
				if (movimiento.getTipo() == atacante.getTipo2()) {
					stab = 1.5;
				}
			}

			efectividad = movimiento.getEfectividad(movimiento.getTipo(), rival.getTipo1());

			if (rival.getTipo2() != Tipo.Ninguno) {
				efectividad += movimiento.getEfectividad(movimiento.getTipo(), rival.getTipo2());
			}

			if (atacante.getEstado() == Estado.Quemado && movimiento.getClase() == Clase.Fisico) {
				quemadura = 0.5;
			}

			if (movimiento instanceof MoveAtkCarga) {
				if (((MoveAtkCarga) movimiento).getTurnosCargados() == ((MoveAtkCarga) movimiento)
						.getTurnosNecesarios()) {
					return attackDamage(atacante, rival, movimiento, modTiempo, crit, random, stab, efectividad,
							quemadura, otro);
				} else {
					return 0;
				}
			} else {
				return attackDamage(atacante, rival, movimiento, modTiempo, crit, random, stab, efectividad, quemadura,
						otro);
			}
		}

		}
	}

	private int attackDamage(Pokemon atacante, Pokemon rival, AbstractMove movimiento, double modTiempo, int crit,
			double random, double stab, double efectividad, double quemadura, double otro) {
		if (movimiento.getClase() == Clase.Fisico) {
			return (int) (((((((2 * atacante.getLevel()) / 5) + 2) * movimiento.getDamage()
					* (atacante.getAttack() / rival.getDefence())) / 50) + 2) * 1 * modTiempo * crit * random * stab
					* efectividad * quemadura * otro);
		} else {
			return (int) (((((((2 * atacante.getLevel()) / 5) + 2) * movimiento.getDamage()
					* (atacante.getSpAttack() / rival.getSpDefence())) / 50) + 2) * 1 * modTiempo * crit * random * stab
					* efectividad * quemadura * otro);
		}
	}

	private void applyDamage(Pokemon atacante, Pokemon rival, AbstractMove movimiento, int damage) {
		if (atacante.getEstado() == Estado.Confusion) {
			if (((int) (Math.random() * 101)) <= 33) {
				atacante.setActualHp(rival.getActualHp() - damage);
			} else {
				rival.setActualHp(rival.getActualHp() - damage);
			}
		} else {
			rival.setActualHp(rival.getActualHp() - damage);

		}
	}

	private void applyStatus(Pokemon atacante, Pokemon rival, AbstractMove movimiento) {
		this.condArena = movimiento.getAplicaCondArena();

		atacante.setAttack(atacante.getAttack() * movimiento.getChnAtkYou());
		atacante.setSpAttack(atacante.getSpAttack() * movimiento.getChnSpAtkYou());
		atacante.setDefence(atacante.getDefence() * movimiento.getChnDefYou());
		atacante.setSpDefence(atacante.getSpDefence() * movimiento.getChnSpDefYou());
		atacante.setSpeed(atacante.getSpeed() * movimiento.getChnSpeYou());

		rival.setAttack(rival.getAttack() * movimiento.getChnAtkRiv());
		rival.setSpAttack(rival.getSpAttack() * movimiento.getChnSpAtkRiv());
		rival.setDefence(rival.getDefence() * movimiento.getChnDefRiv());
		rival.setSpDefence(rival.getSpDefence() * movimiento.getChnSpDefRiv());
		rival.setSpeed(rival.getSpeed() * movimiento.getChnSpeRiv());
	}

	private void applyCondPosiPkmnBeforeCalc(ArrayList<Pokemon> equipo, Pokemon atacante, AbstractMove movimiento,
			Pokemon rival) {
		switch (movimiento.getAplicaCondPosiPkmn()) {
		case Proteccion:
			break;

		case Velo_Magico:
			break;

		default:
		}
	}

	private void condArenaBeforeCalc(AbstractMove movimiento) {
		switch (this.condArena) {
		// Falta, y mucho
		case Soleado:
			switch (movimiento.getTipo()) {
			case Fuego:
				movimiento.setDamage((int) (movimiento.getDamage() * 1.5));
				break;

			case Agua:
				movimiento.setDamage((int) (movimiento.getDamage() * 0.5));
				break;

			default:
			}

		default:
		}
	}

	private void condArenaAfterCalc(ArrayList<Pokemon> combatientes) {
		switch (this.condArena) {
		case Soleado:
			for (Pokemon pokemon : combatientes) {
				if (pokemon.getEstado() == Estado.Congelado) {
					pokemon.setEstado(Estado.Ninguno);
				}
			}
			break;

		default:
		}
	}

	/*
	 * Pinchos, proteccion, etc.
	 */
//	private void condPosiPkmnEntrada() {
//case Pinchos_Venenosos:
//case Pinchos:
//	break;
//
//case Piedras_Afiladas:
//	break;
//
//case Tela_Arana:
//	break;
//
//case Acero_Afilado:
//	break;
//	}

	private void chargeMove(AbstractMove movimiento) {
		switch (movimiento.getMove()) {
		case Solar_Beam:
			switch (this.condArena) {
			case Soleado:
				((MoveAtkCarga) movimiento).setTurnoCargado(2);
				break;

			default:
			}
			break;

		default:
		}

	}

	private void statusAttack(Pokemon atacante, Pokemon rival, AbstractMove movimiento) {
		int turnos;
		if (rival.getTurnosEstado() == 0) {
			switch (movimiento.getMove()) {
			case Relic_Song:
				if (((int) (Math.random() * 101)) <= 10) {
					rival.setEstado(Estado.Dormido);
					rival.setTurnosEstado(3);
				}
				break;

			case Snore:
				if (((int) (Math.random() * 101)) <= 30) {
					rival.setEstado(Estado.Retroceder);
					rival.setTurnosEstado(1);
				}
				break;

			case Bolt_Strike:
				if (((int) (Math.random() * 101)) <= 20) {
					rival.setEstado(Estado.Paralisis);
					rival.setTurnosEstado(999999);
				}
				break;

			case Spark:
				if (((int) (Math.random() * 101)) <= 30) {
					rival.setEstado(Estado.Paralisis);
					rival.setTurnosEstado(999999);
				}
				break;

			case Petal_Dance:
				atacante.setEstado(Estado.Confusion);
				turnos = (int) (Math.random() * 6);
				if (turnos == 0) {
					turnos = 1;
				}
				atacante.setTurnosEstado(turnos);
				break;

			case Thunder_Wave:
				rival.setEstado(Estado.Paralisis);
				rival.setTurnosEstado(999999);
				break;

			case Flamethrower:
				if (((int) (Math.random() * 101)) <= 10) {
					rival.setEstado(Estado.Quemado);
					rival.setTurnosEstado(20);
				}
				break;

			case Fire_Fang:
				if (((int) (Math.random() * 2)) == 0) {
					if (((int) (Math.random() * 101)) <= 10) {
						rival.setEstado(Estado.Quemado);
						rival.setTurnosEstado(20);
					}
				} else {
					if (((int) (Math.random() * 101)) <= 30) {
						rival.setEstado(Estado.Retroceder);
						rival.setTurnosEstado(1);
					}
				}
				break;

			case Blaze_Kick:
				if (((int) (Math.random() * 101)) <= 10) {
					rival.setEstado(Estado.Quemado);
					rival.setTurnosEstado(20);
				}
				break;

			case Hydro_Canon:
				atacante.setEstado(Estado.Recarga);
				atacante.setTurnosEstado(1);
				break;

			default:
			}
		}

		switch (movimiento.getAplicaCondPosiPkmn()) {
		case Atrapado_Clamp:
			if (rival.getDurPkmnStatus() > 0) {
				rival.setCondPkmnStatus(CondPosiPkmn.Atrapado_Clamp);
				turnos = (int) (Math.random() * 6);
				if (turnos < 4)
					turnos = 4;
				rival.setDurPkmnStatus(turnos);
			}
			break;

		case Heal_1p16:
			atacante.setCondPkmnStatus(CondPosiPkmn.Heal_1p16);
			break;

		default:
		}
	}

	private void applyPkmnStatus(Pokemon atacante, Pokemon rival) {
		switch (atacante.getEstado()) {
		case Quemado:
			if (rival.getActualHp() > 0 && (atacante.getTipo1() == Tipo.Fuego || atacante.getTipo2() == Tipo.Fuego)) {
				atacante.setActualHp(atacante.getActualHp() - ((int) (atacante.getMaxHP() / 16)));
			}
			break;

		default:
		}

		switch (rival.getCondPkmnStatus()) {
		case Atrapado_Clamp:
			rival.setActualHp(rival.getActualHp() - (rival.getMaxHP() / 8));
			break;

		default:
		}

		switch (atacante.getCondPkmnStatus()) {
		case Heal_1p16:
			atacante.setActualHp(atacante.getActualHp() + (atacante.getMaxHP() / 16));
			break;

		default:
		}
	}

	private void removePkmnStatus(Pokemon pokemon) {
		if (pokemon.getTurnosEstado() > 0) {
			pokemon.setTurnosEstado(pokemon.getTurnosEstado() - 1);
		} else if (pokemon.getTurnosEstado() == 0) {
			pokemon.setEstado(Estado.Ninguno);
		}

		if (pokemon.getDurPkmnStatus() > 0) {
			pokemon.setDurPkmnStatus(0);
		} else if (pokemon.getDurPkmnStatus() == 0) {
			pokemon.setCondPkmnStatus(CondPosiPkmn.Ninguno);
			;
		}

		switch (pokemon.getEstado()) {
		case Paralisis:
			if ((int) (Math.random() * 101) <= 15) {
				pokemon.setEstado(Estado.Ninguno);
				pokemon.setTurnosEstado(0);
				System.out.println(pokemon.getNombre() + " ya no está paralizado");
			}
			break;

		case Dormido:
			if ((int) (Math.random() * 101) <= 33) {
				pokemon.setEstado(Estado.Ninguno);
				pokemon.setTurnosEstado(0);
				System.out.println(pokemon.getNombre() + " se ha despertado");
			}
			break;

		case Quemado:
			if ((int) (Math.random() * 101) <= 15) {
				pokemon.setEstado(Estado.Ninguno);
				pokemon.setTurnosEstado(0);
				System.out.println(pokemon.getNombre() + " ya no está quemado");
			}
			break;

		default:
		}
	}

	private AbstractMove strangeMove(ArrayList<Pokemon> equipo, AbstractMove movimiento) {
		switch (movimiento.getMove()) {
		case Assist:
			ArrayList<AbstractMove> movimientos_equipo = new ArrayList<AbstractMove>();
			for (Pokemon poke : equipo) {
				for (AbstractMove move : poke.getMovimientos()) {
					movimientos_equipo.add(move);
				}
			}
			return changeMove(movimientos_equipo.get((int) (Math.random() * movimientos_equipo.size())));

		case Metronome:
			return changeMove(utils.Almacen.almacenMovimientos
					.get((int) (Math.random() * utils.Almacen.almacenMovimientos.size())));

		default:
			return movimiento;
		}
	}

	private AbstractMove changeMove(AbstractMove objetivo) {
		if (objetivo instanceof MoveAtk) {
			return new MoveAtk(objetivo.getAplicaEstado(), objetivo.getAplicaCondPosiPkmn(), objetivo.getTipo(),
					objetivo.getClase(), objetivo.getMove(), objetivo.getNombre(), objetivo.getDescripcion(),
					objetivo.getMaxPP(), objetivo.getDamage(), objetivo.getPrecision(), objetivo.getPrioridad(),
					objetivo.getChnAtkYou(), objetivo.getChnAtkRiv(), objetivo.getChnSpAtkYou(),
					objetivo.getChnSpAtkRiv(), objetivo.getChnDefYou(), objetivo.getChnDefRiv(),
					objetivo.getChnSpDefYou(), objetivo.getChnSpDefRiv(), objetivo.getChnSpeYou(),
					objetivo.getChnSpeRiv());
		} else if (objetivo instanceof MoveAtkMulti) {
			return new MoveAtkMulti(objetivo.getAplicaEstado(), objetivo.getAplicaCondPosiPkmn(), objetivo.getTipo(),
					objetivo.getClase(), objetivo.getMove(), objetivo.getNombre(), objetivo.getDescripcion(),
					objetivo.getMaxPP(), objetivo.getDamage(), objetivo.getPrecision(), objetivo.getPrioridad(),
					((MoveAtkMulti) objetivo).getGolpesMin(), ((MoveAtkMulti) objetivo).getGolpesMax(),
					objetivo.getChnAtkYou(), objetivo.getChnAtkRiv(), objetivo.getChnSpAtkYou(),
					objetivo.getChnSpAtkRiv(), objetivo.getChnDefYou(), objetivo.getChnDefRiv(),
					objetivo.getChnSpDefYou(), objetivo.getChnSpDefRiv(), objetivo.getChnSpeYou(),
					objetivo.getChnSpeRiv());
		} else if (objetivo instanceof MoveAtkCarga) {
			return new MoveAtkCarga(objetivo.getAplicaEstado(), objetivo.getAplicaCondPosiPkmn(), objetivo.getTipo(),
					objetivo.getClase(), objetivo.getMove(), objetivo.getNombre(), objetivo.getDescripcion(),
					objetivo.getMaxPP(), objetivo.getDamage(), objetivo.getPrecision(), objetivo.getPrioridad(),
					((MoveAtkCarga) objetivo).getTurnosNecesarios(), objetivo.getChnAtkYou(), objetivo.getChnAtkRiv(),
					objetivo.getChnSpAtkYou(), objetivo.getChnSpAtkRiv(), objetivo.getChnDefYou(),
					objetivo.getChnDefRiv(), objetivo.getChnSpDefYou(), objetivo.getChnSpDefRiv(),
					objetivo.getChnSpeYou(), objetivo.getChnSpeRiv());
		} else {
			return new MoveStatus(objetivo.getAplicaEstado(), objetivo.getAplicaCondArena(),
					objetivo.getAplicaCondPosiPkmn(), objetivo.getTipo(), objetivo.getClase(), objetivo.getMove(),
					objetivo.getNombre(), objetivo.getDescripcion(), objetivo.getMaxPP(), objetivo.getPrecision(),
					objetivo.getPrioridad(), objetivo.getChnAtkYou(), objetivo.getChnAtkRiv(),
					objetivo.getChnSpAtkYou(), objetivo.getChnSpAtkRiv(), objetivo.getChnDefYou(),
					objetivo.getChnDefRiv(), objetivo.getChnSpDefYou(), objetivo.getChnSpDefRiv(),
					objetivo.getChnSpeYou(), objetivo.getChnSpeRiv());
		}
	}

	private void recoilMove(Pokemon atacante, AbstractMove movimiento, int damage) {
		switch (movimiento.getMove()) {
		/*
		 * DAÑO
		 */
		// 1/4 del daño realizado
		case Double_Edge:
		case Head_Charge:
		case Submission:
		case Take_Down:
		case Wild_Charge:
			atacante.setActualHp((int) (damage / 4));
			break;

		// 1/3 del daño realizado
		case Brave_Bird:
		case Flare_Blitz:
		case Volt_Tackle:
		case Wood_Hammer:
			atacante.setActualHp((int) (damage / 3));
			break;

		// 1/2 del daño realizado
		case Head_Smash:
		case Light_Ruin:
			atacante.setActualHp((int) (damage / 2));
			break;

		// 1/2 de la vida actual
		case Shadow_End:
			atacante.setActualHp((int) (atacante.getActualHp() / 3));
			break;

		// 1/16 de la vida maxima
		case Shadow_Rush:
			atacante.setActualHp((int) (atacante.getMaxHP() / 3));
			break;

		/*
		 * CURAR
		 */
		// 1/2 del daño realizado
		case Horn_Leech:
			atacante.setActualHp((int) (damage / 2));
			break;

		default:
		}
	}

	private boolean isFinished() {
		if (!this.entrenador1.puedeCombatir() || !this.entrenador2.puedeCombatir()) {
			return true;
		} else {
			return false;
		}
	}

	private void setEntrenadores() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.print("Nombre del primer entrenador: ");
		this.entrenador1.setNombre(sc.nextLine());
		setPokemon(entrenador1);
		this.pokemon1.add(this.entrenador1.getEquipo().get(0));
		this.entrenador1.getEquipo().get(0).setPosicion(0);

		System.out.print("Nombre del segundo entrenador: ");
		this.entrenador2.setNombre(sc.nextLine());
		setPokemon(entrenador2);
		this.pokemon2.add(this.entrenador2.getEquipo().get(0));
		this.entrenador2.getEquipo().get(0).setPosicion(0);
	}

	private void setPokemon(Entrenador entrenador) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int opc;
		boolean elegido;

		System.out.println("Elige tu Pokemon, " + entrenador.getNombre());
		do {
			utils.Almacen.mostrarPokemons();
			System.out.print("->: ");
			opc = Integer.parseInt(sc.nextLine());
			if (opc < 1 || opc > utils.Almacen.almacenPokemon.size()) {
				elegido = false;
				System.out.println("\n\nElige un numero correcto\n");
			} else {
				elegido = true;
				entrenador.meterPkmnEquipo((Pokemon) utils.Almacen.almacenPokemon.get(opc - 1).copiarPokemon());
				entrenador.getEquipo().get(entrenador.getEquipo().size() - 1).setEntrenador(entrenador);
			}
		} while (!elegido);
		setPkmnMoves(entrenador.getEquipo().get(entrenador.getEquipo().size() - 1));
	}

	private void setPkmnMoves(Pokemon pokemon) {
		for (int i = 0; i < 4; i++) {
			AbstractMove move;
			move = (AbstractMove) utils.Almacen.almacenMovimientos
					.get((int) (Math.random() * utils.Almacen.almacenMovimientos.size())).copiarMove();
			pokemon.aprenderMovimiento(move);
		}
	}

	private void setPkmnId(ArrayList<Pokemon> pokemons) {
		for (int i = 0; i < pokemons.size(); i++) {
			pokemons.get(i).setIdPelea(i);
		}
	}

	/*
	 * TODO En el combate: si condArena != CondArena.Ninguno, aplicar el daño y las
	 * condiciones que sean Si condPoke1/2 != CondPosiPoke.Ninguno, aplicar el daño
	 * y las condiciones que sean
	 */
}
