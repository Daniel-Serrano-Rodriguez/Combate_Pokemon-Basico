package models;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import models.Move.Clase;
import models.TipoPokemon.Tipo;
import utils.CondArena;
import utils.CondPosiPkmn;
import utils.Estado;

public class Combate {
	private Entrenador entrenador1, entrenador2;
	private ArrayList<Pokemon> pokemon1, pokemon2, combatientes;
	// TODO si el pokemon es ditto, y usa el movimiento de copiar, se le reduce en 1
	// al contandor del movimiento, se crea una copia en ditto[], y se actualiza el
	// pokemon con los datos del pokemon objetivo
	private Pokemon[] ditto;
	private Move[] moves;
	private CondArena condArena;
	private int contCondArena;

	/**
	 * Constructor del objeto 'Combate'. Se crean 2 arrayList vacios que representan
	 * los Pokemon actualmente en la arena
	 */
	public Combate(Entrenador entrenador1, Entrenador entrenador2, int tipoCombate) {
		this.entrenador1 = entrenador1;
		this.entrenador2 = entrenador2;
		this.pokemon1 = new ArrayList<Pokemon>();
		this.pokemon2 = new ArrayList<Pokemon>();
		this.combatientes = new ArrayList<Pokemon>();
		if (tipoCombate == 2) {
			this.moves = new Move[4];
		} else if (tipoCombate == 3) {
			this.moves = new Move[6];
		} else {
			this.moves = new Move[2];
		}
		this.condArena = CondArena.Ninguno;
		this.contCondArena = 0;
	}

