package jamesBond;

public class ConstructorSerializadorJSON implements ConstructorSerializadorAbstracto {
    
  private String serializacion;
 
  /**
   * @brief Método que indica la forma en que se inicia el objeto completo (ya sea en formato json, xml u otro).
   * @detail  Esto crea algo parecido al siguiente ejemplo:
   * {
   *  "nombreObjeto" :
   * @param nombreObjeto Nombre del Objeto que al que se quiere guardar/serializar el estado.
   */
  public void inicioObjeto(String nombreObjeto) {
    this.serializacion = "{";
    this.serializacion += "\n\t" + "\"" + nombreObjeto + "\" :";
  }

  /**
   * @brief Método que indica la forma en que se finaliza el objeto completo (ya sea en formato json, xml u otro).
   * @details Esto crea algo parecido al siguiente ejemplo:
   *  objetoJson...
   * }
   * @param nombreObjeto Nombre del Objeto que al que se quiere guardar/serializar el estado.
   */
  public void finObjeto() {
    serializacion += "\n}";
  }

  /**
   * @brief Método que retorna la serialización del objeto, es decir, el producto del constructor.
   * @return String que representa la serialización completa del objeto.
   */
  public String obtenerSerialización() {
    return this.serializacion;
  }

  private String sQts(String hilera) {
    return "\"" + hilera + "\"";
  }

  /**
   * @brief Método que retorna el formato json de un key y un value CON coma. Ejemplo
   * "Key":value,
   * o
   * "Key":"value",
   * @details Para el valor, se tiene que pasar con "" previamente en caso de ser String.
   * @param key Llave/Clave json
   * @param value Valor del json asociado a la clave.
   * @param coma valor de la coma
   * @return Hilera resultante con el formato json.
   */
  private String jsonFormat(String key, String value, String coma) {
    return sQts(key) + ":" + value + coma + "";
  }

    /**
   * @brief Método que retorna el formato json de un key y un value SIN coma. Ejemplo
   * "Key":value
   * o
   * "Key":"value"
   * @details Para el valor, se tiene que pasar con "" previamente en caso de ser String.
   * @param key Llave/Clave json
   * @param value Valor del json asociado a la clave.
   * @return Hilera resultante con el formato json.
   */
  private String jsonFormat(String key, String value) {
    return sQts(key) + ":" + value + "";
  }

  /* SERIALIZADORES */

  /**
   * // TODO completar documentación.
   * @param gameJB
   */
  public void serializarJamesBond(JamesBond gameJB) {

  }
  /**
   * // TODO completar documentación.
   * @param tablero
   */
  public void serializarTablero(Tablero tablero) {

  }  
  /**
   * @brief Método serializador. Se encarga de extraer las pilas y nombre de un jugador para representarlas debidamente en el objeto complejo, ya sea en formato json, xml u otro.
   * @param jugador Objeto Jugador del que se extraerán sus atributos para guardarlos en el objeto compuesto.
   */
  public void serializarJugador(Jugador jugador) {
    
    this.serializacion += "{\n" + sQts("nombre") + ":" + sQts(jugador.getNombre()) + ",\n" 
      + sQts("pilas") + ":[\n";

      for (int i = 0; i < jugador.getMaxPilas(); i++) {
        this.serializacion += "{\n";
        serializarPila(jugador.getPila(i));
        this.serializacion += "}";
        if (i < jugador.getMaxPilas() - 1)
          this.serializacion += ",";
        this.serializacion += "\n";
      }
    this.serializacion += "]\n}";
  }

  /**
   * // TODO completar documentación.
   * @param pila
   */
  public void serializarPila(Pila pila) {

  }
  
  /**
   * @brief Método serializador. Se encarga de extraer los atributos del objeto Carta y representarlos debidamente en el objeto complejo, ya sea en formato json, xml u otro.
   * @param carta Objeto Carta del que se extraerán sus atributos para guardarlos en el objeto compuesto.
   */
  public void serializarCarta(Carta carta) {
    // comprueba si es o no la primer carta de la pila (para no agregar coma).
    if ( this.serializacion.strip().endsWith("[") == false ) {
      // si no es la primera, agrega coma al inicio.
      this.serializacion += ",";
    }

    // agrega inicio de carta y valor del palo
    this.serializacion += "{" + jsonFormat("palo", sQts(String.valueOf(carta.getPalo())), ",");
    // agrega valor del número y final de carta
    this.serializacion += jsonFormat("numero", String.valueOf(carta.getNumero())) + "}";
  }}
