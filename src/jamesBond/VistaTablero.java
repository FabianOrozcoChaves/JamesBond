package jamesBond;
import java.util.Timer;
import java.util.TimerTask;

import jamesBond.JamesBond;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
  int timerCount = 3;
  Button button;

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
    this.comunes_vbx[0] = new VBox();

    inicializarComun(comunes_hbx, vistaComunes, this.comunes_vbx[0]);
  }

  public void inicializarPilas(Jugador jugador, HBox [] pila_hbx, VistaCarta [] vistaPila, VBox vbx) {
    // vista pila
    for (int indexCarta = 0; indexCarta < vistaPila.length; indexCarta++) {
      vistaPila[indexCarta] = new VistaCarta(jugador.getPila(indexCarta).getCarta(0));
    }

    inicializarComun(pila_hbx, vistaPila, vbx);
  }

  public void inicializarComun(HBox [] pila_hbx, VistaCarta [] vistaPila, VBox vbx) {

    // hbox pila
    for(int i = 0; i < pila_hbx.length; i++){
      pila_hbx[i] = new HBox();
      int index = i*2;
      pila_hbx[i].getChildren().addAll(vistaPila[index++].getView(true), vistaPila[index].getView(true));
      pila_hbx[i].setSpacing(20);
      pila_hbx[i].setAlignment(Pos.CENTER);
    }

    // vbox pila
    vbx.setAlignment(Pos.CENTER);
    for(int i = 0; i < pila_hbx.length; i++){
      vbx.getChildren().add(pila_hbx[i]);
    }
    vbx.setSpacing(20);
  }

  public void start(Stage tablero_stg) {
    // crea contexto (GUI)
    gameJB.inicializarTurnos("Luis","Juan","Luis");
    gameJB.repartirCartas();
    Jugador j1 = gameJB.getJugador(1);
    inicializarPilaActiva(j1);
    comunes_vbx[1] = new VBox();
    inicializarPilas(j1, pilaJ1_hbx, vistaPilaJ1, comunes_vbx[1]);
    Jugador j2 = gameJB.getJugador(2);
    comunes_vbx[2] = new VBox();
    inicializarPilas(j2, pilaJ2_hbx, vistaPilaJ2, comunes_vbx[2]);

    button = new Button();
    button.setText("timer: " + timerCount);
    button.setOnAction(event -> {
      Timer tm = new Timer();
      TimerTask task = new TimerTask() {
        public void run(){
          Platform.runLater(new Runnable() {
            @Override
            public void run() {
              button.setText("timer: " + timerCount);
            }
          });
          System.out.println("timer: " + timerCount);
          timerCount--;
          if(timerCount == 0){
              timerCount = 3;
              tm.cancel();
              tm.purge();
            }
          }
        };
        tm.scheduleAtFixedRate(task, 1000,1000);
    });
    HBox timer_hbx = new HBox();
    timer_hbx.getChildren().add(button);
    timer_hbx.setSpacing(20);
    timer_hbx.setAlignment(Pos.CENTER);
    
    

    Tablero tablero = gameJB.getTablero();

    // ventana
    this.tablero_stg = tablero_stg;
    this.tablero_stg.setTitle("Tablero");

    // inicializa comunes
    inicializarCartasComunes(tablero);

    // setea hbox en border pane
    // comunes_vbx[0] es contiene las cartas comunes
    // comunes_vbx[1] es contiene las pilas del jugador 1
    // comunes_vbx[2] es contiene las pilas del jugador 2
    this.tablero_bp.setCenter(this.comunes_vbx[0]);
    this.tablero_bp.setBottom(this.pilaActiva_hbx);
    this.tablero_bp.setRight(this.comunes_vbx[1]);
    this.tablero_bp.setLeft(this.comunes_vbx[2]);
    this.tablero_bp.setTop(timer_hbx);

    
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
