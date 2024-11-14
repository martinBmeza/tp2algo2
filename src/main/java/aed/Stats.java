package aed;

import java.util.ArrayList;

public class Stats {
    ArrayList<Integer> mayoresGanancia; // id s de mayor ganancia
    ArrayList<Integer> mayoresPerdida; // id s de mayor perdida
    ColaPrioridadCiudades superavits;
    ArrayList<Ciudad> ciudades;
    Integer cantidadDespachados;
    Integer sumaDespachados;

    public Stats(Integer cantCiudades) {
        cantidadDespachados = 0;
        sumaDespachados = 0;
        mayoresGanancia = new ArrayList<>();
        mayoresPerdida = new ArrayList<>();
        ciudades = new ArrayList<>();
        for (int i = 0; i < cantCiudades; i++) {
            ciudades.add(new Ciudad(i));
        }
        superavits = new ColaPrioridadCiudades(ciudades);
    }


    public void add(Traslado t) {
        //actualizar perdidas y ganancias
        Integer idO = t.origen;;
        ciudades.get(idO).addToGanancia(t.gananciaNeta);
        Integer idD = t.destino;
        ciudades.get(idD).addToPerdida(t.gananciaNeta);

        
        int g = this.ciudades.get(idO).getGanancia();
        if(mayoresGanancia.isEmpty()){
            mayoresGanancia.add(idO);
        }
        else if (mayoresGanancia.get(0) < g) {
            mayoresGanancia.clear();
            mayoresGanancia.add(idO);
        } else if (mayoresGanancia.get(0) == g) {
            mayoresGanancia.add(idO);
        } // si es menor no hago nada

        int p = this.ciudades.get(idD).getPerdida();
        if(mayoresPerdida.isEmpty()){
            mayoresPerdida.add(idD);
        }
        else if (mayoresPerdida.get(0) < p) {
            mayoresPerdida.clear();
            mayoresPerdida.add(idD);
        } else if (mayoresPerdida.get(0) == p) {
            mayoresPerdida.add(idD);
        } // si es menor no hago nada


        //actualizar superavits
        //sumar en origen
        Ciudad ciudadO = ciudades.get(t.origen);
        //eliminarlos de superavits
        superavits.borrar_ciudad(ciudadO);
        superavits.encolar(ciudadO);
        Ciudad ciduadD = ciudades.get(t.destino);
        superavits.borrar_ciudad(ciduadD);
        superavits.encolar(ciduadD);
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
