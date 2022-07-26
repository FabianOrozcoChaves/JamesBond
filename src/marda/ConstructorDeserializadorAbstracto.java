package marda;

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
   * @brief Método deserializador. Se encarga de cargar los valores del JuegoMarda y restaurando el estado en el que se guardo.
   * @param juego un objecto json que representa al controlador JuegoMarda.
   * @return boolean true si se cargo exitosamente el juego, false en caso contrario.
   */
  public boolean deserializarJuego(JuegoMarda juego);

  /**
   * @brief Método deserializador. Se encarga de cargar los valores de un Tablero, restaurando el estado en el que se guardo.
   * @param cartas un objecto json que representa las cartas comunes.
   * @return ContenedorDeCartasMarda con el estado en el que se guardo.
   */
  public ContenedorDeCartasMarda deserializarTablero(JsonArray cartas);
  
  /**
   * @brief Método deserializador. Se encarga de cargar los valores de un Jugador, restaurando el estado en el que se guardo.
   * @param jugador un objecto json que representa al Jugador.
   * @return Jugador con el estado en el que se guardo.
   */
  public JugadorMarda deserializarJugador(JsonObject jugador);

  /**
   * @brief Método deserializador. Se encarga de cargar los valores de una pila, restaurando el estado en el que se guardo.
   * @param cartas un objecto json que representa las cartas de la pila.
   * @return Pila con el estado en el que se guardo.
   */
  public GrupoDeCartasMarda deserializarGrupoDeCartasMarda(JsonArray cartas);

  /**
   * Método deserializador. Se encarga de cargar los valores de una carta, crear la carta y retornarla como objeto Carta.
   * @param carta un objecto json que representa atributos de la carta.
   * @return Carta con el estado en el que se guardo.
   */
  public Carta deserializarCarta(JsonObject carta);
}