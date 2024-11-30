package noventagrados.util;

/**
 * Representa los posibles sentidos de movimiento en el tablero.
 * Cada sentido tiene asociado un valor de desplazamiento en filas y en columnas.
 * Los valores posibles son:
 * 
 * VERTICAL_N: Movimiento hacia el norte (arriba), con desplazamiento de -1 en filas y 0 en columnas.
 * VERTICAL_S: Movimiento hacia el sur (abajo), con desplazamiento de 1 en filas y 0 en columnas.
 * HORIZONTAL_E: Movimiento hacia el este (derecha), con desplazamiento de 0 en filas y 1 en columnas.
 * HORIZONTAL_O: Movimiento hacia el oeste (izquierda), con desplazamiento de 0 en filas y -1 en columnas.
 * 
 * 
 * @author Miguel Cuevas Ruiz
 * @version 1.0
 */
public enum Sentido {
    /**
     * Movimiento hacia el norte (arriba) con desplazamiento de -1 en filas y 0 en columnas.
     */
    VERTICAL_N(-1, 0),

    /**
     * Movimiento hacia el sur (abajo) con desplazamiento de 1 en filas y 0 en columnas.
     */
    VERTICAL_S(1, 0),

    /**
     * Movimiento hacia el este (derecha) con desplazamiento de 0 en filas y 1 en columnas.
     */
    HORIZONTAL_E(0, 1),

    /**
     * Movimiento hacia el oeste (izquierda) con desplazamiento de 0 en filas y -1 en columnas.
     */
    HORIZONTAL_O(0, -1);

    /**
     * Desplazamiento en filas asociado a este sentido de movimiento.
     */
    private final int desplazamientoEnFilas;

    /**
     * Desplazamiento en columnas asociado a este sentido de movimiento.
     */
    private final int desplazamientoEnColumnas;

    /**
     * Constructor que asigna los valores de desplazamiento en filas y columnas a un sentido.
     *
     * @param desplazamientoEnFilas el valor de desplazamiento en filas para este sentido
     * @param desplazamientoEnColumnas el valor de desplazamiento en columnas para este sentido
     */
    private Sentido(int desplazamientoEnFilas, int desplazamientoEnColumnas) {
        this.desplazamientoEnFilas = desplazamientoEnFilas;
        this.desplazamientoEnColumnas = desplazamientoEnColumnas;
    }

    /**
     * Devuelve el desplazamiento en filas asociado a este sentido de movimiento.
     *
     * @return el valor de desplazamiento en filas
     */
    public int consultarDesplazamientoEnFilas() {
        return desplazamientoEnFilas;
    }

    /**
     * Devuelve el desplazamiento en columnas asociado a este sentido de movimiento.
     *
     * @return el valor de desplazamiento en columnas
     */
    public int consultarDesplazamientoEnColumnas() {
        return desplazamientoEnColumnas;
    }
}

