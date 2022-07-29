package jamesBond;

import java.io.FileWriter;
import java.util.Vector;

import marda.*;

public class ConstructorSerializadorJSON implements ConstructorSerializadorAbstracto {
    
  private String serializacion;
 
  /**
   * @brief Método que indica la forma en que se inicia el objeto completo json.
   * @detail  Esto crea algo parecido al siguiente ejemplo:
   * {
   *  "nombreObjeto" :
   * @param nombreObjeto Nombre del Objeto que al que se quiere guardar/serializar el estado.
   */
  public void inicioObjeto(String nombreObjeto) {
    this.serializacion = "{";
    this.serializacion += "\n" + sQts(nombreObjeto) + ":\n";
  }

  /**
   * @brief Método que indica la forma en que se finaliza el objeto completo json.
   * @details Esto crea algo parecido al siguiente ejemplo:
   *  objetoJson...
   * }
   * @param nombreObjeto Nombre del Objeto que al que se quiere guardar/serializar el estado.
   */
  public void finObjeto() {
    serializacion += "}";
  }

  /**
   * @brief Método que retorna la serialización del objeto, es decir, el producto del constructor.
   * @return String que representa la serialización completa del objeto.
   */
  public String obtenerSerialización() {
    return this.serializacion;
  }

  /**
   * @brief Método que guarda la serialización realizada previamente.
   */
  public void guardarSerializacion() {
    try {
      FileWriter file = new FileWriter("jamesBond.json", false);
      file.write(serializacion);
      file.close();
    } catch (Exception e) {
      System.out.println("No se pudo guardar el estado del juego. Vuelve a intentarlo.");
    }
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
   * @brief Método serializador. Se encarga de extraer los atributos del objeto JamesBond y representarlos debidamente en un objeto json.
   * @param juego Objeto JuegoMarda del que se extraerán sus atributos para guardarlos en el objeto json.
   */
  public void serializarJuego(JuegoMarda juego) {
    JamesBond gameJB = (JamesBond)juego;
    inicioObjeto("JamesBond");
    this.serializacion += "{" + sQts("Jugador1") + ":\n";
    serializarJugador(gameJB.getJugadorMarda(1));
    this.serializacion += ",";
    this.serializacion += sQts("Jugador2") + ":\n";
    serializarJugador(gameJB.getJugadorMarda(2));
    this.serializacion += ",\n";
    this.serializacion += jsonFormatWithComa("turnoActual", sQts(gameJB.getTurnoActual().getNombre())) + "\n";
    this.serializacion += jsonFormatWithComa("temporizador", sQts(String.valueOf(gameJB.getTemporizador()))) + "\n";
    serializarTablero(gameJB.getTablero());
    this.serializacion += "}\n";
    finObjeto();
  }
  /**
   * @brief Método serializador. Se encarga de extraer los atributos del objeto Tablero y representarlos debidamente en un objeto json.
   * @param Tablero Objeto Tablero del que se extraerán sus atributos para guardarlos en el objeto json.
   */
  public void serializarTablero(ContenedorDeCartasMarda tablero) {
    this.serializacion += sQts("Tablero") + ":[\n";
    Vector<Carta> cartas = tablero.getGrupoDeCartas(0).getCartas();
    serializarVectorCartas(cartas);
    this.serializacion += "]\n";
  }
    
  /**
   * @brief Método serializador. Se encarga de extraer las pilas y nombre de un jugador para representarlos debidamente en un objeto json.
   * @param jugador Objeto JugadorMarda del que se extraerán sus atributos para guardarlos en el objeto json.
   */
  public void serializarJugador(JugadorMarda jugador) {
    // Cast
    this.serializacion += "{\n" + sQts("nombre") + ":" + sQts(jugador.getNombre()) + ",\n" 
      + sQts("pilas") + ":[\n";

      for (int i = 0; i < jugador.size(); i++) {
        this.serializacion += "{\n";
        serializarGrupoDeCartas(jugador.getGrupoDeCartas(i));
        this.serializacion += "}";
        if (i < jugador.size() - 1)
          this.serializacion += ",";
        this.serializacion += "\n";
      }
    this.serializacion += "]\n}";
  }
  
  /**
   * @brief Método serializador. Se encarga de extraer los atributos del objeto GrupoDeCartasMarda y representarlos debidamente en un objeto json.
   * @param grupoCartas Objeto GrupoDeCartasMarda del que se extraerán sus atributos para guardarlos en el objeto json.
   */
  public void serializarGrupoDeCartas(GrupoDeCartasMarda grupoCartas) {
    this.serializacion += sQts("GrupoDeCartas") + ":[\n";
    Vector<Carta> cartas = grupoCartas.getCartas();
    serializarVectorCartas(cartas);
    this.serializacion += "]\n";
  }
  
  /**
   * @brief Método serializador. Se encarga de extraer los atributos del objeto Carta y representarlos debidamente en un objeto json.
   * @param carta Objeto Carta del que se extraerán sus atributos para guardarlos en el objeto json.
   */
  public void serializarCarta(Carta carta) {
    // agrega inicio de carta y valor del palo
    this.serializacion += "{" + jsonFormatWithComa("palo", sQts(String.valueOf(carta.getPalo())));
    // agrega valor del número y final de carta
    this.serializacion += jsonFormat("numero", sQts(String.valueOf(carta.getNumero()))) + "}";
  }

  private void serializarVectorCartas(Vector<Carta> cartas){
    for (int i = 0; i < cartas.size(); i++) {
      serializarCarta(cartas.get(i));
      if( i < cartas.size() - 1){
        serializacion += ",";  
      }
      serializacion += "\n";
    }
  }
}