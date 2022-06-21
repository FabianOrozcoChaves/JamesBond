package jamesBond;

import java.util.Vector;
import java.util.Collections;

/**
 * Clase Mazo.
 * Representa el mazo de donde se van a distribuir las cartas.
 * Crea las cartas del juego.
 */
public class Mazo {
  private static Mazo mazo;
  private Vector<Carta> cartas;

  /**
   * @brief Constructor privado de la clase.
   */
  private Mazo() {
    this.cartas = new Vector<Carta>();
  }

  /**
   * @brief Se usa para poder instanciar la clase ya que es un singletoon
   * @return la unica instancia de la clase.
   */
  public static Mazo getInstance() {
	if(mazo == null){
	  mazo =  new Mazo();
	}
	return mazo;
   }
   
   /**
   * @brief genera todas las cartas del mazo
   */
  public void generaCartas() {
    cartas.clear();
	  char[] palos = {'P', 'D', 'C', 'T'};
    for(char c:palos){
	    for(int j = 1; j < 14; j++){
	      StringBuilder sb = new StringBuilder (String.valueOf ("../img/carta"));
	      sb.append (c);
		    sb.append ('_');
	      sb.append (j);
		    sb.append (".png");
	      this.cartas.add(new Carta(c, j, sb.toString ()));
	    }
	  }
  }

  /**
   * @brief mezcla las cartas del mazo
   */
  public void barajas() {
	  Collections.shuffle(this.cartas);
  }
  
  /**
   * @brief Elimina y retorna la primera carta del mazo
   * @return La primera carta del mazo
   */
  public Carta getCarta() {
	  if(this.cartas.size() < 1){
		  return null;
	  }
    return this.cartas.remove(0);
  }
  
  /**
   * @brief Combierte el maso a un string con las cartas que contiene
   * @return Un string que contiene el contenido del mazo
   */
  public String toString(){
	String resultado = "El mazo contiene: ";
    for (Carta carta : this.cartas) {
  	  resultado += carta.toString() + " ";
  	}
  	return resultado;
  }
  
  public int getCartasRestantes(){
	  return this.cartas.size();
  }
}
