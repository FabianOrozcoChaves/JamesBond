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
 * Interfaz grafica del tablero.
 */
public class Prueba extends Application {
  // ventana
  private int anchoVentana = 1000;
  private int alturaVentana = 600;

  // cartas
  BorderPane tablero_bp = new BorderPane();

  // GUI
  private Stage tablero_stg;
  private Scene tablero_scn;

  public void start(Stage tablero_stg) {
    // ventana
    this.tablero_stg = tablero_stg;
    this.tablero_stg.setTitle("Tablero");
    
    // llena cartas comunes
    Mazo mazo = Mazo.getInstance();
    mazo.generaCartas();
    Carta c1 = mazo.getCarta();
    Carta c2 = mazo.getCarta();

    VistaCarta carta_vista_1 = new VistaCarta(c1);
    VistaCarta carta_vista_2 = new VistaCarta(c2);

    ImageView c_1 = carta_vista_1.getView(true);
    ImageView c_2 = carta_vista_2.getView(false);

    HBox par = new HBox();
    par.getChildren().addAll(c_1, c_2);
    par.setSpacing(20);
    par.setAlignment(Pos.CENTER);

    // tablero_scn
    this.tablero_bp.setCenter(par);
    this.tablero_scn = new Scene(tablero_bp, this.anchoVentana, this.alturaVentana);
    this.tablero_scn.setFill(Color.web("#008000"));
    this.tablero_stg.setScene(this.tablero_scn);
    this.tablero_stg.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
