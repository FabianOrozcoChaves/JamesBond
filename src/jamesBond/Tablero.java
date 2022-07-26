package jamesBond;

import java.util.Vector;

import marda.Carta;
import marda.ContenedorDeCartasMarda;
import marda.GrupoDeCartasMarda;

/**
 * Clase Tablero.
 * Representa la mesa donde donde se va juegar James Bond.
 * Contiene las cartas comunes
 */
public class Tablero extends ContenedorDeCartasMarda {
  private Vector<Carta> cartasComunes;

  /**
   * @brief Constructor de la clase.
   */
  public Tablero() {
    cartasComunes = new Vector<Carta>();
  }

  /**
   * @brief elimina una de las cartas comunes del tablero
   * @param int representa la posicion de la carta
   * @param cartaEntrante carta que será introducida en las cartas comunes. Viene del jugador.
   * @return la carta en la posicion solicitada
   */
  public Carta cambiarCarta(int posicion, Carta cartaEntrante) {
	if(posicion > this.cartasComunes.size() && posicion < 0){
		return null;
	}
    return this.cartasComunes.set(posicion, cartaEntrante);
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
   * @return la carta que se encuentra en ese posicion
   */
  public Carta getCarta(int posicion) {
	  return this.cartasComunes.get(posicion);
  }

  //
  /**
   * @brief devuelve las cartas comunes del juego
   * @return un vector de cartas con las cartas comunes
   */
  public Vector<Carta> getCartas() {
	  return this.cartasComunes;
  }

  /**
   * @brief hace clear al vetor de cartas comunes
   */
  public void quitarCartas() {
	  this.cartasComunes.clear();
  }
}
