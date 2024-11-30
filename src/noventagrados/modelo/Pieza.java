package noventagrados.modelo;

import java.util.Objects;

import noventagrados.util.Color;
import noventagrados.util.TipoPieza;

/**
 * Representa una pieza en el juego, que tiene un tipo (peón o reina) y un color (blanco o negro).
 * @author Miguel Cuevas Ruiz
 * @version 1.0
 */

public class Pieza {
    private final TipoPieza tipoPieza;
    private final Color color;

    /**
     * Crea una pieza con un tipo y color específicos.
     *
     * @param tipoPieza el tipo de la pieza (peón o reina).
     * @param color el color de la pieza (blanco o negro).
     */
    public Pieza(TipoPieza tipoPieza, Color color) {
        this.tipoPieza = tipoPieza;
        this.color = color;
    }

    /**
     * Devuelve el color de la pieza.
     *
     * @return el color de la pieza.
     */
    public Color consultarColor() {
        return color;
    }

    /**
     * Devuelve el tipo de la pieza.
     *
     * @return el tipo de la pieza.
     */
    public TipoPieza consultarTipoPieza() {
        return tipoPieza;
    }

    /**
     * Devuelve una representación en texto de la pieza, en formato "TipoColor" (por ejemplo, "PB" para un peón blanco).
     *
     * @return una cadena que representa la pieza en formato "TipoColor".
     */
    public String aTexto() {
        return tipoPieza.toChar() + "" + color.toChar();
    }
    
    /**
     * Crea y devuelve un clon profundo de esta pieza.
     *
     * @return un nuevo objeto Pieza con el mismo tipo y color que esta pieza.
     */
    public Pieza clonar() {
        return new Pieza(this.tipoPieza, this.color); 
        //Devuelve una nueva instancia de Pieza que es una copia exacta
        //los atributos tipoPieza y color son inmutables (son de tipo enum), no se necesita realizar una copia profunda adicional
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pieza other = (Pieza) obj;
		return color == other.color && tipoPieza == other.tipoPieza;
	}

    @Override
	public int hashCode() {
		return Objects.hash(color, tipoPieza);
	}

    @Override
	public String toString() {
		return "Pieza [tipoPieza=" + tipoPieza + ", color=" + color + "]";
	}
}
