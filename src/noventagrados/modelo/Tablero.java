package noventagrados.modelo;

import java.util.Arrays;

import noventagrados.util.Coordenada;

/**
 * Representa el tablero de juego, que consiste en una matriz de celdas de tamaño fijo.
 */
public class Tablero {
    private static final int TAMANO = 7;  // Tamaño del tablero (número de filas y columnas)
    private final Celda[][] matriz;

    /**
     * Crea un tablero con una matriz de celdas de tamaño definido por la constante TAMANO.
     * Inicializa cada celda en su correspondiente coordenada.
     */
    public Tablero() {
        matriz = new Celda[TAMANO][TAMANO];
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                matriz[i][j] = new Celda(new Coordenada(i, j));
            }
        }
    }

    /**
     * Devuelve una representación en texto del tablero.
     *
     * @return una cadena que representa el estado actual del tablero.
     */
    public String aTexto() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TAMANO; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < TAMANO; j++) {
                sb.append(matriz[i][j].estaVacia() ? "--" : matriz[i][j].consultarPieza().aTexto()).append(" ");
            }
            sb.append("\n");
        }
        sb.append("  ");
        for (int j = 0; j < TAMANO; j++) {
            sb.append(j).append("  ");
        }
        return sb.toString();
    }

    /**
     * Crea y devuelve un clon profundo de este tablero.
     *
     * @return un nuevo objeto Tablero con una copia profunda de las celdas.
     */
    public Tablero clonar() {
        Tablero clon = new Tablero();
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                clon.matriz[i][j] = this.matriz[i][j].clonar();
            }
        }
        return clon;
    }

    /**
     * Coloca una pieza en la celda especificada por la coordenada.
     *
     * @param pieza la pieza a colocar.
     * @param coordenada la coordenada donde colocar la pieza.
     */
    public void colocar(Pieza pieza, Coordenada coordenada) {
        // Verifica si la pieza es null o si la coordenada no es válida
        if (pieza == null || coordenada == null || !estaEnTablero(coordenada)) {
            return;  // Ignora la operación si la pieza es null o la coordenada es inválida
        }
        // Coloca la pieza en la celda correspondiente
        matriz[coordenada.fila()][coordenada.columna()].colocar(pieza);
    }

    /**
     * Devuelve una celda en una coordenada especificada.
     *
     * @param coordenada la coordenada de la celda a consultar.
     * @return la celda en la coordenada especificada.
     */
    public Celda consultarCelda(Coordenada coordenada) {
        return estaEnTablero(coordenada) ? matriz[coordenada.fila()][coordenada.columna()].clonar() : null;
    }

    /**
     * Devuelve todas las celdas del tablero en un array unidimensional.
     *
     * @return un array con copias de todas las celdas del tablero.
     */
    public Celda[] consultarCeldas() {
        Celda[] celdas = new Celda[TAMANO * TAMANO];
        int index = 0;
        for (Celda[] fila : matriz) {
            for (Celda celda : fila) {
                celdas[index++] = celda.clonar();
            }
        }
        return celdas;
    }

    /**
     * Devuelve el número de columnas del tablero.
     *
     * @return el número de columnas (TAMANO).
     */
    public int consultarNumeroColumnas() {
        return TAMANO;
    }

    /**
     * Devuelve el número de filas del tablero.
     *
     * @return el número de filas (TAMANO).
     */
    public int consultarNumeroFilas() {
        return TAMANO;
    }

    /**
     * Elimina la pieza de la celda en la coordenada especificada.
     *
     * @param coordenada la coordenada de la celda de la cual se eliminará la pieza.
     */
    public void eliminarPieza(Coordenada coordenada) {
        // Verifica si la coordenada es null o si no está en el tablero
        if (coordenada == null || !estaEnTablero(coordenada)) {
            return;  // Ignora la operación si la coordenada es null o no válida
        }
        // Elimina la pieza en la celda correspondiente
        matriz[coordenada.fila()][coordenada.columna()].eliminarPieza();
    }


    /**
     * Verifica si una coordenada está dentro de los límites del tablero.
     *
     * @param coordenada la coordenada a verificar.
     * @return true si la coordenada está en el tablero, false en caso contrario.
     */
    public boolean estaEnTablero(Coordenada coordenada) {
        int fila = coordenada.fila();
        int columna = coordenada.columna();
        return fila >= 0 && fila < TAMANO && columna >= 0 && columna < TAMANO;
    }

    /**
     * Devuelve la celda en la coordenada especificada sin clonar.
     * Este método tiene acceso amigable.
     *
     * @param coordenada la coordenada de la celda a obtener.
     * @return la referencia a la celda en la coordenada especificada, o null si está fuera del tablero.
     */
    Celda obtenerCelda(Coordenada coordenada) {
        return estaEnTablero(coordenada) ? matriz[coordenada.fila()][coordenada.columna()] : null;
    }
    
    
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablero other = (Tablero) obj;
		return Arrays.deepEquals(matriz, other.matriz);
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(matriz);
		return result;
	}

    @Override
	public String toString() {
		return "Tablero [matriz=" + Arrays.toString(matriz) + "]";
	}
}
