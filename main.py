package aed;

public class Traslado {
   
    int id;
    int origen;
    int destino;
    int gananciaNeta;
    int timestamp;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.gananciaNeta = gananciaNeta;
        this.timestamp = timestamp;
    }

private class Nodo extends Traslado{
    Nodo espejo;
    Nodo padre;
    Nodo left;
    Nodo right;

    private Nodo(){
        super(InfoTraslado);
        this.espejo = null;
        this.padre = null;
        this.left = null;
        this.right = null;
    }
}


private class NodoCiudad {

    Nodo padre;
    Nodo left;
    Nodo right;
    int ciudadId;
    private NodoCiudad(int ciudadId){
        this.ciudadId = ciudadId;
        this.padre = null;
        this.left = null;
        this.right = null;
    }
}

private class colaPrioridad {}

private class ColaDoble {
    colaPrioridad cola1;
    colaPrioridad cola2;
}

private class ColaSuperavit {}


private class Estadistica {
    colaSuperavit ciudadesSupeavit
    ArrayList ganancias, perdidas;
}


