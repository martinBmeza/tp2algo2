package aed;

public class Ciudad implements Comparable<Ciudad> {

    private int id;
    private int superavit;
    private int ganancia;
    private int perdida;

    public Ciudad(int id) {
        this.id = id;
        this.superavit = 0;
        this.ganancia = 0;
        this.perdida = 0;
    }

    //debug
    public Ciudad(int id, int superavit, int ganancia, int perdida) {
        this.id = id;
        this.superavit = superavit;
        this.ganancia = ganancia;
        this.perdida = perdida;
    }

    public int getID() {
        return id;
    }

    public int getGanancia(){
        return ganancia;
    }

    public int getPerdida(){
        return perdida;
    }
    public int getSuperavit(){
        return superavit;
    }

    public void addToGanancia(int v){
        ganancia+=v;
        superavit+=v;
    }

    public void addToPerdida(int v){
        perdida+=v;
        superavit-=v;
    }

    @Override
    public int compareTo(Ciudad otra) {
        int res = Integer.compare(this.superavit, otra.superavit);
        if (res == 0) {
            res = -Integer.compare(this.id, otra.id); // chequear este signo!
        }
        return res;
    }

}
