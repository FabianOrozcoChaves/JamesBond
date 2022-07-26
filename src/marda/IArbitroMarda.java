package marda;

/**
 * Interfaz que valida las jugadas del juegoMARDA.
 */
interface IArbitroMarda {

  /**
   * @brief Evalúa si hay un ganador y retorna la instacia de ser así.
   * @return JugadorMarda que ganó, null en caso de que no exista jugador.
   */
  public JugadorMarda decidirGanador();

  /**
   * @brief Decide si la jugada efectuada es válida.
   * @details el juego concreto debe tener get de la carta escogida y su dueño para verificar el turno actual.
   * @return Booleano que indica si la jugada es válida.
   */
  public Boolean validarJugada();

  /**
   * @brief Cambia de turno entre jugadores.
   */
  public void cambiarTurno();

  /**
   * @brief Metodo que asigna el jugador que tendra el turno inicial.
   * @details modifica el artributo del JuegoMarda <turnoActual>
   * @param nombreJugador String que indica el nombre del jugador con el primer turno.
   */
  public void asignarTurnoInicial(String nombreJugador);
}
