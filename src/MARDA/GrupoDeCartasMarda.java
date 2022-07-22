package MARDA;

import java.util.Collections;
import java.util.Vector;


public class GrupoDeCartasMarda {
  private Vector<Carta> cartas;


  /**
   * @brief Constructor por omision. Inicializa atributos de la clase
   */
  public GrupoDeCartasMarda() {
    this.cartas = new Vector<Carta>();
  }

  /**
   * @brief Método que agrega una carta al vector de cartas.
   * @param carta Carta que se quiere agregar.
   * @param indice Posicion en la que se quiere agregar.
   */
  public void agregarCarta(Carta carta, int indice) {
    this.cartas.set(indice, carta);
  }

  /**
   * @brief Método que quita  una carta del vector de cartas.
   * @param indice Posicion de la carta a eliminar.
   * @return Carta eliminada dek vector de cartas.
   */
  public Carta quitarCarta(int indice) {
    return this.cartas.remove(indice);
  }

  /**
   *@ brief Método get. Retorna la carta que se encuentra en la posición indicada por parámetro.
   * @param posicion posición de la carta en el vector.
   * @return Carta que representa la carta que se solicita.
   */
  public Carta getCarta(int posicion) {
    return this.cartas.elementAt(posicion);
  }

  /**
   * @brief Método que busca una carta en el vector de cartas.
   * @param numero Numero de la carta a buscar.
   * @param palo Palo de la carta a buscar.
   * @return int que representa la posicon de la carta en el vector de cartas.
   */
  public int buscarCarta(int numero, char palo) {
    int indice = -1;
    for (int i = 0; i < this.cartas.size(); i++) {
      if (this.cartas.get(i).getNumero() == numero && 
        this.cartas.get(i).getPalo() == palo) {
          indice = i;
      }
    }
    return indice;
  }

  /**
   * @brief mezcla las cartas del mazo
   */
  public void barajar() {
	  Collections.shuffle(this.cartas);
  }

  // TODO Agregar a UML
  /**
   * Método que da el vector de cartas para ser visualizado (para el GUI).
   * @return Vector de tipo Carta que contiene todas las cartas de la pila.
   */
  public Vector<Carta> getCartas() {
    return this.cartas;
  }
}