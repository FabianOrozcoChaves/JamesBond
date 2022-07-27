package marda;

import java.util.Vector;

public class ContenedorDeCartasMarda {
    protected Vector<GrupoDeCartasMarda> gruposDeCartas;

    /**
     * @brief Constructor crea el vector de gruposDeCartas.
     */
    public ContenedorDeCartasMarda() {
        gruposDeCartas = new Vector<GrupoDeCartasMarda>();
    }

    /**
     * @brief Método que agrega un GrupoDeCartasMarda al vector gruposDeCartas.
     * @param grupo GrupoDeCartasMarda a agregar.
     */
    public void agregarGrupoDeCartas(GrupoDeCartasMarda grupo){
        this.gruposDeCartas.add(grupo);
    }

    /**
     * @brief Método que quita un GrupoDeCartasMarda del vector gruposDeCartas.
     * @param indice Posicion del GrupoDeCartasMarda a eliminar.
     */
    public void quitarGrupoDeCartas(int indice){
        this.gruposDeCartas.removeElementAt(indice);
    }

    /**
     * @brief Método que retorna un GrupoDeCartasMarda del vector gruposDeCartas.
     * @param indice Posicion del GrupoDeCartasMarda a retornar.
     * @return GrupoDeCartasMarda en la posicion selecionada.
     */
    public GrupoDeCartasMarda getGrupoDeCartas(int indice){
        return this.gruposDeCartas.get(indice);
    }

    /**
     * @brief Método que retorna el tamaño del vector gruposDeCartas.
     * @return int con el tamaño del vector.
     */
    public int size() {
        return gruposDeCartas.size();
    }
}