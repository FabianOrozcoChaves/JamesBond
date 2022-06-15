class Prueba {
  String nombre = "";

  Prueba(String nombre) {
    this.nombre = nombre;
  }

  void setNombre(String nuevo) {
    this.nombre = nuevo;
  }

  String getNombre() {
    return this.nombre;
  }
  public static void main(String[] args) {
    Prueba p1 = new Prueba("Carlos");
    Prueba p2 = new Prueba("Daniel");
    Prueba p1Ptr = p1;

    System.out.println (p1 == p2 ? ("p1 es igual a p2") : ("p1 es diferente a p2"));
    System.out.println (p1 == p1Ptr ? ("p1 es igual a p1Ptr") : ("p1 es diferente a p1Ptr"));

    p1Ptr.setNombre("julian");
    System.out.println("p1: " + p1.getNombre() + " | p1Ptr: " + p1Ptr.getNombre());

  }
}