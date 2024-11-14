package aed;

import java.util.ArrayList;
/*
 * Casi todas las operaciones tienen una version donde una cola
 * es en la que opero y la otra es la que actualizo o propago
 * el efecto de la operacion. A la cola sobre la que opero 
 * la voy a llamar colaOp y a la otra cola la voy a llamar colaProp.
 */

public class ColaDoble<T extends Comparable<T>>{
    /* Cola de prioridad implementada sobre HEAP */
    private ArrayList<Handle> colaAntiguedad;
    private ArrayList<Handle> colaRedito;
    private int nelems; 

    public class Handle {
        T value;
        int index; // index en la otra cola

        public Handle(T value, int index) {
            this.value = value;
            this.index = index;
        }

        public T getValue() {
            return value;
        }
    }

    public ColaDoble(){
        colaAntiguedad = new ArrayList<Handle>();
        colaRedito = new ArrayList<Handle>();
        nelems = 0;
    }

    public int nelems(){
        return nelems;
    }

    private void swap(ArrayList<Handle> colaOp, ArrayList<Handle> colaProp,  int i, int j){
        Handle aux_i = colaOp.get(i);
        Handle aux_j = colaOp.get(j); 
        colaOp.set(i, aux_j);
        colaOp.set(j, aux_i);
        colaProp.get(aux_i.index).index = j;
        colaProp.get(aux_j.index).index = i;
    }

    public void borrar_indice(ArrayList<Handle> colaOp, ArrayList<Handle> colaProp, int i){
        colaOp.set(i, colaOp.get(nelems-1));
        // bajar el elemento que puse en i
        int dedito = i;
        while(dedito < nelems){
            int hijoIzq = 2*dedito + 1;
            int hijoDer = 2*dedito + 2;
            if(hijoIzq >= nelems){
                break;
            }
            int mayor = hijoIzq;
            if(hijoDer < nelems && colaOp.get(hijoDer).value.compareTo(colaOp.get(hijoIzq).value) > 0){
                mayor = hijoDer;
            }
            if(colaOp.get(dedito).value.compareTo(colaOp.get(mayor).value) < 0){
                swap(colaOp, colaProp, dedito, mayor);
                dedito = mayor;
            }else{
                break;
            }
        }
    }

    public void subir(ArrayList<Handle> colaOp, ArrayList<Handle> colaProp, int i){
        int dedito = i;
        while(dedito > 0){
            int padre = (dedito-1)/2; // floor operation
            if(colaOp.get(dedito).value.compareTo(colaOp.get(padre).value) > 0){
                swap(colaOp, colaProp, dedito, padre);
                dedito = padre;
            }else{
                break;
            }
        }
    }

    public void encolar(T valor_antiguedad, T valor_redito){
        Handle handle_antiguedad = new Handle(valor_antiguedad, nelems);
        Handle handle_redito = new Handle(valor_redito, nelems);
        colaAntiguedad.add(handle_antiguedad);
        colaRedito.add(handle_redito);
        subir(colaAntiguedad, colaRedito, nelems);
        subir(colaRedito, colaAntiguedad, nelems);
        nelems++;
    }

    public T desencolar(ArrayList<Handle> colaOp, ArrayList<Handle> colaProp){
        if (nelems == 0){
            return null;
        }
        Handle res = colaOp.get(0);
        colaOp.set(0, colaOp.get(nelems-1));
        // bajar lo que puse como raiz
        int dedito = 0;
        while(dedito < nelems){
            int hijoIzq = 2*dedito + 1;
            int hijoDer = 2*dedito + 2;
            if(hijoIzq >= nelems){
                break;
            }
            int mayor = hijoIzq;
            if(hijoDer < nelems && colaOp.get(hijoDer).value.compareTo(colaOp.get(hijoIzq).value) > 0){
                mayor = hijoDer;
            }
            if(colaOp.get(dedito).value.compareTo(colaOp.get(mayor).value) < 0){
                swap(colaOp, colaProp, dedito, mayor);
                dedito = mayor;
            }else{
                break;
            }
        }
        // borrar de la otra cola (colaProp) el elemento que saque de esta cola (colaOp)
        borrar_indice(colaProp, colaOp, res.index); 
        nelems--;
        return res.value;
    }

    public T desencolarAntiguedad(){
        return desencolar(colaAntiguedad, colaRedito);
    }

    public T desencolarRedito(){
        return desencolar(colaRedito, colaAntiguedad);
    }
}
