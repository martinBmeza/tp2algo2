package aed;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BestEffort {
    ColaDoble colaTraslados;
    Stats stats;


    public BestEffort(int cantCiudades, Traslado[] traslados){
        colaTraslados = new ColaDoble(traslados); // O(T)
        stats = new Stats(cantCiudades);
    }

    public void registrarTraslados(Traslado[] traslados){
        for (Traslado t : traslados){
            colaTraslados.encolar(t);
        }
    }

    public int[] despacharMasRedituables(int n){
        Traslado t = colaTraslados.desencolarRedito();

    }

    public int[] despacharMasAntiguos(int n){
        // Implementar
        return null;
    }

    public int ciudadConMayorSuperavit(){
        // Implementar
        return 0;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        // Implementar
        return null;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        // Implementar
        return null;
    }

    public int gananciaPromedioPorTraslado(){
        // Implementar
        return 0;
    }
    
}
