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
   * @brief Construye las secciones del tablero
   * @details las secciones se asigana vacias, para que muestren algo se deben agregar los elementos usando agregarElemento(Pane, elemento)
   * @param ventanaPrincipal es el Stage que se recibe por parametro en el metodo start(Stage) de javaFX en la clase GUI
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

  /**
   * @brief Metodo para agregar elementos graficos a una seccion del tablero.
   * @details Se pueden agregar elementos de javaFX que hereden de node. Estos son cajas de texto, imagenes, botones, etc.
   * @param seccion es la seccion a la que se le desea agregar el elemento. Con los metodo get se obtiene la seccion.
   * @param elemento es el nodo de javaFX que se desea agregar como botones, imegenes, texto y entrada de datos.
   */
  public void agregarElemento(Pane seccion, Node elemento) {
    seccion.getChildren().addAll(elemento);
  }

  /**
   * @brief Devuelve el conetendor de la seccion central
   * @details al devolver el contenedor se puede agregar objetos a este o configurar la posicion y otras caracteristicas de javaFX
   * @return el VBox o conetendor vertical de la seccion central
   */
  public VBox getSeccionCentro() {
    return this.seccionCentro;
  }
  
  /**
   * @brief Devuelve el conetendor de la seccion inferior
   * @details al devolver el contenedor se puede agregar objetos a este o configurar la posicion y otras caracteristicas de javaFX
   * @return el HBox o conetendor horizontal de la seccion inferior
   */
  public HBox getSeccionSur() {
    return this.seccionSur;
  }

  /**
   * @brief Devuelve el conetendor de la seccion superior
   * @details al devolver el contenedor se puede agregar objetos a este o configurar la posicion y otras caracteristicas de javaFX
   * @return el HBox o conetendor horizontal de la seccion superior
   */
  public HBox getSeccionNorte() {
    return this.seccionNorte;
  }

  /**
   * @brief Devuelve el conetendor de la seccion de la izquierda
   * @details al devolver el contenedor se puede agregar objetos a este o configurar la posicion y otras caracteristicas de javaFX
   * @return el VBox o conetendor vertical de la seccion de la izquierda
   */
  public VBox getSeccionOeste() {
    return this.seccionOeste;
  }

  /**
   * @brief Devuelve el conetendor de la seccion de la derecha
   * @details al devolver el contenedor se puede agregar objetos a este o configurar la posicion y otras caracteristicas de javaFX
   * @return el VBox o conetendor vertical de la seccion de la derecha
   */
  public VBox getSeccionEste() {
    return this.seccionEste;
  }


  /**
   * @brief Inicializa las instancias de las secciones
   * @details este solo las inicializ y deja las instancias vacias. Lo elemntos se deben agregar a mano
   */
  protected void init() {
    this.anchoVentana = 1000;
    this.alturaVentana = 650;
    this.estructura = new BorderPane();
    this.seccionEste = new VBox();
    this.seccionOeste = new VBox();
    this.seccionCentro  = new VBox();
    this.seccionNorte  = new HBox();
    this.seccionSur  = new HBox();
  }

  /**
   * @brief Elimina todos los elementos del tablero
   */
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

  /**
   * @brief Devuelve el ancho de la ventana que se esta usando
   * @return un entero con el ancho de la ventana
   */
  public int getAnchoVentana() {
    return this.anchoVentana;
  }

  /**
   * @brief Devuelve la altura de la ventana que se esta usando
   * @return un entero con el ancho de la ventana
   */
  public int getAlturaVentana() {
    return this.alturaVentana;
  }
}
