package noventagrados.control;

import noventagrados.modelo.Tablero;
import noventagrados.modelo.Pieza;
import noventagrados.util.Color;
import noventagrados.util.Coordenada;
import noventagrados.modelo.Celda;
import noventagrados.modelo.Jugada;
import noventagrados.util.TipoPieza;


/**
 * Clase que gestiona las reglas del juego y supervisa el estado de la partida.
 * 
 * @author Miguel Cuevas Ruiz
 * @version 4.0
 */
public class Arbitro {
	private final Tablero tablero;
	private Color turnoActual;
	private final Caja cajaPiezasBlancas;
	private final Caja cajaPiezasNegras;
	private int contadorJugadas;

	/**
     * Constructor que inicializa el árbitro con un tablero.
     *
     * @param tablero el tablero de juego.
     */	
	public Arbitro(Tablero tablero) {
		this.tablero = tablero;
		this.cajaPiezasBlancas = new Caja(Color.BLANCO);
		this.cajaPiezasNegras = new Caja(Color.NEGRO);
		this.turnoActual = null;
		this.contadorJugadas = 0;

	}
	
	/**
     * Cambia el turno al siguiente jugador.
     */
	public void cambiarTurno() {
		turnoActual = (turnoActual == Color.BLANCO) ? Color.NEGRO : Color.BLANCO;
	}
	
	/**
     * Coloca las piezas en el tablero según las coordenadas proporcionadas.
     *
     * @param piezas       las piezas a colocar.
     * @param coordenadas  las coordenadas donde colocar las piezas.
     * @param turnoActual  el turno actual.
     */
	public void colocarPiezas(Pieza[] piezas, Coordenada[] coordenadas, Color turnoActual) {
		for (int i = 0; i < piezas.length; i++) {
			tablero.colocar(piezas[i], coordenadas[i]);
		}
		this.turnoActual = turnoActual;
	}
	
	/**
     * Configura las piezas en la posición inicial
     */
	public void colocarPiezasConfiguracionInicial() {
		Pieza reinaBlanca = new Pieza(TipoPieza.REINA, Color.BLANCO);
		Pieza peonBlanco = new Pieza(TipoPieza.PEON, Color.BLANCO);
		Pieza reinaNegra = new Pieza(TipoPieza.REINA, Color.NEGRO);
		Pieza peonNegro = new Pieza(TipoPieza.PEON, Color.NEGRO);

		tablero.colocar(reinaBlanca, new Coordenada(0, 0));
		tablero.colocar(peonBlanco, new Coordenada(0, 1));
		tablero.colocar(peonBlanco, new Coordenada(0, 2));
		tablero.colocar(peonBlanco, new Coordenada(0, 3));
		tablero.colocar(peonBlanco, new Coordenada(1, 0));
		tablero.colocar(peonBlanco, new Coordenada(2, 0));
		tablero.colocar(peonBlanco, new Coordenada(3, 0));

		tablero.colocar(reinaNegra, new Coordenada(6, 6));
		tablero.colocar(peonNegro, new Coordenada(3, 6));
		tablero.colocar(peonNegro, new Coordenada(4, 6));
		tablero.colocar(peonNegro, new Coordenada(5, 6));
		tablero.colocar(peonNegro, new Coordenada(6, 3));
		tablero.colocar(peonNegro, new Coordenada(6, 4));
		tablero.colocar(peonNegro, new Coordenada(6, 5));

		turnoActual = Color.BLANCO;
	}
	
	/**
     * Consulta la caja de piezas capturadas del color especificado.
     *
     * @param color el color de la caja a consultar.
     * @return la caja correspondiente al color.
     */
	public Caja consultarCaja(Color color) {
		return (color == Color.BLANCO) ? cajaPiezasBlancas : cajaPiezasNegras;
	}
	
	/**
     * Devuelve el número de jugadas realizadas hasta el momento.
     *
     * @return el contador de jugadas.
     */
	public int consultarNumeroJugada() {

		return contadorJugadas;
	}
	
	/**
     * Devuelve una copia del estado actual del tablero.
     *
     * @return el tablero clonado.
     */
	public Tablero consultarTablero() {
		return tablero.clonar();
	}
	
	/**
     * Devuelve el color del jugador que tiene el turno actual.
     *
     * @return el color del turno actual.
     */
	public Color consultarTurno() {
		return turnoActual;
	}

