package models;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import models.Move.Clase;
import utils.CondArena;
import utils.CondPosiPkmn;
import utils.Estado;
import utils.Moves;
import utils.Pokemons;
import utils.Tipo;

public class Combate {
	private Entrenador entrenador1, entrenador2;
	private ArrayList<Pokemon> pokemon1, pokemon2, combatientes;
	private Pokemon[] ditto;
	private Pokemon[] change;
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
			this.ditto = new Pokemon[4];
			this.change = new Pokemon[4];
		} else if (tipoCombate == 3) {
			this.moves = new Move[6];
			this.ditto = new Pokemon[6];
			this.change = new Pokemon[6];
		} else {
			this.moves = new Move[2];
			this.ditto = new Pokemon[2];
			this.change = new Pokemon[2];
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
			this.ditto = new Pokemon[4];
			this.change = new Pokemon[4];
		} else if (tipoCombate == 3) {
			this.moves = new Move[6];
			this.ditto = new Pokemon[6];
			this.change = new Pokemon[6];
		} else {
			this.moves = new Move[2];
			this.ditto = new Pokemon[2];
			this.change = new Pokemon[2];
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

	/**
	 * Funcion que configura el combate si se va a realizar en el terminal
	 */
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

			System.out.println("\n\nTurno de " + this.entrenador1.getNombre() + "\nQue quieres hacer?\n");
			choiceBlock(this.entrenador1, this.pokemon1, this.pokemon2);

			System.out.println("\n\nTurno de " + this.entrenador2.getNombre() + "\nQue quieres hacer?\n");
			choiceBlock(this.entrenador2, this.pokemon2, this.pokemon1);

			ListIterator<Pokemon> combate = this.combatientes.listIterator();

			ArrayList<Pokemon> priori = new ArrayList<Pokemon>();

			while (combate.hasNext() && this.moves != null) {
				int prioridad = Integer.MIN_VALUE;

				for (Move move : this.moves) {
					if (move != null)
						if (move.getPrioridad() > prioridad) {
							prioridad = move.getPrioridad();
						}
				}

				for (Pokemon poke : this.combatientes) {
					if (this.moves[poke.getIdPelea()] != null) {
						priori.add(poke);
					}
				}

				atacante = quienEmpiezaTurno(priori);

				if (atacante.getEntrenador() == this.entrenador1) {
					if (atacante.hasCond(CondPosiPkmn.Cargando)) {
						System.out.println(atacante.getNombre() + " esta cargando "
								+ this.moves[atacante.getIdPelea()].getNombre());
						aplicarMovimiento(this.entrenador1.getEquipo(), null, atacante,
								this.moves[atacante.getIdPelea()], this.pokemon2.get(atacante.getAtaca()));
					} else {
						if (this.moves[atacante.getIdPelea()].getMove() == Moves.Change) {
							System.out.println(this.entrenador1.getNombre() + " cambia a "
									+ this.pokemon1.get(atacante.getIdPelea()).getNombre() + " por "
									+ this.change[atacante.getIdPelea()].getNombre());
							aplicarMovimiento(this.entrenador1.getEquipo(), this.pokemon1, atacante,
									this.moves[atacante.getIdPelea()], this.pokemon2.get(0));
						} else {
							System.out.println(atacante.getNombre() + " ha usado "
									+ this.moves[atacante.getIdPelea()].getNombre());
							aplicarMovimiento(this.entrenador1.getEquipo(), null, atacante,
									this.moves[atacante.getIdPelea()], this.pokemon2.get(atacante.getAtaca()));
						}
					}
				} else {
					if (atacante.hasCond(CondPosiPkmn.Cargando)) {
						System.out.println(atacante.getNombre() + " esta cargando "
								+ this.moves[atacante.getIdPelea()].getNombre());
						aplicarMovimiento(this.entrenador2.getEquipo(), null, atacante,
								this.moves[atacante.getIdPelea()], this.pokemon1.get(atacante.getAtaca()));
					} else {
						if (this.moves[atacante.getIdPelea()].getMove() == Moves.Change) {
							System.out.println(this.entrenador2.getNombre() + " cambia a "
									+ this.pokemon2.get(atacante.getPosicion()).getNombre() + " por "
									+ this.change[atacante.getIdPelea()].getNombre());
							aplicarMovimiento(this.entrenador2.getEquipo(), this.pokemon2, atacante,
									this.moves[atacante.getIdPelea()], this.pokemon2.get(0));
						} else {
							System.out.println(atacante.getNombre() + " ha usado "
									+ this.moves[atacante.getIdPelea()].getNombre());
							aplicarMovimiento(this.entrenador2.getEquipo(), null, atacante,
									this.moves[atacante.getIdPelea()], this.pokemon1.get(atacante.getAtaca()));
						}
					}
				}
				combatientes.remove(atacante);
				priori.clear();
			}
		}

		System.out.println(this.entrenador1.getEquipo().get(0));
		System.out.println(this.entrenador2.getEquipo().get(0));

