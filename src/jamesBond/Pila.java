package jamesBond;
import jamesBond.Carta;

public class Pila {

  public boolean isEmpty() {
    return cartas.size() == 0;
  }

  public Carta getCarta(int posicion) {
    return this.cartas.elementAt(posicion);
  }
  public static void main(String[] args) {
    
  }
}