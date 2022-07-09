package jamesBond;

import javafx.scene.image.Image;
/**
 * @class Carta permite represnetar una carta de una mazo.
 * @details los numeros del 11 al 13 corresponde a la J Q y K. El numero 1 es el As.
 */
public class Carta {
  private char palo; // Trebol (t), Pica (p), Diamante (d) o Corazon (c). 
  private int numero; // val del 1 al 13.  del 11 al 13 corresponde a la J Q y K. El numero 1 es el As.
  private Image imagen; // ruta de la imagen que utilza el GUI para mostrar una carta.

  /** 
   * @brief Constructor por parametros.
   * @param palo char con el palod e la carta. Trebol (t), Pica (p), Diamante (d) o Corazon (c). 
   * @param numero es un entero con el nuemro de la carta.
   */
  public Carta(char palo, int numero) {
    this.palo = palo;
    this.numero = numero;
    cargarRuta(); // carga la ruta de la imagen de la carta
  }

  /** 
   * @brief Constructor por parametros no carga la imagen se usa para los unit test.
   * @param numero es un entero con el nuemro de la carta.
   * @param palo char con el palod e la carta. Trebol (t), Pica (p), Diamante (d) o Corazon (c). 
   */
  public Carta(int numero, char palo) {
    this.palo = palo;
    this.numero = numero;
  }

  /**
   * @brief carga el string con la ruta de donde se encuentra la imagen de la carta.
   */
  private void cargarRuta() {
    this.imagen = new Image(getClass().getResourceAsStream("/img/" + this.palo + "_" + this.numero + ".png"));
  }

  /**
   * @brief Metodo para acceder al numero de la carta.
   * @return el entero del numero de la carta.
   */
  public int getNumero() {
    return this.numero;
  }


  /**
   * @brief Metodo para acceder a la imagen de la carta.
   * @return la imagen de la carta.
   */
  public Image getImagen() {
    return this.imagen;
  }

  /**
   * @brief Metodo para acceder al palo de la carta.
   * @return un char con el palo de la carta.
   */
  public char getPalo() {
    return this.palo;
  }

  /**
   * @brief Metodo para imprimir un carta y hacer prueas.
   * @return un string que representa la carta a nivel de terminal.
   */
  public String toString() {
    String resultado = "";
    resultado += this.convertirNumeroAString() + " de " + this.palo;
    return resultado;
  }

  /**
   * @brief Convierte un numeor a su respectiva representacion en char segun la carta.
   * @details este metodo es utilizado para el toString de testing.
   * @return String con el numeor o letra segun la carta.
   */
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