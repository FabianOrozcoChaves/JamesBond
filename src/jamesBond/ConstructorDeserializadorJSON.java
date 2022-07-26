package jamesBond;

import java.io.BufferedReader;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import marda.*;

public class ConstructorDeserializadorJSON implements ConstructorDeserializadorAbstracto{
  boolean test = false;

  /* DESERIALIZADORES */
  
  /**
   * Método que lee el archivo y carga los nuevos atributos para el jamesBond
   * @param jamesBond juego que representa el estado actual de juego.
   */
  public boolean deserializarJuego(JuegoMarda juego){
    JamesBond jamesBond = (JamesBond)juego;
    // objeto general
    JsonObject objeto;
    // lee archivo
    try {
      FileReader file = new FileReader("jamesBond.json");
      BufferedReader bufferedReader = new BufferedReader(file);
      Gson gson = new Gson();
      objeto = gson.fromJson(bufferedReader, JsonObject.class);
      deserializar(jamesBond, objeto.get("JamesBond").getAsJsonObject());
      file.close();
      return true;
    } catch (Exception e) {
      System.out.println("No se pudo cargar el archivo \"jamesBond.json\". Vuelve a intentarlo.");
    }
    return false;
  }

  /**
   * @brief Método deserializador de ayuda para deserializar se separa y se hace publico para las pruebas.
   * @param jamesBond juego que representa el estado actual de juego.
   * @param objetoJson un objecto Json que contiene el estado en el cual se guardo el juego.
   */
  public void deserializar(JuegoMarda juego, JsonObject objetoJson){
    JamesBond jamesBond = new JamesBond();
    Tablero tablero = deserializarTablero(objetoJson.get("Tablero").getAsJsonArray());
    Jugador jugador2 = (Jugador)deserializarJugador(objetoJson.get("Jugador2").getAsJsonObject());
    Jugador jugador1 = (Jugador)deserializarJugador(objetoJson.get("Jugador1").getAsJsonObject());
    Jugador turnoActual = jugador1.getNombre().equals(objetoJson.get("turnoActual").getAsString()) ? jugador1 : jugador2;
    int temporizador = objetoJson.get("temporizador").getAsInt();
    jamesBond.cargarEstado(jugador1, jugador2, turnoActual, temporizador, tablero);
  }

  /**
   * @brief Método deserializador. Se encarga de cargar los valores del tablero, restaurar el estado del Tablero en el que se guardo.
   * @param Cartas Texto en formato json que representa las cartas en comunes que posee el tablero.
   * @return Tablero con el estado en el que se guardo.
   */
  public Tablero deserializarTablero(JsonArray cartas){
    Tablero tablero = new Tablero();
    tablero.quitarCartas();
    for (int i = 0; i < cartas.size(); i++) {
      JsonObject carta = cartas.get(i).getAsJsonObject();
      tablero.agregarCarta(deserializarCarta(carta));
    }
    return tablero;
  }
  
   /**
   * @brief Método deserializador. Se encarga de cargar los valores de un Jugador, crear el Jugador y retornarlo como objeto Jugador.
   * @param jugador Texto en formato json que representa los atributos del Jugador.
   * @return Jugador con los atributos cargados
   */
  public JugadorMarda deserializarJugador(JsonObject jugador){
    String nombre = jugador.get("nombre").getAsString();
    Jugador temp = new Jugador(nombre);
    JsonArray pilas = jugador.getAsJsonArray("pilas");

    for (int i = 0; i < temp.getGrupoDeCartas(0).getCartas().size(); i++) {
      JsonObject pilaObject = pilas.get(i).getAsJsonObject();
      JsonArray pilaArray = pilaObject.getAsJsonArray("pila");
      temp.agregarPila(deserializarGrupoDeCartasMarda(pilaArray));
    }

    return temp;
  }
  // TODO Documentación
  /**
   * @brief Método deserializador. Se encarga de cargar los valores de una pila, restaurar el estado en el que se guardo.
   * @param Cartas Texto en formato json que representa las cartas que se encuentra la pila.
   * @return Pila con el estado en el que se guardo.
   */
  public GrupoDeCartasMarda deserializarGrupoDeCartasMarda(JsonArray cartas){
    GrupoDeCartasMarda grupoDeCartas = new GrupoDeCartasMarda();
    for (int i = 0; i < cartas.size(); i++) {
      JsonObject carta = cartas.get(i).getAsJsonObject();
      grupoDeCartas.agregarCarta(deserializarCarta(carta));
    }
    return grupoDeCartas;
  }

  /**
   * @brief Método deserializador. Se encarga de cargar los valores de una carta, crear la carta y retornarla como objeto Carta.
   * @param carta Texto en formato json que representa los atributos de la carta.
   * @return Carta con los atributos cargados
   */
  public Carta deserializarCarta(JsonObject carta) {
    char palo = carta.get("palo").getAsCharacter();
    int numero = carta.get("numero").getAsInt();
    if(!test){
      return new Carta(palo, numero); 
    }
    return new Carta(numero, palo);
  }

  public void setTest(boolean test){
    this.test = test;
  }
}