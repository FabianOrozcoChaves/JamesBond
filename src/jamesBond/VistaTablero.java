package jamesBond;
import jamesBond.JamesBond;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
public class VistaTablero extends Application {
  // ventana
  private int anchoVentana = 1000;
  private int alturaVentana = 600;

  // cartas
  private VistaCarta [] vistaComunes = new VistaCarta[4];
  private VistaCarta [] vistaPilaJ1 = new VistaCarta[6];
  private VistaCarta [] vistaPilaJ2 = new VistaCarta[6];
  private VistaCarta [] vistaPilaActiva = new VistaCarta[4];
  
  BorderPane tablero_bp = new BorderPane();

  HBox pilaActiva_hbx = new HBox();
  HBox [] comunes_hbx = new HBox[2];
  HBox [] pilaJ1_hbx = new HBox[3];
  HBox [] pilaJ2_hbx = new HBox[3];
  VBox [] comunes_vbx = new VBox[3];

  // GUI
  private Stage tablero_stg;
  private Scene tablero_scn;

  private JamesBond gameJB = new JamesBond(); //simul. borrar luego

  // métodos
  public void inicializarPilaActiva(Jugador jugador) {
    Pila pilaActiva = jugador.pilaActiva();
    pilaActiva_hbx = new HBox();
    
    // vistas comunes
    for (int indexCarta = 0; indexCarta < vistaPilaActiva.length; indexCarta++) {
      vistaPilaActiva[indexCarta] = new VistaCarta(pilaActiva.getCarta(indexCarta));
      pilaActiva_hbx.getChildren().add(vistaPilaActiva[indexCarta].getView(true));
    }
    // hbox comunes
    pilaActiva_hbx.setSpacing(20);
    pilaActiva_hbx.setAlignment(Pos.BOTTOM_CENTER);
  }


  public void inicializarCartasComunes(Tablero tablero) {
    // vistas comunes
    for (int indexCarta = 0; indexCarta < vistaComunes.length; indexCarta++) {
      vistaComunes[indexCarta] = new VistaCarta(tablero.getCarta(indexCarta));
    }

    // hbox comunes
    comunes_hbx[0] = new HBox();
    comunes_hbx[0].getChildren().addAll(vistaComunes[0].getView(true), vistaComunes[1].getView(true));
    comunes_hbx[0].setSpacing(20);
    comunes_hbx[0].setAlignment(Pos.CENTER);

    comunes_hbx[1] = new HBox();
    comunes_hbx[1].getChildren().addAll(vistaComunes[2].getView(true), vistaComunes[3].getView(true));
    comunes_hbx[1].setSpacing(20);
    comunes_hbx[1].setAlignment(Pos.CENTER);

    // vbox comunes
    this.comunes_vbx[0] = new VBox();
    this.comunes_vbx[0].setAlignment(Pos.CENTER);
    this.comunes_vbx[0].getChildren().addAll(this.comunes_hbx[0], this.comunes_hbx[1]);
    this.comunes_vbx[0].setSpacing(20);
  }

  public void start(Stage tablero_stg) {
    // crea contexto (GUI)
    gameJB.inicializarTurnos("Luis","Juan","Luis");
    gameJB.repartirCartas();
    Jugador j1 = gameJB.getJugador(1);
    inicializarPilaActiva(j1);

    Tablero tablero = gameJB.getTablero();

    // ventana
    this.tablero_stg = tablero_stg;
    this.tablero_stg.setTitle("Tablero");

    // inicializa comunes
    inicializarCartasComunes(tablero);

    // setea hbox en border pane
    this.tablero_bp.setCenter(this.comunes_vbx[0]);
    this.tablero_bp.setBottom(this.pilaActiva_hbx);
    
    // escena y stage
    this.tablero_scn = new Scene(this.tablero_bp, this.anchoVentana, this.alturaVentana);
    this.tablero_scn.setFill(Color.web("#008000"));
    this.tablero_stg.setScene(this.tablero_scn);
    this.tablero_stg.show();
  }

  public Scene getTablero_scn() {
    return this.tablero_scn;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
