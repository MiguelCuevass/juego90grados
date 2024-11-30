package noventagrados.control;

import noventagrados.modelo.Tablero;
import noventagrados.modelo.Pieza;
import noventagrados.util.Coordenada;
import noventagrados.util.Color;
import noventagrados.util.TipoPieza;
import noventagrados.util.Sentido;
import noventagrados.modelo.Celda;


/**
 * Realiza consultas complejas sobre el estado actual del tablero.
 * 
 * @author Miguel Cuevas Ruiz
 * @version 2.0
 */
public class TableroConsultor {
    private final Tablero tablero;

    /**
     * Constructor para inicializar el consultor con un tablero específico.
     *
     * @param tablero el tablero sobre el cual se realizarán las consultas.
     */
    public TableroConsultor(Tablero tablero) {
        this.tablero = tablero;
    }

    /**
     * Calcula el sentido del movimiento entre dos coordenadas.
     *
     * @param origen  la coordenada inicial.
     * @param destino la coordenada final.
     * @return el sentido del movimiento o {@code null} si no es un movimiento válido.
     */
    public Sentido calcularSentido(Coordenada origen, Coordenada destino) {
        int filaDiff = destino.fila() - origen.fila();
        int colDiff = destino.columna() - origen.columna();
        if (filaDiff == 0 && colDiff > 0) return Sentido.HORIZONTAL_E;
        if (filaDiff == 0 && colDiff < 0) return Sentido.HORIZONTAL_O;
        if (colDiff == 0 && filaDiff > 0) return Sentido.VERTICAL_S;
        if (colDiff == 0 && filaDiff < 0) return Sentido.VERTICAL_N;
        return null;
    }

    /**
     * Consulta la distancia horizontal entre dos coordenadas.
     *
     * @param origen  la coordenada inicial.
     * @param destino la coordenada final.
     * @return la distancia horizontal o -1 si no es un movimiento horizontal.
     */
    public int consultarDistanciaEnHorizontal(Coordenada origen, Coordenada destino) {
    	if (origen.fila() == destino.fila()) {
			return Math.abs(origen.columna() - destino.columna());
		}
		return -1; // Retorna -1 si no es un movimiento horizontal
	}
    
    /**
     * Consulta la distancia vertical entre dos coordenadas.
     *
     * @param origen  la coordenada inicial.
     * @param destino la coordenada final.
     * @return la distancia vertical o -1 si no es un movimiento vertical.
     */
    public int consultarDistanciaEnVertical(Coordenada origen, Coordenada destino) {
    	if (origen.columna() == destino.columna()) {
			return Math.abs(origen.fila() - destino.fila());
		}
		return -1; // Retorna -1 si no es un movimiento vertical
	}
    
    /**
     * Consulta el número de piezas presentes en una fila específica del tablero.
     *
     * @param coordenada la coordenada que indica la fila a analizar.
     * @return el número de piezas en la fila.
     */
    public int consultarNumeroPiezasEnHorizontal(Coordenada coordenada) {
        int count = 0;
        int fila = coordenada.fila();

        for (int col = 0; col < tablero.consultarNumeroColumnas(); col++) {
            Pieza pieza = tablero.consultarCelda(new Coordenada(fila, col)).consultarPieza();
            if (pieza != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Consulta el número de piezas presentes en una columna específica del tablero.
     *
     * @param coordenada la coordenada que indica la columna a analizar.
     * @return el número de piezas en la columna.
     */
    public int consultarNumeroPiezasEnVertical(Coordenada coordenada) {
        int count = 0;
        int columna = coordenada.columna();

        for (int fila = 0; fila < tablero.consultarNumeroFilas(); fila++) {
            Pieza pieza = tablero.consultarCelda(new Coordenada(fila, columna)).consultarPieza();
            if (pieza != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Consulta el número de piezas de un tipo y color específicos en el tablero.
     *
     * @param tipoPieza el tipo de la pieza a contar.
     * @param color     el color de las piezas a contar.
     * @return el número de piezas que cumplen con los criterios.
     */
    public int consultarNumeroPiezas(TipoPieza tipoPieza, Color color) {
        int count = 0;
        for (Celda celda : tablero.consultarCeldas()) {
            if (celda != null) {
                Pieza pieza = celda.consultarPieza();
                if (pieza != null && pieza.consultarColor() == color && pieza.consultarTipoPieza() == tipoPieza) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Verifica si la reina de un color específico está en el centro del tablero.
     *
     * @param color el color de la reina a verificar.
     * @return {@code true} si la reina está en el centro, {@code false} en caso contrario.
     */
    public boolean estaReinaEnElCentro(Color color) {
    	Celda celdaReina = buscarCeldaReina(color);
		if (celdaReina != null) {
			Coordenada centro = new Coordenada(3, 3); // Centro del tablero
			return celdaReina.consultarCoordenada().equals(centro);
		}
		return false;
	}
    
    /**
     * Busca la celda que contiene la reina de un color específico.
     *
     * @param color el color de la reina a buscar.
     * @return la celda que contiene la reina o {@code null} si no se encuentra.
     */
    public Celda buscarCeldaReina(Color color) {
		for (int fila = 0; fila < tablero.consultarNumeroFilas(); fila++) {
			for (int columna = 0; columna < tablero.consultarNumeroColumnas(); columna++) {
				Celda celda = tablero.consultarCelda(new Coordenada(fila, columna));
				// Verifica si la celda contiene una pieza y si es la reina del color
				// especificado
				if (celda.consultarPieza() != null && celda.consultarPieza().consultarTipoPieza() == TipoPieza.REINA
						&& celda.consultarPieza().consultarColor() == color) {
					return celda;
				}
			}
		}
		// Si no se encuentra la reina, devuelve null
		return null;
	}

    /**
     * Verifica si hay una reina de un color específico en el tablero.
     *
     * @param color el color de la reina a verificar.
     * @return {@code true} si hay una reina de ese color, {@code false} en caso contrario.
     */
    public boolean hayReina(Color color) {
        return consultarNumeroPiezas(TipoPieza.REINA, color) > 0;
    }

    @Override
	public String toString() {
		return "TableroConsultor [tablero=" + tablero + "]";
	}
}