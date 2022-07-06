package test;

import jamesBond.JamesBond;
import jamesBond.ConstructorSerializadorJSON;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

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
