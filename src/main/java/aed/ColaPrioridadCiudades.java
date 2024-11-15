package aed;

import java.util.ArrayList;

public class ColaPrioridadCiudades{
    /* Cola de prioridad implementada sobre HEAP */
    private ArrayList<Handle> cola;
    private ArrayList<Integer> ciudades; //aca me guardo la relacion ciudad-posicion en el array
    private int nelems; 

    public class Handle {
        Ciudad value;
        int index;

        public Handle(Ciudad value, int index) {
            this.value = value;
            this.index = index;
        }

        public int getValue() {
            return value.getID();
        }
    }

    // public ColaPrioridadCiudades(int cantCiudades){
    //     cola = new ArrayList<Handle>();
    //     ciudades = new ArrayList<Integer>(cantCiudades);
    //     nelems = 0;
    // }

    public ColaPrioridadCiudades(ArrayList<Ciudad> seq_ciudades){
        nelems = 0;
        cola = new ArrayList<Handle>();
        ciudades = new ArrayList<Integer>();
        for (int i = 0; i < seq_ciudades.size(); i++){
            cola.add(new Handle(seq_ciudades.get(i), i));
            ciudades.add(i);
            nelems++;
        }
        for (int i = nelems/2 - 1; i >= 0; i--){
            bajar(i);
        }
    }

    public void bajar(int i){
        int masgrande = i;
        int hijoIzq = 2*i + 1;
        int hijoDer = 2*i + 2;
        if (hijoIzq < nelems && cola.get(hijoIzq).value.compareTo(cola.get(masgrande).value)>0){
            masgrande = hijoIzq;
        }

        // Comprobar si el hijo derecho es mayor que el nodo más grande encontrado hasta ahora
        if (hijoDer < nelems && cola.get(hijoDer).value.compareTo(cola.get(masgrande).value) > 0) {
            masgrande = hijoDer;
        }

        // Si el nodo actual no es el mayor, intercambiar con el mayor y continuar ajustando
        if (masgrande != i) {
            swap(i, masgrande);
            bajar(masgrande); // Llamada recursiva para el nodo que fue intercambiado
        }
    }

    public int nelems(){
        return nelems;
    }

    private void swap(int i, int j){
        Handle aux_i = cola.get(i);
        Handle aux_j = cola.get(j);
        ciudades.set(aux_i.value.getID(), j);
        ciudades.set(aux_j.value.getID(), i);
        cola.set(i, aux_j);
        cola.set(j, aux_i);
    }
    
    public void encolar(Ciudad ciudad){
        Handle handle = new Handle(ciudad, nelems);
        cola.add(handle);
        ciudades.set(ciudad.getID(), nelems);
        int dedito = nelems;
        while(dedito > 0){
            int padre = (dedito-1)/2; // floor operation
            if(cola.get(dedito).value.compareTo(cola.get(padre).value) > 0){
                swap(dedito, padre);
                dedito = padre;
            }else{
                break;
            }
        }
        nelems++;
    }
    public Ciudad desencolar(){
        if (nelems == 0){
            return null;
        }
        if (nelems == 1){
            nelems--;
            return cola.remove(0).value;
        }
        Handle salida = cola.get(0);
        cola.set(0, cola.remove(cola.size()-1)); // O(1)
        nelems--;
        int dedito = 0;
        while(dedito < nelems){
            int hijoIzq = 2*dedito + 1;
            int hijoDer = 2*dedito + 2;
            if(hijoIzq >= nelems){
                break;
            }
            int mayor = hijoIzq;
            if(hijoDer < nelems && cola.get(hijoDer).value.compareTo(cola.get(hijoIzq).value) > 0){
                mayor = hijoDer;
            }
            if(cola.get(dedito).value.compareTo(cola.get(mayor).value) < 0){
                swap(dedito, mayor);
                dedito = mayor;
            }else{
                break;
            }
        }
        return salida.value;
    }

    public Handle proximo(){
        return cola.get(0);
    }

    public void borrar_ciudad(Ciudad ciudad){
        int i = ciudades.get(ciudad.getID());
        borrar_indice(i);
    }

    public void borrar_indice(int i){
        if (i == cola.size()-1){
            cola.remove(cola.size()-1);
            nelems--;
            return;
        }
        //if (i >= cola.size()){
        //    return;
        //}

        cola.set(i, cola.remove(cola.size()-1)); // O(1)
        nelems--;
        int dedito = i;
        while(dedito < nelems){
            int hijoIzq = 2*dedito + 1;
            int hijoDer = 2*dedito + 2;
            if(hijoIzq >= nelems){
                break;
            }
            int mayor = hijoIzq;
            if(hijoDer < nelems && cola.get(hijoDer).value.compareTo(cola.get(hijoIzq).value) > 0){
                mayor = hijoDer;
            }
            if(cola.get(dedito).value.compareTo(cola.get(mayor).value) < 0){
                swap(dedito, mayor);
                dedito = mayor;
            }else{
                break;
            }
        }
    }
}
