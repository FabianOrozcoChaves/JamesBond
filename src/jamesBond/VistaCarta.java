package jamesBond;
import jamesBond.Carta;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VistaCarta {
  private Image imagenCarta;
  private ImageView imagenVistaCarta;
  
  public VistaCarta (Carta carta) {
    this.imagenCarta = carta.getImagen();
    this.imagenVistaCarta = new ImageView();
    this.imagenVistaCarta.setPreserveRatio(true);
    this.imagenVistaCarta.setFitHeight(100);
    this.imagenVistaCarta.setFitWidth(100);
  }
  // TODO agregar rol wrapper en UML
  public ImageView getView(boolean activa) {
    if (activa) {
      this.imagenVistaCarta.setImage(imagenCarta);
    } else {
      Image back = new Image(getClass().getResourceAsStream("./img/back.png"));
      this.imagenVistaCarta.setImage(back);
    }
    return this.imagenVistaCarta;
  }
}
