package MARDA;

import javafx.application.Application;
import javafx.stage.Stage;

abstract class IPantallaInicio extends Application {

  public abstract void start(Stage stage);

  public static void main(String[] args) {
    Application.launch(args);
  }
}