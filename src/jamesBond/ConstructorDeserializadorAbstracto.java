package jamesBond;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import marda.Carta;

/**
 * Interface ConstructorDeserializadorAbstracto
 * Esta interfaz permite implementar un patrón de diseño creacional: Patrón Constructor.
 * Permite deserializar un estado del juego JamesBond. Carga lo previamente guardado.
 */
public interface ConstructorDeserializadorAbstracto {

  /* DESERIALIZADORES */
  
  /**
   * @brief Método deserializador. Se encarga de cargar los valores del JamesBond y restaurando el estado en el que se guardo.
   * @param jamesBond un objecto json que representa al controlador JamesBond.
   * @return JamesBond con el estado en el que se guardo.
   */
  public boolean deserializarJamesBond(JamesBond jamesBond);

  /**
   * @brief Método deserializador. Se encarga de cargar los valores de un Tablero, restaurando el estado en el que se guardo.
   * @param cartas un objecto json que representa las cartas comunes.
   * @return Tablero con el estado en el que se guardo.
   */
  public Tablero deserializarTablero(JsonArray cartas);
  
  /**
   * @brief Método deserializador. Se encarga de cargar los valores de un Jugador, restaurando el estado en el que se guardo.
   * @param jugador un objecto json que representa al Jugador.
   * @return Jugador con el estado en el que se guardo.
   */
  public Jugador deserializarJugador(JsonObject jugador);

  /**
   * @brief Método deserializador. Se encarga de cargar los valores de una pila, restaurando el estado en el que se guardo.
   * @param cartas un objecto json que representa las cartas de la pila.
   * @return Pila con el estado en el que se guardo.
   */
  public Pila deserializarPila(JsonArray cartas);

  /**
   * Método deserializador. Se encarga de cargar los valores de una carta, crear la carta y retornarla como objeto Carta.
   * @param carta un objecto json que representa atributos de la carta.
   * @return Carta con el estado en el que se guardo.
   */
  public Carta deserializarCarta(JsonObject carta);
}
