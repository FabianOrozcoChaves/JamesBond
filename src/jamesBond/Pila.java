package jamesBond;

import java.util.Vector;

/**
 * Clase Pila.
 * Representa una cantidad de cartas que posee un jugador.
 * Puede estar boca abajo en la mesa o en la mano del jugador.
 */
public class Pila {
  private int maxCartas;
  private Vector<Carta> cartas;

  /**
   * Método constructor por parámetros. 
   * @param max_cartas indica la cantidad máxima de cartas que puede contener la pila.
   */
  public Pila(int maxCartas) {
    this.maxCartas = maxCartas;
    this.cartas = new Vector<Carta>(this.maxCartas);
  }

  /**
   * Método que elimina una carta del vector de cartas y la retorna.
   * @param posicion representa la posición en el vector de la carta que se quiere eliminar.
   * @param cartaEntrante carta que será introducida en la pila actual del jugador. Viene desde la mesa.
   * @return Carta que se elimina.
   */
  // TODO: cambiar nombre del metodo en UML y agregar parametro
  public Carta cambiarCarta(int posicion, Carta cartaEntrante) {
    Carta carta = this.cartas.set(posicion, cartaEntrante);
    return carta;
  }

  /**
   * Método que agrega una carta al vector de cartas.
   * @param carta Carta que se quiere agregar.
   */
  public void agregarCarta(Carta carta) {
    this.cartas.add(carta);
  }
  
   /**
   * Verifica si todas las cartas de la pila poseen el mismo número.
   * Funciona cuando la pila no está vacía.
   * @return true en caso de que todos los números coincidan, false en caso contrario.
   */
  public boolean cartasIguales() {
    if (!this.isEmpty() && this.cartas.size() == maxCartas) {
      // selecciona el número de la primera carta como pivote.
      int numeroPivote = this.cartas.elementAt(0).getNumero();
  
      // recorre cada carta para ver si su número es igual
      for (int carta = 1; carta < maxCartas; ++carta) {
        if (this.cartas.elementAt(carta).getNumero() != numeroPivote) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /**
   * Método que da el vector de cartas para ser visualizado (para el GUI).
   * @return Vector de tipo Carta que contiene todas las cartas de la pila.
   */
  public Vector<Carta> getCartas() {
    return this.cartas;
  }

  /**
   * Método que retorna si la pila de cartas se encuentra vacía.
   * @return true en caso que que la pila esté vacía. false en caso contrario.
   */
  public boolean isEmpty() {
    return cartas.size() == 0;
  }

  /**
   * Método get. Retorna la carta que se encuentra en la posición indicada por parámetro.
   * @param posicion posición de la carta en el vector.
   * @return Carta que representa la carta que se solicita.
   */
  public Carta getCarta(int posicion) {
    return this.cartas.elementAt(posicion);
  }
}