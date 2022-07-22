package MARDA;

import ContenedorDeCartasMarda;

abstract class JugadorMarda extends  ContenedorDeCartasMarda {
    protected String nombre;

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }
}