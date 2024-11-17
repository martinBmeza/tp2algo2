package aed;

import java.util.ArrayList;
/*
 * Casi todas las operaciones tienen una version donde una cola
 * es en la que opero y la otra es la que actualizo o propago
 * el efecto de la operacion. A la cola sobre la que opero 
 * la voy a llamar colaOp y a la otra cola la voy a llamar colaProp.
 */

public class ColaDoble{
    private ArrayList<Handle> colaAntiguedad;
    private ArrayList<Handle> colaRedito;

    public class Handle {
        int value; // prioridad
        int index; // index en la otra cola
        Traslado traslado;

        public Handle(int value, int index, Traslado traslado) {
            this.value = value;
            this.index = index;
            this.traslado = traslado;
        }

        public Handle(Handle h){
            this.value = h.value;
            this.index = h.index;
            this.traslado = h.traslado;
        }

        public int getValue() {
            return value;
        }
    }

    public ColaDoble(){
        colaAntiguedad = new ArrayList<Handle>();
        colaRedito = new ArrayList<Handle>();
    }

    public ColaDoble(Traslado[] seq_traslados){
        // cola desde secuencia (Alg de Floyd)
        colaAntiguedad = new ArrayList<Handle>();
        colaRedito = new ArrayList<Handle>();
        int nelems = 0;
        for (Traslado t : seq_traslados){
            colaAntiguedad.add(new Handle(-t.timestamp, nelems, t));
            colaRedito.add(new Handle(t.gananciaNeta, nelems, t));
            nelems++;
        }
        for (int i = nelems/2 - 1; i >= 0; i--){
            bajar(colaAntiguedad, colaRedito, i);
            bajar(colaRedito, colaAntiguedad, i);
        }
    }

    public void bajar(ArrayList<Handle> colaOp, ArrayList<Handle> colaProp, int i){
        int masgrande = i;
        int hijoIzq = 2*i + 1;
        int hijoDer = 2*i + 2;
        if (hijoIzq < colaOp.size() && colaOp.get(hijoIzq).value > colaOp.get(masgrande).value) {
            masgrande = hijoIzq;
        }
        if (hijoDer < colaOp.size() && colaOp.get(hijoDer).value > colaOp.get(masgrande).value) {
            masgrande = hijoDer;
        }
        if (masgrande != i) {
            swap(colaOp, colaProp, i, masgrande);
            bajar(colaOp, colaProp, masgrande);
        }
    }

    private void swap(ArrayList<Handle> colaOp, ArrayList<Handle> colaProp,  int i, int j){
        Handle aux_i = colaOp.get(i);
        Handle aux_j = colaOp.get(j); 
        colaOp.set(i, aux_j);
        colaOp.set(j, aux_i);
        if(aux_i.index>=colaProp.size()){
            aux_i.index= colaProp.size()-1;
        }
        if(aux_j.index>=colaProp.size()){
            aux_j.index = colaProp.size()-1;
        }
        colaProp.get(aux_i.index).index = j;
        colaProp.get(aux_j.index).index = i;
    }

    public void borrar_indice(ArrayList<Handle> colaOp, ArrayList<Handle> colaProp, int i){
        if (i >= colaOp.size()-1){
            colaOp.remove(colaOp.size()-1);
            return;
        }
        colaOp.set(i, colaOp.remove(colaOp.size()-1));
        // bajar el elemento que puse en i
        bajar(colaOp, colaProp, i);
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
        Handle handle_antiguedad = new Handle(-traslado.timestamp, colaAntiguedad.size(), traslado);
        Handle handle_redito = new Handle(traslado.gananciaNeta, colaRedito.size(), traslado);
        colaAntiguedad.add(handle_antiguedad);
        colaRedito.add(handle_redito);
        subir(colaAntiguedad, colaRedito, colaAntiguedad.size()-1);
        subir(colaRedito, colaAntiguedad, colaRedito.size()-1);
    }

    public Traslado desencolar(ArrayList<Handle> colaOp, ArrayList<Handle> colaProp){
        if (colaOp.size() == 0){
            return null;
        }
        if (colaOp.size() == 1){
            return colaOp.remove(0).traslado;
        }
        Handle res = new Handle(colaOp.get(0));
        colaOp.set(0, colaOp.remove(colaOp.size()-1));
        // bajar lo que puse como raiz
        bajar(colaOp, colaProp, 0);
        borrar_indice(colaProp, colaOp, res.index); 
        return res.traslado;
    }

    public Traslado desencolarAntiguedad(){
        return desencolar(colaAntiguedad, colaRedito);
    }

    public Traslado desencolarRedito(){
        return desencolar(colaRedito, colaAntiguedad);
    }
}