	/**
	 * Constructor del objeto 'Combate'. Se crean 2 entrenadores que han de ser
	 * creados y 2 arrayList vacios que representan los Pokemon actualmente en la
	 * arena
	 */
	public Combate(int tipoCombate) {
		this.entrenador1 = new Entrenador();
		this.entrenador2 = new Entrenador();
		this.pokemon1 = new ArrayList<Pokemon>();
		this.pokemon2 = new ArrayList<Pokemon>();
		this.combatientes = new ArrayList<Pokemon>();
		this.combatientes = new ArrayList<Pokemon>();
		if (tipoCombate == 2) {
			this.moves = new Move[4];
		} else if (tipoCombate == 3) {
			this.moves = new Move[6];
		} else {
			this.moves = new Move[2];
		}
		this.condArena = CondArena.Ninguno;
		this.contCondArena = 0;
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

	protected int getContCondArena() {
		return contCondArena;
	}

	protected void setContCondArena(int contCondArena) {
		this.contCondArena = contCondArena;
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

			System.out.println("\n\nTurno de " + this.entrenador1.getNombre() + "\n¿Qué quieres hacer?\n");
			choiceBlock(this.entrenador1, this.pokemon1, this.pokemon2);

			System.out.println("\n\nTurno de " + this.entrenador2.getNombre());
			choiceBlock(this.entrenador2, this.pokemon2, this.pokemon1);

			ListIterator<Pokemon> combate = this.combatientes.listIterator();

			ArrayList<Pokemon> priori = new ArrayList<Pokemon>();

			while (combate.hasNext() && this.moves != null) {
				int prioridad = Integer.MIN_VALUE;

				for (Move move : this.moves) {
					if (move.getPrioridad() > prioridad) {
						prioridad = move.getPrioridad();
					}
				}

				for (int i = 0; i < this.moves.length; i++) {
					if (moves[i].getPrioridad() == prioridad) {
						if (i < this.combatientes.size())
							priori.add(this.combatientes.get(i));
					}
				}

				atacante = quienEmpiezaTurno(priori);

				combatientes.remove(atacante);
				if (atacante.getEntrenador() == this.entrenador1) {
					if (atacante.hasCond(CondPosiPkmn.Cargando)) {
						System.out.println(atacante.getNombre() + " está cargando "
								+ this.moves[atacante.getIdPelea()].getNombre());
						aplicarMovimiento(this.entrenador1.getEquipo(), atacante, this.moves[atacante.getIdPelea()],
								this.pokemon2.get(atacante.getAtaca()));
					} else {
						System.out.println(
								atacante.getNombre() + " ha usado " + this.moves[atacante.getIdPelea()].getNombre());
						aplicarMovimiento(this.entrenador1.getEquipo(), atacante, this.moves[atacante.getIdPelea()],
								this.pokemon2.get(atacante.getAtaca()));
					}
				} else {
					System.out.println(
							atacante.getNombre() + " ha usado " + this.moves[atacante.getIdPelea()].getNombre());
					aplicarMovimiento(this.entrenador2.getEquipo(), atacante, this.moves[atacante.getIdPelea()],
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

	private void choiceBlock(Entrenador entrenador, ArrayList<Pokemon> atacantes, ArrayList<Pokemon> rivales) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean choice = false, notNum = true;

		do {
			while (notNum) {
				try {
					for (Pokemon pokemon : atacantes) {
						System.out.println("Estado " + pokemon);
					}
					System.out.println("\n1.Atacar\n2.Cambiar Pokemon\n");
					System.out.print("->: ");
					switch (Integer.parseInt(sc.nextLine())) {
					case 1:
						choice = true;
						notNum = false;
						selAtack(atacantes, rivales);
						break;

					case 2:
						choice = true;
						notNum = false;
						choosePkmn(entrenador.getEquipo());
						break;

					default:
						choice = false;
						System.out.println("\n\nElige un numero correcto\n");
					}
				} catch (NumberFormatException e) {
					System.out.println("\n\nIntroduce un numero\n");
				}
			}
		} while (!choice);
	}

	private void selAtack(ArrayList<Pokemon> atacantes, ArrayList<Pokemon> rivales) {
		for (Pokemon pkmn : atacantes) {
			if (!pkmn.hasCond(CondPosiPkmn.Cargando)) {
				System.out.print("\n\n");
				this.moves[pkmn.getIdPelea()] = pkmn.elegirMovimiento();
				if (this.combatientes.size() == 2) {
					pkmn.setAtaca(0);
				}
				// WIP expansión para combates 2vs2, 3vs3
			}
		}
	}

	private void choosePkmn(ArrayList<Pokemon> equipo) {
		System.out.println("\n¿A quien quieres sacar?\n");
		for (int i = 0; i < equipo.size(); i++) {
			if (i % 2 == 0)
				System.out.println();

			System.out.print((i + 1) + "." + equipo.get(i).getNombre() + "     \t");
		}
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

	private void aplicarMovimiento(ArrayList<Pokemon> equipo, Pokemon atacante, Move movimiento, Pokemon rival) {
		if (atacante.getActualHp() > 0) {
			if (movimiento.getActPP() > 0) {
				removePkmnStatus(atacante);
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

				default: {
					if (movimiento.getGolpesMax() != 1) {
						int chance = (int) (Math.random() * movimiento.getGolpesMax() + 1);
						if (chance < movimiento.getGolpesMin()) {
							chance = movimiento.getGolpesMin();
						}
						for (int i = 0; i < chance; i++) {
							doMove(equipo, atacante, movimiento, rival);
						}
						System.out.println(movimiento.getNombre() + " ha golpeado " + chance + " veces");
					} else if (movimiento.getAplicaCondPosiPkmn() == CondPosiPkmn.Cargando) {
						if (!atacante.hasCond(CondPosiPkmn.Cargando)) {
							statusAttack(atacante, rival, movimiento);
						} else if (atacante.hasCond(CondPosiPkmn.Cargando)) {
							if (movimiento.getTurnosCargados() < movimiento.getTurnosNecesarios()) {
								System.out.println(atacante.getNombre() + " esta cargando el ataque");
								movimiento.setTurnosCargados(movimiento.getTurnosCargados() + 1);
							} else {
								movimiento.setTurnosCargados(1);
								doMove(equipo, atacante, movimiento, rival);
							}
						}
					} else
						doMove(equipo, atacante, movimiento, rival);

					movimiento.setActPP(movimiento.getActPP() - 1);
				}
				}

			} else {
				int damage = calcDamage(atacante, rival, utils.Almacen.almacenMovimientos.get(0));

				rival.setActualHp(rival.getActualHp() - damage);

				atacante.setActualHp(atacante.getActualHp() - ((int) (atacante.getMaxHP() / 4)));
			}
			applyPkmnStatus(atacante, rival);
		}
	}

	public void doMove(ArrayList<Pokemon> equipo, Pokemon atacante, Move movimiento, Pokemon rival) {
		if (movimiento.getPrecision() > 0) {
			if (((int) (Math.random() * 101)) < movimiento.getPrecision()) {
				ataque(equipo, atacante, movimiento, rival);
			} else {
				System.out.println("Ha fallado!");
			}
		} else {
			ataque(equipo, atacante, movimiento, rival);
		}
	}

	public void ataque(ArrayList<Pokemon> equipo, Pokemon atacante, Move movimiento, Pokemon rival) {
		Move move = strangeMove(equipo, movimiento);
		Move apoyo = (Move) movimiento.copiarMove();
		movimiento = (Move) move.copiarMove();
		statusAttack(atacante, rival, movimiento);
		chargeMove(movimiento);
		condArenaBeforeCalc(movimiento);

		int damage = calcDamage(atacante, rival, movimiento);

		applyDamage(atacante, rival, damage);
		applyStatChanges(atacante, rival, movimiento);
		applyCondPosiPkmnAfterCalc(equipo, atacante, movimiento, rival);

		condArenaAfterCalc(this.combatientes);

		recoilMove(atacante, movimiento, damage);

		if (!atacante.hasCond(CondPosiPkmn.Cargando))
			movimiento = (Move) apoyo.copiarMove();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private int calcDamage(Pokemon atacante, Pokemon rival, Move movimiento) {
		switch (movimiento.getMove()) {
		case Endeavor:
			return rival.getActualHp() - (rival.getActualHp() - atacante.getActualHp());

		case Super_Fang:
			return (int) (rival.getActualHp() / 2);

		default: {
			if (movimiento.getClase() == Clase.Estado) {
				return 0;
			} else {
				double modTiempo = 1, random = 1, stab = 1, quemadura = 1, otro = 1;
				double efectividad;
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

				random = (int) (Math.random() * 101);

				if (random <= 85) {
					random = 85;
				}

				random = random / 100;

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

				int damage = attackDamage(atacante, rival, movimiento, modTiempo, crit, random, stab, efectividad,
						quemadura, otro);
				System.out.println(movimiento.getNombre() + " ha hecho " + damage);

				return damage;
			}
		}

		}
	}

	private int attackDamage(Pokemon atacante, Pokemon rival, Move movimiento, double modTiempo, int crit,
			double random, double stab, double efectividad, double quemadura, double otro) {
		if (movimiento.getClase() == Clase.Fisico) {
			return (int) (((((((2 * atacante.getLevel()) / 5) + 2) * movimiento.getDamage()
					* (atacante.getAttack() / rival.getDefence())) / 50) + 2) * 1 * modTiempo * crit * random * stab
					* efectividad * quemadura * otro);
		} else if (movimiento.getClase() == Clase.Especial) {
			return (int) ((((((((2 * atacante.getLevel()) / 5) + 2) * movimiento.getDamage())
					* (atacante.getSpAttack() / rival.getSpDefence())) / 50) + 2) * 1 * modTiempo * crit * random * stab
					* efectividad * quemadura * otro);
		}
		return 0;
	}

	private void applyDamage(Pokemon atacante, Pokemon rival, int damage) {
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

	private void applyStatChanges(Pokemon atacante, Pokemon rival, Move movimiento) {
		this.condArena = movimiento.getAplicaCondArena();
		this.contCondArena = 5;
//
//		atacante.setAttack(atacante.getAttack() * movimiento.getChnAtkYou());
//		atacante.setSpAttack(atacante.getSpAttack() * movimiento.getChnSpAtkYou());
//		atacante.setDefence(atacante.getDefence() * movimiento.getChnDefYou());
//		atacante.setSpDefence(atacante.getSpDefence() * movimiento.getChnSpDefYou());
//		atacante.setSpeed(atacante.getSpeed() * movimiento.getChnSpeYou());
//
//		rival.setAttack(rival.getAttack() * movimiento.getChnAtkRiv());
//		rival.setSpAttack(rival.getSpAttack() * movimiento.getChnSpAtkRiv());
//		rival.setDefence(rival.getDefence() * movimiento.getChnDefRiv());
//		rival.setSpDefence(rival.getSpDefence() * movimiento.getChnSpDefRiv());
//		rival.setSpeed(rival.getSpeed() * movimiento.getChnSpeRiv());
	}

	private void applyCondPosiPkmnAfterCalc(ArrayList<Pokemon> equipo, Pokemon atacante, Move movimiento,
			Pokemon rival) {
		if (atacante.hasCond(CondPosiPkmn.Heal_1p16)) {
			if (atacante.getActualHp() + (atacante.getMaxHP() / 16) > atacante.getMaxHP()) {
				atacante.setMaxHP(atacante.getMaxHP());
			} else {
				atacante.setActualHp(atacante.getActualHp() + (atacante.getMaxHP() / 16));
			}
		}

	}

//	private void applyCondPosiPkmnBeforeCalc(ArrayList<Pokemon> equipo, Pokemon atacante, Move movimiento,
//			Pokemon rival) {
//	}

	private void condArenaBeforeCalc(Move movimiento) {
		switch (this.condArena) {
		// Falta, y mucho
		case Soleado:
			// Precisiones y esas movidas

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

		if (this.contCondArena > 0) {
			this.contCondArena -= 1;
		} else {
			this.condArena = CondArena.Ninguno;
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

	private void chargeMove(Move movimiento) {
		switch (movimiento.getMove()) {
		case Solar_Beam:
			switch (this.condArena) {
			case Soleado:
				movimiento.setTurnosCargados(2);
				break;

			default:
			}
			break;

		default:
		}

	}

	private void statusAttack(Pokemon atacante, Pokemon rival, Move movimiento) {
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
					rival.setTurnosEstado(-1);
				}
				break;

			case Spark:
				if (((int) (Math.random() * 101)) <= 30) {
					rival.setEstado(Estado.Paralisis);
					rival.setTurnosEstado(-1);
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
				rival.setTurnosEstado(-1);
				break;

			case Flamethrower:
				if (((int) (Math.random() * 101)) <= 10) {
					rival.setEstado(Estado.Quemado);
					rival.setTurnosEstado(-1);
				}
				break;

			case Fire_Fang:
				if (((int) (Math.random() * 2)) == 0) {
					if (((int) (Math.random() * 101)) <= 10) {
						rival.setEstado(Estado.Quemado);
						rival.setTurnosEstado(-1);
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
					rival.setTurnosEstado(-1);
				}
				break;

			case Hydro_Canon:
				atacante.setEstado(Estado.Recarga);
				atacante.setTurnosEstado(1);
				break;

			default:
			}
		}

		switch (movimiento.getMove()) {
		case Clamp:
			rival.addPkmnCond(CondPosiPkmn.Atrapado);
			break;

		case Aqua_Ring:
			atacante.addPkmnCond(CondPosiPkmn.Heal_1p16);
			break;

		case Solar_Beam:
			if (!atacante.hasCond(CondPosiPkmn.Cargando))
				atacante.addPkmnCond(CondPosiPkmn.Cargando);
			else {
				atacante.removeDurPkmnCond(atacante.getPosiCond(CondPosiPkmn.Cargando));
				atacante.removePkmnCond(CondPosiPkmn.Cargando);
			}
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
	}

	private void removePkmnStatus(Pokemon pokemon) {
		if (pokemon.getTurnosEstado() > 0) {
			pokemon.setTurnosEstado(pokemon.getTurnosEstado() - 1);
		} else if (pokemon.getTurnosEstado() == 0) {
			pokemon.setEstado(Estado.Ninguno);
		}

		if (pokemon.getPkmnCond() != null)
			for (int i = 0; i < pokemon.getPkmnCond().size(); i++) {
				if (pokemon.getDurPkmnCond().get(i) > 0) {
					pokemon.setDurPkmnCond(i, pokemon.getDurPkmnCond().get(i) - 1);
				} else if (pokemon.getDurPkmnCond().get(i) == 0) {
					pokemon.removePkmnCond(pokemon.getPkmnCond().get(i));
					pokemon.removeDurPkmnCond(i);
				}
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

		case Congelado:
			if ((int) (Math.random() * 101) <= 20) {
				pokemon.setEstado(Estado.Ninguno);
				pokemon.setTurnosEstado(0);
				System.out.println(pokemon.getNombre() + " ya no está congelado");
			}
			break;

		default:
		}
	}

	private Move strangeMove(ArrayList<Pokemon> equipo, Move movimiento) {
		switch (movimiento.getMove()) {
		case Assist:
			ArrayList<Move> movimientos_equipo = new ArrayList<Move>();
			for (Pokemon poke : equipo) {
				for (Move move : poke.getMovimientos()) {
					movimientos_equipo.add(move);
				}
			}
			System.out.print("Ayuda ha usado ");
			return changeMove(movimientos_equipo.get((int) (Math.random() * movimientos_equipo.size())));

		case Metronome:
			System.out.print("Metronomo ha usado ");
			return changeMove(utils.Almacen.almacenMovimientos
					.get((int) (Math.random() * utils.Almacen.almacenMovimientos.size())));

		default:
			return movimiento;
		}
	}

	private Move changeMove(Move objetivo) {
		System.out.println(objetivo.getNombre());
		return (Move) objetivo.copiarMove();
	}

	private void recoilMove(Pokemon atacante, Move movimiento, int damage) {
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
			atacante.setActualHp(atacante.getActualHp() - ((int) (damage / 4)));
			break;

		// 1/3 del daño realizado
		case Brave_Bird:
		case Flare_Blitz:
		case Volt_Tackle:
		case Wood_Hammer:
			atacante.setActualHp(atacante.getActualHp() - ((int) (damage / 3)));
			break;

		// 1/2 del daño realizado
		case Head_Smash:
		case Light_Ruin:
			atacante.setActualHp(atacante.getActualHp() - ((int) (damage / 2)));
			break;

		// 1/2 de la vida actual
		case Shadow_End:
			atacante.setActualHp(atacante.getActualHp() - ((int) (atacante.getActualHp() / 3)));
			break;

		// 1/16 de la vida maxima
		case Shadow_Rush:
			atacante.setActualHp(atacante.getActualHp() - ((int) (atacante.getMaxHP() / 3)));
			break;

		/*
		 * CURAR
		 */
		// 1/2 del daño realizado
		case Horn_Leech:
			if (atacante.getActualHp() + ((int) (damage / 2)) > atacante.getMaxHP()) {
				System.out.println(
						movimiento.getNombre() + " ha curado " + (atacante.getMaxHP() - atacante.getActualHp()));
				atacante.setActualHp(atacante.getMaxHP());
			} else {
				System.out.println(movimiento.getNombre() + " ha curado " + (int) (damage / 2));
				atacante.setActualHp(atacante.getActualHp() + ((int) (damage / 2)));
			}
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
		System.out.println();
	}

	private void setPkmnMoves(Pokemon pokemon) {
		for (int i = 0; i < 4; i++) {
			pokemon.aprenderMovimiento(utils.Almacen.almacenMovimientos
					.get(((int) (Math.random() * utils.Almacen.almacenMovimientos.size()))).copiarMove());
		}
	}

	private void setPkmnId(ArrayList<Pokemon> pokemons) {
		int i;
		for (i = 0; i < pokemons.size(); i++) {
			pokemons.get(i).setIdPelea(i);
		}
	}
}
