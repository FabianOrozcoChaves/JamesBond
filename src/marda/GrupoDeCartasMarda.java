package marda;

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
   * @brief Constructor por parámetros. Inicializa atributos de la clase
   */
  public GrupoDeCartasMarda(int cantidadCartas) {
    this.cartas = new Vector<Carta>(cantidadCartas);
  }

  /**
   * @brief Método que agrega una carta al FINAL al vector de cartas.
   * @param carta Carta que se quiere agregar.
   */
  public void agregarCarta(marda.Carta carta) {
    this.cartas.add(carta);
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
   * @brief mezcla las cartas del GrupoDeCartasMarda
   */
  public void barajar() {
	  Collections.shuffle(this.cartas);
  }

  /**
   * Método que da el vector de cartas para ser visualizado (para el GUI).
   * @return Vector de tipo Carta que contiene todas las cartas de la pila.
   */
  public Vector<Carta> getCartas() {
    return this.cartas;
  }

  /**
   * @brief Método que limpia todo el vector de cartas, lo restablece.
   */
  public void clear() {
    this.cartas.clear();
  }

  /**
   * @brief Método que retorna la última carta del vector.
   * @return objeto Carta que representa la ultima carta del vector.
   */
  public Carta topNpop() {
    if(this.cartas.size() < 1){
		  return null;
	  }
    return this.cartas.remove(0);
  }

  /**
   * @brief Método que retorna un valor booleano que representa si el vector de cartas está vacío
   * @return True si el vector de cartas está vacío, false en caso contrario.
   */
  public Boolean isEmpty() {
    return this.cartas.isEmpty();
  }

  /**
   * Método que intercambia una carta comun (que está en la mesa), con una de la pila activa del jugador.
   * @param cartaEntrante carta que será introducida en el grupo de cartas.
   * @param posicionSaliente posicion de la carta que será sacada del grupo de cartas.
   * @return Carta que representa la carta que fue sacada de la mesa
   */
  public Carta cambiarCarta(int posicionSaliente, Carta cartaEntrante) {
    // set retorna la carta que esta reemplazando la nueva carta en posicion posicionSaliente.
    return this.cartas.set(posicionSaliente, cartaEntrante);
  }
}