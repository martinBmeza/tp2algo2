package aed;

import java.util.ArrayList;
/*
 * Casi todas las operaciones tienen una version donde una cola
 * es en la que opero y la otra es la que actualizo o propago
 * el efecto de la operacion. A la cola sobre la que opero 
 * la voy a llamar colaOp y a la otra cola la voy a llamar colaProp.
 */

public class ColaDoble<Handle>{
    /* Cola de prioridad implementada sobre HEAP */
    private ArrayList<Handle> colaAntiguedad;
    private ArrayList<Handle> colaRedito;
    private int nelems; 

    public class Handle {
        int value; // prioridad
        int index; // index en la otra cola
        Traslado traslado;

        public Handle(int value, int index, Traslado traslado) {
            this.value = value;
            this.index = index;
            this.traslado = traslado;
        }

        public int getValue() {
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
            if(hijoDer < nelems &&  colaOp.get(hijoDer).value>colaOp.get(hijoIzq).value){
                mayor = hijoDer;
            }
            if(colaOp.get(dedito).value < colaOp.get(mayor).value){
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
            if(colaOp.get(dedito).value > colaOp.get(padre).value){
                swap(colaOp, colaProp, dedito, padre);
                dedito = padre;
            }else{
                break;
            }
        }
    }

    public void encolar(Traslado traslado){
        Handle handle_antiguedad = new Handle(traslado.timestamp, nelems, traslado);
        Handle handle_redito = new Handle(traslado.gananciaNeta, nelems, traslado);
        colaAntiguedad.add(handle_antiguedad);
        colaRedito.add(handle_redito);
        subir(colaAntiguedad, colaRedito, nelems);
        subir(colaRedito, colaAntiguedad, nelems);
        nelems++;
    }

    public Traslado desencolar(ArrayList<Handle> colaOp, ArrayList<Handle> colaProp){
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
            if(hijoDer < nelems && colaOp.get(hijoDer).value > colaOp.get(hijoIzq).value){
                mayor = hijoDer;
            }
            if(colaOp.get(dedito).value < colaOp.get(mayor).value){
                swap(colaOp, colaProp, dedito, mayor);
                dedito = mayor;
            }else{
                break;
            }
        }
        // borrar de la otra cola (colaProp) el elemento que saque de esta cola (colaOp)
        borrar_indice(colaProp, colaOp, res.index); 
        nelems--;
        return res.traslado;
    }

    public Traslado desencolarAntiguedad(){
        return desencolar(colaAntiguedad, colaRedito);
    }

    public Traslado desencolarRedito(){
        return desencolar(colaRedito, colaAntiguedad);
    }
}
