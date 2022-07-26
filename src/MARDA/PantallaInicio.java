package marda;

import javafx.application.Application;
import javafx.stage.Stage;

public abstract class PantallaInicio extends Application {
  public abstract void start(Stage stage);
  public static void main(String[] args) {
    System.out.println("James Bond");
    Application.launch(args);
  }
}