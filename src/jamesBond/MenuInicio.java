package jamesBond;

public class MenuInicio {
  private String jugador1 = "Jugador 1";
  private String jugador2 = "Jugador 2";
  private String turnoInicial = "random";
  private String nombreJuego = "James Bond";
  public MenuInicio(String nombre) {
    this.nombreJuego = nombre;
  }

  public String [] mostrarOpciones() {
    String [] opciones = new String [6];
    int i = 0;
    opciones[i++] = "Jugar";
    opciones[i++] = "Cargar partida";
    opciones[i++] = "Reglas";
    opciones[i++] ="Salir";
    opciones[i++] ="Jugador 1";
    opciones[i++] ="Jugador 2";
    return opciones;
  }

  public void cargarPartida() {
    System.out.println("Se debe leer de un archivo.");
  }

  public String mostrarReglas() {
    String reglas = "Especificaciones:\n2 jugadores.\nSe juega por turnos con un tiempo máximo.\n52 cartas en total (sin jokers).\n24 cartas cada jugador.\n4 cartas comunes en la mesa.\n\nRepartición\nSe reparten 4 cartas boca abajo intercalando jugadores, hasta formar 6 pilas para cada jugador.\nJ1 recibe 4 cartas (forma la 1º pila)\nJ2 recibe 4 cartas (forma la 1º pila)\n. . .\nJ2 recibe 4 cartas (forma la 6º pila)\n\nReglas\nLa pila que está mirando el jugador debe volver a colocarse boca abajo sobre la mesa antes de que pueda recogerla y mirar otra pila.\nSolo se puede intercambiar una carta por turno con las 4 boca arriba del centro de la mesa.\nCuando un jugador tiene 4 cartas del mismo tipo en una pila, coloca su pila boca arriba. Cuando un jugador tiene 4 cartas iguales\nen sus seis pilas debe gritar “James Bond” para ganar el juego.\n\nTurnos:\nSegún el juego original, se juega de manera simultánea, no hay turnos,todos tan rápido como pueden;\nsin embargo, el equipo adaptó los turnos para manejarlos al azar, de manera que\nun jugador puede tener turnos simultáneos para simular la velocidad, teniendo en el anejo de probabilidades\npara que no sea de forma injusta.\nAdemás, cada turno posee un tiempo límite para intercambiar tantas cartas como pueda.";
    return reglas;
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

  public String getNombreJUego() {
    return this.nombreJuego;
  }
}
