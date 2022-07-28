package marda;

/**
 * Clase que representa un juego genérico de cartas.
 */
public abstract class JuegoMarda implements IArbitroMarda {
  protected JugadorMarda jugador1;
  protected JugadorMarda jugador2;
  protected ContenedorDeCartasMarda tablero;
  protected JugadorMarda turnoActual;  // jugador con el turno actual.

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
    constructor.serializarJuego(juego);
    constructor.guardarSerializacion();
  }

  /**
   * @brief Método que carga el estado del juego desde un archivo guardado anteriormente.
   * @param constructor Constructor que deserializa (carga) el juegoMarda por referencia.
   * @param juego juegoMarda que quiere ser deserializado.
   * @return Booleano que indica si el juego ha sido cargado de forma exitosa(true) o no (false).
   */
  public boolean cargar (ConstructorDeserializadorAbstracto constructor, JuegoMarda juego) {
    return constructor.deserializarJuego(juego);
  }

  public JugadorMarda getJugadorMarda(int jugador) {
    if (jugador == 1) {
      return this.jugador1;
    }
    return  this.jugador2;
  }
}
