package jamesBond;
import marda.*;
import marda.Carta;

/**
 * @brief Clase JamesBond, controladora
 * @details Encargada de control de las jugadas, turnos y determinar ganadores.
 */
public class JamesBond extends marda.JuegoMarda{
  
  // ATRIBUTOS DE JAMESBOND PROPIOS
  private GrupoDeCartasMarda mazo;
  private int temporizador;  // representa la cantidad de segundos del temporizador para cada turno.

  // MÉTODOS DE IARBITRO

  /**
   * @brief Evalúa si hay un ganador y retorna la instacia de ser así.
   * @return JugadorMarda que ganó, null en caso de que no exista jugador.
   */
  @Override
  public JugadorMarda decidirGanador() {
    // jugador 1 es el ganador
    if (revisarPilas((Jugador) this.jugador1)) {
      return this.jugador1;
    // jugador 2 es el ganador
    } else if (revisarPilas((Jugador) this.jugador2)) {
      return this.jugador2;
    // ninguno ha ganado
    } else {
      return null;
    }
  }

  /**
   * @brief Decide si la jugada efectuada es válida dependiendo del turno actual
   * @return Booleano que indica si la jugada es válida. True en caso de que sea el turno del jugador indicado por parámetro.
   */
  @Override
  public Boolean validarJugada(JugadorMarda jugador) {
    return turnoActual.equals(jugador);
  }

  /**
   * @brief Cambia de turno entre jugadores.
   */
  @Override
  public void cambiarTurno() {
    if (this.turnoActual.equals(jugador1))
      turnoActual = jugador2;
    else
      turnoActual = jugador1;
  }

  /**
   * @brief Metodo que asigna el jugador que tendra el turno inicial.
   * @details modifica el artributo del JuegoMarda <turnoActual>
   * @param nombreJugador String que indica el nombre del jugador con el primer turno
   */
  @Override
  public void asignarTurnoInicial(String nombreJugador) {
    if (nombreJugador.equals(jugador1.getNombre()))
      this.turnoActual = jugador1;
    else if (nombreJugador.equals(jugador2.getNombre()))
      this.turnoActual = jugador2;
    else
      randomizarTurno();
  }

  // MÉTODOS DE JUEGOMARDA
  /**
   * @brief inicializa el juego JamesBond. Reparte las cartas
   */
  @Override
  public void start() {
    // antes de llamar a start se tuvo que inicializar los turnos y setear el temporizador
    this.mazo.barajar();
    repartir();
  }

  // MÉTODOS DE JAMESBOND PROPIOS.
  /**
   * @brief Constructor. Inicializa atributos de la clase
   */
  public JamesBond() {
    this.tablero = new Tablero();
    mazo = new GrupoDeCartasMarda();
    llenarMazo();
    temporizador = 0;
  }

  /**
   * @brief genera TODAS las cartas del mazo.
   * @details genera según la bajara francesa
   */
  public void llenarMazo() {
    this.mazo.clear();
	  char[] palos = {'P', 'D', 'C', 'T'};
    for(char c:palos){
	    for(int j = 1; j < 14; j++){
	      this.mazo.agregarCarta(new Carta( c, j ));
	    }
	  }
  }

  /**
   * @brief Reparte las cartas del mazo, 24 a cada jugador y 4 al tablero.
   */
  private void repartir(){
    for (int i = 0; i < 6; i++) {
      GrupoDeCartasMarda pilaJugador1 = new GrupoDeCartasMarda(4);
      GrupoDeCartasMarda pilaJugador2 = new GrupoDeCartasMarda(4);
      for (int j = 0; j < 4; j++) {
        pilaJugador1.agregarCarta(this.mazo.topNpop());
        pilaJugador2.agregarCarta(this.mazo.topNpop());
      }
      ((Jugador) jugador1).agregarPila(pilaJugador1);
      ((Jugador) jugador2).agregarPila(pilaJugador2);
    }
    // llena el mazo de cartas en común.
    for(int i = 0; i < 4; i++) {
      tablero.getGrupoDeCartas(0).agregarCarta(this.mazo.topNpop());
    }
  }

   /**
   * @brief Método que intercambia las cartas entre el tablero y el jugador
   * @param posTablero la posicion de la carta en el tablero 
   * @param posJugador La posicion de la carta del jugador
   */
  public void intercambiarCarta(int posTablero, int posJugador) {
    Carta cartaJugador = tablero.getGrupoDeCartas(0).getCarta(posTablero);
    Carta cartaTablero;

    if (validarJugada(jugador1))
      cartaTablero = ((Jugador) jugador1).intercambiarCarta(cartaJugador, posJugador);
    else
      cartaTablero = ((Jugador) jugador2).intercambiarCarta(cartaJugador, posJugador);
  
    ((Tablero) tablero).cambiarCarta(posTablero, cartaTablero);
  }
  
  /**
   * @brief Método encargado de inicializar los turnos y asignar los nombres a los jugadores
   * @param nombreJugador1 El nombre para el jugador 1 
   * @param nombreJugador2 El nombre para el jugador 2
   * @param nombreJugador String que indica el nombre del jugador con el primer turno
   */
  public void inicializarTurnos(String nombreJugador1, String nombreJugador2, String jugadorInicial) {
    this.jugador1 = new Jugador(nombreJugador1);
    this.jugador2 = new Jugador(nombreJugador2);
    // tablero.quitarCartas();

    asignarTurnoInicial(jugadorInicial);
  }
  /**
   * @brief Método que elige el primer turno al azar
   */
  public void randomizarTurno() {
    int turnoAleatorio = (int) (Math.random() * 2);
    turnoActual = turnoAleatorio == 0 ? jugador1 : jugador2;
  }

  /**
   * @brief Método que revisa las pilas de un jugador
   * @return boolean true si las cartas del jugador son iguales, false en caso contrario
   */
  public boolean revisarPilas(Jugador jugador) {
    return jugador.pilasIguales();
  }

  /**
   * @brief Método que asigna el tiempo de duración de cada turno
   */
  public void setTemporizador(int segundos) {
    temporizador = segundos;
  }

  /**
   * @brief Método que devuelve el tiempo de duración de cada turno
   * @return int del tiempo en segundos
   */
  public int getTemporizador() {
    return temporizador;
  }

  /**
   * @brief Método que devuelve el tablero
   * @return objeto Tablero del tablero creado para la partida
   */
  public Tablero getTablero() {
    return (Tablero) this.tablero;
  }

  /**
   * @brief Método que devuelve el jugador el cual tiene el turno actual
   * @return objeto Jugador del jugador con el turno actual
   */
  public Jugador getTurnoActual() {
    return (Jugador) this.turnoActual;
  }
  
  /**
   * @brief Método encargado de inicializar el estado del juego en el punto donde se guardo
   * @param jugador1 Jugador que representa al jugador 1
   * @param jugador2 Jugador que representa al jugador 2
   * @param turnoInicial Jugador que tiene el turno actual
   * @param temporizador int que representa la duracion de los turnos
   * @param tablero Tablero que muestra
   */
  public void cargarEstado(Jugador jugador1, Jugador jugador2, Jugador turnoActual, int temporizador, Tablero tablero) {
    this.jugador1 = jugador1;
    this.jugador2 = jugador2;
    this.turnoActual = turnoActual;
    this.temporizador = temporizador;
    this.tablero = tablero;
  }
}
