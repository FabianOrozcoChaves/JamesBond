package jamesBond;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.beans.VetoableChangeListenerProxy;

import javax.swing.plaf.ButtonUI;
import javax.swing.tree.RowMapper;

import jamesBond.MenuInicio;
/**
 * Clase GUI.
 * Interfaz grafica
 */
public class GUI extends Application {
  private int anchoVentana = 1000;
  private int alturaVentana = 600;
  private Stage mainStage;
  private Scene menuIncio, menuAjustes;

  public void start(Stage stage) {
    this.mainStage = stage;
    stage.setTitle("James Bond");
    this.mainStage.setOnCloseRequest(e -> this.cerrarVentana());


    MenuInicio test = new MenuInicio();
    this.mostrarMenuInicio(test);

    stage.show();
  }

  public void mostrarMenuInicio(MenuInicio menu) {
    String [] opciones = menu.mostrarOpciones();
    Button [] botonesMenu = new Button[4];

    for (int i = 0; i < botonesMenu.length; ++i) {
      botonesMenu[i] = new Button();
      botonesMenu[i].setText(opciones[i]);
    }
    botonesMenu[0].setOnAction(e -> System.out.println("Jugar"));

    HBox topMenuBotones = new HBox();
    TextField nombreJugador1 = new TextField(menu.getJugadorJ1());
    nombreJugador1.setOnAction(e -> { 
      nombreJugador1.setPromptText(nombreJugador1.getText());
      menu.asignarNombreJ1(nombreJugador1.getText());
    });
    TextField nombreJugador2 = new TextField(menu.getJugadorJ2());
    nombreJugador2.setOnAction(e ->{ 
      nombreJugador2.setPromptText(nombreJugador2.getText());
      menu.asignarNombreJ1(nombreJugador2.getText());
    });

    topMenuBotones.getChildren().addAll(nombreJugador1, nombreJugador2);
    topMenuBotones.setSpacing(600);
    topMenuBotones.setAlignment(Pos.CENTER);

    HBox topMenuLabels = new HBox();
    Label asignarJugador1 = new Label("Nombre jugador 1:");
    Label asignarJugador2 = new Label("Nombre jugador 2:");
    topMenuLabels.getChildren().addAll(asignarJugador1, asignarJugador2);
    topMenuLabels.setSpacing(700);
    topMenuLabels.setAlignment(Pos.CENTER);

    VBox topMenu = new VBox();
    topMenu.getChildren().addAll(topMenuLabels, topMenuBotones);

    
    VBox centerMenu = new VBox();
    centerMenu.getChildren().addAll(botonesMenu[0],botonesMenu[1],botonesMenu[2],botonesMenu[3]);
    centerMenu.setAlignment(Pos.CENTER);
    centerMenu.setSpacing(50);

    BorderPane borderpane = new BorderPane();
    borderpane.setTop(topMenu);
    borderpane.setCenter(centerMenu);



   
    this.menuIncio = new Scene(borderpane, this.anchoVentana, this.alturaVentana);
    this.mainStage.setScene(this.menuIncio);
  }

  private void cerrarVentana() {

    this.mainStage.close();
  }

  public static void main(String[] args) {
    System.out.println("Ventana cerrada");
    launch(args);
  }
}
