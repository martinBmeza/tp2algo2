package aed;

import java.util.ArrayList;

public class Stats {
    private ArrayList<Integer> mayoresGanancia; // id s de mayor ganancia
    private ArrayList<Integer> mayoresPerdida; // id s de mayor perdida
    private ArrayList<Integer> ganancias;
    private ArrayList<Integer> perdidas;
    // ColaSuperavit
    // array de nodos

    public Stats(Integer C) {
        this.mayoresGanancia = new ArrayList<>();
        this.mayoresPerdida = new ArrayList<>();
        this.ganancias = new ArrayList<>();
        this.perdidas = new ArrayList<>();
        for (int i = 0; i < C; i++) {
            this.ganancias.add(0);
            perdidas.add(0);
        }
    }

    private void actualizarMayores(ArrayList<Integer> lista, ArrayList<Integer> mayores, int id, int val) {
        int g = lista.get(id);
        lista.set(id, g + val);
        if(mayores.isEmpty()){
            mayores.add(id);return;
        }
        if (mayores.get(0) < lista.get(id)) {
            mayores.clear();
            mayores.add(id);
        } else if (mayores.get(0) == lista.get(id)) {
            mayores.add(id);
        } // si es menor no hago nada
    }

    public void add(Traslado t) {
        actualizarMayores(ganancias, mayoresGanancia, t.origen, t.gananciaNeta);
        actualizarMayores(perdidas, mayoresPerdida, t.destino, t.gananciaNeta);
    }

    public ArrayList<Integer> getCiudadesConMayorGanancias(){
        return mayoresGanancia;
    }

    public ArrayList<Integer> getCiudadesConMayorPerdida(){
        return mayoresPerdida;
    }





}
