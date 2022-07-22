package MARDA;

// import GrupoDeCartas;

abstract class ContenedorDeCartasMarda {
    protected Vector<GrupoDeCartas> gruposDeCartas;

    public abstract void agregarGrupoDeCartas(GrupoDeCartas grupo);
    public abstract void quitarGrupoDeCartas(int indice);
    public abstract GrupoDeCartas getGrupoDeCartas(int indice);
}