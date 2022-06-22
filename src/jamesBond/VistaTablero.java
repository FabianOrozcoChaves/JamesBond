package jamesBond;
import java.util.Timer;
import java.util.TimerTask;

import jamesBond.JamesBond;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
  HBox [] comunes_hbx = new HBox[2];  // cada índice es un par de cartas
  HBox [] pilaJ1_hbx = new HBox[3];   // cada índice es un par de cartas
  HBox [] pilaJ2_hbx = new HBox[3];   // cada índice es un par de cartas
  VBox [] pilas_vbox = new VBox[3];   // 0: comunes(mesa) | 1: pilaJ1 | 2: pilaJ2

  Text pilaActiva_txt = new Text("Pila activa");
  Text pilaJ1_txt = new Text();
  Text pilaJ2_txt = new Text();
  Text comunes_txt = new Text();

  // Temporizador
  int temporizador = 3;
  final int SEGUNDOS = 10;
  int segunderoTurnos = 10;

  // GUI
  private Stage tablero_stg;
  private Scene tablero_scn;

  private JamesBond gameJB = new JamesBond(); //simul. borrar luego


  public void definirTextos(String jugador1, String jugador2) {
    pilaActiva_txt.setFill(Color.WHITE);
    pilaActiva_txt.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));

    comunes_txt.setFill(Color.WHITE);
    comunes_txt.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));

    pilaJ1_txt.setFill(Color.WHITE);
    pilaJ1_txt.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
    pilaJ1_txt.setText("Pila de " + jugador1);

    pilaJ2_txt.setFill(Color.WHITE);
    pilaJ2_txt.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
    pilaJ2_txt.setText("Pila de " + jugador2);
  }

  // métodos
  public void inicializarPilaActiva(Jugador jugador) {
    Pila pilaActiva = jugador.pilaActiva();
    pilaActiva_hbx = new HBox();
    pilaActiva_hbx.getChildren().add(this.pilaActiva_txt);

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
    this.pilas_vbox[0] = new VBox();
    inicializarComun(comunes_hbx, vistaComunes, this.pilas_vbox[0]);
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


  public void mostrarTemporizador(Text seconds) {
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
      public void run(){
        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            seconds.setText("00:0" + temporizador);
          }
        });
        System.out.println("timer: " + temporizador);
        temporizador--;
        if(temporizador == 0){
            temporizador = SEGUNDOS;
            timer.cancel();
            timer.purge();
          }
        }
      };
      timer.scheduleAtFixedRate(task, 0,1000);
  }

  public void start(Stage tablero_stg) {
    // crea contexto (GUI)
    gameJB.inicializarTurnos("Luis","Juan","Luis");
    gameJB.repartirCartas();
    Jugador j1 = gameJB.getJugador(1);
    inicializarPilaActiva(j1);
    pilas_vbox[1] = new VBox();
    pilas_vbox[1].getChildren().add(this.pilaJ1_txt);
    inicializarPilas(j1, pilaJ1_hbx, vistaPilaJ1, pilas_vbox[1]);
    Jugador j2 = gameJB.getJugador(2);
    pilas_vbox[2] = new VBox();
    pilas_vbox[2].getChildren().add(this.pilaJ2_txt);
    inicializarPilas(j2, pilaJ2_hbx, vistaPilaJ2, pilas_vbox[2]);

    Tablero tablero = gameJB.getTablero();

    // ventana
    this.tablero_stg = tablero_stg;
    this.tablero_stg.setTitle("Tablero");

    // inicializa comunes
    inicializarCartasComunes(tablero);


    // setea hbox en border pane
    // pilas_vbox[0] es contiene las cartas comunes
    // pilas_vbox[1] es contiene las pilas del jugador 1
    // pilas_vbox[2] es contiene las pilas del jugador 2
    this.tablero_bp.setCenter(this.pilas_vbox[0]);
    this.tablero_bp.setBottom(this.pilaActiva_hbx);
    this.tablero_bp.setRight(this.pilas_vbox[1]);
    this.tablero_bp.setLeft(this.pilas_vbox[2]);

    // escena y stage
    this.tablero_scn = new Scene(this.tablero_bp, this.anchoVentana, this.alturaVentana);
    // setea el color al background
    this.tablero_bp.setBackground(new Background(new BackgroundFill(Color.web("#008000"), new CornerRadii(0), Insets.EMPTY)));
    this.tablero_stg.setScene(this.tablero_scn);
    this.tablero_stg.show();

    // Temporizador
    Text seconds = new Text();
    seconds.setFill(Color.WHITE);
    seconds.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
    seconds.setText("00:0" + temporizador);
    
    HBox timer_hbx = new HBox();
    timer_hbx.getChildren().addAll(seconds);
    timer_hbx.setSpacing(5);
    timer_hbx.setAlignment(Pos.CENTER);
    this.tablero_bp.setTop(timer_hbx);

    definirTextos(j1.getNombre(), j2.getNombre());

    mostrarTemporizador(seconds);
  }

  public Scene getTablero_scn() {
    return this.tablero_scn;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
