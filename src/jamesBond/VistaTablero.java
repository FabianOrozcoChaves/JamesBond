package jamesBond;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Clase gráfica del tablero.
 * Maneja la posición de las pilas y cartas comunes. Así como los nombres para cada una, los botones de menú de ajustes, el de jamesbond y el temporizador.
 */
public class VistaTablero {
  // ventana
  private final int anchoVentana = 1000;
  private final int alturaVentana = 600;
  
  // cartas
  private VistaCarta [] vistaComunes;   // Vista de las cartas comunes
  private VistaCarta [] vistaPilaJ1;   // Vista de la pila del jugador 1
  private VistaCarta [] vistaPilaJ2;   // Vista de la pila del jugador 1
  private VistaCarta [] vistaPilaActiva;  // Vista de la pila activa
  
  // layouts
  HBox pilaActiva_hbx;      // para posicionar la pila activa horizontalmente
  VBox pilaActiva_vbx;      // para colocar nombre a la pila activa
  VBox centro;              // para colocar nombre a las cartas comunes

  BorderPane topBar;   // border pane interno (para el nivel superior)-
  HBox [] comunes_hbx;      // cada índice es un par de cartas
  HBox [] pilaJ1_hbx;       //               " "
  HBox [] pilaJ2_hbx;       //               " "
  VBox [] pilas_vbox;       // 0: comunes(mesa) | 1: pila jugador 1 | 2: pila jugador 2

  // menuAjustes
  MenuAjustes menuAjustes;
  Scene menuInicio_scene;

  // Temporizador
  int temporizador = 10;
  int segunderoTurnos = 11;  // delay de 1 segundo para cada cambio.
  Timer timer;
  Timer timerGrafico;

  // textos
  Text pilaActiva_txt;
  Text pilaJ1_txt;
  Text pilaJ2_txt;
  Text comunes_txt;
  Text turno_txt;
  Text seconds;

  // GUI
  private BorderPane tablero;
  private Scene turnoJuego; 
  private Stage mainStage;

  // Controlador
  private JamesBond gameJB;  // controlador del juego
  private Jugador jugador1;
  private Jugador jugador2;
  Button ganarJugador1;
  Button ganarJugador2;

  
  // 
  private int indicadorPila;
  private int indicadorTablero;

  /**
   * Método constructor por omisión. Para crear instancias con valores por defecto.
   */
  public VistaTablero() {
  }

  private void init() {
    this.vistaComunes = new VistaCarta[4];     // Vista de las cartas comunes
    this.vistaPilaJ1 = new VistaCarta[6];      // Vista de la pila del jugador 1
    this.vistaPilaJ2 = new VistaCarta[6];      // Vista de la pila del jugador 1
    this.vistaPilaActiva = new VistaCarta[4];  // Vista de la pila activa
    
    // layouts
    this.pilaActiva_hbx = new HBox();      // para posicionar la pila activa horizontalmente
    this.pilaActiva_vbx = new VBox();      // para colocar nombre a la pila activa
    this.centro = new VBox();              // para colocar nombre a las cartas comunes
  
    this.topBar = new BorderPane();  // border pane interno (para el nivel superior)-
    this.comunes_hbx = new HBox[2];     // cada índice es un par de cartas
    this.pilaJ1_hbx = new HBox[3];      //               " "
    this.pilaJ2_hbx = new HBox[3];      //               " "
    this.pilas_vbox = new VBox[3];      // 0: comunes(mesa) | 1: pila jugador 1 | 2: pila jugador 2
  
    // menuAjustes
    this.menuAjustes = new MenuAjustes();
  
    // Temporizador
    this.temporizador = 10;
    this.segunderoTurnos = 11;  // delay de 1 segundo para cada cambio.
  
    // textos
    this.pilaActiva_txt = new Text("Pila activa");
    this.pilaJ1_txt = new Text();
    this.pilaJ2_txt = new Text();
    this.comunes_txt = new Text();
    this.turno_txt = new Text();
    this.seconds = new Text();
  
    // GUI
    this.tablero = new BorderPane();
  
    // Controlador
    this.gameJB = new JamesBond();  // controlador del juego
    this.ganarJugador1 = new Button("James Bond!");
    this.ganarJugador2 = new Button("James Bond!");
  }

