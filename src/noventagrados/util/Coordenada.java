package noventagrados.util;

/**
 * Representa una coordenada en un tablero con valores de fila y columna.
 * La clase es tipo Record por lo tanto es inmutable y se utiliza para almacenar la posición de una celda en el juego.
 * 
 * @author Miguel Cuevas Ruiz
 * @version 1.0
 */
public record Coordenada(int fila, int columna) {

    /**
     * Devuelve la representación en formato texto de la coordenada.
     * 
     * @return una cadena que representa la coordenada en el formato "fila columna" (por ejemplo, "23").
     */
    public String aTexto() {
        return String.format("%d%d", fila, columna);
    }

	
}
