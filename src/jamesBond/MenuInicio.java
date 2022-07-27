package jamesBond;

/**
 * @class MenuInicio.
 * Esta clase le ofrece al GUI los metodos necesarios para que el menu de inicio reaccione a los eventos.
 */
public class MenuInicio {
  private String jugador1 = "Jugador 1";
  private String jugador2 = "Jugador 2";
  private String turnoInicial = "random";
  private String nombreJuego = "James Bond";
  private int temporizador = 15;
  /**
   * @brief Constructor
   * @param nombre es un string con el nombre del juego
   */
  public MenuInicio(String nombre) {
    this.nombreJuego = nombre;
  }

  /**
   * @brief Metodo que indica las opciones que contiene el menu de inicio.
   * @return un arreglo de hileras donde cada hilera es una opcion del menu.
   */
  public String [] mostrarOpciones() {
    String [] opciones = new String [7];
    int i = 0;
    opciones[i++] = "Jugar";
    opciones[i++] = "Cargar partida";
    opciones[i++] = "Reglas";
    opciones[i++] ="Salir";
    opciones[i++] ="Jugador 1";
    opciones[i++] ="Jugador 2";
    opciones[i++] ="Tiempo por turno (segundos):";
    return opciones;
  }

  /**
   * @brief Metodo para cargar una partida que fue guardad previeamante.
   */
  public void cargarPartida() {
    System.out.println("Se debe leer de un archivo.");
  }

  /**
   * @brief Metodo que muestra las reglas del juego.
   * @return retorna un string con las reglas del juego.
   */
  public String mostrarReglas() {
    String reglas = "Especificaciones:\n2 jugadores.\nSe juega por turnos con un tiempo máximo.\n52 cartas en total (sin jokers).\n24 cartas cada jugador.\n4 cartas comunes en la mesa.\n\nRepartición\nSe reparten 4 cartas boca abajo intercalando jugadores, hasta formar 6 pilas para cada jugador.\nJ1 recibe 4 cartas (forma la 1º pila)\nJ2 recibe 4 cartas (forma la 1º pila)\n. . .\nJ2 recibe 4 cartas (forma la 6º pila)\n\nReglas\nLa pila que está mirando el jugador debe volver a colocarse boca abajo sobre la mesa antes de que pueda recogerla y mirar otra pila.\nSolo se puede intercambiar una carta por turno con las 4 boca arriba del centro de la mesa.\nCuando un jugador tiene 4 cartas del mismo tipo en una pila, coloca su pila boca arriba. Cuando un jugador tiene 4 cartas iguales\nen sus seis pilas debe gritar “James Bond” para ganar el juego.\n\nTurnos:\nSegún el juego original, se juega de manera simultánea, no hay turnos,todos tan rápido como pueden;\nsin embargo, el equipo adaptó los turnos para manejarlos al azar, de manera que\nun jugador puede tener turnos simultáneos para simular la velocidad, teniendo en el anejo de probabilidades\npara que no sea de forma injusta.\nAdemás, cada turno posee un tiempo límite para intercambiar tantas cartas como pueda.";
    return reglas;
  }

  /** 
   * @brief Permite asignarle un nombre al jugador 1.
   * @deaails modifica el atributo del menu this.jugador1.
   * @param nombre es el string con el nombre que se desea asignar.
  */
  public void asignarNombreJ1(String nombre) {
    this.jugador1 = nombre;
  }

  /** 
   * @brief Permite asegnarle un nombre al jugador 2.
   * @deaails modifica el atributo del menu this.jugador2.
   * @param nombre es el string con el nombre que se desea asignar.
  */
  public void asignarNombreJ2(String nombre) {
    this.jugador2 = nombre;
  }

  /**
   * @brief Metodo para acceder al nombre del jugador 1
   * @return un string con el nombre del jugador 1
   */
  public String getJugadorJ1() {
    return jugador1;
  }

  /**
   * @brief Metodo para acceder al nombre del jugador 2.
   * @return un string con el nombre del jugador 2.
   */
  public String getJugadorJ2() {
    return jugador2;
  }

  /**
   * @brief Metodo para acceder al nombre del jugador que tiene el turno inicial.
   * @return un string con el nombre del jugador que inicia el juego.
   */
  public String getTurnoInicial() {
    return turnoInicial;
  }

  /**
   * @brief Metodo que ejecuta el juego.
   */
  public void jugar() {
    // llamar a controlador

  }

  /**
   * @brief Metodo que asigna el jugador que tendra el turno inicial.
   * @deatils modifica el artributo del menu this.turnoInicial
   * @param jugadorInicial string cone l niombre del jugador con el primer turno
   */
  public void asignarTurnoInicial(String jugadorInicial) {
    this.turnoInicial = jugadorInicial;
  }

  /**
   * @brief Get del nombre del juego.
   * @return string con el nombre del juego
   */
  public String getNombreJUego() {
    return this.nombreJuego;
  }

  public void asignarTemporizador(int temp) {
    this.temporizador = temp;
  }

  public int getTemporizador() {
    return this.temporizador;
  }
}