	public Color consultarTurnoGanador() {
	    TableroConsultor consultor = new TableroConsultor(tablero);
	    Color ganador = null;

	    if (consultor.buscarCeldaReina(Color.BLANCO) == null && consultor.buscarCeldaReina(Color.NEGRO) != null) {
	        ganador = Color.NEGRO;
	    } else if (consultor.buscarCeldaReina(Color.NEGRO) == null && consultor.buscarCeldaReina(Color.BLANCO) != null) {
	        ganador = Color.BLANCO;
	    } else if (consultor.estaReinaEnElCentro(Color.BLANCO)) {
	        ganador = Color.BLANCO;
	    } else if (consultor.estaReinaEnElCentro(Color.NEGRO)) {
	        ganador = Color.NEGRO;
	    }

	    return ganador;
	}

	/**
     * Ejecuta una jugada empujando las piezas según las reglas.
     *
     * @param jugada la jugada a ejecutar.
     */
	public void empujar(Jugada jugada) {
	    Coordenada origen = jugada.origen().consultarCoordenada();
	    Coordenada destino = jugada.destino().consultarCoordenada();
	    Pieza piezaAMover = tablero.consultarCelda(origen).consultarPieza();
	    if (piezaAMover != null) {
	        // Crea una instancia de TableroConsultor para el tablero actual
	        TableroConsultor consultor = new TableroConsultor(tablero);

	        int distanciaHorizontal = consultor.consultarDistanciaEnHorizontal(origen, destino);
	        int distanciaVertical = consultor.consultarDistanciaEnVertical(origen, destino);

	        if (distanciaHorizontal == -1 && distanciaVertical > 0) {
	            movimientoVertical(origen, destino);
	            contadorJugadas++;
	        } else if (distanciaVertical == -1 && distanciaHorizontal > 0) {
	            movimientoHorizontal(origen, destino);
	            contadorJugadas++;
	        }
	    }
	}

	
	/**
	 * Mueve las piezas en la dirección vertical desde el origen hasta el destino, empujando otras piezas si es necesario.
	 *
	 * @param origen la coordenada de origen.
	 * @param destino la coordenada de destino.
	 */
	private void movimientoVertical(Coordenada origen, Coordenada destino) {
	    int filaOrigen = origen.fila();
	    int filaDestino = destino.fila();
	    int columna = origen.columna();
	    int direccion = (filaDestino > filaOrigen) ? 1 : -1; 
	    int distancia = Math.abs(filaDestino - filaOrigen);

	    int casillasVaciasEntre = 0;
	    boolean sinPiezaEncontrada = true;

	    for (int i = filaOrigen + direccion; i != filaDestino && sinPiezaEncontrada; i += direccion) {
	        Coordenada posicionActual = new Coordenada(i, columna);
	        if (tablero.consultarCelda(posicionActual).estaVacia()) {
	            casillasVaciasEntre++;
	        } else {
	            sinPiezaEncontrada = false;
	        }
	    }
	
		int distanciaEmpuje = distancia - casillasVaciasEntre;

		for (int i = filaOrigen + direccion; i != filaDestino + direccion; i += direccion) {
			Coordenada posicionActual = new Coordenada(i, columna);
			Coordenada nuevaPosicion = new Coordenada(i + distanciaEmpuje * direccion, columna);
			Pieza pieza = tablero.consultarCelda(posicionActual).consultarPieza();
			
			if (pieza != null) { //Si hay una pieza en posicionActual la elimina
				tablero.eliminarPieza(posicionActual);

				if (tablero.estaEnTablero(nuevaPosicion)) { //Verifica si la nueva posición está dentro del tablero
					tablero.colocar(pieza, nuevaPosicion); //Si la posición está dentro del tablero la colocamos
				} else { //Si nuevaPosicion está fuera del tablero la almacena en su caja
					
					Caja caja = (pieza.consultarColor() == Color.BLANCO) ? cajaPiezasBlancas : cajaPiezasNegras;
					caja.añadir(pieza);
					
					if (pieza.consultarTipoPieza() == TipoPieza.REINA) {
						System.out.println("¡Victoria para el equipo " + (pieza.consultarColor() == Color.BLANCO ? "Negro" : "Blanco") + "!");
						return; 
					}
				}
			}
		}

		Pieza piezaAMover = tablero.consultarCelda(origen).consultarPieza(); //Guardar la pieza que será movida
		tablero.eliminarPieza(origen); //Asegurar que la celda de origen quede vacía después de mover la pieza
		tablero.colocar(piezaAMover, destino); //Colocar la pieza en la celda de destino
	}
	
