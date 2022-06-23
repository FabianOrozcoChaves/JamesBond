package jamesBond;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VentanaPopUp {
  public static void mostrar(String titulo, String mensaje) {
    Stage ventana = new Stage();
    ventana.initModality(Modality.APPLICATION_MODAL);
    ventana.setTitle(titulo);
    ventana.setMinWidth(250);

    Label texto = new Label();
    texto.setText(mensaje);
    texto.setPadding(new Insets(20));

    Button cerrar = new Button("Cerrar");
    cerrar.setOnAction(e -> ventana.close());

    Label vacio = new Label();
    VBox orden = new VBox();
    orden.getChildren().addAll(texto, cerrar, vacio);
    orden.setAlignment(Pos.CENTER);

    Scene escena = new Scene(orden);
    ventana.setScene(escena);
    ventana.showAndWait();
  }

  public static void mostrar(MenuAjustes menuAjustes) {
    Stage ventana = new Stage();
    ventana.initModality(Modality.APPLICATION_MODAL);
    ventana.setTitle(menuAjustes.getTitulo());
    ventana.setWidth(300);
    ventana.setHeight(300);


    int reglas = 0;
    int guardar = 1;
    int cargar = 2;
    int salir = 3;

    String [] opciones = menuAjustes.mostrarOpciones();
    Button [] botones = new Button [opciones.length];
    for (int i = 0; i < opciones.length; ++i) {
      botones[i] = new Button(opciones[i]);
    }

    botones[reglas].setOnAction(e -> {
      VentanaPopUp.mostrar("reglas", menuAjustes.mostrarReglas());
    });

    botones[guardar].setOnAction(e -> {
      menuAjustes.guardarPartida();
    });

    botones[cargar].setOnAction(e -> {
      menuAjustes.cargarPartida();
    });

    ventana.setOnCloseRequest(e -> ventana.close());

    botones[salir].setOnAction(e -> ventana.close());

    VBox orden = new VBox();
    orden.getChildren().addAll( botones[reglas], botones[guardar], botones[cargar], botones[salir]);
    orden.setAlignment(Pos.CENTER);
    orden.setSpacing(20);

    Scene escena = new Scene(orden);
    ventana.setScene(escena);
    ventana.showAndWait();
  }
}
