package aed;

public class Ciudad implements Comparable<Ciudad> {

    private int id;
    private int superavit;
    private int ganancia;
    private int perdida;

    public Ciudad(int id) {//O(1)
        this.id = id;
        this.superavit = 0;
        this.ganancia = 0;
        this.perdida = 0;
    }

    //debug
    public Ciudad(int id, int superavit, int ganancia, int perdida) {//O(1)
        this.id = id;
        this.superavit = superavit;
        this.ganancia = ganancia;
        this.perdida = perdida;
    }

    public int getID() {//O(1)
        return id;
    }

    public int getGanancia(){//O(1)
        return ganancia;
    }

    public int getPerdida(){//O(1)
        return perdida;
    }
    public int getSuperavit(){
        return superavit;//O(1)
    }

    public void addToGanancia(int v){//O(1)
        ganancia+=v;//O(1)
        superavit+=v;//O(1)
    }

    public void addToPerdida(int v){//O(1)
        perdida+=v;
        superavit-=v;
    }

    @Override
    public int compareTo(Ciudad otra) { //O(1)
        int res = Integer.compare(this.superavit, otra.superavit);
        if (res == 0) {
            res = -Integer.compare(this.id, otra.id); 
        }
        return res;
    }

}
