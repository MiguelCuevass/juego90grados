package noventagrados.util;

/**
 * Representa los tipos de piezas posibles en el juego: PEON y REINA.
 * Cada tipo de pieza tiene asociado un carácter que la identifica ('P' para PEON y 'R' para REINA).
 * 
 * @author Miguel Cuevas Ruiz
 * @version 1.0
 */
public enum TipoPieza {
    /**
     * Constante que representa el tipo de pieza peón, identificado por el carácter 'P'.
     */
    PEON('P'),

    /**
     * Constante que representa el tipo de pieza reina, identificado por el carácter 'R'.
     */
    REINA('R');

    /**
     * Carácter que identifica el tipo de pieza.
     */
    private final char caracter;

    /**
     * Constructor que asigna el carácter identificador al tipo de pieza.
     *
     * @param caracter el carácter que identifica el tipo de pieza
     */
    private TipoPieza(char caracter) {
        this.caracter = caracter;
    }

    /**
     * Devuelve el carácter asociado al tipo de pieza.
     *
     * @return el carácter que identifica el tipo de pieza ('P' para PEON y 'R' para REINA).
     */
    public char toChar() {
        return caracter;
    }
}
