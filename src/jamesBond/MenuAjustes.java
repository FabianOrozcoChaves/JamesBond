package jamesBond;

public class MenuAjustes implements Menu {
  private String titulo = "Menu de ajustes"; 

  public String [] mostrarOpciones() {
    String [] opciones = new String[4]; 
    int i = 0;
    opciones[i++] = "Reglas";
    opciones[i++] = "Guardar partida";
    opciones[i++] = "Cargar partida";
    opciones[i++] = "Salir";
    return opciones;
  }

  public void cargarPartida() {
    System.out.println("Se debe leer de un archivo.");
  }

  public void guardarPartida() {
    System.out.println("Se debe ewscribir en un archivo el estado actual.");
    
  }

  public String mostrarReglas() {
    String reglas = "Especificaciones:\n2 jugadores.\nSe juega por turnos con un tiempo máximo.\n52 cartas en total (sin jokers).\n24 cartas cada jugador.\n4 cartas comunes en la mesa.\n\nRepartición\nSe reparten 4 cartas boca abajo intercalando jugadores, hasta formar 6 pilas para cada jugador.\nJ1 recibe 4 cartas (forma la 1º pila)\nJ2 recibe 4 cartas (forma la 1º pila)\n. . .\nJ2 recibe 4 cartas (forma la 6º pila)\n\nReglas\nLa pila que está mirando el jugador debe volver a colocarse boca abajo sobre la mesa antes de que pueda recogerla y mirar otra pila.\nSolo se puede intercambiar una carta por turno con las 4 boca arriba del centro de la mesa.\nCuando un jugador tiene 4 cartas del mismo tipo en una pila, coloca su pila boca arriba. Cuando un jugador tiene 4 cartas iguales\nen sus seis pilas debe gritar “James Bond” para ganar el juego.\n\nTurnos:\nSegún el juego original, se juega de manera simultánea, no hay turnos,todos tan rápido como pueden;\nsin embargo, el equipo adaptó los turnos para manejarlos al azar, de manera que\nun jugador puede tener turnos simultáneos para simular la velocidad, teniendo en el anejo de probabilidades\npara que no sea de forma injusta.\nAdemás, cada turno posee un tiempo límite para intercambiar tantas cartas como pueda.";
    return reglas;
  }

  public String getTitulo() {
    return this.titulo;
  }
}
