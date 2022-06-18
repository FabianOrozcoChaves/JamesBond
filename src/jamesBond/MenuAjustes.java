package jamesBond;

public class MenuAjustes implements Menu {

  public void mostrarOpciones() {
    System.out.println("Menu de ajustes:");
    System.out.println("\t1.Reglas\n");
    System.out.println("\t2.Guardar partida");
    System.out.println("\n3.Cargar partida");
    System.out.println("\n4.Salir");
  }

  public void cargarPartida() {
    System.out.println("Se debe leer de un archivo.");
  }

  public void guardarPartida() {
    System.out.println("Se debe ewscribir en un archivo el estado actual.");
    
  }

  public void mostrarReglas() {
    System.out.println("Mostrando reglas");
  }

  public void salir() {
    System.out.println("Gracias por jugar. Hasta la proxima!");
    System.exit(0);
  }
}
