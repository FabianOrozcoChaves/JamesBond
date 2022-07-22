package marda;

/**
 * Clase que representa un juego genérico de cartas.
 */
abstract class JuegoMarda implements IArbitroMarda {
  protected JugadorMarda jugador1;
  protected JugadorMarda jugador2;
  protected ContenedorDeCartasMarda tablero;
  protected JugadorMarda turnoActual;
}
