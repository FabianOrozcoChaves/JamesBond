package marda;

import java.util.Vector;

public class ContenedorDeCartasMarda {
    protected Vector<GrupoDeCartasMarda> gruposDeCartas;

    public ContenedorDeCartasMarda() {
        gruposDeCartas = new Vector<GrupoDeCartasMarda>();
    }

    public void agregarGrupoDeCartas(GrupoDeCartasMarda grupo){
        this.gruposDeCartas.add(grupo);
    }

    public void quitarGrupoDeCartas(int indice){
        this.gruposDeCartas.removeElementAt(indice);
    }
    public GrupoDeCartasMarda getGrupoDeCartas(int indice){
        return this.gruposDeCartas.get(indice);
    }

    public int size() {
        return gruposDeCartas.size();
    }
}