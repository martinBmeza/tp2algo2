package aed;

import java.util.ArrayList;

public class Stats {
    ArrayList<Integer> mayoresGanancia; // id s de mayor ganancia
    ArrayList<Integer> mayoresPerdida; // id s de mayor perdida
    ArrayList<Integer> ganancias;
    ArrayList<Integer> perdidas;
    ColaPrioridadCiudades superavits;
    ArrayList<Ciudad> ciudades;
    Integer cantidadDespachados;
    Integer sumaDespachados;

    public Stats(Integer cantCiudades) {
        mayoresGanancia = new ArrayList<>();
        mayoresPerdida = new ArrayList<>();
        ganancias = new ArrayList<>();
        perdidas = new ArrayList<>();
        for (int i = 0; i < cantCiudades; i++) {
            ganancias.add(0);
            perdidas.add(0);
            ciudades.add(new Ciudad(i));
        }
        superavits = new ColaPrioridadCiudades(ciudades);
    }

    private void actualizarMayores(ArrayList<Integer> lista, ArrayList<Integer> mayores, int id, int val) {
        int g = lista.get(id);
        lista.set(id, g + val);
        if(mayores.isEmpty()){
            mayores.add(id);
        }
        else if (mayores.get(0) < lista.get(id)) {
            mayores.clear();
            mayores.add(id);
        } else if (mayores.get(0) == lista.get(id)) {
            mayores.add(id);
        } // si es menor no hago nada
    }

    public void add(Traslado t) {
        //actualizar perdidas y ganancias
        actualizarMayores(ganancias, mayoresGanancia, t.origen, t.gananciaNeta);
        actualizarMayores(perdidas, mayoresPerdida, t.destino, t.gananciaNeta);
        //actualizar superavits
        //sumar en origen
        int v;Nodo nodo;
        Nodo nodoO = nodosCiudades.get(t.origen);
        nodoO.val += t.gananciaNeta;
        nodosCiudades.set(t.origen, nodoO);
        //restar en destino
        Nodo nodoD = nodosCiudades.get(t.destino);
        nodoD.val += t.gananciaNeta;
        nodosCiudades.set(t.destino, nodoD);//revisar si podia ser negativo
        //eliminarlos de superavits
        superavits.eliminarYReodenar(NodoO)
        superavits.eliminarYReodenar(NodoD);
        //agregarlos de vuelta
        superavits.encolar(NodoO)
        superavits.encolar(NodoD);
        //actualizar suma y cantidad despacho
        cantidadDespachados++;
        sumaDespachados += t.gananciaNeta;
        
    }

    public ArrayList<Integer> getCiudadesConMayorGanancias(){
        return mayoresGanancia;
    }

    public ArrayList<Integer> getCiudadesConMayorPerdida(){
        return mayoresPerdida;
    }
    public Integer getPromedio(){
        return sumaDespachados / cantidadDespachados;
    }
}
