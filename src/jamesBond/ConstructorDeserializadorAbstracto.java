package jamesBond;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Interface ConstructorDeserializadorAbstracto
 * Esta interfaz permite implementar un patrón de diseño creacional: Patrón Constructor.
 * Permite deserializar un estado del juego JamesBond. Carga lo previamente guardado.
 */
public interface ConstructorDeserializadorAbstracto {

  /* DESERIALIZADORES */
  
  /**
   * // TODO completar documentación.
   * @param gameJB
   */
  public boolean deserializarJamesBond(JamesBond jamesBond);

  /**
   * // TODO completar documentación.
   * @param tablero
   */
  public Tablero deserializarTablero(JsonArray cartas);
  
  /**
   * // TODO completar documentación.
   * @param jugador
   */
  public Jugador deserializarJugador(JsonObject jugador);

  /**
   * // TODO completar documentación.
   * @param pila
   */
  public Pila deserializarPila(JsonArray cartas);

  /**
   * Método deserializador. Se encarga de cargar los valores de una carta, crear la carta y retornarla como objeto Carta.
   * @param carta Texto en formato json que representa los atributos de la carta.
   */
  public Carta deserializarCarta(JsonObject carta);
}
