package jamesBond;

import java.util.Vector;

/**
 * Clase Tablero.
 * Representa la mesa donde donde se va juegar James Bond.
 * Contiene las cartas comunes
 */
public class Tablero {
  private static Tablero tablero;
  private Vector<Carta> cartasComunes;

  /**
   * @brief Constructor privado de la clase.
   */
  private Tablero() {
    cartasComunes = new Vector<Carta>();
  }

  /**
   * @brief Se usa para poder instanciar la clase ya que es un singletoon
   * @return la unica instancia de la clase.
   */
  public static Tablero getInstance() {
	if(tablero == null){
	  tablero =  new Tablero();
	}
	return tablero;
   }

  /**
   * @brief elimina una de las cartas comunes del tablero
   * @param int representa la posicion de la carta
   * @return la carta en la posicion solicitada
   */
  public Carta eliminarCarta(int posicion) {
	if(posicion > this.cartasComunes.size() && posicion < 0){
		return null;
	}
    return this.cartasComunes.remove(posicion);
  }
  
  /**
   * @brief Agrega una carta a las cartas comunes
   * @param Carta que va a ser agregada a las cartasComunes
   * @return pila que representa la que el jugador tiene en mano actualmente.
   */
  public void agregarCarta(Carta carta) {
	  this.cartasComunes.add(carta);
  }

  /**
   * @brief devuelve la carta en al posicion escogida
   * @param int posicion 
   * @return la carata que se encuentra en ese posicion
   */
  public Carta getCarta(int posicion) {
	  return this.cartasComunes.get(posicion);
  }
}
