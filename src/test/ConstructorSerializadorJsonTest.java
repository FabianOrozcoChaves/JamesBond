package test;

import jamesBond.JamesBond;
import jamesBond.ConstructorSerializadorJSON;

import org.junit.Assert;
import org.junit.Test;

public class ConstructorSerializadorJsonTest {
  @Test
  public void serializarJamesBond() {
    JamesBond controlador = new JamesBond();
    controlador.inicializarTurnos("Luis", "Juan", "Luis");
    controlador.repartirCartasSinBarajar();
    ConstructorSerializadorJSON serializador = new ConstructorSerializadorJSON();
    serializador.serializarJamesBond(controlador);
    String json = serializador.obtenerSerialización();
    Assert.assertEquals(json, JsonGenerator.generateJson());
  }
}
