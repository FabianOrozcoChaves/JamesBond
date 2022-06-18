package jamesBond;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    Button [] botonesMenu = new Button[6];

    for (int i = 0; i < botonesMenu.length; ++i) {
      botonesMenu[i] = new Button();
      botonesMenu[i].setText(opciones[i]);
    }
    botonesMenu[0].setOnAction(e -> System.out.println("Jugar"));

    HBox topMenu = new HBox();
    botonesMenu[4].setAlignment(Pos.TOP_RIGHT);
    topMenu.getChildren().addAll(botonesMenu[4], botonesMenu[5]);
    topMenu.setSpacing(700);
    topMenu.setAlignment(Pos.CENTER);

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