	/**
	 * Mueve las piezas en la dirección horizontal desde el origen hasta el destino, empujando otras piezas si es necesario.
	 *
	 * @param origen la coordenada de origen.
	 * @param destino la coordenada de destino.
	 */
	private void movimientoHorizontal(Coordenada origen, Coordenada destino) {
		int columnaOrigen = origen.columna();
		int columnaDestino = destino.columna();
		int fila = origen.fila();
		int direccion = (columnaDestino > columnaOrigen) ? 1 : -1;
		int distancia = Math.abs(columnaDestino - columnaOrigen);
		int casillasVaciasEntre = 0;
		boolean sinPiezaEncontrada = true;

	    for (int i = columnaOrigen + direccion; i != columnaDestino && sinPiezaEncontrada; i += direccion) {
	        Coordenada posicionActual = new Coordenada(fila, i);
	        if (tablero.consultarCelda(posicionActual).estaVacia()) {
	            casillasVaciasEntre++;
	        } else {
	            sinPiezaEncontrada = false; // Cambia la condición para detener el bucle
	        }
		}

		int distanciaEmpuje = distancia - casillasVaciasEntre;
		for (int i = columnaOrigen + direccion; i != columnaDestino + direccion; i += direccion) {
			Coordenada posicionActual = new Coordenada(fila, i);
			Coordenada nuevaPosicion = new Coordenada(fila, i + distanciaEmpuje * direccion);
			Pieza pieza = tablero.consultarCelda(posicionActual).consultarPieza();
			
			if (pieza != null) {
				tablero.eliminarPieza(posicionActual);

				if (tablero.estaEnTablero(nuevaPosicion)) {
					tablero.colocar(pieza, nuevaPosicion);
				} else {
					// Si la pieza sale del tablero, añadirla a la caja y verificar si es una reina
					Caja caja = (pieza.consultarColor() == Color.BLANCO) ? cajaPiezasBlancas : cajaPiezasNegras;
					caja.añadir(pieza);
					
					if (pieza.consultarTipoPieza() == TipoPieza.REINA) {
						System.out.println("¡Victoria para el equipo " + (pieza.consultarColor() == Color.BLANCO ? "Negro" : "Blanco") + "!");
						return; // Termina el juego si una reina es expulsada
					}
				}
			}
		}

		Pieza piezaAMover = tablero.consultarCelda(origen).consultarPieza();
		tablero.eliminarPieza(origen);
		tablero.colocar(piezaAMover, destino);
	}

	/**
	 * Verifica si una jugada es legal según las reglas del juego.
	 *
	 * @param jugada la jugada a verificar.
	 * @return true si la jugada es legal, false en caso contrario.
	 */
	public boolean esMovimientoLegal(Jugada jugada) {
	    boolean movimientoLegal = true;

	    if (estaFinalizadaPartida()) {
	        movimientoLegal = false;
	    } else {
	        Coordenada origen = jugada.origen().consultarCoordenada();
	        Coordenada destino = jugada.destino().consultarCoordenada();

	        // Verificar si el origen o el destino están fuera del tablero
	        if (!tablero.estaEnTablero(origen) || !tablero.estaEnTablero(destino)) {
	            System.out.println("Movimiento ilegal: coordenada fuera del tablero.");
	            movimientoLegal = false;
	        } else {
	            Pieza pieza = tablero.consultarCelda(origen).consultarPieza();
	            if (pieza == null || pieza.consultarColor() != turnoActual) {
	                movimientoLegal = false;
	            } else {
	                int desplazamientoPermitido = calcularDesplazamientoPermitido(origen, destino);
	                if (desplazamientoPermitido == 0 || desplazamientoPermitido != calcularDistancia(origen, destino)) {
	                    movimientoLegal = false;
	                } else if (verificarRetrocesoInmediato(jugada)) {
	                    movimientoLegal = false;
	                }
	            }
	        }
	    }

	    return movimientoLegal;
	}

	/**
	 * Calcula la distancia en línea recta entre dos coordenadas.
	 * Si las coordenadas no están alineadas horizontal o verticalmente, devuelve -1.
	 *
	 * @param origen la coordenada de origen.
	 * @param destino la coordenada de destino.
	 * @return la distancia en línea recta o -1 si no es válida.
	 */
	private int calcularDistancia(Coordenada origen, Coordenada destino) {
	    int distancia;
	    TableroConsultor consultor = new TableroConsultor(tablero);
	    
	    if (origen.fila() == destino.fila()) {
	        
	        distancia = consultor.consultarDistanciaEnHorizontal(origen, destino);
	    } else if (origen.columna() == destino.columna()) {
	        
	        distancia = consultor.consultarDistanciaEnVertical(origen, destino);
	        
	    } else {
	    	
	        // No es un movimiento en línea recta (horizontal o vertical), devuelve -1
	        distancia = -1;
	    }

	    return distancia;
	}

	private Jugada ultimaJugada; // Jugada previa del oponente

