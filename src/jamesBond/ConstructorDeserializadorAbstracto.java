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
  public JamesBond deserializarJamesBond(JSONObject jsonJB);

  /**
   * // TODO completar documentación.
   * @param tablero
   */
  public Tablero deserializarTablero(JSONObject tablero);
  
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
   * Método serializador. Se encarga de extraer los atributos del objeto Carta y representarlos debidamente en el objeto complejo, ya sea en formato json, xml u otro.
   * @param carta Objeto Carta del que se extraerán sus atributos para guardarlos en el objeto compuesto.
   */
  public void deserializarCarta(JSONObject carta);
}