  /**
   * @brief Llama al controlador para construir el mazo y repartir las cartas.
   * @param turnoInicial  String que representa el nombre del jugador inicial.
   * @param nombreJ1 String que representa el nombre del jugador 1
   * @param nombreJ2 String que representa el nombre del jugador 2
   * @param mainStage Ventana principal del GUI
   * @param menuInicio Escena principal del menú de inicio del GUI
   */
  public void construirJuego(String turnoInicial, String nombreJ1, String nombreJ2, Stage mainStage, Scene menuInicio) {
    this.init();
    this.menuInicio_scene = menuInicio;
    this.mainStage = mainStage;

    gameJB.inicializarTurnos(nombreJ1, nombreJ2, turnoInicial);
    gameJB.repartirCartas();

    this.indicadorPila = -1;
    this.indicadorTablero = -1;

    // Jugador 1
    this.jugador1 = gameJB.getJugador(1);
    pilas_vbox[1] = new VBox();
    pilas_vbox[1].getChildren().add(this.pilaJ1_txt);
    inicializarPilas(this.jugador1, pilaJ1_hbx, vistaPilaJ1, pilas_vbox[1]);

    // Jugador 2
    this.jugador2 = gameJB.getJugador(2);
    pilas_vbox[2] = new VBox();
    pilas_vbox[2].getChildren().add(this.pilaJ2_txt);
    inicializarPilas(this.jugador2, pilaJ2_hbx, vistaPilaJ2, pilas_vbox[2]);

    // PilaActiva
    inicializarPilaActiva(gameJB.getTurnoActual());

    // inicializa comunes
    Tablero tablero = gameJB.getTablero();
    inicializarCartasComunes(tablero);
    definirTextos(this.jugador1.getNombre(), this.jugador2.getNombre());

    // barra superior
    this.inicializarBarraTop();
    
    // construir escenas de cada jugador
    this.construirTablero();
    
    // Cierra los temporizadores
    mainStage.setOnCloseRequest(e -> {
      this.timer.cancel();
      this.timer.purge();
      this.timerGrafico.cancel();
      this.timerGrafico.purge();
    });

    mainStage.setScene(this.turnoJuego);

  }

  /**
   * @brief Inicia el flujo de los turnos.
   */
  public void run() {
    this.timer  = new Timer();
    temporizador = 10;
    mostrarTemporizador(seconds);
    if (gameJB.getTurnoActual() == jugador1) {
      construirEscenaJ1();
      vistaPilaJ1[jugador1.getIndexPilaActiva()].resaltarCarta();
    } else if (gameJB.getTurnoActual() == jugador2) {
      construirEscenaJ2();
      vistaPilaJ2[jugador2.getIndexPilaActiva()].resaltarCarta();
    }
    this.timer.schedule(new TimerTask() {
      Jugador turno = gameJB.getTurnoActual(); 
      @Override
      public void run() {
        gameJB.cambiarTurno();
        temporizador = 10;
        turno = gameJB.getTurnoActual();
        System.out.println("Turno de " + turno.getNombre());
        if (turno == jugador1) {
          construirEscenaJ1();
          cambiarPilaActiva(jugador1, 0);
          actualizarPilasJugador(jugador1);
          vistaPilaJ1[jugador1.getIndexPilaActiva()].resaltarCarta();
          vistaPilaJ2[jugador2.getIndexPilaActiva()].normalizarCarta();
          if (indicadorPila != -1) 
            vistaPilaActiva[indicadorPila].normalizarCarta();
          indicadorPila = -1;
        } else if (turno == jugador2) {
          construirEscenaJ2();
          cambiarPilaActiva(jugador2, 0);
          actualizarPilasJugador(jugador2);
          vistaPilaJ2[jugador2.getIndexPilaActiva()].resaltarCarta();
          vistaPilaJ1[jugador1.getIndexPilaActiva()].normalizarCarta();
          if (indicadorPila != -1) 
            vistaPilaActiva[indicadorPila].normalizarCarta();
          indicadorPila = -1;
        }
      }
    }, this.segunderoTurnos * 1000, this.segunderoTurnos * 1000);//wait 0 ms before doing the action and do it every 1000ms (1second)
  }

