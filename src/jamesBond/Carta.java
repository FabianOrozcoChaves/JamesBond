package jamesBond;


public class Carta {
  private char palo;
  private int numero;
  private String imagen;

  public Carta() {
    this(' ', 0, "../img/back.png");
  }

  public Carta(char palo, int numero, String imagen) {
    this.palo = palo;
    this.numero = numero;
    this.imagen = imagen;
  }

  public int getNumero() {
    return this.numero;
  }

  public String getImagen() {
    return this.imagen;
  }

  public char getPalo() {
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