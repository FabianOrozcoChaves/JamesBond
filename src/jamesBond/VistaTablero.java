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
import javafx.scene.layout.Border;
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
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jamesBond.MenuAjustes;
import jamesBond.VentanaPopUp;

/**
 * Clase GUI.
 * Interfaz grafica del tablero.
 */
public class VistaTablero {
  // ventana
  private int anchoVentana = 1000;
  private int alturaVentana = 600;
  
  // cartas
  private VistaCarta [] vistaComunes = new VistaCarta[4];
  private VistaCarta [] vistaPilaJ1 = new VistaCarta[6];
  private VistaCarta [] vistaPilaJ2 = new VistaCarta[6];
  private VistaCarta [] vistaPilaActiva = new VistaCarta[4];
  
  // layouts
  HBox pilaActiva_hbx = new HBox();
  VBox pilaActiva_vbx = new VBox();
  VBox centro = new VBox();
  BorderPane topBar = new BorderPane();
  HBox [] comunes_hbx = new HBox[2];  // cada índice es un par de cartas
  HBox [] pilaJ1_hbx = new HBox[3];   // cada índice es un par de cartas
  HBox [] pilaJ2_hbx = new HBox[3];   // cada índice es un par de cartas
  VBox [] pilas_vbox = new VBox[3];   // 0: comunes(mesa) | 1: pilaJ1 | 2: pilaJ2

  // menuAjustes
  MenuAjustes menuAjustes = new MenuAjustes();
  Scene menuInicio_scene;


  // textos
  Text pilaActiva_txt = new Text("Pila activa");
  Text pilaJ1_txt = new Text();
  Text pilaJ2_txt = new Text();
  Text comunes_txt = new Text();
  Text turno_txt = new Text();

  // Temporizador
  int temporizador = 10;
  int segunderoTurnos = 11;

  // GUI
  private BorderPane tablero = new BorderPane();
  private Scene turnoJuego; 
  Text seconds = new Text();


  // Controlador
  private JamesBond gameJB = new JamesBond(); //simul. borrar luego
  private Jugador jugador1;
  private Jugador jugador2;
  
  Timer timer = new Timer();
  Timer timerGrafico;

  public VistaTablero() {
  }

  /*
   * @brief LLama al controlador para cdonstruir el mazo y repartir las cartas.
   */
  public void construirJuego(String turnoInicial, String nombreJ1, String nombreJ2, Stage mainStage, Scene menuInicio) {
    this.menuInicio_scene = menuInicio;

    gameJB.inicializarTurnos(nombreJ1, nombreJ2, turnoInicial);
    gameJB.repartirCartas();

    // Jugador 1
    this.jugador1 = gameJB.getJugador(1);
    inicializarPilaActiva(this.jugador1);
    pilas_vbox[1] = new VBox();
    pilas_vbox[1].getChildren().add(this.pilaJ1_txt);
    inicializarPilas(this.jugador1, pilaJ1_hbx, vistaPilaJ1, pilas_vbox[1]);

    // Jugador 2
    this.jugador2 = gameJB.getJugador(2);
    pilas_vbox[2] = new VBox();
    pilas_vbox[2].getChildren().add(this.pilaJ2_txt);
    inicializarPilas(this.jugador2, pilaJ2_hbx, vistaPilaJ2, pilas_vbox[2]);

    // inicializa comunes
    Tablero tablero = gameJB.getTablero();
    inicializarCartasComunes(tablero);
    definirTextos(this.jugador1.getNombre(), this.jugador2.getNombre());

    // barra superior
    this.inicializarBarraTop(mainStage);
    
    // construir escenas de cada jugador
    this.construirTablero();
    
    mainStage.setOnCloseRequest(e -> {
      this.timer.cancel();
      this.timer.purge();
      this.timerGrafico.cancel();
      this.timerGrafico.purge();
    });

    mainStage.setScene(this.turnoJuego);

  }

  public void run(Stage mainStage) {
    mostrarTemporizador(seconds);
    if (gameJB.getTurnoActual() == jugador1) {
      construirEscenaJ1();
    } else if (gameJB.getTurnoActual() == jugador2) {
      construirEscenaJ2();
    }
    gameJB.cambiarTurno();

    this.timer.schedule(new TimerTask() {
      Jugador turno = gameJB.getTurnoActual(); 
      @Override
      public void run() {
        temporizador = 10;
        System.out.println("Turno de+ " + turno.getNombre());
        turno = gameJB.getTurnoActual();
        if (turno == jugador1) {
          construirEscenaJ1();
        } else if (turno == jugador2) {
          construirEscenaJ2();
        }
        gameJB.cambiarTurno();
      }
    }, this.segunderoTurnos * 1000, this.segunderoTurnos * 1000);//wait 0 ms before doing the action and do it every 1000ms (1second)
  }



