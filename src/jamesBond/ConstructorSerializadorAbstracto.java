package jamesBond;

import marda.Carta;

/**
 * Interface ConstructorSerializadorAbstracto
 * Esta interfaz permite implementar un patrón de diseño creacional: Patrón Constructor.
 * Permite serializar un estado del juego JamesBond para poder cargarlo en el futuro.
 */
public interface ConstructorSerializadorAbstracto {
  
  /**
   * @brief Método que indica la forma en que se inicia el objeto completo (ya sea en formato json, xml u otro).
   * @param nombreObjeto Nombre del Objeto que al que se quiere guardar/serializar el estado.
   */
  public void inicioObjeto(String nombreObjeto);

  /**
   * @brief Método que indica la forma en que se finaliza el objeto completo (ya sea en formato json, xml u otro).
   * @param nombreObjeto Nombre del Objeto que al que se quiere guardar/serializar el estado.
   */
  public void finObjeto();

  /**
   * @brief Método que retorna la serialización del objeto, es decir, el producto del constructor.
   * @return String que representa la serialización completa del objeto.
   */
  public String obtenerSerialización();

  /**
   * @brief Método que se encarga de crear/sobreescribir un archivo con la serialización previamente realizada.
   */
  public void guardarSerializacion();

  /* SERIALIZADORES */
  
  /**
   * @brief Método serializador. Se encarga de extraer los atributos del objeto Tablero y representarlos debidamente en un objeto json.
   * @param Tablero Objeto Tablero del que se extraerán sus atributos para guardarlos en el objeto json.
   */
  public void serializarJamesBond(JamesBond gameJB);

  /**
   * @brief Método serializador. Se encarga de extraer los atributos del objeto Tablero y representarlos debidamente en el objeto complejo, ya sea en formato json, xml u otro.
   * @param tablero Objeto Tablero del que se extraerán sus atributos para guardarlos en el objeto compuesto.
   */
  public void serializarTablero(Tablero tablero);
  
  /**
   * @brief Método serializador. Se encarga de extraer las pilas y nombre de un jugador para representarlas debidamente en el objeto complejo, ya sea en formato json, xml u otro.
   * @param jugador Objeto Jugador del que se extraerán sus atributos para guardarlos en el objeto compuesto.
   */
  public void serializarJugador(Jugador jugador);

  /**
   * @brief Método serializador. Se encarga de extraer los atributos del objeto Pila y representarlos debidamente en el objeto complejo, ya sea en formato json, xml u otro.
   * @param pila Objeto Pila del que se extraerán sus atributos para guardarlos en el objeto compuesto.
   */
  public void serializarPila(Pila pila);

  /**
   * Método serializador. Se encarga de extraer los atributos del objeto Carta y representarlos debidamente en el objeto complejo, ya sea en formato json, xml u otro.
   * @param carta Objeto Carta del que se extraerán sus atributos para guardarlos en el objeto compuesto.
   */
  public void serializarCarta(Carta carta);
}
