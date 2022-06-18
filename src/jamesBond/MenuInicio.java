package jamesBond;


public class MenuInicio {
  private String jugador1 = "Jugador 1";
  private String jugador2 = "Jugador 2";
  private String turnoInicial;
  private String  NombreJuego = "James Bond";
  public MenuInicio() {

  }

  public String [] mostrarOpciones() {
    String [] opciones = new String [6];
    int i = 0;
    opciones[i++] = "1.Jugar";
    opciones[i++] = "3.Cargar partida";
    opciones[i++] = "1.Reglas";
    opciones[i++] ="4.Salir";
    opciones[i++] ="Jugador 1";
    opciones[i++] ="Jugador 2";
    return opciones;
  }

  public void cargarPartida() {
    System.out.println("Se debe leer de un archivo.");
  }

  public void mostrarReglas() {
    System.out.println("Mostrando reglas");
  }

  public void salir() {
    System.out.println("Gracias por jugar. Hasta la proxima!");
    System.exit(0);
  }

  public void asignarNombreJ1(String nombre) {
    this.jugador1 = nombre;
  }

  public void asignarNombreJ2(String nombre) {
    this.jugador2 = nombre;
  }

  public String getJugadorJ1() {
    return jugador1;
  }

  public String getJugadorJ2() {
    return jugador2;
  }

  public String getTurnoInicial() {
    return turnoInicial;
  }

  public void jugar() {
    // llamar a controlador

  }

  public void asignarTurnoInicial(String jugadorInicial) {
    this.turnoInicial = jugadorInicial;
  }
}
