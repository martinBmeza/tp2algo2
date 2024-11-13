package aed;

import java.util.ArrayList;

public class BestEffort {
    //DobleColaOrdenada
        //ordenadaPorAntiguedad
        //odenadaPorRdituable

    //stats
        //colaSuperavit
        //arrayNodos para colaSuperavit
        //cantidadDespachados
        //sumaDespachados
        //mayoresPerdidas : Array<int> 
        //mayoresGanancias : Array<int>
        //ganancias : Array<int> |C|
        //peridas : Array<int> |C|
    private Stats stats;

    public BestEffort(int cantCiudades, Traslado[] traslados){
        //crear DobleCola
        //crear Stats
        stats = new Stats(cantCiudades);
    }

    public void registrarTraslados(Traslado[] traslados){
        // for t en traslados
            //dobleCola.encolar(t)
    }

    public int[] despacharMasRedituables(int n){
        //dobleCola.obtener(n, redituable)
        //stats.actualizar
        return null;
    }

    public int[] despacharMasAntiguos(int n){
        // Implementa
        return null;
    }

    public int ciudadConMayorSuperavit(){
        // stat.mayorSuperavit
        return 0;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return stats.getCiudadesConMayorGanancias();
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return stats.getCiudadesConMayorPerdida();
    }

    public int gananciaPromedioPorTraslado(){
        // return stats.gananciaPromeido
        return 0;
    }
    
}
