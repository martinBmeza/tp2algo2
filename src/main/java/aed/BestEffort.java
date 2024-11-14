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
        this.traslados = new ColaDoble(traslados);
        // crear Stats
        this.stats = new Stats(cantCiudades);
    }

    public void registrarTraslados(Traslado[] traslados) {
        for (int i = 0; i < traslados.length; i++) {
            this.traslados.encolar(traslados[0]);
        }
    }

    public int[] despacharMasRedituables(int n) {
        // dobleCola.obtener(n, redituable)
        // stats.actualizar
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            Traslado t = this.traslados.desencolarRedito();
            stats.add(t);
            res[i] = t.id;
        }
        return res;
    }

    public int[] despacharMasAntiguos(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            Traslado t = this.traslados.desencolarAntiguedad();
            stats.add(t);
            res[i] = t.id;
        }
        return res;
    }

    public int ciudadConMayorSuperavit() {
        return stats.getMayorSuperavit();
    }

    public ArrayList<Integer> ciudadesConMayorGanancia() {
        return stats.getCiudadesConMayorGanancias();
    }

    public ArrayList<Integer> ciudadesConMayorPerdida() {
        return stats.getCiudadesConMayorPerdida();
    }

    public int gananciaPromedioPorTraslado() {
        return stats.getPromedio();
    }

}
