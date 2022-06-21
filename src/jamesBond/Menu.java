package jamesBond;

/** 
 * @interface Menu
 * Con esta interfaz se pueden agregar nuevos menus.
 */
public interface Menu {

  /*
   * @brief Metodo que devuelve un arreglo de strings con la lista de las opciones del menu.
   * @return arreglo de Strings
   */
  public String [] mostrarOpciones();

  /*
   * @brief Metodo que llama a la opcion de cargar una partidad.
   */
  public void cargarPartida();

  /*
   * @brief Metodo que muestra las reglas de juego.
   * @return un String con las reglas.
   */
  public String mostrarReglas();

  /*
   * @brief Metodo para cerrar el menu.
   * @return String con un mensaje de cierre.
   */
  public String salir();
}
