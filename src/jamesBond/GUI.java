package jamesBond;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import jamesBond.VistaTablero;

/**
 * @class Clase GUI.
 * @details Interfaz grafica que dirige las pantallas de la aplicacion.
 */
public class GUI extends Application {
  private int anchoVentana = 1000; // ancho de la ventana en pixeles.
  private int alturaVentana = 600; // altura de la ventana en pixeles.
  private Stage mainStage; // ventana principal que se utiliza.
  private Scene menuIncio_scene; // escena que repesenta el menu de inicio.
  private MenuInicio menuInicio = new MenuInicio("James Bond");  // instancia del menu de inicio para poder acceder a sus metodos.
  private VistaTablero tablero = new VistaTablero(); // instancia de la parte grafica del tablero.

  /**
   * @metodo principal para ejecutar el programa.
   */
  public void start(Stage stage) {
    this.mainStage = stage;
    // asignamos el titulo ala ventana
    stage.setTitle(this.menuInicio.getNombreJUego());

    // asignamos la accion a realizar cuando se le da al boton de cerrar ventana.
    this.mainStage.setOnCloseRequest(e -> this.cerrarVentana());

    // metodo que construye la escena del menu de inicio
    this.construirMenuInicio();

    // mostramos el menu de inicio
    this.mainStage.setScene(this.menuIncio_scene);

    // mostramos la ventana.
    stage.show();
  }

