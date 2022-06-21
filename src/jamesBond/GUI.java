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
import javafx.stage.Stage;

/**
 * Clase GUI.
 * Interfaz grafica
 */
public class GUI extends Application {
  private int anchoVentana = 1000;
  private int alturaVentana = 600;
  private Stage mainStage;
  private Scene menuIncio_scene, menuAjustes_scene;
  private MenuInicio menuInicio;

  public void start(Stage stage) {
    this.mainStage = stage;
    this.menuInicio = new MenuInicio("James Bond");
    stage.setTitle(this.menuInicio.getNombreJUego());
    this.mainStage.setOnCloseRequest(e -> this.cerrarVentana());
    this.mostrarMenuInicio();
    stage.show();
  }

  public void mostrarMenuInicio() {
    int jugar = 0;
    int cargar = 1;
    int reglas = 2;
    int salir = 3;

    String [] opciones = this.menuInicio.mostrarOpciones();
    Button [] botonesMenu = new Button[4];
 
    for (int i = 0; i < botonesMenu.length; ++i) {
      botonesMenu[i] = new Button();
      botonesMenu[i].setText(opciones[i]);
    }
    botonesMenu[jugar].setOnAction(e -> System.out.println(this.menuInicio.getJugadorJ1() + this.menuInicio.getJugadorJ2()));
    botonesMenu[cargar].setOnAction(e -> System.out.println("Cargar"));
    botonesMenu[reglas].setOnAction(e ->{
      PopUp.mostrar("Reglas", this.menuInicio.mostrarReglas());

    });
    botonesMenu[salir].setOnAction(e -> {
      this.cerrarVentana();
    });


    HBox topMenuBotones = new HBox();
    TextField nombreJugador1 = new TextField(this.menuInicio.getJugadorJ1());
    nombreJugador1.setOnAction(e -> {
      this.menuInicio.asignarNombreJ1(nombreJugador1.getText());
      nombreJugador1.setText(nombreJugador1.getText() + " " + Character.toString(10004));
      nombreJugador1.setEditable(false);

    });
    TextField nombreJugador2 = new TextField(this.menuInicio.getJugadorJ2());
    nombreJugador2.setOnAction(e ->{ 
      this.menuInicio.asignarNombreJ2(nombreJugador2.getText());
      nombreJugador2.setText(nombreJugador2.getText() + " " + Character.toString(10004));
      nombreJugador2.setEditable(false);
    });

    topMenuBotones.getChildren().addAll(nombreJugador1, nombreJugador2);
    topMenuBotones.setSpacing(500);
    topMenuBotones.setAlignment(Pos.CENTER);
    
    
    HBox topMenuLabels = new HBox();
    Label asignarJugador1 = new Label("Nombre jugador 1:");
    Label asignarJugador2 = new Label("Nombre jugador 2:");
    topMenuLabels.getChildren().addAll(asignarJugador1, asignarJugador2);
    topMenuLabels.setSpacing(550);
    topMenuLabels.setAlignment(Pos.CENTER);


    HBox topMenuCheckboxs = new HBox();
    CheckBox checkJugador1 = new CheckBox("Asignar turno inicial");
    CheckBox checkJugador2 = new CheckBox("Asignar turno inicial");
    CheckBox checkRandom = new CheckBox("Turno inicial aleatorio");

    topMenuCheckboxs.getChildren().addAll(checkJugador1, checkRandom, checkJugador2);
    
    // default
    checkRandom.setSelected(true);

    checkJugador1.setOnAction(e -> {
      if (!checkJugador1.isSelected() && !checkJugador2.isSelected()) {
        checkRandom.setSelected(true);
        this.menuInicio.asignarTurnoInicial("random");
        System.out.println("El turno inicial es aleatorio");
      } else if (checkJugador2.isSelected() || checkRandom.isSelected()) {
        checkJugador2.setSelected(false);
        checkRandom.setSelected(false);
      }
      if (checkJugador1.isSelected()) {
        this.menuInicio.asignarTurnoInicial(this.menuInicio.getJugadorJ1());
        System.out.println("El turno inicial lo tiene " + this.menuInicio.getTurnoInicial());
      }
    });

    checkJugador2.setOnAction(e -> {
      if (!checkJugador1.isSelected() && !checkJugador2.isSelected()) {
        checkRandom.setSelected(true);
        this.menuInicio.asignarTurnoInicial("random");
        System.out.println("El turno inicial es aleatorio");
      } else if (checkJugador1.isSelected() || checkRandom.isSelected()) {
        checkJugador1.setSelected(false);
        checkRandom.setSelected(false);
      }
      if (checkJugador2.isSelected()) {
        this.menuInicio.asignarTurnoInicial(this.menuInicio.getJugadorJ2());
        System.out.println("El turno inicial lo tiene " + this.menuInicio.getTurnoInicial());
      }
    });

    checkRandom.setOnAction(e -> {
      if (checkJugador1.isSelected() || checkJugador2.isSelected()) {
        checkJugador1.setSelected(false);
        checkJugador2.setSelected(false);
      }
      this.menuInicio.asignarTurnoInicial("random");
      System.out.println("El turno inicial es aleatorio");
      checkRandom.setSelected(true);
    });

    topMenuCheckboxs.setSpacing(190);
    topMenuCheckboxs.setAlignment(Pos.CENTER);

    VBox topMenu = new VBox();
    topMenu.getChildren().addAll(topMenuLabels, topMenuBotones, topMenuCheckboxs);

    
    VBox centerMenu = new VBox();
    centerMenu.getChildren().addAll(botonesMenu[0],botonesMenu[1],botonesMenu[2],botonesMenu[3]);
    centerMenu.setAlignment(Pos.CENTER);
    centerMenu.setSpacing(50);

    BorderPane borderpane = new BorderPane();
    borderpane.setTop(topMenu);
    borderpane.setCenter(centerMenu);

    this.menuIncio_scene = new Scene(borderpane, this.anchoVentana, this.alturaVentana);
    this.mainStage.setScene(this.menuIncio_scene);
  }

  private void cerrarVentana() {
    System.out.println("Ventana cerrada");
    this.mainStage.close();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
