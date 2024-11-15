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
        Integer idO = t.origen;
        int g = this.ciudades.get(idO).getGanancia();
        g+=t.gananciaNeta;
        Integer idD = t.destino;
        int p = this.ciudades.get(idD).getPerdida();
        p+= t.gananciaNeta;        
        
        if(mayoresGanancia.isEmpty()){
            mayoresGanancia.add(idO);
        }
        else if (ciudades.get(mayoresGanancia.get(0)).getGanancia() < g) {
            mayoresGanancia.clear();
            mayoresGanancia.add(idO);
        } else if (ciudades.get(mayoresGanancia.get(0)).getGanancia()  == g) {
            mayoresGanancia.add(idO);
        } // si es menor no hago nada
        
        if(mayoresPerdida.isEmpty()){
            mayoresPerdida.add(idD);
        }
        else if (ciudades.get(mayoresPerdida.get(0)).getPerdida() < p) {
            mayoresPerdida.clear();
            mayoresPerdida.add(idD);
        } else if (ciudades.get(mayoresPerdida.get(0)).getPerdida() == p) {
            mayoresPerdida.add(idD);
        } // si es menor no hago nada
        
        ciudades.get(idO).addToGanancia(t.gananciaNeta);
        ciudades.get(idD).addToPerdida(t.gananciaNeta);
        
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

    public int getMayorSuperavit(){
        return superavits.proximo().value.getID();
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
