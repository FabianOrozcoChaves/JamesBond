package jamesBond;

public class MenuInicio {
  String jugador1;
  String jugador2;
  String turnoInicial;

  public MenuInicio() {

  }

  public void mostrarOpciopnes() {
    System.out.println("Menu de ajustes:");
    System.out.println("\t1.Reglas\n");
    System.out.println("\t2.Guardar partida");
    System.out.println("\n3.Cargar partida");
    System.out.println("\n4.Salir");
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

  public void jugar() {
    // llamar a controlador

  }

  public void asignarTurnoInicial(String jugadorInicial) {
    this.turnoInicial = jugadorInicial;
    String [] x = new String [2];
  }
}
