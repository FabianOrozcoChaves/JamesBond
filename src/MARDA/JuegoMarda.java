package marda;

/**
 * Clase que representa un juego genérico de cartas.
 */
abstract class JuegoMarda implements IArbitroMarda {
  protected JugadorMarda jugador1;
  protected JugadorMarda jugador2;
  protected ContenedorDeCartasMarda tablero;
  protected JugadorMarda turnoActual;  // jugador con el turno actual.

  /**
   * @brief método plantilla que corre el juego, se encarga del flujo general.
   */
  abstract public void run(); // método plantilla

  /**
   * @brief inicializa los juegos concretos.
   */
  abstract public void start(); // método del concreto

  /**
   * Método que guarda el estado del juego en un archivo.
   * @param constructor Constructor que serializa (guarda) el juegoMarda.
   * @param juego juegoMarda que quiere ser serializado.
   */
  public void guardar(ConstructorSerializadorAbstracto constructor, JuegoMarda juego) {
    // TODO
  }

  /**
   * @brief Método que carga el estado del juego desde un archivo guardado anteriormente.
   * @param constructor Constructor que deserializa (carga) el juegoMarda por referencia.
   * @param juego juegoMarda que quiere ser deserializado.
   */
  public void cargar (ConstructorDeserializadorAbstracto constructor, JuegoMarda juego) {
    // TODO
  }
}
