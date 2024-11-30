package noventagrados.control;

import noventagrados.modelo.Pieza; 
import noventagrados.util.Color;
import noventagrados.util.TipoPieza;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa una caja que contiene piezas de un color específico
 * con una capacidad máxima definida.
 * @author Miguel Cuevas Ruiz
 * @version 1.0
 */
public class Caja {
    private final Color color;
    private final List<Pieza> piezas;
    private static final int CAPACIDAD_MAXIMA = 7;

    /**
     * Constructor que inicializa una caja para un color específico.
     *
     * @param color el color de las piezas que contendrá la caja.
     */
    public Caja(Color color) {
        this.color = color;
        this.piezas = new ArrayList<>();
    }
    
    /**
     * Añade una pieza a la caja si cumple con los requisitos:
     * - La pieza no es nula.
     * - La pieza es del color correspondiente.
     * - La caja no ha alcanzado su capacidad máxima.
     *
     * @param pieza la pieza que se desea añadir a la caja.
     */
    public void añadir(Pieza pieza) {
        // Solo añade la pieza si es del color correcto y no supera la capacidad
        if (pieza != null && pieza.consultarColor() == this.color && piezas.size() < CAPACIDAD_MAXIMA) {
            piezas.add(pieza);
        }
    }
    
    /**
     * Crea una copia profunda de la caja, incluyendo todas las piezas contenidas en ella.
     *
     * @return una nueva instancia de {@code Caja} que contiene copias de las piezas originales.
     */
    public Caja clonar() {
        Caja clon = new Caja(this.color);
        for (Pieza pieza : piezas) { //Bucle for-each En cada paso del bucle, el valor de pieza será un elemento diferente de la lista piezas, y el cuerpo del bucle se ejecutará con ese valo
            clon.añadir(pieza.clonar()); // Clonación profunda de cada pieza
        }
        return clon;
    }
    
    /**
     * Consulta el color de las piezas que esta caja puede almacenar.
     *
     * @return el color asociado a la caja.
     */
    public Color consultarColor() {
        return color;
    }

    /**
     * Devuelve un array de las piezas almacenadas en la caja.
     *
     * @return un array que contiene las piezas en la caja.
     */
    public Pieza[] consultarPiezas() {
        return piezas.toArray(new Pieza[0]); //El método toArray es parte de la clase List y sirve para convertir una colección a un array y si la lista esta vacia devuelve uno vacio
    }
    
    /**
     * Cuenta el número total de piezas almacenadas en la caja.
     *
     * @return el número de piezas en la caja.
     */
    public int contarPiezas() {
        return piezas.size();
    }

    /**
     * Cuenta el número de piezas de un tipo específico almacenadas en la caja.
     *
     * @param tipoPieza el tipo de pieza que se desea contar.
     * @return el número de piezas del tipo especificado en la caja.
     */
    public int contarPiezas(TipoPieza tipoPieza) {
        int count = 0;
        for (Pieza pieza : piezas) {
            if (pieza.consultarTipoPieza() == tipoPieza) {
                count++;
            }
        }
        return count;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caja other = (Caja) obj;
		return color == other.color && Objects.equals(piezas, other.piezas);
	}

    @Override
	public int hashCode() {
		return Objects.hash(color, piezas);
	}

    @Override
	public String toString() {
		return "Caja [color=" + color + ", piezas=" + piezas + "]";
	}
}