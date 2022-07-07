package jamesBond;

import java.io.BufferedReader;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ConstructorDeserializadorJSON implements ConstructorDeserializadorAbstracto{

  /* DESERIALIZADORES */
  
  /**
   * Método que lee el archivo y carga los nuevos atributos para el jamesBond
   * @param jamesBond juego que representa el estado actual de juego.
   */
  public void deserializarJamesBond(JamesBond jamesBond){
    // objeto general
    JsonObject objeto;
    // lee archivo
    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader("jamesBond.json"));
      Gson gson = new Gson();
      objeto = gson.fromJson(bufferedReader, JsonObject.class);
      deserializar(jamesBond, objeto);
    } catch (Exception e) {
      System.out.println("No se pudo cargar el archivo \"jamesBond.json\". Vuelve a intentarlo.");
    }
  }

  public void deserializar(JamesBond jamesBond, JsonObject objetoJson){
    Tablero tablero = deserializarTablero(objetoJson.get("Tablero").getAsJsonArray());
    Jugador jugador1 = deserializarJugador(objetoJson.get("Jugador1").getAsJsonObject());
    Jugador jugador2 = deserializarJugador(objetoJson.get("Jugador2").getAsJsonObject());
    Jugador turnoActual = jugador1.getNombre().equals(objetoJson.get("turnoActual").getAsString()) ? jugador1 : jugador2;
    int temporizador = objetoJson.get("temporizador").getAsInt();
    jamesBond.cargarEstado(jugador1, jugador2, turnoActual, temporizador, tablero);
  }

  /**
   * // TODO completar documentación.
   * @param tablero
   */
  public Tablero deserializarTablero(JsonArray cartas){
    Tablero tablero = Tablero.getInstance();
    tablero.quitarCartas();
    for (int i = 0; i < cartas.size(); i++) {
      JsonObject carta = cartas.get(i).getAsJsonObject();
      tablero.agregarCarta(deserializarCarta(carta));
    }
    return tablero;
  }
  
  /**
   * // TODO completar documentación.
   * @param jugador
   */
  public Jugador deserializarJugador(JsonObject jugador){
    Jugador temp = new Jugador();
    return temp;
  }

  /**
   * // TODO completar documentación.
   * @param pila
   */
  public Pila deserializarPila(JsonArray cartas){
    Pila pila = new Pila(cartas.size());
    for (int i = 0; i < cartas.size(); i++) {
      JsonObject carta = cartas.get(i).getAsJsonObject();
      pila.agregarCarta(deserializarCarta(carta));
    }
    return pila;
  }

  /**
   * @brief Método deserializador. Se encarga de cargar los valores de una carta, crear la carta y retornarla como objeto Carta.
   * @param carta Texto en formato json que representa los atributos de la carta.
   * @return Carta con los atributos cargados
   */
  public Carta deserializarCarta(JsonObject carta) {
    char palo = carta.get("palo").getAsCharacter();
    int numero = carta.get("numero").getAsInt();
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
      JsonObject carta = cartas.getJsonObject(j);
      char palo = carta.getString("palo").charAt(0);
      int numero = carta.getInt("numero");
        System.out.println("palo:" + palo + " numero:" + numero);
        // construirCarta(palo, numero);
      }
    }
  }
}*/