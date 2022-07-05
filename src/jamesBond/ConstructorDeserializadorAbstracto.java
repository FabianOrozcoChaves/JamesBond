package jamesBond;

import org.json.simple.JSONObject;
import java.io.FileReader;

/**
 * Interface ConstructorDeserializadorAbstracto
 * Esta interfaz permite implementar un patrón de diseño creacional: Patrón Constructor.
 * Permite deserializar un estado del juego JamesBond. Carga lo previamente guardado.
 */
public interface ConstructorDeserializadorAbstracto {

  /**
   * // TODO completar implementación
   */
  public JamesBond obtenerDeserialización(FileReader archivoJson);

  /* DESERIALIZADORES */
  
  /**
   * // TODO completar documentación.
   * @param gameJB
   */
  public void deserializarJamesBond(JSONObject jsonJB);

  /**
   * // TODO completar documentación.
   * @param tablero
   */
  public void deserializarTablero(JSONObject tablero);
  
  /**
   * // TODO completar documentación.
   * @param jugador
   */
  public void deserializarJugador(JSONObject jugador);

  /**
   * // TODO completar documentación.
   * @param pila
   */
  public void deserializarPila(JSONObject pila);

  /**
   * Método deserializador. Se encarga de cargar los valores de una carta, crear la carta y retornarla como objeto Carta.
   * @param carta Texto en formato json que representa los atributos de la carta.
   */
  public Carta deserializarCarta(JSONObject carta);
}
