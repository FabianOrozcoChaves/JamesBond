package marda;

/**
 * @brief Clase que representa un juego genérico de cartas.
 * @details Código reutilizable.
 */
public abstract class JuegoMarda implements IArbitroMarda {
  protected JugadorMarda jugador1;
  protected JugadorMarda jugador2;
  protected ContenedorDeCartasMarda tablero;
  protected JugadorMarda turnoActual;  // jugador con el turno actual.

  /**
   * @brief inicializa los juegos concretos.
   * @brief Código reutilizable. Utilizado por el GUI para realizar métodos iniciales del juego concreto.
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

  /**
   * @brief Método que devuelve el jugador mediante un int como identificador.
   * @param jugador entero que representa el jugador deseado. 1: Jugador 1. diferente de 1: Jugador 2
   * @return JugadorMarda deseado. jugador1 si el parámetro es 1, jugador2 si el parámetro es otro.
   */
  public JugadorMarda getJugadorMarda(int jugador) {
    return (jugador == 1) ? this.jugador1 : this.jugador2;
  }
}