  /**
   * @brief COnstructor de la escena que corresponde al menu de inicio.
   * @details modifica el atributo this.menuInicio.
   */
  public void construirMenuInicio() {
    // indices para cceder a los botones de las diferentes opciones del menu.
    int jugar = 0;
    int cargar = 1;
    int reglas = 2;
    int salir = 3;

    // extraemos las opciones del menu.
    String [] opciones = this.menuInicio.mostrarOpciones();
    Button [] botonesMenu = new Button[4];
 
    // creamos un boton por cada opcion.
    for (int i = 0; i < botonesMenu.length; ++i) {
      botonesMenu[i] = new Button();
      botonesMenu[i].setText(opciones[i]);
    }

    // asignamos la accion que realiza cada boton.

    // jugar crea una vista del tablero que utiliza el controlador para jugar.
    botonesMenu[jugar].setOnAction(e -> {
      this.tablero.construirJuego(this.menuInicio.getTurnoInicial(), this.menuInicio.getJugadorJ1(), this.menuInicio.getJugadorJ2(), this.mainStage, this.menuIncio_scene, 15);
      this.tablero.run();

    });

    // opcion cargar no ha sido implementada, por ahora solo hace un print.
    botonesMenu[cargar].setOnAction(e ->{
      System.out.println("Cargar");
    });

    // opcion reglas muestra las reglas del juego.
    botonesMenu[reglas].setOnAction(e ->{
      VentanaPopUp.mostrar("Reglas", this.menuInicio.mostrarReglas());
    });

    // oppcion salir cierra el programa.
    botonesMenu[salir].setOnAction(e -> {
      this.cerrarVentana();
    });

    //; el menu del medio tiene todos lo botones con las opciones del menu de inicio.
    VBox centerMenu = new VBox();
    centerMenu.getChildren().addAll(botonesMenu[0],botonesMenu[1],botonesMenu[2],botonesMenu[3]);
    centerMenu.setAlignment(Pos.CENTER);
    centerMenu.setSpacing(50);

    // se crea una caja horizontal para lo que se va a poner en la parte superior de la pantalla.
    // en la parte superior se ponen los nombres de los jugador, los cuadros para modificarlos y los checkboxs para sgnar el turno inicial.
    // caja horizontal para los cuadros de entrada de datos para signar los nombres de los jugadores.
    HBox topMenuBotones = new HBox();

    // cuadro para ingresar el nombre del jugador 1
    TextField nombreJugador1 = new TextField(this.menuInicio.getJugadorJ1());
    
    // accion que realiza el asiganr el nombre del jugador 1
    nombreJugador1.setOnAction(e -> {
      // asigna en la instacia del menu de inicio el nombre del jugador 1
      this.menuInicio.asignarNombreJ1(nombreJugador1.getText());

      // asigna en la visualizacion el nombre y un check que muestra que se asigno correctamente.
      nombreJugador1.setText(nombreJugador1.getText() + " " + Character.toString(10004));
      nombreJugador1.setEditable(false);

    });

    // cuadro para ingresar el nombre del jugador 2
    TextField nombreJugador2 = new TextField(this.menuInicio.getJugadorJ2());
    nombreJugador2.setOnAction(e ->{ 
      // asigna en la instacia del menu de inicio el nombre del jugador 2
      this.menuInicio.asignarNombreJ2(nombreJugador2.getText());

      // asigna en la visualizacion el nombre y un check que muestra que se asigno correctamente.
      nombreJugador2.setText(nombreJugador2.getText() + " " + Character.toString(10004));
      nombreJugador2.setEditable(false);
    });

    // asignamos a la caja horizontal los campos de texto de los jugadores
    topMenuBotones.getChildren().addAll(nombreJugador1, nombreJugador2);
    topMenuBotones.setSpacing(500);
    topMenuBotones.setAlignment(Pos.CENTER);

    // caja horizintal para escirbir los labels de "Jugador1" y "Jugador2".
    HBox topMenuLabels = new HBox();
    Label asignarJugador1 = new Label("Nombre jugador 1:");
    Label asignarJugador2 = new Label("Nombre jugador 2:");
    topMenuLabels.getChildren().addAll(asignarJugador1, asignarJugador2);
    topMenuLabels.setSpacing(550);
    topMenuLabels.setAlignment(Pos.CENTER);

    // creacion de caja horizontal para los checkboxs para sgnar el turno inicial.
    HBox topMenuCheckboxs = new HBox();
    // creacion de los tres checkboxs
    CheckBox checkJugador1 = new CheckBox("Asignar turno inicial");
    CheckBox checkJugador2 = new CheckBox("Asignar turno inicial");
    CheckBox checkRandom = new CheckBox("Turno inicial aleatorio");

    topMenuCheckboxs.getChildren().addAll(checkJugador1, checkRandom, checkJugador2);
    
    // default
    checkRandom.setSelected(true);


    // asignacion de la accion del checkbox del jugador 1
    checkJugador1.setOnAction(e -> {
      // si ningun checbox es seleccionado se escoge el random
      if (!checkJugador1.isSelected() && !checkJugador2.isSelected()) {
        checkRandom.setSelected(true);
        this.menuInicio.asignarTurnoInicial("random");
        System.out.println("El turno inicial es aleatorio");

        // el checkbox del otro jugador esta seleccionado y se selecciona el actual, se deselecciona el del otro jugador
      } else if (checkJugador2.isSelected() || checkRandom.isSelected()) {
        checkJugador2.setSelected(false);
        checkRandom.setSelected(false);
      }

      // si el actual fue seleccionado asignamos en la instacia del menu de incio el turno inicial.
      if (checkJugador1.isSelected()) {
        this.menuInicio.asignarTurnoInicial(this.menuInicio.getJugadorJ1());
        System.out.println("El turno inicial lo tiene " + this.menuInicio.getTurnoInicial());
      }
    });

    checkJugador2.setOnAction(e -> {
      // si ningun checbox es seleccionado se escoge el random

      if (!checkJugador1.isSelected() && !checkJugador2.isSelected()) {
        checkRandom.setSelected(true);
        this.menuInicio.asignarTurnoInicial("random");
        System.out.println("El turno inicial es aleatorio");
        // el checkbox del otro jugador esta seleccionado y se selecciona el actual, se deselecciona el del otro jugador
      } else if (checkJugador1.isSelected() || checkRandom.isSelected()) {
        checkJugador1.setSelected(false);
        checkRandom.setSelected(false);
      }

      // si el actual fue seleccionado asignamos en la instacia del menu de incio el turno inicial.
      if (checkJugador2.isSelected()) {
        this.menuInicio.asignarTurnoInicial(this.menuInicio.getJugadorJ2());
        System.out.println("El turno inicial lo tiene " + this.menuInicio.getTurnoInicial());
      }
    });

    checkRandom.setOnAction(e -> {
      // se ponen en false los botnoes  de los jugadores
      if (checkJugador1.isSelected() || checkJugador2.isSelected()) {
        checkJugador1.setSelected(false);
        checkJugador2.setSelected(false);
      }

      // se asigna en el menu de inicio que el turno es aleatorio
      this.menuInicio.asignarTurnoInicial("random");
      System.out.println("El turno inicial es aleatorio");
      checkRandom.setSelected(true);
    });

    topMenuCheckboxs.setSpacing(190);
    topMenuCheckboxs.setAlignment(Pos.CENTER);

    VBox topMenu = new VBox();
    topMenu.getChildren().addAll(topMenuLabels, topMenuBotones, topMenuCheckboxs);


    // asiganamos en el layout general los conjuntos de botones.
    BorderPane borderpane = new BorderPane();
    borderpane.setTop(topMenu);
    borderpane.setCenter(centerMenu);

    this.menuIncio_scene = new Scene(borderpane, this.anchoVentana, this.alturaVentana);
  }

  /**
   * @brief Metodo para cerrar la aplicacion del juego.
   */
  private void cerrarVentana() {
    System.out.println("Ventana cerrada");
    this.mainStage.close();
    System.exit(0);
  }

    /**
     * @brief Main principal de la aplicacion.
     */
  public static void main(String[] args) {
    launch(args);
  }
}
