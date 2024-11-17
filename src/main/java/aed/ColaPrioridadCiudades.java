package aed;

import java.util.ArrayList;

public class ColaPrioridadCiudades{
    /* Cola de prioridad implementada sobre HEAP */
    private ArrayList<Handle> cola;
    private ArrayList<Integer> ciudades; //aca me guardo la relacion ciudad-posicion en el array

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

    public ColaPrioridadCiudades(ArrayList<Ciudad> seq_ciudades){
        cola = new ArrayList<Handle>();
        ciudades = new ArrayList<Integer>();
        for (int i = 0; i < seq_ciudades.size(); i++){
            cola.add(new Handle(seq_ciudades.get(i), i));
            ciudades.add(i);
        }
        for (int i = cola.size()/2 - 1; i >= 0; i--){
            bajar(i);
        }
    }

    private void bajar(int i){
        int masgrande = i;
        int hijoIzq = 2*i + 1;
        int hijoDer = 2*i + 2;
        if (hijoIzq < cola.size() && cola.get(hijoIzq).value.compareTo(cola.get(masgrande).value)>0){
            masgrande = hijoIzq;
        }
        if (hijoDer < cola.size() && cola.get(hijoDer).value.compareTo(cola.get(masgrande).value) > 0) {
            masgrande = hijoDer;
        }   
        if (masgrande != i) {
            swap(i, masgrande);
            bajar(masgrande); 
        }
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
        Handle handle = new Handle(ciudad, cola.size());
        cola.add(handle);
        ciudades.set(ciudad.getID(), cola.size()-1);
        // subir
        int dedito = cola.size()-1;
        while(dedito > 0){
            int padre = (dedito-1)/2; // floor operation
            if(cola.get(dedito).value.compareTo(cola.get(padre).value) > 0){
                swap(dedito, padre);
                dedito = padre;
            }else{
                break;
            }
        }
    }


    public Ciudad desencolar(){
        if (cola.size() == 0){
            return null;
        }
        if (cola.size() == 1){
            return cola.remove(0).value;
        }
        Handle salida = cola.get(0);
        cola.set(0, cola.remove(cola.size()-1));
        bajar(0);
        return salida.value;
    }

    public Handle proximo(){
        return cola.get(0);
    }

    public void borrar_ciudad(Ciudad ciudad){
        int i = ciudades.get(ciudad.getID());
        borrar_indice(i);
    }

    private void borrar_indice(int i){
        if (i == cola.size()-1){
            cola.remove(cola.size()-1);
            return;
        }
        cola.set(i, cola.remove(cola.size()-1));
        bajar(i);
    }
}
