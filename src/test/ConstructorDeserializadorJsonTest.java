package test;

import jamesBond.JamesBond;
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
    deserializador.deserializar(controlador, jsonObjeto);
    Assert.assertEquals(controlador.getJugador(1), "Luis");
    Assert.assertEquals(controlador.getJugador(2), "Juan");
  }
}
