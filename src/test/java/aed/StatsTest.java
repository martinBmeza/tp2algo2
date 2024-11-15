package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class StatsTest {
    

    int cantCiudades;
    Traslado[] listaTraslados;
    ArrayList<Integer> actual;


    @BeforeEach
    void init(){
        //Reiniciamos los valores de las ciudades y traslados antes de cada test
        cantCiudades = 7;
        listaTraslados = new Traslado[] {
                                            new Traslado(1, 0, 1, 100, 10),
                                            new Traslado(2, 0, 1, 400, 20),
                                            new Traslado(3, 3, 4, 500, 50),
                                            new Traslado(4, 4, 3, 500, 11),
                                            new Traslado(5, 1, 0, 1000, 40),
                                            new Traslado(6, 1, 0, 1000, 41),
                                            new Traslado(7, 6, 3, 2000, 42)
                                        };
    }

    void assertSetEquals(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        assertEquals(s1.size(), s2.size());
        for (int e1 : s1) {
            boolean encontrado = false;
            for (int e2 : s2) {
                if (e1 == e2) encontrado = true;
            }
            assertTrue(encontrado, "No se encontró el elemento " +  e1 + " en el arreglo " + s2.toString());
        }
    }

    @Test
    void basic_stat_test(){
        Stats sis = new Stats(this.cantCiudades);

        sis.add(listaTraslados[0]);
        
        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.getCiudadesConMayorGanancias());
        assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.getCiudadesConMayorPerdida());
        assertEquals(100, sis.getPromedio());
        assertEquals(0, sis.getMayorSuperavit());

        sis.add(listaTraslados[6]);
        assertSetEquals(new ArrayList<>(Arrays.asList(6)), sis.getCiudadesConMayorGanancias());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.getCiudadesConMayorPerdida());
    }

    @Test
    void add_test(){
        Stats sis = new Stats(this.cantCiudades);

        sis.add(listaTraslados[0]);
        
        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.getCiudadesConMayorGanancias());
        assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.getCiudadesConMayorPerdida());

        sis.add(listaTraslados[6]);
        assertSetEquals(new ArrayList<>(Arrays.asList(6)), sis.getCiudadesConMayorGanancias());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.getCiudadesConMayorPerdida());
    }

    @Test
    void devolver_varias_ciudades_e_igual(){
        listaTraslados = new Traslado[] {
            new Traslado(1, 0, 1, 100, 10),
            new Traslado(2, 0, 1, 400, 20),
            new Traslado(3, 3, 4, 500, 50),
            new Traslado(4, 4, 3, 500, 11),
            new Traslado(5, 1, 0, 1000, 40),
            new Traslado(6, 1, 0, 1000, 41),
            new Traslado(7, 6, 3, 2000, 42)
        };
        Stats sis = new Stats(this.cantCiudades);
        sis.add(listaTraslados[0]);
        sis.add(listaTraslados[1]);
        sis.add(listaTraslados[2]);
        sis.add(listaTraslados[3]);
        assertSetEquals(new ArrayList<>(Arrays.asList(1,3,4)), sis.getCiudadesConMayorPerdida());
        assertSetEquals(new ArrayList<>(Arrays.asList(0,3,4)), sis.getCiudadesConMayorGanancias());
        sis.add(listaTraslados[6]);
        sis.add(listaTraslados[5]);
        sis.add(listaTraslados[4]);
        assertSetEquals(new ArrayList<>(Arrays.asList(1,6)), sis.getCiudadesConMayorGanancias());
    }

    @Test
    void basic_superavit(){
        listaTraslados = new Traslado[] {
            new Traslado(1, 0, 1, 100, 10),
            new Traslado(2, 0, 1, 400, 20),
            new Traslado(3, 3, 4, 500, 50),
            new Traslado(4, 4, 3, 500, 11),
            new Traslado(5, 1, 0, 1000, 40),
            new Traslado(6, 1, 0, 1000, 41),
            new Traslado(7, 6, 3, 2000, 42)
        };
        Stats sis = new Stats(this.cantCiudades);
        sis.add(listaTraslados[0]);
        assertEquals(0, sis.getMayorSuperavit());
        sis.add(listaTraslados[1]);
        assertEquals(0,sis.getMayorSuperavit());
        sis.add(listaTraslados[4]);
        assertEquals(1,sis.getMayorSuperavit());
        sis.add(listaTraslados[2]);
        sis.add(listaTraslados[3]);
        sis.add(listaTraslados[5]);
        sis.add(listaTraslados[6]);
        assertEquals(6,sis.getMayorSuperavit());
    }
    @Test
    void basic_promedio(){
        listaTraslados = new Traslado[] {
            new Traslado(1, 0, 1, 100, 10),
            new Traslado(2, 0, 1, 400, 20),
            new Traslado(3, 3, 4, 500, 50),
            new Traslado(4, 4, 3, 500, 11),
            new Traslado(5, 1, 0, 1000, 40),
            new Traslado(6, 1, 0, 1000, 41),
            new Traslado(7, 6, 3, 2000, 42)
        };
        Stats sis = new Stats(this.cantCiudades);
        sis.add(listaTraslados[0]);
        assertEquals(100, sis.getPromedio());
        sis.add(listaTraslados[1]);
        assertEquals(250, sis.getPromedio());
        sis.add(listaTraslados[2]);
        assertEquals(333, sis.getPromedio());
        sis.add(listaTraslados[3]);
        assertEquals(375, sis.getPromedio());
        sis.add(listaTraslados[4]);
        assertEquals(500, sis.getPromedio());
        sis.add(listaTraslados[5]);
        assertEquals(583, sis.getPromedio());
        sis.add(listaTraslados[6]);
        assertEquals(785, sis.getPromedio());
    }
}
