package noventagrados.modelo;

/**
 * Representa una jugada en el juego, con una celda de origen y una de destino.
 * @author Miguel Cuevas Ruiz
 * @version 1.0
 */
public record Jugada(Celda origen, Celda destino) {

	/**
     * Devuelve una representación en texto de la jugada en formato "origen-destino".
     *
     * @return una cadena que representa la jugada en formato "origen-destino".
     */
	 public String aTexto() {
	        return origen.consultarCoordenada().aTexto() + "-" + destino.consultarCoordenada().aTexto();
	    }
}