//		for (Pokemon poke : combatientes) {
//			System.out.println(poke);
//		}
	}

	/**
	 * Funcion para las diferentes elecciones que se pueden tener en el combate
	 * 
	 * @param entrenador Objeto 'Entrenador' que representa el entrenador
	 * @param atacantes  ArrayList<Pokemon> que representa los pokemon del
	 *                   entrenador que estan combatiendo
	 * @param rivales    ArrayList<Pokemon> que representa los pokemon del
	 *                   entrenador rival que estan combatiendo
	 */
	private void choiceBlock(Entrenador entrenador, ArrayList<Pokemon> atacantes, ArrayList<Pokemon> rivales) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean choice = false, notNum = true;

		for (Pokemon pokemon : atacantes) {
			if (pokemon.getActualHp() <= 0) {
				System.out.println(pokemon.getNombre() + " no puede continuar la batalla.");
				choosePkmn(entrenador.getEquipo(), atacantes, pokemon.getIdPelea());
				pokemon.setEstado(Estado.Ninguno);
				pokemon.setTurnosEstado(0);
				pokemon.clearPkmnCond();
				pokemon.clearDurPkmnCond();
				atacantes.remove(pokemon);
				atacantes.add(this.change[pokemon.getIdPelea()]);
				this.change[pokemon.getIdPelea()] = null;
				this.moves[pokemon.getIdPelea()] = null;
			}

			if (!pokemon.hasCond(CondPosiPkmn.Cargando)) {
				do {
					while (notNum) {
						try {
							System.out.println("Estado " + pokemon);
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
								choosePkmn(entrenador.getEquipo(), atacantes, pokemon.getIdPelea());
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
		}
	}

	/**
	 * Funcion que permite seleccionar el ataque de cada pokemon
	 * 
	 * @param atacantes ArrayList<Pokemon> de los pokemon de los que se estan
	 *                  seleccionando los movimientos
	 * @param rivales   ArrayList<Pokemon> de los pokemon rivales en la arena para
	 *                  seleccionar a quien atacar
	 */
	private void selAtack(ArrayList<Pokemon> atacantes, ArrayList<Pokemon> rivales) {
		for (Pokemon pkmn : atacantes) {
			System.out.print("\n\n");
			this.moves[pkmn.getIdPelea()] = pkmn.elegirMovimiento();
			if (this.combatientes.size() == 2) {
				pkmn.setAtaca(0);
			}
			// WIP expansion para combates 2vs2, 3vs3
		}
	}

	/**
	 * Funcion que nos permite cambiar nuestro pokemon por otro del equipo
	 * 
	 * @param equipo     ArrayList<Pokemon> que representa el equipo del entrenador
	 * @param atacantes  ArrayList<Pokemon> que representa los pokemons en la arena
	 *                   del entrenador
	 * @param posicicion ID del pokemon que vamos a cambiar
	 */
	private void choosePkmn(ArrayList<Pokemon> equipo, ArrayList<Pokemon> atacantes, int posicion) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean notNum = true, choice = false;
		int opc;

		do {
			while (notNum) {
				try {
					System.out.println("\nA quien quieres sacar?\n");
					for (int i = 0; i < equipo.size(); i++) {
						if (i % 2 == 0)
							System.out.println();

						if (equipo.get(i).getIdPelea() == posicion)
							if (equipo.get(i).getActualHp() <= 0)
								System.out.print(
										(i + 1) + "." + equipo.get(i).getNombre() + " (En uso) No puede combatir  \t");
							else
								System.out.print((i + 1) + "." + equipo.get(i).getNombre() + " (En uso)  \t");
						else if (equipo.get(i).getActualHp() <= 0)
							System.out.print((i + 1) + "." + equipo.get(i).getNombre() + " No puede combatir  \t");
						else
							System.out.print((i + 1) + "." + equipo.get(i).getNombre() + "           \t");
					}
					System.out.print("\n\n->: ");

					opc = Integer.parseInt(sc.nextLine());

					if (opc < 1 && opc > equipo.size()) {
						choice = false;
						System.out.println("\n\nElige un numero correcto\n");
					} else if (equipo.get(opc-1).getActualHp() <= 0) {
						choice = false;
						System.out.print(equipo.get(opc).getNombre() + " no puede combatir  \t");
					} else {
						choice = true;
						notNum = false;

						this.change[posicion] = equipo.get(opc - 1);
						this.change[posicion].setIdPelea(posicion);
						for (Pokemon poke : atacantes)
							if (poke.getIdPelea() == posicion)
								this.change[posicion].setPosicion(poke.getPosicion());

						this.moves[posicion] = utils.Almacen.almacenMovimientos.get(1);
					}
				} catch (NumberFormatException e) {
					System.out.println("\n\nIntroduce un numero\n");
				}
			}
		} while (!choice);

	}

	/**
	 * Devuelve el pokemon mas rapido del grupo
	 * 
	 * @param pokemons ArrayList<Pokemon> de los atacantes con la misma velocidad
	 * @return Objeto 'Pokemon' con mayor velocidad
	 */
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

	/**
	 * Funcion en la cual se aplica el movimiento si cumple con las condiciones
	 * adecuadas
	 * 
	 * @param equipo     ArrayList<Pokemon> que representa el equipo del entrenador
	 *                   que esta atacando
	 * @param atacante   Objeto 'Pokemon' que representa al pokemon atacando
	 * @param movimiento Objeto 'Move' que representa el movimiento que se aplica
	 * @param rival      Objetp 'Pokemon' que representa al pokemon al que se ataca
	 */
	private void aplicarMovimiento(ArrayList<Pokemon> equipo, ArrayList<Pokemon> pkmnArena, Pokemon atacante,
			Move movimiento, Pokemon rival) {
		if (atacante.getActualHp() > 0) {
			if (movimiento.getActPP() > 0) {
				removePkmnStatus(atacante);
				switch (atacante.getEstado()) {
				case Paralisis: {
					if ((int) (Math.random() * 101) > 25) {
						doMove(equipo, atacante, movimiento, rival);
					} else {
						System.out.println(atacante.getNombre() + " esta paralizado");
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
							System.out.println(atacante.getNombre() + " esta cargando el ataque");
							movimiento.setTurnosCargados(movimiento.getTurnosCargados() + 1);
						} else if (atacante.hasCond(CondPosiPkmn.Cargando)) {
							movimiento.setTurnosCargados(1);
							doMove(equipo, atacante, movimiento, rival);
						}
					} else
						doMove(equipo, atacante, movimiento, rival);

					movimiento.setActPP(movimiento.getActPP() - 1);
				}
				}

			} else {
				if (movimiento.getMove() == Moves.Change) {
					pkmnArena.add(this.change[atacante.getIdPelea()]);

					if (atacante.getPokemon() == Pokemons.Ditto) {
						atacante.dittoRecuperar(this.ditto[atacante.getIdPelea()]);
						this.change[atacante.getIdPelea()] = null;
					}
					if (this.change[atacante.getIdPelea()] != null)
						if (this.change[atacante.getIdPelea()].getPokemon() == Pokemons.Ditto) {
							this.ditto[atacante.getIdPelea()] = this.change[atacante.getIdPelea()].dittoGuardar();
							this.change[atacante.getIdPelea()].dittoCopia(atacante);
						}

					this.moves[atacante.getIdPelea()] = null;
					atacante.setIdPelea(-1);
					atacante.setPosicion(-1);

					pkmnArena.remove(atacante);
				} else {
					int damage = calcDamage(atacante, rival, utils.Almacen.almacenMovimientos.get(0));

					rival.setActualHp(rival.getActualHp() - damage);

					atacante.setActualHp(atacante.getActualHp() - ((int) (atacante.getMaxHP() / 4)));
				}
			}
			applyPkmnStatus(atacante, rival);
		}
	}

	/**
	 * Funcion en la que se comprueba la precision del movimiento y se realiza
	 * 
	 * @param equipo     ArrayList<Pokemon> que representa el equipo del entrenador
	 *                   que esta atacando
	 * @param atacante   Objeto 'Pokemon' que representa al pokemon atacando
	 * @param movimiento Objeto 'Move' que representa el movimiento que se aplica
	 * @param rival      Objetp 'Pokemon' que representa al pokemon al que se ataca
	 */
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

	/**
	 * Funcion en el que se realizan todos los cambios necesarios con respecto al
	 * ataque utilizado
	 * 
	 * @param equipo     ArrayList<Pokemon> que representa el equipo del entrenador
	 *                   que esta atacando
	 * @param atacante   Objeto 'Pokemon' que representa al pokemon atacando
	 * @param movimiento Objeto 'Move' que representa el movimiento que se aplica
	 * @param rival      Objetp 'Pokemon' que representa al pokemon al que se ataca
	 */
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

	/**
	 * Funcion en la que se calcula el damage del movimiento
	 * 
	 * @param atacante   Objeto 'Pokemon' que representa al pokemon atacando
	 * @param movimiento Objeto 'Move' que representa el movimiento que se aplica
	 * @param rival      Objetp 'Pokemon' que representa al pokemon al que se ataca
	 * 
	 * @return Entero que representa cuanta vida quitamos al pokemon rival
	 */
	private int calcDamage(Pokemon atacante, Pokemon rival, Move movimiento) {
		switch (movimiento.getMove()) {
		case Endeavor:
			if (atacante.getActualHp() == atacante.getMaxHP())
				return 0;
			else
				return (int) (rival.getMaxHP() - atacante.getActualHp());

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

	/**
	 * Funcion que calcula cuanta salud vamos a quitar dependiendo del tipo de
	 * movimiento
	 * 
	 * @param atacante    Objeto 'Pokemon' que representa al pokemon atacando
	 * @param rival       Objetp 'Pokemon' que representa al pokemon al que se ataca
	 * @param movimiento  Objeto 'Move' que representa el movimiento que se aplica
	 * @param modTiempo   Doble que representa el modificiador con respecto al
	 *                    tiempo que hace en la arena
	 * @param crit        Entero que representa el modificiador de damage critico (en
	 *                    pokemon como tal es distinto)
	 * @param random      Doble que representa el modificiador aleatorio
	 * @param stab        Doble que representa el modificiador de S.T.A.B.
	 * @param efectividad Doble que representa el modificiador de la efectividad del
	 *                    ataque
	 * @param quemadura   Doble que representa el modificiador de si el pokemon
	 *                    esta quemado
	 * @param otro        Doble que representa un modificador extra usado en raras
	 *                    ocasiones
	 * 
	 * @return Entero que representa la salud que quitamos
	 */
	private int attackDamage(Pokemon atacante, Pokemon rival, Move movimiento, double modTiempo, int crit,
			double random, double stab, double efectividad, double quemadura, double otro) {
		if (movimiento.getClase() == Clase.Fisico) {
			return (int) (((((((2 * atacante.getLevel()) / 5) + 2) * movimiento.getDamage()
					* ((double) atacante.getAttack() / (double) rival.getDefence())) / 50) + 2) * 1 * modTiempo * crit
					* random * stab * efectividad * quemadura * otro);
		} else if (movimiento.getClase() == Clase.Especial) {
			return (int) ((((((((2 * atacante.getLevel()) / 5) + 2) * movimiento.getDamage())
					* ((double) atacante.getSpAttack() / (double) rival.getSpDefence())) / 50) + 2) * 1 * modTiempo
					* crit * random * stab * efectividad * quemadura * otro);
		}
		return 0;
	}

	/**
	 * Funcion que aplica el damage al pokemon rival
	 * 
	 * @param atacante Objeto 'Pokemon' que representa al atacante
	 * @param rival    Objeto 'Pokemon' que representa al rival
	 * @param damage   Entero que representa la salud que quitamos
	 */
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

	/**
	 * Funcion que aplica todos los cambios de estado, asi como el tiempo de la
	 * arena
	 * 
	 * @param atacante   Objeto 'Pokemon' que representa al atacante
	 * @param rival      Objeto 'Pokemon' que representa al rival
	 * @param movimiento Objeto 'Move' que representa el movimiento utilizado
	 */
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

	/**
	 * Funcion que aplica efectos de estado de movimientos como 'Drenadoras',
	 * 'Anillo acuatico', etc.
	 * 
	 * @param equipo     ArrayList<Pokemon> que representa el equipo del entrenador
	 *                   que esta atacando
	 * @param atacante   Objeto 'Pokemon' que representa al pokemon atacando
	 * @param movimiento Objeto 'Move' que representa el movimiento que se aplica
	 * @param rival      Objetp 'Pokemon' que representa al pokemon al que se ataca
	 */
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

	/**
	 * Funcion que aplica los efectos de antes de ejecutar el golpe, como
	 * 'Proteccion'
	 * 
	 * @param equipo     ArrayList<Pokemon> que representa el equipo del entrenador
	 *                   que esta atacando
	 * @param atacante   Objeto 'Pokemon' que representa al pokemon atacando
	 * @param movimiento Objeto 'Move' que representa el movimiento que se aplica
	 * @param rival      Objetp 'Pokemon' que representa al pokemon al que se ataca
	 */
//	private void applyCondPosiPkmnBeforeCalc(ArrayList<Pokemon> equipo, Pokemon atacante, Move movimiento,
//			Pokemon rival) {
//	}

	/**
	 * Funcion que calcula y aplica los efectos antes de ejecutar el golpe
	 * dependiendo del tiempo de la arena
	 * 
	 * @param movimiento Objeto 'Move' que representa el movimiento que se aplica
	 */
	private void condArenaBeforeCalc(Move movimiento) {
		switch (this.condArena) {
		// Falta, y mucho
		case Soleado:
			// Precisiones y esas movidas

		default:
		}
	}

	/**
	 * Funcion que calcula y aplica los efectos despues de ejecutar el golpe
	 * dependiendo del tiempo de la arena
	 * 
	 * @param movimiento Objeto 'Move' que representa el movimiento que se aplica
	 */
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

	/**
	 * Funcion que comprueba si el movimiento es cargado y si la condicion de la
	 * arena es la correcta, para que el movimiento se ejecute en un solo turno
	 * 
	 * @param movimiento
	 */
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

	/**
	 * Funcion que aplica los estados especificos de cada movimiento
	 * 
	 * @param atacante   Objeto 'Pokemon' que representa al pokemon atacando
	 * @param rival      Objetp 'Pokemon' que representa al pokemon al que se ataca
	 * @param movimiento Objeto 'Move' que representa el movimiento que se aplica
	 */
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

	/**
	 * Aplica el damage o condicion proviniente del estado que tiene aplicado el
	 * pokemon
	 * 
	 * @param atacante Objeto 'Pokemon' que representa al pokemon atacando
	 * @param rival    Objetp 'Pokemon' que representa al pokemon al que se ataca
	 */
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

	/**
	 * Elimina el estado del pokemon si cumple las condiciones adecuadas
	 * 
	 * @param pokemon Objeto 'Pokemon' que representa el pokemon al que le
	 *                comprobamos los estados
	 */
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
				System.out.println(pokemon.getNombre() + " ya no esta paralizado");
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
				System.out.println(pokemon.getNombre() + " ya no esta congelado");
			}
			break;

		default:
		}
	}

	/**
	 * Funcion que aplica los movimientos de estilo 'Metronomo', 'Ayuda', 'Paliza',
	 * etc.
	 * 
	 * @param equipo     ArrayList<Pokemon> que representa el equipo del entrenador
	 *                   del pokemon que esta atacado
	 * @param movimiento Objeto 'Movimiento' que representa el movimiento que se
	 *                   esta usando
	 * 
	 * @return Objeto 'Move' que representa el movimiento que se ha escogido en la
	 *         funcion
	 */
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

	/**
	 * Obtiene el movimiento que se ha conseguido con strangeMove()
	 * 
	 * @param objetivo Objeto 'Move' que representa el movimiento del que vamos a
	 *                 usar
	 * 
	 * @return Objeto 'Move'
	 */
	private Move changeMove(Move objetivo) {
		System.out.println(objetivo.getNombre());
		return (Move) objetivo.copiarMove();
	}

	/**
	 * Funcion que aplica el damage de retroceso o curacion del movimiento
	 * 
	 * @param atacante   Objeto 'Pokemon' que representa al pokemon atacando
	 * @param movimiento Objeto 'Move' que representa el movimiento que se aplica
	 * @param damage     Entero que representa el damage que se ha realizado con el
	 *                   ataque
	 */
	private void recoilMove(Pokemon atacante, Move movimiento, int damage) {
		switch (movimiento.getMove()) {
		/*
		 * DAÑO
		 */
		// 1/4 del damage realizado
		case Double_Edge:
		case Head_Charge:
		case Submission:
		case Take_Down:
		case Wild_Charge:
			atacante.setActualHp(atacante.getActualHp() - ((int) (damage / 4)));
			break;

		// 1/3 del damage realizado
		case Brave_Bird:
		case Flare_Blitz:
		case Volt_Tackle:
		case Wood_Hammer:
			atacante.setActualHp(atacante.getActualHp() - ((int) (damage / 3)));
			break;

		// 1/2 del damage realizado
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
		// 1/2 del damage realizado
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

	/**
	 * Funcion que comprueba que todos los entrenadores tienen pokemons que pueden
	 * combatir en su equipo y en la arena
	 * 
	 * @return Booleano
	 */
	private boolean isFinished() {
		if (!this.entrenador1.puedeCombatir() || !this.entrenador2.puedeCombatir()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Funcion que nos permite configurar los entrenadores del combate
	 */
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

		if (this.pokemon1.get(0).getPokemon() == Pokemons.Ditto) {
			this.ditto[0] = this.pokemon1.get(0).dittoGuardar();
			this.pokemon1.get(0).dittoCopia(this.pokemon2.get(0));
		}

		if (this.pokemon2.get(0).getPokemon() == Pokemons.Ditto) {
			this.ditto[1] = this.pokemon2.get(0).dittoGuardar();
			this.pokemon2.get(0).dittoCopia(this.pokemon1.get(0));
		}
	}

	/**
	 * Funcion que nos permite configurar los pokemons del combate
	 * 
	 * @param entrenador Objeto 'Entrenador' que representa el entrenador que posee
	 *                   los pokemons
	 */
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
		for (int i = 1; i < 6; i++) {
			entrenador.meterPkmnEquipo(utils.Almacen.almacenPokemon
					.get((int) (Math.random() * utils.Almacen.almacenPokemon.size())).copiarPokemon());
			entrenador.getEquipo().get(i).setEntrenador(entrenador);
			setPkmnMoves(entrenador.getEquipo().get(i));
		}
		System.out.println();
	}

	/**
	 * Funcion que asigna movimientos a los pokemon
	 * 
	 * @param pokemon Objeto 'Pokemon' que representa el pokemon al que le asignamos
	 *                los movimientos
	 */
	private void setPkmnMoves(Pokemon pokemon) {
		for (int i = 0; i < 4; i++) {
			int random = (int) (Math.random() * utils.Almacen.almacenMovimientos.size());
			if (random < 2)
				random = 2;
			pokemon.aprenderMovimiento(utils.Almacen.almacenMovimientos.get(random).copiarMove());
		}
	}

	/**
	 * Funcion que asigna una ID al pokemon en el combate
	 * 
	 * @param pokemons ArrayList<Pokemon> que representa a los pokemon en la arena
	 */
	private void setPkmnId(ArrayList<Pokemon> pokemons) {
		int i;
		for (i = 0; i < pokemons.size(); i++) {
			pokemons.get(i).setIdPelea(i);
		}
	}
}
