/* Pongo aca los tests que sumo para las clases nuevas. */
package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;

public class DevTests {

    // @Test
    // void crearColaPrioridad(){
    //     // CdeP
    //     ColaPrioridad<Integer> Cola = new ColaPrioridad<Integer>();
    //     assertNotNull(Cola);
    //     assertEquals(0, Cola.nelems());
    // }


    // @Test
    // void encolarDesencolar(){
    //     // CdeP
    //     ColaPrioridad<Integer> Cola = new ColaPrioridad<Integer>();
    //     Cola.encolar(1);
    //     Cola.encolar(3);
    //     Cola.encolar(2);
    //     Cola.encolar(0);
    //     assertEquals(4, Cola.nelems());
    //     assertEquals(3, Cola.desencolar());
    //     assertEquals(2, Cola.desencolar());
    //     assertEquals(1, Cola.desencolar());
    //     assertEquals(0, Cola.desencolar());
    //     assertEquals(0, Cola.nelems());
    //     assertEquals(null, Cola.desencolar());

    // }

    @Test
    void crearColaDoble(){
        ColaDoble Cola = new ColaDoble();
        assertNotNull(Cola);
        assertEquals(0, Cola.nelems());
    }

    @Test
    void ColaDobleDesdeSecuencia(){
        Traslado[] traslados = new Traslado[] {
            new Traslado(8, 1, 2, 1452, 5),
            new Traslado(9, 1, 2, 334, 2),
            new Traslado(10, 1, 2, 24, 3),
            new Traslado(11, 1, 2, 333, 4),
            new Traslado(12, 2, 1, 9000, 1)
        };
        ColaDoble Cola = new ColaDoble(traslados);
        assertNotNull(Cola);
        assertEquals(5, Cola.nelems());
        assertEquals(5, Cola.desencolarAntiguedad().timestamp);
        assertEquals(9000, Cola.desencolarRedito().gananciaNeta);
        assertEquals(3, Cola.nelems());
    }

    @Test
    void encolarDesencolarColaDoble(){
        // CdeP
        ColaDoble Cola = new ColaDoble();
        Traslado[] nuevos = new Traslado[] {
            new Traslado(8, 1, 2, 1452, 5),
            new Traslado(9, 1, 2, 334, 2),
            new Traslado(10, 1, 2, 24, 3),
            new Traslado(11, 1, 2, 333, 4),
            new Traslado(12, 2, 1, 9000, 1)
        };
        for (Traslado t : nuevos){
            Cola.encolar(t);
        }
        assertEquals(5, Cola.nelems());
        assertEquals(5, Cola.desencolarAntiguedad().timestamp);
        assertEquals(9000, Cola.desencolarRedito().gananciaNeta);
        assertEquals(3, Cola.nelems());   
    }

    @Test
    void basi_cola_prioridad(){
        Ciudad[] ciudades = new Ciudad[] {
            new Ciudad(0, 100, 200, 100),
            new Ciudad(1, 200, 100, 100),
            new Ciudad(2, 300, 300, 100),
            new Ciudad(3, 50, 400, 100),
        };
        ArrayList<Ciudad> ciudades_seq = new ArrayList<Ciudad>();
        for (Ciudad c : ciudades){
            ciudades_seq.add(c);
        } 
        ColaPrioridadCiudades Cola = new ColaPrioridadCiudades(ciudades_seq);
        assertNotNull(Cola);
        assertEquals(4, Cola.nelems());
        assertEquals(2, Cola.proximo().value.getID());
        Cola.borrar_ciudad(ciudades[3]);
        //ciudades[1].addToGanancia(1000);
        //assertEquals(1200, ciudades[1].getSuperavit());
        //assertEquals(true, ciudades[1].compareTo(ciudades[2])>0);
        Cola.encolar(new Ciudad(3, 1300, 300, 100));
        assertEquals(3, Cola.desencolar().getID());
    }

    @Test
    void CrearColaPrioridadCiudades(){
        Ciudad[] ciudades = new Ciudad[] {
            new Ciudad(0, 100, 200, 100),
            new Ciudad(1, 200, 100, 100),
            new Ciudad(2, 300, 300, 100),
            new Ciudad(3, 50, 400, 100),
        };
        ArrayList<Ciudad> ciudades_seq = new ArrayList<Ciudad>();
        for (Ciudad c : ciudades){
            ciudades_seq.add(c);
        } 
        ColaPrioridadCiudades Cola = new ColaPrioridadCiudades(ciudades_seq);
        assertNotNull(Cola);
        assertEquals(4, Cola.nelems());
        assertEquals(2, Cola.desencolar().getID());
        Cola.encolar(new Ciudad(2, 100, 100, 100));
        assertEquals(4, Cola.nelems());
        assertEquals(1, Cola.desencolar().getID());
        Cola.encolar(new Ciudad(1, 100, 100, 100));
        assertEquals(4, Cola.nelems());
        assertEquals(0, Cola.desencolar().getID());
        Cola.encolar(new Ciudad(1, 100, 100, 100));
        Cola.encolar(new Ciudad(1, 100, 100, 100));
        Cola.encolar(new Ciudad(1, 100, 100, 100));
        // me deja encolar muchas veces la misma ciudad. Es peligroso pero no deberia suceder
        // si se usa correctamente. Consultar si es un problema o intentar arreglarlo
        assertEquals(6, Cola.nelems());
        Cola.borrar_ciudad(new Ciudad(1, 100, 100, 100));
        assertEquals(5, Cola.nelems());

    }


    @Test
    void encolarDesencolarColaPrioridadCiudades(){
        // Agregar mas tests para ver si funciona bien

    }



}