  /**
   * @brief Método que construye la estructura de la vista del tablero (cartas comunes).
   */
  public void construirTablero() {
    // Agrupa el texto del turno actual con la pila de cartas comunes.
    this.centro.getChildren().addAll(this.turno_txt, this.pilas_vbox[0]);
    this.centro.setAlignment(Pos.CENTER);
    this.centro.setSpacing(20);

    HBox bottomMenu = new HBox();
    this.ganarJugador1 = new Button("James Bond!");
    this.ganarJugador1.setOnAction(e -> {
      System.out.println("Jugador 1 grito james bond");
      Boolean ganador = this.gameJB.revisarPilas(this.gameJB.getJugador(1));
      if (ganador == true) {
        VentanaPopUp.mostrar("Ganador", "El ganador es " + this.gameJB.getJugador(1).getNombre());
        this.mainStage.setScene(menuInicio_scene);
        this.destruirTablero();
      } else {
        VentanaPopUp.mostrar("Ganador", "Aun no has ganado, sigue jugando");
      }

    });
    this.ganarJugador2 = new Button("James Bond!");
    this.ganarJugador2.setOnAction(e -> {
      System.out.println("Jugador 2 grito james bond");
      Boolean ganador = this.gameJB.revisarPilas(this.gameJB.getJugador(0));
      if (ganador == true) {
        VentanaPopUp.mostrar("Ganador", "El ganador es " + this.gameJB.getJugador(1).getNombre());
        this.mainStage.setScene(menuInicio_scene);
        this.destruirTablero();
      } else {
        VentanaPopUp.mostrar("Ganador", "Aun no has ganado, sigue jugando");
      }
    });

    bottomMenu.setAlignment(Pos.CENTER);
    bottomMenu.setSpacing(200);
    bottomMenu.getChildren().addAll(this.ganarJugador1, this.pilaActiva_vbx, this.ganarJugador2);

    this.tablero.setCenter(this.centro);          // vbox con el nombre del turno y cartas comunes
    this.tablero.setBottom(bottomMenu);  // vbox de pila activa
    this.tablero.setLeft(this.pilas_vbox[1]);     // vbox con el nombre y las pilas del jugador 1
    this.tablero.setRight(this.pilas_vbox[2]);    // vbox con el nombre y las pilas del jugador 1
    this.tablero.setTop(this.topBar);             // menú de ajustes, botón jamesBond y temporizador
    
    // botones pila de jugador 1
    this.vistaPilaJ1[0].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 1 Escogio la pila 0");
      this.vistaPilaJ1[jugador1.getIndexPilaActiva()].normalizarCarta();
      cambiarPilaActiva(jugador1, 0);
      this.vistaPilaJ1[jugador1.getIndexPilaActiva()].resaltarCarta();
    });
    this.vistaPilaJ1[1].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 1 Escogio la pila 1");
      this.vistaPilaJ1[jugador1.getIndexPilaActiva()].normalizarCarta();
      cambiarPilaActiva(jugador1, 1);
      this.vistaPilaJ1[jugador1.getIndexPilaActiva()].resaltarCarta();
    });
    this.vistaPilaJ1[2].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 1 Escogio la pila 2");
      this.vistaPilaJ1[jugador1.getIndexPilaActiva()].normalizarCarta();
      cambiarPilaActiva(jugador1, 2);
      this.vistaPilaJ1[jugador1.getIndexPilaActiva()].resaltarCarta();
    });
    this.vistaPilaJ1[3].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 1 Escogio la pila 3");
      this.vistaPilaJ1[jugador1.getIndexPilaActiva()].normalizarCarta();
      cambiarPilaActiva(jugador1, 3);
      this.vistaPilaJ1[jugador1.getIndexPilaActiva()].resaltarCarta();
    });
    this.vistaPilaJ1[4].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 1 Escogio la pila 4");
      this.vistaPilaJ1[jugador1.getIndexPilaActiva()].normalizarCarta();
      cambiarPilaActiva(jugador1, 4);
      this.vistaPilaJ1[jugador1.getIndexPilaActiva()].resaltarCarta();
    });
    this.vistaPilaJ1[5].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 1 Escogio la pila 5");
      this.vistaPilaJ1[jugador1.getIndexPilaActiva()].normalizarCarta();
      cambiarPilaActiva(jugador1, 5);
      this.vistaPilaJ1[jugador1.getIndexPilaActiva()].resaltarCarta();
    });

    // botones pila de jugador 2
    this.vistaPilaJ2[0].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 2 Escogio la pila 0");
      this.vistaPilaJ2[jugador2.getIndexPilaActiva()].normalizarCarta();
      cambiarPilaActiva(jugador2, 0);
      this.vistaPilaJ2[jugador2.getIndexPilaActiva()].resaltarCarta();
    });
    this.vistaPilaJ2[1].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 2 Escogio la pila 1");
      this.vistaPilaJ2[jugador2.getIndexPilaActiva()].normalizarCarta();
      cambiarPilaActiva(jugador2, 1);
      this.vistaPilaJ2[jugador2.getIndexPilaActiva()].resaltarCarta();
    });
    this.vistaPilaJ2[2].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 2 Escogio la pila 2");
      this.vistaPilaJ2[jugador2.getIndexPilaActiva()].normalizarCarta();
      cambiarPilaActiva(jugador2, 2);
      this.vistaPilaJ2[jugador2.getIndexPilaActiva()].resaltarCarta();
    });
    this.vistaPilaJ2[3].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 2 Escogio la pila 3");
      this.vistaPilaJ2[jugador2.getIndexPilaActiva()].normalizarCarta();
      cambiarPilaActiva(jugador2, 3);
      this.vistaPilaJ2[jugador2.getIndexPilaActiva()].resaltarCarta();
    });
    this.vistaPilaJ2[4].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 2 Escogio la pila 4");
      this.vistaPilaJ2[jugador2.getIndexPilaActiva()].normalizarCarta();
      cambiarPilaActiva(jugador2, 4);
      this.vistaPilaJ2[jugador2.getIndexPilaActiva()].resaltarCarta();
    });
    this.vistaPilaJ2[5].getImageView().setOnMouseClicked(e -> {
      System.out.println("Jugador 2 Escogio la pila 5");
      this.vistaPilaJ2[jugador2.getIndexPilaActiva()].normalizarCarta();
      cambiarPilaActiva(jugador2, 5);
      this.vistaPilaJ2[jugador2.getIndexPilaActiva()].resaltarCarta();
    });

    //Botones de las cartas comunes (mesa/centro)
    this.vistaComunes[0].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio la carta comun 0");
      resaltarCartaComun(0);
      if (this.indicadorPila != -1) {
        intercambiarCarta(this.indicadorTablero, this.indicadorPila);
      }
    });
    this.vistaComunes[1].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio la carta comun 1");
      resaltarCartaComun(1);
      if (this.indicadorPila != -1) {
        intercambiarCarta(this.indicadorTablero, this.indicadorPila);
      }
    });
    this.vistaComunes[2].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio la carta comun 2");
      resaltarCartaComun(2);
      if (this.indicadorPila != -1) {
        intercambiarCarta(this.indicadorTablero, this.indicadorPila);
      }
    });
    this.vistaComunes[3].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio la carta comun 3");
      resaltarCartaComun(3);
      if (this.indicadorPila != -1) {
        intercambiarCarta(this.indicadorTablero, this.indicadorPila);
      }
    });
  
    // botones pila activa
    this.vistaPilaActiva[0].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio de la pila activa la carta 0");
      resaltarCartaPila(0);
      if (this.indicadorTablero != -1) {
        intercambiarCarta(this.indicadorTablero, this.indicadorPila);
      }
    });
    this.vistaPilaActiva[1].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio de la pila activa la carta 1");
      resaltarCartaPila(1);
      if (this.indicadorTablero != -1) {
        intercambiarCarta(this.indicadorTablero, this.indicadorPila);
      }
    });
    this.vistaPilaActiva[2].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio de la pila activa la carta 2");
      resaltarCartaPila(2);
      if (this.indicadorTablero != -1) {
        intercambiarCarta(this.indicadorTablero, this.indicadorPila);
      }
    });
    this.vistaPilaActiva[3].getImageView().setOnMouseClicked(e -> {
      System.out.println("Se escogio de la pila activa la carta 3");
      resaltarCartaPila(3);
      if (this.indicadorTablero != -1) {
        intercambiarCarta(this.indicadorTablero, this.indicadorPila);
      }
    });

    // set  del color al background
    this.tablero.setBackground(new Background(new BackgroundFill(Color.web("#008000"), new CornerRadii(0), Insets.EMPTY)));

    this.turnoJuego = new Scene(this.tablero, this.anchoVentana, this.alturaVentana);
  }

  /**
   * @brief Construye la escena para el jugador 1.
   * Gira las cartas del rival, cambia el nombre del turno actual y deshabilita el click de las cartas del jugador 2
   */
  public void construirEscenaJ1() {
    this.turno_txt.setText("Turno de " + jugador1.getNombre());
    this.ganarJugador1.setDisable(false);
    this.ganarJugador2.setDisable(true);
    this.girarCartas(vistaPilaJ2, false);
    this.girarCartas(vistaPilaJ1, true);
    for (int i = 0; i < this.vistaPilaJ2.length; ++i) { 
      this.vistaPilaJ2[i].getImageView().setDisable(true);
    }
    for (int i = 0; i < this.vistaPilaJ1.length; ++i) { 
      this.vistaPilaJ1[i].getImageView().setDisable(false);
    }
  }

  /**
   * @brief Construye la escena para el jugador 2.
   * Gira las cartas del rival, cambia el nombre del turno actual y deshabilita el click de las cartas del jugador 1.
   */
  public void construirEscenaJ2() {
    this.turno_txt.setText("Turno de " + jugador2.getNombre());
    this.ganarJugador1.setDisable(true);
    this.ganarJugador2.setDisable(false);
    this.girarCartas(vistaPilaJ2, true);
    this.girarCartas(vistaPilaJ1, false);
    for (int i = 0; i < this.vistaPilaJ2.length; ++i) { 
      this.vistaPilaJ2[i].getImageView().setDisable(false);
    }
    for (int i = 0; i < this.vistaPilaJ1.length; ++i) { 
      this.vistaPilaJ1[i].getImageView().setDisable(true);
    }
  }

  /**
   * @brief Metodo que gira las cartas que le pasan por parámetro según el booleano indicado.
   * @param cartas Arreglo de las cartas que se quieren girar
   * @param activar Booleano que indica si las cartas se giran hacia arriba(True) o hacia abajo (False)
   */
  public void girarCartas(VistaCarta [] cartas, Boolean activar) {
    for (int i = 0; i < cartas.length; ++i) {
      cartas[i].getView(activar);
    }
  }

  /**
   * Coloca y configura el menú de ajustes y el temporizador.
   */
  public void inicializarBarraTop() {
    Button menu = new Button("Menú");
    
    menu.setOnAction(e -> {
      this.timer.cancel();
      this.timerGrafico.cancel();
      Boolean salirDeljuego = VentanaPopUp.mostrar(this.menuAjustes);
      if (salirDeljuego == true) {
        this.mainStage.setScene(menuInicio_scene);
        this.destruirTablero();
      } else {
        this.run();
      }
    });

    this.topBar.setCenter(this.seconds);
    this.topBar.setLeft(menu);
  }

  /**
   * @brief Define el estilo de los textos del tablero
   * @param jugador1 String que representa el nombre del jugador 1
   * @param jugador2 String que representa el nombre del jugador 1
   */
  public void definirTextos(String jugador1, String jugador2) {
    // pila activa
    pilaActiva_txt.setFill(Color.WHITE);
    pilaActiva_txt.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
    // cartas comunes
    comunes_txt.setFill(Color.WHITE);
    comunes_txt.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
    // pila del jugador 1
    pilaJ1_txt.setFill(Color.WHITE);
    pilaJ1_txt.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
    pilaJ1_txt.setText("Pilas de " + jugador1);
    // pila del jugador 2
    pilaJ2_txt.setFill(Color.WHITE);
    pilaJ2_txt.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
    pilaJ2_txt.setText("Pilas de " + jugador2);
    // turno actual
    this.turno_txt.setFill(Color.WHITE);
    this.turno_txt.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
    this.turno_txt.setText("Turno de ");
    // temporizador
    this.seconds.setFill(Color.WHITE);
    this.seconds.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
    this.seconds.setText("00:" + temporizador);
  }

  // 
  /**
   * @brief Inicia la vista de la pila activa según el jugador y la pila que tenga en mano.
   * @param jugador jugador del juego que posee una pila activa cuando es su turno.
   */
  public void inicializarPilaActiva(Jugador jugador) {
    Pila pilaActiva = jugador.pilaActiva();
    // asigna el hbox y vbox de la pila activa
    this.pilaActiva_hbx.setAlignment(Pos.BOTTOM_CENTER);
    this.pilaActiva_vbx.getChildren().addAll(this.pilaActiva_txt, pilaActiva_hbx);
    this.pilaActiva_vbx.setAlignment(Pos.BOTTOM_CENTER);

    // instancia la vista de pila activa y la agrega al hbox de la pila activa.
    for (int indexCarta = 0; indexCarta < vistaPilaActiva.length; indexCarta++) {
      vistaPilaActiva[indexCarta] = new VistaCarta(pilaActiva.getCarta(indexCarta));
      pilaActiva_hbx.getChildren().add(vistaPilaActiva[indexCarta].getView(true));
    }
    // centra y espacea el hbox de pila activa
    pilaActiva_hbx.setSpacing(20);
    pilaActiva_hbx.setAlignment(Pos.BOTTOM_CENTER);
  }

  /**
   * @brief Inicia las vistas de las cartas comunes con las que inicia el juego según el tablero.
   * @param tablero Tablero que indica cuales son las cartas comunes.
   */
  public void inicializarCartasComunes(Tablero tablero) {
    // instancia la vista del cartas comunes.
    for (int indexCarta = 0; indexCarta < vistaComunes.length; indexCarta++) {
      vistaComunes[indexCarta] = new VistaCarta(tablero.getCarta(indexCarta));
    }
    this.pilas_vbox[0] = new VBox();
    inicializarComun(comunes_hbx, vistaComunes, this.pilas_vbox[0]);
  }

  /**
   * @brief Inicia una pila de jugador
   * @param jugador Jugador que contiene la pila que se quiere visualizar.
   * @param pila_hbx Pila en la que se quiere contener la pila a visualizar.
   * @param vistaPila Arreglo de vistas que se quieren visualizar.
   * @param vbx vbox que contendrá la pila a visualizar.
   */
  public void inicializarPilas(Jugador jugador, HBox [] pila_hbx, VistaCarta [] vistaPila, VBox vbx) {
    // instancia la vista de la pila que se quiere visualizar.
    for (int indexCarta = 0; indexCarta < vistaPila.length; indexCarta++) {
      vistaPila[indexCarta] = new VistaCarta(jugador.getPila(indexCarta).getCarta(0));
    }
    inicializarComun(pila_hbx, vistaPila, vbx);
  }

  /**
   * @brief Metodo de comun para agregar vistas a las hbx y estas los vbx
   * @param pila_hbx Pila en la que se quiere contener la pila a visualizar.
   * @param vistaPila Arreglo de vistas que se quieren visualizar.
   * @param vbx vbox que contendrá la pila a visualizar.
   */
  private void inicializarComun(HBox [] pila_hbx, VistaCarta [] vistaPila, VBox vbx) {

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

  /**
   * @brief Muestra el temporizador del juego.
   * @details <temporizador> es un atributo de clase.
   * @param seconds
   */
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
        if (temporizador < 0) {
          temporizador = 10;
        }
      }
    }, 1000, 1000);
  }

   /**
   * @brief Cambia la vista de la pila activa cuando el jugador desea ver otra.
   * @param jugador El jugador que quiere cambiar la pila
   * @param index Indice de la pila que quiere cambiar
   */
   public void cambiarPilaActiva(Jugador jugador, int index) {
    jugador.cambiarPila(index);
    Pila pilaActiva = jugador.pilaActiva();
    
    for (int indexCarta = 0; indexCarta < vistaPilaActiva.length; indexCarta++) {
      vistaPilaActiva[indexCarta].getImageView().setImage(pilaActiva.getCarta(indexCarta).getImagen());
    }
  }

  // TODO agregar a UML
  /**
   * @brief Método que intercambia las cartas y sus vistas entre el tablero y el jugador
   * @param posTablero la posicion de la carta en el tablero 
   * @param posJugador La posicion de la carta del jugador
   */
  public void intercambiarCarta(int posTablero, int posJugador) {
    Pila pilaActiva =  gameJB.getTurnoActual().pilaActiva();
    Carta auxPila = pilaActiva.getCarta(posJugador);
    Carta auxComunes = gameJB.getTablero().getCarta(posTablero);

    this.gameJB.intercambiarCarta(posTablero, posJugador);

    vistaPilaActiva[posJugador].getImageView().setImage(auxComunes.getImagen());
    vistaComunes[posTablero].getImageView().setImage(auxPila.getImagen());
    this.vistaComunes[this.indicadorTablero].normalizarCarta();
    this.vistaPilaActiva[this.indicadorPila].normalizarCarta();
    System.out.println(this.gameJB.getTurnoActual().getNombre());
    this.actualizarPilasJugador(this.gameJB.getTurnoActual());

    this.indicadorPila = -1;
    this.indicadorTablero = -1;
  }
  
  /**
   * @brief Método que resalta la carta comun seleccionada
   * @param posicion la posicion de la carta comun que se quiere resaltar
   */
  public void resaltarCartaComun(int posicion) {
    if (this.indicadorTablero != -1)
        this.vistaComunes[this.indicadorTablero].normalizarCarta();
    this.indicadorTablero = posicion;
    this.vistaComunes[this.indicadorTablero].resaltarCarta();
  }

  /**
   * @brief Método que resalta la carta seleccionada de la pila activa
   * @param posicion la posicion de la carta en la pila que se quiere resaltar
   */
  public void resaltarCartaPila(int posicion) {
    if (this.indicadorPila != -1)
        this.vistaPilaActiva[this.indicadorPila].normalizarCarta();
    this.indicadorPila = posicion;
    this.vistaPilaActiva[this.indicadorPila].resaltarCarta();
  }

  /**
   * @brief Actualiza las vistas de las pilas del jugador
   * @param jugador El jugador al cual se le actualizaran las vistas de las pilas
   */
  public void actualizarPilasJugador(Jugador jugador) {
    Carta aux;
    if (jugador == this.jugador1) {
      for (int indexCarta = 0; indexCarta < this.vistaPilaJ1.length; indexCarta++) {
        aux = jugador.getPila(indexCarta).getCarta(0);
        vistaPilaJ1[indexCarta].getImageView().setImage(aux.getImagen());
      }
    } else {
      for (int indexCarta = 0; indexCarta < this.vistaPilaJ1.length; indexCarta++) {
        aux = jugador.getPila(indexCarta).getCarta(0);
        vistaPilaJ2[indexCarta].getImageView().setImage(aux.getImagen());
      }
    }
  }

  public void destruirTablero() {
    this.timer.cancel();
    this.timerGrafico.cancel();
    this.timer.purge();
    this.timerGrafico.purge();
    this.vistaComunes = null;     // Vista de las cartas comunes
    this.vistaPilaJ1 = null;      // Vista de la pila del jugador 1
    this.vistaPilaJ2 = null;      // Vista de la pila del jugador 1
    this.vistaPilaActiva = null;  // Vista de la pila activa
    
    // layouts
    this.pilaActiva_hbx = null;      // para posicionar la pila activa horizontalmente
    this.pilaActiva_vbx = null;      // para colocar nombre a la pila activa
    this.centro = null;              // para colocar nombre a las cartas comunes
  
    this.topBar = null;  // border pane interno (para el nivel superior)-
    this.comunes_hbx = null;     // cada índice es un par de cartas
    this.pilaJ1_hbx = null;      //               " "
    this.pilaJ2_hbx = null;      //               " "
    this.pilas_vbox = null;      // 0: comunes(mesa) | 1: pila jugador 1 | 2: pila jugador 2
  
    // menuAjustes
    this.menuAjustes = null;
  
  
    // textos
    this.pilaActiva_txt = null;
    this.pilaJ1_txt = null;
    this.pilaJ2_txt = null;
    this.comunes_txt = null;
    this.turno_txt = null;
    this.seconds = null;
  
    // GUI
    this.tablero = null;
  
    // Controlador
    this.gameJB = null;  // controlador del juego
    this.ganarJugador1 = null;
    this.ganarJugador2 = null;
  }
}
