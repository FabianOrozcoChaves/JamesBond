package jamesBond;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

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
  public void deserializarJamesBond(JsonObject jsonJB){
  }

  /**
   * // TODO completar documentación.
   * @param tablero
   */
  public void deserializarTablero(JsonObject tablero){

  }
  
  /**
   * // TODO completar documentación.
   * @param jugador
   */
  public void deserializarJugador(JsonObject jugador){

  }

  /**
   * // TODO completar documentación.
   * @param pila
   */
  public void deserializarPila(JsonObject pila){

  }

  /**
   * @brief Método deserializador. Se encarga de cargar los valores de una carta, crear la carta y retornarla como objeto Carta.
   * @param carta Texto en formato json que representa los atributos de la carta.
   * @return Carta con los atributos cargados
   */
  public Carta deserializarCarta(JsonObject carta) {
    char palo = carta.getString("palo").charAt(0);
    int numero = carta.getInt("numero");
    return new Carta(palo, numero);
  }
}   

 /**FORMA DE DESERIALIZAR

 public static void main(String[] args){

// accede al objeto en general
JsonObject objeto;
try {
  InputStream fis = new FileInputStream("jamesBondExample.json");
  JsonReader reader = Json.createReader(fis);
     
  objeto = reader.readObject();
  reader.close();
} catch (Exception e) {
  e.printStackTrace();
  return;
}

// accede al objeto jamesbond
JsonObject jamesbond = objeto.getJsonObject("JamesBond");
// accede al jugador
JsonObject jugador1 = jamesbond.getJsonObject("Jugador1");
// accede las pilas del jugador
JsonArray pilas = jugador1.getJsonArray("pilas");
// recorre las pilas del jugador
for (int i = 0; i < pilas.size(); ++i) {
  JsonObject pila = pilas.getJsonObject(i);
  // accede a las cartas de la pila actual
  JsonArray cartas = pila.getJsonArray("pila");
  // recorre las cartas de la pila
  for (int j = 0; j < cartas.size(); j++) {
      JsonObject carta = (JsonObject) cartas.get(j);
      char palo = carta.getString("palo").charAt(0);
      int numero = carta.getInt("numero");
        System.out.println("palo:" + palo + " numero:" + numero);
        // construirCarta(palo, numero);
      }
    }
  }
}*/