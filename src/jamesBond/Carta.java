package jamesBond;


public class Carta {
  private char palo;
  private int numero;
  private String imagen;

  public Carta() {
    this.palo = ' ';
    this.numero = 0;
    this.imagen = new String("../img/back.png");
  }

  public Carta(char palo, int numero, String imagen) {
    this.palo = palo;
    this.numero = numero;
    this.imagen = imagen;
  }

  public Carta(Carta otraCarta) {
    this.palo = otraCarta.palo;
    this.numero = otraCarta.numero;
    this.imagen = otraCarta.imagen;
  }

  public int getNumero() {
    return this.numero;
  }

  public int getPalo() {
    return this.palo;
  }

  public String toString() {
    String resultado = "";
    resultado += this.convertirNumeroAString() + " de " + this.palo;
    return resultado;
  }

  private String convertirNumeroAString() {
    String resultado = "";
    switch(this.numero){
      case 11:
        resultado += 'J';
      break;
      case 12:
        resultado += 'Q';
      break;
      case 13:
        resultado += 'K';
      break;
      case 1:
        resultado += 'A';
      break;
      default:
        resultado += this.numero;
    }
    return resultado;
  }
}