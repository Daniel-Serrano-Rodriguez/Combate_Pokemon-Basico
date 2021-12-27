package models;

public class TipoPokemon {
	public enum Tipo {
		Ninguno, Normal, Fuego, Agua, Planta, Electrico, Hielo, Lucha, Veneno, Tierra, Volador, Psiquico, Bicho, Roca,
		Fantasma, Siniestro, Dragon, Acero, Hada
	}

	/**
	 * Funcion que devuelve un multiplicador en funcion de la efectividad del
	 * movimiento
	 * 
	 * @param atacante Tipo del ataque del objeto 'Pokemon' que ataca
	 * @param rival    Tipo del objeto 'Pokemon' rival
	 * @return Doble que representa el multiplicador (0, 0.5, 1, 2)
	 */
	public double getEfectividad(Tipo atacante, Tipo rival) {
		switch (atacante) {
		case Normal: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Fuego:
			case Agua:
			case Electrico:
			case Planta:
			case Hielo:
			case Lucha:
			case Veneno:
			case Tierra:
			case Volador:
			case Psiquico:
			case Bicho:
			case Dragon:
			case Siniestro:
			case Hada:
				return 1;

			// Devuelven 2

			// Devuelven 0.5
			case Roca:
			case Acero:
				return 0.5;

			// Devuelven 0
			case Fantasma:
				return 0;
			}
		}

		case Fuego: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Electrico:
			case Lucha:
			case Veneno:
			case Tierra:
			case Volador:
			case Psiquico:
			case Fantasma:
			case Siniestro:
			case Hada:
				return 1;

			// Devuelven 2
			case Planta:
			case Hielo:
			case Bicho:
			case Acero:
				return 2;

			// Devuelven 0.5
			case Fuego:
			case Agua:
			case Roca:
			case Dragon:
				return 0.5;

