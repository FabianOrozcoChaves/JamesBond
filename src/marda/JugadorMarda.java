package marda;

public class JugadorMarda extends  ContenedorDeCartasMarda {
    protected String nombre;

    /**
     * @brief Constructor por omision.
     */
    public JugadorMarda() {
        super();
    }

    /**
     * @brief Método que edita el nombre del jugador.
     * @param String representa el nuevo nombre del jugador.
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * @brief Método que retorna el nombre del jugador.
     * @return String el nombre del jugador.
     */
    public String getNombre(){
        return this.nombre;
    }
}