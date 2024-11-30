package noventagrados.modelo;

import java.util.Objects;

import noventagrados.util.*;

/**
 * Representa una celda en el tablero, que tiene una coordenada y puede contener una pieza.
 * @author Miguel Cuevas Ruiz
 * @version 1.0
 */

public class Celda {
    private final Coordenada coordenada;
    private Pieza pieza;

    /**
     * Crea una celda con una coordenada específica.
     *
     * @param coordenada la coordenada de la celda en el tablero.
     */
    public Celda(Coordenada coordenada) {
        this.coordenada = coordenada;
        this.pieza = null;
    }
    
    /**
     * Crea y devuelve un clon profundo de esta celda.
     *
     * @return un nuevo objeto Celda con la misma coordenada y, si tiene, una copia de la pieza.
     */
    public Celda clonar() {
        Celda clon = new Celda(this.coordenada); // La coordenada es inmutable, no necesita clonarse.
        if (this.pieza != null) {
            clon.colocar(this.pieza.clonar()); // Clona la pieza si existe.
        }
        return clon;
    }
    /**
     * Coloca una pieza en la celda.
     *
     * @param pieza la pieza a colocar en la celda.
     */
    public void colocar(Pieza pieza) {
        this.pieza = pieza;
    }

    /**
     * Devuelve el color de la pieza en la celda, o null si no hay pieza.
     *
     * @return el color de la pieza, o null si no hay pieza.
     */
    public Color consultarColorDePieza() {
        return pieza != null ? pieza.consultarColor() : null;
    }

    /**
     * Devuelve la coordenada de la celda.
     *
     * @return la coordenada de la celda.
     */
    public Coordenada consultarCoordenada() {
        return coordenada;
    }

    /**
     * Devuelve la pieza en la celda, o null si la celda está vacía.
     *
     * @return la pieza en la celda, o null si no hay pieza.
     */
    public Pieza consultarPieza() {
        return pieza;
    }

    /**
     * Elimina la pieza de la celda, dejándola vacía.
     */
    public void eliminarPieza() {
        this.pieza = null;
    }

    /**
     * Verifica si la celda está vacía.
     *
     * @return true si la celda está vacía, false si contiene una pieza.
     */
    public boolean estaVacia() {
        return pieza == null;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celda other = (Celda) obj;
		return Objects.equals(coordenada, other.coordenada) && Objects.equals(pieza, other.pieza);
	}

    @Override
	public int hashCode() {
		return Objects.hash(coordenada, pieza);
	}

    @Override
	public String toString() {
		return "Celda [coordenada=" + coordenada + ", pieza=" + pieza + "]";
	}
}