	/**
	 * Verifica si un movimiento intenta retroceder inmediatamente la última jugada.
	 *
	 * @param jugadaActual la jugada que el jugador intenta realizar.
	 * @return true si la jugada intenta un retroceso inmediato, false en caso contrario.
	 */
	private boolean verificarRetrocesoInmediato(Jugada jugadaActual) {
		if (ultimaJugada == null) {
			// No hay una jugada previa registrada, por lo que no se puede verificar
			// retroceso
			return false;
		}

		Coordenada origenActual = jugadaActual.origen().consultarCoordenada();
		Coordenada destinoActual = jugadaActual.destino().consultarCoordenada();

		Coordenada origenUltima = ultimaJugada.origen().consultarCoordenada();
		Coordenada destinoUltima = ultimaJugada.destino().consultarCoordenada();

		// Verificar si el destino de la jugada actual coincide con el origen de la
		// última jugada
		// y si el origen de la jugada actual coincide con el destino de la última
		// jugada.
		return origenActual.equals(destinoUltima) && destinoActual.equals(origenUltima);
	}
	
	/**
	 * Calcula el desplazamiento permitido para un movimiento entre dos coordenadas.
	 *
	 * @param origen la coordenada de origen.
	 * @param destino la coordenada de destino.
	 * @return el desplazamiento permitido o 0 si no es válido.
	 */
	private int calcularDesplazamientoPermitido(Coordenada origen, Coordenada destino) {
		// Verifica si el movimiento es horizontal o vertical
		if (origen.fila() == destino.fila()) {
			// Movimiento horizontal: cuenta piezas en la columna de la fila de origen
			return contarPiezasEnColumna(origen.columna());
		} else if (origen.columna() == destino.columna()) {
			// Movimiento vertical: cuenta piezas en la fila de la columna de origen
			return contarPiezasEnFila(origen.fila());
		}
		// Si no es un movimiento horizontal o vertical, devuelve 0 (no permitido)
		return 0;
	}
	
	/**
     * Determina si la partida ha finalizado según las condiciones actuales.
     *
     * @return true si la partida está finalizada, false en caso contrario.
     */
	public boolean estaFinalizadaPartida() {
		
		TableroConsultor consultor = new TableroConsultor(tablero);
		Celda celdaReinaBlanca = consultor.buscarCeldaReina(Color.BLANCO);
	    Celda celdaReinaNegra = consultor.buscarCeldaReina(Color.NEGRO);
		Coordenada centro = new Coordenada(3, 3);

		// Verificar si alguna de las reinas está en el centro del tablero
		boolean reinaBlancaEnCentro = celdaReinaBlanca != null && celdaReinaBlanca.consultarCoordenada().equals(centro);
		boolean reinaNegraEnCentro = celdaReinaNegra != null && celdaReinaNegra.consultarCoordenada().equals(centro);

		// Verificar si alguna de las reinas ha sido expulsada del tablero (es decir, no está en ninguna celda)
		boolean reinaBlancaExpulsada = (celdaReinaBlanca == null);
		boolean reinaNegraExpulsada = (celdaReinaNegra == null);

		// La partida está finalizada si alguna reina está en el centro o si alguna ha sido expulsada
		return reinaBlancaEnCentro || reinaNegraEnCentro || reinaBlancaExpulsada || reinaNegraExpulsada;
	}
	
	/**
	 * Cuenta el número de piezas presentes en una columna específica del tablero.
	 *
	 * @param columna la columna del tablero que se desea analizar.
	 * @return el número de piezas en la columna indicada.
	 */
	private int contarPiezasEnColumna(int columna) {
		int count = 0;
		for (int fila = 0; fila < tablero.consultarNumeroFilas(); fila++) {
			if (!tablero.consultarCelda(new Coordenada(fila, columna)).estaVacia()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Cuenta el número de piezas presentes en una fila específica del tablero.
	 *
	 * @param fila la fila del tablero que se desea analizar.
	 * @return el número de piezas en la fila indicada.
	 */
	private int contarPiezasEnFila(int fila) {
		int count = 0;
		for (int columna = 0; columna < tablero.consultarNumeroColumnas(); columna++) {
			if (!tablero.consultarCelda(new Coordenada(fila, columna)).estaVacia()) {
				count++;
			}
		}
		return count;
	}

	
	@Override
	public String toString() {
		return "Arbitro [tablero=" + tablero + ", turnoActual=" + turnoActual + ", cajaPiezasBlancas="
				+ cajaPiezasBlancas + ", cajaPiezasNegras=" + cajaPiezasNegras + ", contadorJugadas=" + contadorJugadas
				+ ", ultimaJugada=" + ultimaJugada + "]";
	}
}
