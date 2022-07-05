package jamesBond;

import java.io.FileReader;
import org.json.simple.JSONObject;

public class ConstructorDeserializadorJSON implements ConstructorDeserializadorAbstracto{
  private JamesBond jamesBond;

  /**
   * // TODO completar implementación
   */
  public JamesBond obtenerDeserialización(FileReader archivoJson){
    return jamesBond;
  }

  /* DESERIALIZADORES */
  
  /**
   * // TODO completar documentación.
   * @param gameJB
   */
  public void deserializarJamesBond(JSONObject jsonJB){
  }

  /**
   * // TODO completar documentación.
   * @param tablero
   */
  public void deserializarTablero(JSONObject tablero){

  }
  
  /**
   * // TODO completar documentación.
   * @param jugador
   */
  public void deserializarJugador(JSONObject jugador){

  }

  /**
   * // TODO completar documentación.
   * @param pila
   */
  public void deserializarPila(JSONObject pila){

  }

  /**
   * @brief Método deserializador. Se encarga de cargar los valores de una carta, crear la carta y retornarla como objeto Carta.
   * @param carta Texto en formato json que representa los atributos de la carta.
   * @return Carta con los atributos cargados
   */
  public Carta deserializarCarta(JSONObject carta) {
    char palo = ((String) carta.get("palo")).charAt(0);
    int numero = Integer.parseInt(((String) carta.get("numero")));
    return new Carta(palo, numero);
  }
    
}

/* FORMA DE DESERIALIZAR 

// accede al objeto en general
JSONObject objeto = (JSONObject) new JSONParser().parse(new FileReader("jamesBondExample.json"));

// accede al objeto jamesbond
JSONObject jamesbond = (JSONObject) objeto.get("JamesBond");
// accede al jugador
JSONObject jugador1 = (JSONObject) jamesbond.get("Jugador1");
// accede las pilas del jugador
JSONArray pilas = (JSONArray) jugador1.get("pilas");
// recorre las pilas del jugador
for (int i = 0; i < pilas.size(); ++i) {
    JSONObject pila = (JSONObject) pilas.get(i);
    // accede a las cartas de la pila actual
    JSONArray cartas = (JSONArray) pila.get("pila");
    // recorre las cartas de la pila
    for (int j = 0; j < cartas.size(); j++) {
        JSONObject carta = (JSONObject) cartas.get(j);
        char palo = ((String) carta.get("palo")).charAt(0);
        int numero = Integer.parseInt(((String) carta.get("numero")));
        // construirCarta(palo, numero);
    }
}
*/