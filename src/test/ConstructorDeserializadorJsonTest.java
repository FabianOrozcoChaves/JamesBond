package test;

import jamesBond.JamesBond;
import jamesBond.Jugador;
import jamesBond.Carta;
import jamesBond.ConstructorDeserializadorJSON;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConstructorDeserializadorJsonTest {
  @Test
  public void deserializarJamesBond() {
    JamesBond controlador = new JamesBond();
    JsonObject jsonObjeto = new JsonParser().parse(JsonGenerator.generateJson()).getAsJsonObject();
    ConstructorDeserializadorJSON deserializador = new ConstructorDeserializadorJSON();
    deserializador.setTest(true);
    deserializador.deserializar(controlador, jsonObjeto.get("JamesBond").getAsJsonObject());
    Jugador jugador1 =controlador.getJugador(1);
    Jugador jugador2 =controlador.getJugador(2);
    Assert.assertEquals(jugador1.getNombre(), "Luis");
    Assert.assertEquals(jugador2.getNombre(), "Juan");
    // primera carta del tablero
    Carta carta = controlador.getTablero().getCartas().get(0);
    Assert.assertEquals(carta.getPalo(), 'T');
    Assert.assertEquals(carta.getNumero(), 10);
    // ultima carta del tablero
    carta = controlador.getTablero().getCartas().get(3);
    Assert.assertEquals(carta.getPalo(), 'T');
    Assert.assertEquals(carta.getNumero(), 13);
    // primera carta del jugador 1
    carta = jugador1.getPila(0).getCarta(0);
    Assert.assertEquals(carta.getPalo(), 'P');
    Assert.assertEquals(carta.getNumero(), 1);
    // ultima carta del jugador 1
    carta = jugador1.getPila(5).getCarta(3);
    Assert.assertEquals(carta.getPalo(), 'T');
    Assert.assertEquals(carta.getNumero(), 8);
    // primera carta del jugador 2
    carta = jugador2.getPila(0).getCarta(0);
    Assert.assertEquals(carta.getPalo(), 'P');
    Assert.assertEquals(carta.getNumero(), 2);
    // ultima carta del jugador 2
    carta = jugador2.getPila(5).getCarta(3);
    Assert.assertEquals(carta.getPalo(), 'T');
    Assert.assertEquals(carta.getNumero(), 9);
  }
}
