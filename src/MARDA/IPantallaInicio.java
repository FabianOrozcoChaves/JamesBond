package MARDA;

import javafx.application.Application;
import javafx.stage.Stage;

interface IPantallaInicio {

  public void start(Stage stage);

  public static void main(String[] args) {
    Application.launch(args);
  }
}