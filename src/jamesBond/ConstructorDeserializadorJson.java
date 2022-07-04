package jamesBond;

import java.io.FileReader;
import org.json.simple.JSONObject;

public class ConstructorDeserializadorJson implements ConstructorDeserializadorAbstracto{
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
   * Método serializador. Se encarga de extraer los atributos del objeto Carta y representarlos debidamente en el objeto complejo, ya sea en formato json, xml u otro.
   * @param carta Objeto Carta del que se extraerán sus atributos para guardarlos en el objeto compuesto.
   */
  public void deserializarCarta(JSONObject carta){
    
  }
    
}
