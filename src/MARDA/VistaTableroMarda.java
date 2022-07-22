package marda;

import javafx.scene.layout.Pane;
import jamesBond.VistaCarta;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class VistaTableroMarda {
  private int anchoVentana; // ancho de la ventana en pixeles.
  private int alturaVentana; // altura de la ventana en pixeles.
  private BorderPane estructura;
  private VBox seccionEste;
  private VBox seccionOeste;
  private VBox seccionCentro;
  private HBox seccionNorte;
  private HBox seccionSur;
  private Scene tableroEscena;

  VistaTableroMarda() {
    this.init();
  }

  private void construirTablero(Stage ventanaPrincipal) {
    this.estructura.setTop(this.seccionNorte);
    this.estructura.setLeft(this.seccionOeste);
    this.estructura.setRight(this.seccionEste);
    this.estructura.setBottom(this.seccionSur);
    this.estructura.setCenter(this.seccionCentro);
    this.tableroEscena = new Scene(this.estructura, this.anchoVentana, this.alturaVentana);
    ventanaPrincipal.setScene(this.tableroEscena);
  }

  public void agregarElemento(Pane seccion, Node elemento) {
    seccion.getChildren().addAll(elemento);
  }

  /*public VistaCarta [] crearArregloVistaCarta(GrupoDeCartas cartas) {

  }*/

  public VBox getSeccionCentro() {
    return this.seccionCentro;
  }

  public HBox getSeccionSur() {
    return this.seccionSur;
  }

  public HBox getSeccionNorte() {
    return this.seccionNorte;
  }

  public VBox getSeccionOeste() {
    return this.seccionOeste;
  }

  public VBox getSeccionEste() {
    return this.seccionEste;
  }

  public void init() {
    this.anchoVentana = 1000;
    this.alturaVentana = 600;
    this.estructura = new BorderPane();
    this.seccionEste = new VBox();
    this.seccionOeste = new VBox();
    this.seccionCentro  = new VBox();
    this.seccionNorte  = new HBox();
    this.seccionSur  = new HBox();
  }


  public void destruirTablero() {
    this.anchoVentana = 0;
    this.alturaVentana = 0;
    this.estructura = null;
    this.seccionEste = null;
    this.seccionOeste = null;
    this.seccionCentro = null;
    this.seccionNorte = null;
    this.seccionSur = null;
  }

}