			// Devuelven 0

			}
		}

		case Agua: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Electrico:
			case Hielo:
			case Lucha:
			case Veneno:
			case Volador:
			case Psiquico:
			case Bicho:
			case Fantasma:
			case Siniestro:
			case Acero:
			case Hada:
				return 1;

			// Devuelven 2
			case Fuego:
			case Tierra:
			case Roca:
				return 2;

			// Devuelven 0.5
			case Agua:
			case Planta:
			case Dragon:
				return 0.5;

			// Devuelven 0

			}
		}

		case Electrico: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Fuego:
			case Hielo:
			case Lucha:
			case Veneno:
			case Psiquico:
			case Bicho:
			case Roca:
			case Fantasma:
			case Siniestro:
			case Acero:
			case Hada:
				return 1;

			// Devuelven 2
			case Agua:
			case Volador:
				return 2;

			// Devuelven 0.5
			case Electrico:
			case Planta:
			case Dragon:
				return 0.5;

			// Devuelven 0
			case Tierra:
				return 0;

			}
		}

		case Planta: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Electrico:
			case Hielo:
			case Lucha:
			case Psiquico:
			case Fantasma:
			case Siniestro:
			case Hada:
				return 1;

			// Devuelven 2
			case Agua:
			case Tierra:
			case Roca:
				return 2;

			// Devuelven 0.5
			case Fuego:
			case Planta:
			case Veneno:
			case Volador:
			case Bicho:
			case Dragon:
			case Acero:
				return 0.5;

			// Devuelven 0

			}
		}

		case Hielo: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Electrico:
			case Lucha:
			case Veneno:
			case Psiquico:
			case Bicho:
			case Roca:
			case Fantasma:
			case Siniestro:
			case Hada:
				return 1;

			// Devuelven 2
			case Planta:
			case Tierra:
			case Volador:
			case Dragon:

				return 2;

			// Devuelven 0.5
			case Fuego:
			case Agua:
			case Hielo:
			case Acero:
				return 0.5;

			// Devuelven 0

			}
		}

		case Lucha: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Fuego:
			case Agua:
			case Electrico:
			case Planta:
			case Lucha:
			case Tierra:
			case Dragon:
				return 1;

			// Devuelven 2
			case Normal:
			case Hielo:
			case Roca:
			case Siniestro:
			case Acero:
				return 2;

			// Devuelven 0.5
			case Veneno:
			case Volador:
			case Psiquico:
			case Bicho:
			case Hada:
				return 0.5;

			// Devuelven 0
			case Fantasma:
				return 0;
			}
		}

		case Veneno: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Fuego:
			case Agua:
			case Electrico:
			case Hielo:
			case Lucha:
			case Volador:
			case Psiquico:
			case Bicho:
			case Dragon:
			case Siniestro:
				return 1;

			// Devuelven 2
			case Planta:
			case Hada:
				return 2;

			// Devuelven 0.5
			case Veneno:
			case Tierra:
			case Roca:
			case Fantasma:
				return 0.5;

			// Devuelven 0
			case Acero:
				return 0;
			}
		}

		case Tierra: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Agua:
			case Hielo:
			case Lucha:
			case Tierra:
			case Psiquico:
			case Fantasma:
			case Dragon:
			case Siniestro:
			case Hada:
				return 1;

			// Devuelven 2
			case Fuego:
			case Electrico:
			case Veneno:
			case Roca:
			case Acero:
				return 2;

			// Devuelven 0.5
			case Planta:
			case Bicho:
				return 0.5;

			// Devuelven 0
			case Volador:
				return 0;
			}
		}

		case Volador: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Fuego:
			case Agua:
			case Hielo:
			case Veneno:
			case Tierra:
			case Volador:
			case Psiquico:
			case Fantasma:
			case Dragon:
			case Siniestro:
			case Hada:
				return 1;

			// Devuelven 2
			case Planta:
			case Lucha:
			case Bicho:
				return 2;

			// Devuelven 0.5
			case Electrico:
			case Roca:
			case Acero:
				return 0.5;

			// Devuelven 0
			}
		}

		case Psiquico: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Fuego:
			case Agua:
			case Electrico:
			case Planta:
			case Hielo:
			case Tierra:
			case Volador:
			case Bicho:
			case Roca:
			case Fantasma:
			case Dragon:
				return 1;

			// Devuelven 2
			case Lucha:
			case Veneno:
				return 2;

			// Devuelven 0.5
			case Psiquico:
			case Acero:
			case Hada:
				return 0.5;

			// Devuelven 0
			case Siniestro:
				return 0;
			}
		}

		case Bicho: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Agua:
			case Electrico:
			case Hielo:
			case Tierra:
			case Bicho:
			case Roca:
			case Dragon:
				return 1;

			// Devuelven 2
			case Planta:
			case Psiquico:
			case Siniestro:
				return 2;

			// Devuelven 0.5
			case Fuego:
			case Lucha:
			case Veneno:
			case Volador:
			case Fantasma:
			case Acero:
			case Hada:
				return 0.5;

			// Devuelven 0
			}
		}

		case Roca: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Agua:
			case Electrico:
			case Planta:
			case Veneno:
			case Psiquico:
			case Roca:
			case Fantasma:
			case Dragon:
			case Siniestro:
			case Hada:
				return 1;

			// Devuelven 2
			case Fuego:
			case Hielo:
			case Volador:
			case Bicho:
				return 2;

			// Devuelven 0.5
			case Lucha:
			case Tierra:
			case Acero:
				return 0.5;

			// Devuelven 0
			}
		}

		case Fantasma: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Fuego:
			case Agua:
			case Electrico:
			case Planta:
			case Hielo:
			case Lucha:
			case Veneno:
			case Tierra:
			case Volador:
			case Bicho:
			case Roca:
			case Dragon:
			case Acero:
			case Hada:
				return 1;

			// Devuelven 2
			case Psiquico:
			case Fantasma:
				return 2;

			// Devuelven 0.5
			case Siniestro:
				return 0.5;

			// Devuelven 0
			case Normal:
				return 0;
			}
		}

		case Dragon: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Fuego:
			case Agua:
			case Electrico:
			case Planta:
			case Hielo:
			case Lucha:
			case Veneno:
			case Tierra:
			case Volador:
			case Psiquico:
			case Bicho:
			case Roca:
			case Fantasma:
			case Siniestro:
				return 1;

			// Devuelven 2
			case Dragon:
				return 2;

			// Devuelven 0.5
			case Acero:
				return 0.5;

			// Devuelven 0
			case Hada:
				return 0;
			}
		}

		case Siniestro: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Fuego:
			case Agua:
			case Electrico:
			case Planta:
			case Hielo:
			case Veneno:
			case Tierra:
			case Volador:
			case Bicho:
			case Roca:
			case Dragon:
			case Acero:
				return 1;

			// Devuelven 2
			case Psiquico:
			case Fantasma:
				return 2;

			// Devuelven 0.5
			case Lucha:
			case Siniestro:
			case Hada:
				return 0.5;

			// Devuelven 0
			}
		}

		case Acero: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Planta:
			case Lucha:
			case Veneno:
			case Tierra:
			case Volador:
			case Psiquico:
			case Bicho:
			case Fantasma:
			case Dragon:
			case Siniestro:
				return 1;

			// Devuelven 2
			case Hielo:
			case Roca:
			case Hada:
				return 2;

			// Devuelven 0.5
			case Fuego:
			case Agua:
			case Electrico:
			case Acero:
				return 0.5;

			// Devuelven 0
			}
		}

		case Hada: {
			switch (rival) {
			// Devuelven 1
			case Ninguno:
			case Normal:
			case Agua:
			case Electrico:
			case Planta:
			case Hielo:
			case Tierra:
			case Volador:
			case Psiquico:
			case Bicho:
			case Roca:
			case Fantasma:
			case Hada:
				return 1;

			// Devuelven 2
			case Lucha:
			case Dragon:
			case Siniestro:
				return 2;

			// Devuelven 0.5
			case Fuego:
			case Veneno:
			case Acero:
				return 0.5;

			// Devuelven 0
			}
		}

		case Ninguno:
			return 1;

		default:
			return 1;
		}
	}
}
