package jamesBond;

import java.util.Vector;

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
   * @return Hilera resultante con el formato json.
   */
  private String jsonFormatWithComa(String key, String value) {
    return sQts(key) + ":" + value + ",";
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
    this.serializacion = "{" + sQts("JamesBond") + ":\n";
    this.serializacion += "{" + sQts("Jugador1") + ":\n";
    serializarJugador(gameJB.getJugador(1));
    this.serializacion += "\n},";
    this.serializacion += "{" + sQts("Jugador2") + ":\n";
    serializarJugador(gameJB.getJugador(2));
    this.serializacion += "\n},";
    this.serializacion += jsonFormatWithComa("turnoActual", gameJB.getTurnoActual().getNombre()) + "\n";
    this.serializacion += jsonFormatWithComa("temporazador", String.valueOf(gameJB.getTemporizador())) + "\n";
    serializarTablero(gameJB.getTablero());
    // agregar cuando implementemos turnos jugados
    //this.serializacion += ",\n" + jsonFormat("turnosJugados", String.valueOf(gameJB.getTurnosJugados())) + "\n";
    this.serializacion += "\n}";
  }
  /**
   * @brief Método serializador. Se encarga de extraer los atributos del objeto Tablero y representarlos debidamente en un objeto json.
   * @param Tablero Objeto Tablero del que se extraerán sus atributos para guardarlos en el objeto json.
   */
  public void serializarTablero(Tablero tablero) {
    this.serializacion += "\n" + sQts("Tablero") + ":[\n";
    Vector<Carta> cartas = tablero.getCartas();
    for (Carta carta : cartas) {
      serializarCarta(carta);
    }
    this.serializacion += ":]\n";
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
   * @brief Método serializador. Se encarga de extraer los atributos del objeto Pila y representarlos debidamente en un objeto json.
   * @param pila Objeto Pila del que se extraerán sus atributos para guardarlos en el objeto json.
   */
  public void serializarPila(Pila pila) {
    this.serializacion += "\n" + sQts("pila") + ":[\n";
    Vector<Carta> cartas = pila.getCartas();
    for (Carta carta : cartas) {
      serializarCarta(carta);
    }
    this.serializacion += ":]\n";
  }
  
  /**
   * @brief Método serializador. Se encarga de extraer los atributos del objeto Carta y representarlos debidamente en el objeto complejo, ya sea en formato json, xml u otro.
   * @param carta Objeto Carta del que se extraerán sus atributos para guardarlos en el objeto compuesto.
   */
  public void serializarCarta(Carta carta) {
    // comprueba si es o no la primer carta de la pila (para no agregar coma).
    if ( this.serializacion.strip().endsWith("[\n") == false ) {
      // si no es la primera, agrega coma al inicio.
      this.serializacion += ",";
    }

    // agrega inicio de carta y valor del palo
    this.serializacion += "{" + jsonFormatWithComa("palo", sQts(String.valueOf(carta.getPalo())));
    // agrega valor del número y final de carta
    this.serializacion += jsonFormat("numero", String.valueOf(carta.getNumero())) + "}";
  }}
