package jamesBond;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUp {
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
}
