package marda;

import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @class calse gráfica del tablero marda.
 * @details Construye un layout automatico con secciones definidas para mostrar elementos graficos.
 */
public class VistaTableroMarda {
  private int anchoVentana; // ancho de la ventana en pixeles.
  private int alturaVentana; // altura de la ventana en pixeles.
  private BorderPane estructura; // contiene las 5 secciones del tablero
  private VBox seccionEste; // seccion que esta a la derecha
  private VBox seccionOeste; // seccion que esta a la izquierda
  private VBox seccionCentro; // seccion que esta a en el centro
  private HBox seccionNorte; // seccion que esta a la superior
  private HBox seccionSur; // seccion que esta a la inferior
  private Scene tableroEscena; // escena requeridad por javafx para mostrar los elementos

  /**
   * @brief Construtor
   * @details para poder mostrar en pantalla el tablero es necesario llamar construirTableroMarda(Stage)
   */
  public VistaTableroMarda() {
    this.init();
  }

  /**
   * @brief Construtor
   * @details este metodo 
   */
  public void construirTableroMarda(Stage ventanaPrincipal) {
    this.estructura.setTop(this.seccionNorte);
    this.estructura.setLeft(this.seccionOeste);
    this.estructura.setRight(this.seccionEste);
    this.estructura.setBottom(this.seccionSur);
    this.estructura.setCenter(this.seccionCentro);
    this.tableroEscena = new Scene(this.estructura, this.anchoVentana, this.alturaVentana);
    this.estructura.setBackground(new Background(new BackgroundFill(Color.web("#008000"), new CornerRadii(0), Insets.EMPTY)));

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
    this.alturaVentana = 650;
    this.estructura = new BorderPane();
    this.seccionEste = new VBox();
    this.seccionOeste = new VBox();
    this.seccionCentro  = new VBox();
    this.seccionNorte  = new HBox();
    this.seccionSur  = new HBox();
  }


  public void destruirTableroMarda() {
    this.anchoVentana = 0;
    this.alturaVentana = 0;
    this.estructura = null;
    this.seccionEste = null;
    this.seccionOeste = null;
    this.seccionCentro = null;
    this.seccionNorte = null;
    this.seccionSur = null;
  }

  public int getAnchoVentana() {
    return this.anchoVentana;
  }

  public int getAlturaVentana() {
    return this.alturaVentana;
  }

  public BorderPane getEstructura() {
    return this.estructura;
  }
}
