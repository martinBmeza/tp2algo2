package aed;

import java.util.ArrayList;

public class Stats {
    private ArrayList<Integer> mayoresGanancia; // id s de mayor ganancia
    private ArrayList<Integer> mayoresPerdida; // id s de mayor perdida
    private ColaPrioridadCiudades superavits;
    private ArrayList<Ciudad> ciudades;
    private Integer cantidadDespachados;
    private Integer sumaDespachados;

    public Stats(Integer cantCiudades) {
        cantidadDespachados = 0;
        sumaDespachados = 0;
        mayoresGanancia = new ArrayList<>();
        mayoresPerdida = new ArrayList<>();
        ciudades = new ArrayList<>();
        for (int i = 0; i < cantCiudades; i++) {//O(|cantCiudades|)
            ciudades.add(new Ciudad(i));//O(1)
        }
        superavits = new ColaPrioridadCiudades(ciudades);//O(|cantCiudades|)
    }


    public void add(Traslado t) {//O(log|C|)
        //actualizar perdidas y ganancias
        Integer idO = t.origen;
        int g = this.ciudades.get(idO).getGanancia();
        g+=t.gananciaNeta;
        Integer idD = t.destino;
        int p = this.ciudades.get(idD).getPerdida();
        p+= t.gananciaNeta;        
        
        if(mayoresGanancia.isEmpty()){//O(1)
            mayoresGanancia.add(idO);
        }
        else if (ciudades.get(mayoresGanancia.get(0)).getGanancia() < g) {//O(1)
            mayoresGanancia.clear();//O(1)
            mayoresGanancia.add(idO);//O(1)
        } else if (ciudades.get(mayoresGanancia.get(0)).getGanancia()  == g) {//O(1)
            mayoresGanancia.add(idO);//O(1)
        } // si es menor no hago nada
        
        if(mayoresPerdida.isEmpty()){//O(1)
            mayoresPerdida.add(idD);//O(1)
        }
        else if (ciudades.get(mayoresPerdida.get(0)).getPerdida() < p) {//O(1)
            mayoresPerdida.clear();//O(1)
            mayoresPerdida.add(idD);//O(1)
        } else if (ciudades.get(mayoresPerdida.get(0)).getPerdida() == p) {
            mayoresPerdida.add(idD);//O(1)
        } // si es menor no hago nada
        
        ciudades.get(idO).addToGanancia(t.gananciaNeta);//O(1)
        ciudades.get(idD).addToPerdida(t.gananciaNeta);//O(1)
        
        //actualizar superavits
        //sumar en origen
        Ciudad ciudadO = ciudades.get(t.origen);//O(1)
        //eliminarlos de superavits
        superavits.borrar_ciudad(ciudadO);//O(log|C|)
        superavits.encolar(ciudadO);//O(log|C|)
        Ciudad ciduadD = ciudades.get(t.destino);
        superavits.borrar_ciudad(ciduadD);//O(log|C|)
        superavits.encolar(ciduadD);//O(log|C|)
        //actualizar suma y cantidad despacho
        cantidadDespachados++;//O(1)
        sumaDespachados += t.gananciaNeta;//O(1)
        
    }

    public int getMayorSuperavit(){
        return superavits.proximo().value.getID();//O(1)
    }

    public ArrayList<Integer> getCiudadesConMayorGanancias(){//O(1)
        return mayoresGanancia;//O(1)
    }

    public ArrayList<Integer> getCiudadesConMayorPerdida(){//O(1)
        return mayoresPerdida;//O(1)
    }
    public Integer getPromedio(){//O(1)
        return sumaDespachados / cantidadDespachados;//O(1)
    }
}
