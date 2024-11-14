package aed;

import java.util.ArrayList;

public class ColaPrioridad<T extends Comparable<T>>{
    /* Cola de prioridad implementada sobre HEAP */
    private ArrayList<Handle> cola;
    private int nelems; 

    public class Handle {
        T value;
        int index;

        public Handle(T value, int index) {
            this.value = value;
            this.index = index;
        }

        public T getValue() {
            return value;
        }
    }

    public ColaPrioridad(){
        cola = new ArrayList<Handle>();
        nelems = 0;
    }

    public int nelems(){
        return nelems;
    }

    private void swap(int i, int j){
        Handle aux = cola.get(i); 
        cola.set(i, cola.get(j));
        cola.set(j, aux);
    }
    
    public void encolar(T valor){
        Handle handle = new Handle(valor, nelems);
        cola.add(handle);
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
    public T desencolar(){
        if (nelems == 0){
            return null;
        }
        Handle salida = cola.get(0);
        cola.set(0, cola.get(nelems-1));
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

    public void borrar_indice(int i){
        cola.set(i, cola.get(nelems-1));
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
