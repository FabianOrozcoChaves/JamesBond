package marda;

public class JugadorMarda extends  ContenedorDeCartasMarda {
    protected String nombre;

    public JugadorMarda() {
        super();
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }
}