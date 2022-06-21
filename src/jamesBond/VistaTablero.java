package jamesBond;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Clase GUI.
 * Interfaz grafica
 */
public class VistaTablero extends Application {
  // ventana
  private int anchoVentana = 1000;
  private int alturaVentana = 600;

  // cartas
  private String [] direcciones_comunes = new String[4];        // no util para el final
  private Image [] imagenes_comunes = new Image[4];             // 
  private ImageView [] vista_img_comunes = new ImageView[4];
  BorderPane tablero_bp = new BorderPane();
  HBox [] par_comun = new HBox[2];
  VBox comunes = new VBox();

  // GUI
  private Stage tablero_stg;
  private Scene tablero_scn;

  public void inicializarCartasComunes() {
    String palo = "C";
    int numero = 1;

    for (int i = 0; i < this.direcciones_comunes.length; i++) {
      this.direcciones_comunes[i] = "./img/" + palo + "_" + numero++ + ".png";

      this.imagenes_comunes[i] = new Image(getClass().getResourceAsStream(this.direcciones_comunes[i]));

      vista_img_comunes[i] = new ImageView();

      vista_img_comunes[i].setImage(this.imagenes_comunes[i]);
      vista_img_comunes[i].setPreserveRatio(true);
      vista_img_comunes[i].setFitHeight(100);
      vista_img_comunes[i].setFitWidth(100);
    }
  }

  private void tableroHboxComunes(int hboxIndex, int indexVistaComunes1, int indexVistaComunes2) {
    this.par_comun[hboxIndex] = new HBox();
    this.par_comun[hboxIndex].getChildren().addAll(vista_img_comunes[indexVistaComunes1], vista_img_comunes[indexVistaComunes2]);
    this.par_comun[hboxIndex].setSpacing(20);
    this.par_comun[hboxIndex].setAlignment(Pos.CENTER);
  }
  public void start(Stage tablero_stg) {
    
    // ventana
    this.tablero_stg = tablero_stg;
    this.tablero_stg.setTitle("Tablero");
    
    // llena cartas comunes
    inicializarCartasComunes();

    // imagen carta
    tableroHboxComunes(0, 0, 1);
    tableroHboxComunes(1, 2, 3);

    comunes.setAlignment(Pos.CENTER);
    comunes.getChildren().addAll(par_comun);
    comunes.setSpacing(20);
    // tablero_scn
    crearTablero();
    this.tablero_stg.show();
  }
  
  public void crearTablero() {
    this.tablero_bp.setCenter(comunes);
    this.tablero_scn = new Scene(tablero_bp, this.anchoVentana, this.alturaVentana);
    this.tablero_scn.setFill(Color.web("#008000"));
    this.tablero_stg.setScene(this.tablero_scn);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
