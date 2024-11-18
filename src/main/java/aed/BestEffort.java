package aed;

import java.util.ArrayList;

public class BestEffort {
    // DobleColaOrdenada
    // ordenadaPorAntiguedad
    // odenadaPorRdituable
    private ColaDoble traslados;
    private Stats stats;

    public BestEffort(int cantCiudades, Traslado[] traslados) {
        // crear DobleCola
        this.traslados = new ColaDoble(traslados); //O(log|traslados|)
        // crear Stats
        this.stats = new Stats(cantCiudades); //O(log|cantCiudades|)
    }

    public void registrarTraslados(Traslado[] traslados) {
        for (int i = 0; i < traslados.length; i++) { //O (|traslados|log|T|)
            this.traslados.encolar(traslados[i]); //O(log|T|)
        }
    }

    public int[] despacharMasRedituables(int n) {
        int[] res = new int[n]; //O(n)
        for (int i = 0; i < n; i++) { //log(n(log|T| + log|C|))
            Traslado t = this.traslados.desencolarRedito(); //O(log|T|)
            stats.add(t); //O(log|C|)
            res[i] = t.id; //O(1)
        }
        return res;
    }

    public int[] despacharMasAntiguos(int n) {
        int[] res = new int[n];//O(n)
        for (int i = 0; i < n; i++) {//log(n(log|T| + log|C|))
            Traslado t = this.traslados.desencolarAntiguedad();//O(log|T|)
            stats.add(t); //O(log|C|)
            res[i] = t.id;//O(1)
        }
        return res;
    }

    public int ciudadConMayorSuperavit() {
        return stats.getMayorSuperavit();//O(1)
    }

    public ArrayList<Integer> ciudadesConMayorGanancia() {
        return stats.getCiudadesConMayorGanancias();//O(1)
    }

    public ArrayList<Integer> ciudadesConMayorPerdida() {
        return stats.getCiudadesConMayorPerdida();//O(1)
    }

    public int gananciaPromedioPorTraslado() {
        return stats.getPromedio();//O(1)
    }

}
