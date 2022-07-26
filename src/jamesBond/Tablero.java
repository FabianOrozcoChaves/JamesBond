package jamesBond;

import marda.Carta;
import marda.ContenedorDeCartasMarda;
import marda.GrupoDeCartasMarda;

/**
 * Clase Tablero.
 * Representa la mesa donde donde se va juegar James Bond.
 * Contiene las cartas comunes
 */
public class Tablero extends ContenedorDeCartasMarda {

  /**
   * @brief Constructor de la clase.
   */
  public Tablero() {
    super();
    super.agregarGrupoDeCartas(new GrupoDeCartasMarda());
  }

  /**
   * @brief elimina una de las cartas comunes del tablero
   * @param int representa la posicion de la carta
   * @param cartaEntrante carta que será introducida en las cartas comunes. Viene del jugador.
   * @return la carta en la posicion solicitada
   */
  public Carta cambiarCarta(int posicion, Carta cartaEntrante) {
    if(posicion > super.getGrupoDeCartas(0).getCartas().size() && posicion < 0) {
      return null;
    }
    return super.getGrupoDeCartas(0).cambiarCarta(posicion, cartaEntrante);
  }
  
  /**
   * @brief Agrega una carta a las cartas comunes
   * @param Carta que va a ser agregada a las cartasComunes
   * @return pila que representa la que el jugador tiene en mano actualmente.
   */
  public void agregarCarta(Carta carta) {
	  super.getGrupoDeCartas(0).agregarCarta(carta);
  }

  /**
   * @brief devuelve la carta en al posicion escogida
   * @param int posicion 
   * @return la carta que se encuentra en ese posicion
   */
  public Carta getCarta(int posicion) {
	  return super.getGrupoDeCartas(0).getCarta(posicion);
  }

  //
  /**
   * @brief devuelve las cartas comunes del juego
   * @return un vector de cartas con las cartas comunes
   */
  public GrupoDeCartasMarda getCartas() {
	  return super.getGrupoDeCartas(0);
  }

  /**
   * @brief hace clear al vector de cartas comunes
   */
  public void quitarCartas() {
    super.getGrupoDeCartas(0).clear();
  }
}
