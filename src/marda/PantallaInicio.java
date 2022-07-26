package marda;

import javafx.application.Application;
import javafx.stage.Stage;

public class PantallaInicio extends Application {
  public void start(Stage stage) {
    VistaTableroMarda x = new VistaTableroMarda();
    x.init();
    x.construirTableroMarda(stage);
    stage.show();

  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}