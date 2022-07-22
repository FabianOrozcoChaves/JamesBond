package MARDA;

import java.util.Vector;

class ContenedorDeCartasMarda {
    protected Vector<GrupoDeCartasMarda> gruposDeCartas;

    public void agregarGrupoDeCartas(GrupoDeCartasMarda grupo){
        this.gruposDeCartas.add(grupo);
    }

    public void quitarGrupoDeCartas(int indice){
        this.gruposDeCartas.removeElementAt(indice);
    }
    public GrupoDeCartasMarda getGrupoDeCartas(int indice){
        return this.gruposDeCartas.get(indice);
    }
}