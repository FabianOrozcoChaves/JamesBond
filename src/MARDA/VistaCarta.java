package marda;

import jamesBond.Carta;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Clase que representa la vista de una carta.
 */
public class VistaCarta {
  private Image imagenCarta;  // ruta de la imagen respectiva a la carta
  private ImageView imagenVistaCarta;  // vista de la imagen respectiva de la carta
  
  /**
   * Método constructor de la clase.
   * @param carta Carta que se quiere adaptar.
   */
  public VistaCarta (Carta carta) {
    this.imagenCarta = carta.getImagen();
    this.imagenVistaCarta = new ImageView();
    this.imagenVistaCarta.setPreserveRatio(true);
    this.imagenVistaCarta.setFitHeight(100);
    this.imagenVistaCarta.setFitWidth(100);
  }

  /**
   * Método get. Retorna la vista de la imagen de la carta.
   * @param activa Booleano que representa si la carta debe mostrarse boca arriba o boca abajo.
   * @return Vista de la imagen de la carta boca arriba en caso que <activa> sea True, vista de la imagen de la carta boca abajo en caso contrario.
   */
  public ImageView getView(boolean activa) {
    if (activa) {
      this.imagenVistaCarta.setImage(imagenCarta);
    } else {
      Image back = new Image(getClass().getResourceAsStream("/img/back.png"));
      this.imagenVistaCarta.setImage(back);
    }
    return this.imagenVistaCarta;
  }

  /**
   * Método get. Retorna la vista de la imagen de la carta.
   * @return Vista de la imagen de la carta boca arriba.
   */
  public ImageView getImageView() {
    return this.imagenVistaCarta;
  }

  //
   /**
   * @brief Resalta la carta aumentando su tamaño
   */
  public void resaltarCarta() {
    this.imagenVistaCarta.setFitHeight(115);
    this.imagenVistaCarta.setFitWidth(115);
  }

  /**
   * @brief Regresa la carta a su tamaño normal
   */
  public void normalizarCarta() {
    this.imagenVistaCarta.setFitHeight(100);
    this.imagenVistaCarta.setFitWidth(100);
  }
}
