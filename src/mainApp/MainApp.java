package mainApp;

import java.util.Scanner;

import models.Combate;
import models.Move;
import models.Move.Clase;
import models.Pokemon;
import utils.CondArena;
import utils.CondPosiPkmn;
import utils.Estado;
import utils.Moves;
import utils.Pokemons;
import utils.Tipo;

public class MainApp {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean choice = false, isNum = false;

		System.out.println("Bienvenido a un combate Pokemon. De que tipo ser· el combate?");
		do {
			while (!isNum) {
				System.out.println("\n1.1vs1\n2.2vs2\n3.3vs3");
				System.out.print("\n->: ");
				try {
					switch (Integer.parseInt(sc.nextLine())) {
					case 1:
						choice = true;
						isNum = true;
						System.out.println("\n\nIntroduce un numero\n");
						initiateCombat(1);
						break;

					case 2:
						choice = true;
						isNum = true;
						System.out.println("\n\nJeje solo hay 1vs1 xD\n");
						initiateCombat(1);
						break;

					case 3:
						choice = true;
						isNum = true;
						System.out.println("\n\nJeje solo hay 1vs1 xD\n");
						initiateCombat(1);
						break;

					default:
						System.out.println("\n\nIntroduce una opciÛn correcta\n");
						choice = false;
					}

				} catch (NumberFormatException e) {
					isNum = false;
					System.out.println("\n\nIntroduce un numero\n");
				}
			}
		} while (!choice);
	}

	private static void initiateCombat(int opc) {
		createPkmnAndMoves();
		Combate comba = new Combate(opc);
		comba.prepararCombateTerminal(opc);
	}

	private static void createPkmnAndMoves() {
		utils.Almacen.almacenMovimientos.add(new Move(Estado.Ninguno, CondPosiPkmn.Ninguno, Tipo.Normal, Clase.Fisico,
				Moves.Struggle, "Combate", "A pelo", 0, 50, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos
				.add(new Move(null, null, null, null, Moves.Change, "", "", 0, 0, 0, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));

		// NORMAL
		utils.Almacen.almacenMovimientos.add(new Move(Estado.Dormido, CondPosiPkmn.Ninguno, Tipo.Normal, Clase.Especial,
				Moves.Relic_Song, "Cancion reliquia?", "Cantas o algo", 10, 75, 100, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos
				.add(new Move(Estado.Retroceder, CondPosiPkmn.Ninguno, Tipo.Normal, Clase.Especial, Moves.Snore,
						"Roncar", "En serio cabron?", 15, 50, 100, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos.add(new Move(Estado.Ninguno, CondPosiPkmn.Ninguno, Tipo.Normal, Clase.Fisico,
				Moves.Scratch, "Aranyazo", "Le aranyas", 35, 40, 100, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos
				.add(new Move(Estado.Ninguno, CondPosiPkmn.Ninguno, Tipo.Normal, Clase.Fisico, Moves.Endeavor,
						"Vengador", "Igualas la vida del rival a la tuya", 5, 0, 100, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos.add(new Move(Estado.Ninguno, CondPosiPkmn.Ninguno, Tipo.Normal, Clase.Estado,
				Moves.Metronome, "Metronomo", "Tic tac hijoputa", 10, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos
				.add(new Move(Estado.Ninguno, CondPosiPkmn.Ninguno, Tipo.Normal, Clase.Fisico, Moves.Super_Fang,
						"Super Colmillo", "El colmillo de Super-Man", 10, 0, 100, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));

		// ELECTRICO
		utils.Almacen.almacenMovimientos
				.add(new Move(Estado.Paralisis, CondPosiPkmn.Ninguno, Tipo.Electrico, Clase.Fisico, Moves.Bolt_Strike,
						"Impactrueno", "Ka-chow", 5, 130, 85, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos
				.add(new Move(Estado.Ninguno, CondPosiPkmn.Ninguno, Tipo.Electrico, Clase.Fisico, Moves.Wild_Charge,
						"Carga salvaje", "Muy carga, mucha salvaje", 15, 90, 100, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos
				.add(new Move(Estado.Paralisis, CondPosiPkmn.Ninguno, Tipo.Electrico, Clase.Especial, Moves.Thunder,
						"Trueno", "Te cae la ira de Zeus", 10, 110, 70, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos.add(new Move(Estado.Paralisis, CondArena.Ninguno, CondPosiPkmn.Ninguno,
				Tipo.Electrico, Clase.Estado, Moves.Thunder_Wave, "Ola truenotastica", "Ondas que hacen cosquillas", 20,
				90, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));

		// PLANTA
		utils.Almacen.almacenMovimientos.add(new Move(Estado.Ninguno, CondPosiPkmn.Ninguno, Tipo.Planta, Clase.Fisico,
				Moves.Bullet_Seed, "Semilla bala", "Me estoy inventando los nombres a este punto", 30, 25, 100, 0, 1, 5,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos.add(new Move(Estado.Ninguno, CondPosiPkmn.Ninguno, Tipo.Planta, Clase.Fisico,
				Moves.Horn_Leech, "Cuerno sanguijuela", "Le metes todo tu cuerno ~", 10, 75, 100, 0, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1));
		utils.Almacen.almacenMovimientos
				.add(new Move(Estado.Confusion, CondPosiPkmn.Ninguno, Tipo.Planta, Clase.Especial, Moves.Petal_Dance,
						"Danza petalo", "Baila ele", 10, 120, 100, 0, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos.add(new Move(Estado.Recarga, CondPosiPkmn.Ninguno, Tipo.Planta, Clase.Especial,
				Moves.Frenzy_Plant, "Planta loca", "Como la de Plantas VS Zombies pero crazy", 5, 90, 90, 0, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos
				.add(new Move(Estado.Ninguno, CondPosiPkmn.Cargando, Tipo.Planta, Clase.Especial, Moves.Solar_Beam,
						"Rayo solar", "El poder del sol en mi boca", 10, 120, 100, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));

		// FUEGO
		utils.Almacen.almacenMovimientos.add(new Move(Estado.Quemado, CondPosiPkmn.Ninguno, Tipo.Fuego, Clase.Especial,
				Moves.Flamethrower, "Lanzallamas", "Arma que supongo que estar√° prohibida en la guerra", 15, 90, 100,
				0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos.add(new Move(Estado.Quemado, CondPosiPkmn.Ninguno, Tipo.Fuego, Clase.Especial,
				Moves.Fire_Fang, "Colmillo fuego", "Como se queda la boca despu√©s de comer Jalapenyos", 15, 65, 95, 0,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos.add(new Move(Estado.Quemado, CondPosiPkmn.Ninguno, Tipo.Fuego, Clase.Fisico,
				Moves.Blaze_Kick, "Patada fuego", "Te quemas el chandal y le golpeas con el", 10, 85, 90, 0, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos
				.add(new Move(Estado.Ninguno, CondArena.Soleado, CondPosiPkmn.Ninguno, Tipo.Fuego, Clase.Estado,
						Moves.Sunny_Day, "Dia soleado", "Ponte gafas de sol", 5, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));

		// AGUA
		utils.Almacen.almacenMovimientos.add(new Move(Estado.Ninguno, CondPosiPkmn.Ninguno, Tipo.Agua, Clase.Fisico,
				Moves.Aqua_Jet, "Aqua jet", "Jetpack que lanza agua", 20, 40, 100, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos.add(new Move(Estado.Ninguno, CondPosiPkmn.Atrapado, Tipo.Agua, Clase.Fisico,
				Moves.Clamp, "Pinza", "La del cangrejo", 15, 35, 85, 0, 4, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos.add(new Move(Estado.Recarga, CondPosiPkmn.Ninguno, Tipo.Agua, Clase.Especial,
				Moves.Hydro_Canon, "Hidro canyon", "Mangera a presion", 5, 150, 90, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		utils.Almacen.almacenMovimientos
				.add(new Move(Estado.Ninguno, CondArena.Ninguno, CondPosiPkmn.Heal_1p16, Tipo.Agua, Clase.Estado,
						Moves.Aqua_Ring, "anillo agua", "anillo", 20, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));

		// Pokemons
		utils.Almacen.almacenPokemon.add(new Pokemon(Tipo.Normal, Tipo.Ninguno, Estado.Ninguno, Pokemons.Eevee, "Eevee",
				78, 50, 130, 75, 70, 65, 85, 75));

		utils.Almacen.almacenPokemon.add(new Pokemon(Tipo.Electrico, Tipo.Ninguno, Estado.Ninguno, Pokemons.Pikachu,
				"Pikachu", 25, 50, 110, 75, 60, 70, 70, 110));

		utils.Almacen.almacenPokemon.add(new Pokemon(Tipo.Planta, Tipo.Veneno, Estado.Ninguno, Pokemons.Bulbasaur,
				"Bulbasaur", 1, 50, 120, 69, 69, 85, 85, 65));

		utils.Almacen.almacenPokemon.add(new Pokemon(Tipo.Fuego, Tipo.Ninguno, Estado.Ninguno, Pokemons.Charmander,
				"Charmander", 4, 50, 114, 72, 63, 80, 70, 85));

		utils.Almacen.almacenPokemon.add(new Pokemon(Tipo.Agua, Tipo.Ninguno, Estado.Ninguno, Pokemons.Squirtle,
				"Squirtle", 7, 50, 119, 68, 85, 70, 84, 63));

		utils.Almacen.almacenPokemon.add(new Pokemon(Tipo.Normal, Tipo.Ninguno, Estado.Ninguno, Pokemons.Ditto, "Ditto",
				132, 50, 123, 68, 68, 68, 68, 68));
	}

}
