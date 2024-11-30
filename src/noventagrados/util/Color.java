package noventagrados.util;

/**
 *
 * Representa los colores posibles de las piezas en el juego: BLANCO y NEGRO.
 * Cada color tiene asociado un carácter que lo identifica ('B' para BLANCO y 'N' para NEGRO).
 * La clase proporciona un método para consultar el color contrario al actual.
 * 
 * @author Miguel Cuevas Ruiz
 * @version 1.0
 */
public enum Color {
    /**
     * Constante que representa el color blanco, identificado por el carácter 'B'.
     */
    BLANCO('B'),

    /**
     * Constante que representa el color negro, identificado por el carácter 'N'.
     */
    NEGRO('N');

    /**
     * Carácter que identifica el color.
     */
    private final char letra;

    /**
     * Constructor que asigna el carácter identificador al color.
     *
     * @param letra el carácter que identifica el color (B\N)
     */
    private Color(char letra) {
        this.letra = letra;
    }

    /**
     * Devuelve el color contrario al color actual.
     *
     * @return el color contrario al actual. Si el color es BLANCO, devuelve NEGRO; si es NEGRO, devuelve BLANCO.
     */
    public Color consultarContrario() {
        return this == BLANCO ? NEGRO : BLANCO;
    }

    /**
     * Devuelve el carácter asociado al color.
     *
     * @return el carácter que identifica el color ('B' para BLANCO y 'N' para NEGRO).
     */
    public char toChar() {
        return letra;
    }
}