  public void construirTablero() {
    this.turno_txt.setFill(Color.WHITE);
    this. turno_txt.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
    this.turno_txt.setText("Turno de ");

    this.centro.getChildren().addAll(turno_txt, this.pilas_vbox[0]);
    this.centro.setAlignment(Pos.CENTER);
    this.centro.setSpacing(20);

    // setea hbox en border pane
    // pilas_vbox[0] es contiene las cartas comunes
    // pilas_vbox[1] es contiene las pilas del jugador 1
    // pilas_vbox[2] es contiene las pilas del jugador 2
    this.tablero.setCenter(this.centro);
    this.tablero.setBottom(this.pilaActiva_vbx);
    this.tablero.setLeft(this.pilas_vbox[1]);
    this.tablero.setRight(this.pilas_vbox[2]);
    this.tablero.setTop(this.topBar);
    // botnoes pila de jugador 1
    this.vistaPilaJ1[0].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 1 Escogio la pila 0");
    });
    this.vistaPilaJ1[1].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 1 Escogio la pila 1");
    });
    this.vistaPilaJ1[2].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 1 Escogio la pila 2");
    });
    this.vistaPilaJ1[3].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 1 Escogio la pila 3");
    });
    this.vistaPilaJ1[4].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 1 Escogio la pila 4");
    });
    this.vistaPilaJ1[5].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 1 Escogio la pila 5");
    });

    // botnoes pila de jugador 2
    this.vistaPilaJ2[0].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 2 Escogio la pila 0");
    });
    this.vistaPilaJ2[1].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 2 Escogio la pila 1");
    });
    this.vistaPilaJ2[2].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 2 Escogio la pila 2");
    });
    this.vistaPilaJ2[3].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 2 Escogio la pila 3");
    });
    this.vistaPilaJ2[4].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 2 Escogio la pila 4");
    });
    this.vistaPilaJ2[5].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 2 Escogio la pila 5");
    });

    // botones pila comun
    this.vistaComunes[0].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio la carta comun 0");
    });
    this.vistaComunes[1].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio la carta comun 1");
    });
    this.vistaComunes[2].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio la carta comun 2");
    });
    this.vistaComunes[3].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio la carta comun 3");
    });
  
    // botones pila activa
    this.vistaPilaActiva[0].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio de la pila activa la carta 0");
    });
    this.vistaPilaActiva[1].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio de la pila activa la carta 1");
    });
    this.vistaPilaActiva[2].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio de la pila activa la carta 2");
    });
    this.vistaPilaActiva[3].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio de la pila activa la carta 3");
    });

    // set  del color al background
    this.tablero.setBackground(new Background(new BackgroundFill(Color.web("#008000"), new CornerRadii(0), Insets.EMPTY)));

    this.turnoJuego = new Scene(this.tablero, this.anchoVentana, this.alturaVentana);
  }

  public void construirEscenaJ1() {
    this.turno_txt.setText("Turno de " + jugador1.getNombre());

    this.girarCartas(vistaPilaJ2, false);
    this.girarCartas(vistaPilaJ1, true);
    for (int i = 0; i < this.vistaPilaJ2.length; ++i) { 
      this.vistaPilaJ2[i].getImageView().setDisable(true);
    }
    for (int i = 0; i < this.vistaPilaJ1.length; ++i) { 
      this.vistaPilaJ1[i].getImageView().setDisable(false);
    }
  }

  public void construirEscenaJ2() {
    this.turno_txt.setText("Turno de " + jugador2.getNombre());

    this.girarCartas(vistaPilaJ2, true);
    this.girarCartas(vistaPilaJ1, false);
    for (int i = 0; i < this.vistaPilaJ2.length; ++i) { 
      this.vistaPilaJ2[i].getImageView().setDisable(false);
    }
    for (int i = 0; i < this.vistaPilaJ1.length; ++i) { 
      this.vistaPilaJ1[i].getImageView().setDisable(true);
    }
  }

  public void girarCartas(VistaCarta [] cartas, Boolean activar) {
    for (int i = 0; i < cartas.length; ++i) {
      cartas[i].getView(activar);
    }
  }


  public void inicializarBarraTop(Stage mainStage) {
    this.seconds.setFill(Color.WHITE);
    this.seconds.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
    this.seconds.setText("00:" + temporizador);

    Button menu = new Button("Menú");
    
    menu.setOnAction(e -> {
      VentanaPopUp.mostrar(this.menuAjustes);
    });

    this.topBar.setCenter(this.seconds);
    this.topBar.setLeft(menu);
  }

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
    this.pilaActiva_hbx.setAlignment(Pos.BOTTOM_CENTER);
    this.pilaActiva_vbx.getChildren().addAll(this.pilaActiva_txt, pilaActiva_hbx);
    this.pilaActiva_vbx.setAlignment(Pos.BOTTOM_CENTER);

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
    this.timerGrafico = new Timer();
    this.timer.schedule(new TimerTask() {
      @Override
      public void run() {
        if (temporizador == 10) {
          seconds.setText("00:" + temporizador);
        } else {
          seconds.setText("00:0" + temporizador);
        }
        --temporizador;
      }
    }, 1000, 1000);
  }
}
