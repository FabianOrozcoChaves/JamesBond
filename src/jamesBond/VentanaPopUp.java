package jamesBond;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * @class VentanaPopUp permite abrir popups.
 * @details se sobreacarga el metodo mostrar para mostrar diferentes popups.
 */
public class VentanaPopUp {
  static int opcionIngresada = 4;

  /**
   * @brief Abre una ventana y muestra el mensaje que se le indique.
   * @details es un metodo estatico.
   * @param titulo es un string con el tiutlo de la ventana.
   * @param mensaje string con el mensaje a mostrar.
   */
  public static Boolean mostrar(String titulo, String mensaje) {
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
    return false;
  }


  /**
   * @brief sobrecraga del metodo que pemrite mostrar un menu de ajustes.
   * @param menuAjustes instancia de menu de ajustes.
   */
  public static int mostrar(MenuAjustes menuAjustes) {
    Stage ventana = new Stage();
    ventana.initModality(Modality.APPLICATION_MODAL);
    ventana.setTitle(menuAjustes.getTitulo());
    ventana.setWidth(300);
    ventana.setHeight(300);
    opcionIngresada = 4;
    int reglas = 0;
    int guardar = 1;
    int cargar = 2;
    int salir = 3;
    int reanudar = 4;


    String [] opciones = menuAjustes.mostrarOpciones();
    Button [] botones = new Button [opciones.length];
    for (int i = 0; i < opciones.length; ++i) {
      botones[i] = new Button(opciones[i]);
    }

    botones[reglas].setOnAction(e -> {
      opcionIngresada = reglas;
      VentanaPopUp.mostrar("reglas", menuAjustes.mostrarReglas());
    });

    botones[guardar].setOnAction(e -> {
      opcionIngresada = guardar;
      menuAjustes.guardarPartida();
      ventana.close();
    });

    botones[cargar].setOnAction(e -> {
      opcionIngresada = cargar;
      menuAjustes.cargarPartida();
      ventana.close();
    });

    ventana.setOnCloseRequest(e -> ventana.close());

    botones[salir].setOnAction(e ->{
      opcionIngresada = salir;
      ventana.close();
    });

    botones[reanudar].setOnAction(e ->{
      opcionIngresada = reanudar;
      ventana.close();
    });

    VBox orden = new VBox();
    orden.getChildren().addAll(botones);
    orden.setAlignment(Pos.CENTER);
    orden.setSpacing(20);

    Scene escena = new Scene(orden);
    ventana.setScene(escena);
    ventana.showAndWait();
    return opcionIngresada;
  }
}
