package jamesBond;

import javafx.scene.image.Image;

public class Carta {
  private char palo;
  private int numero;
  private Image imagen;
  private String ruta;

  public Carta() {
    this(' ', 0, "./img/back.png");
  }

  public Carta(char palo, int numero, String rutaImagen) {
    this.palo = palo;
    this.numero = numero;
    this.ruta = rutaImagen;
    this.imagen = new Image(getClass().getResourceAsStream(rutaImagen));
  }

  public int getNumero() {
    return this.numero;
  }

  public Image getImagen() {
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