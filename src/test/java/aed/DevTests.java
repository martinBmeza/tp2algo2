/* Pongo aca los tests que sumo para las clases nuevas. */
package aed;

import static org.junit.jupiter.api.Assertions.*;

//import java.util.ArrayList;
//import java.util.Arrays;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;

public class DevTests {

    @Test
    void crearColaPrioridad(){
        // CdeP
        ColaPrioridad<Integer> Cola = new ColaPrioridad<Integer>();
        assertNotNull(Cola);
        assertEquals(0, Cola.nelems());
    }


    @Test
    void encolarDesencolar(){
        // CdeP
        ColaPrioridad<Integer> Cola = new ColaPrioridad<Integer>();
        Cola.encolar(1);
        Cola.encolar(3);
        Cola.encolar(2);
        Cola.encolar(0);
        assertEquals(4, Cola.nelems());
        assertEquals(3, Cola.desencolar());
        assertEquals(2, Cola.desencolar());
        assertEquals(1, Cola.desencolar());
        assertEquals(0, Cola.desencolar());
        assertEquals(0, Cola.nelems());
        assertEquals(null, Cola.desencolar());

    }

    @Test
    void crearColaDoble(){
        ColaDoble<Integer> Cola = new ColaDoble<Integer>();
        assertNotNull(Cola);
        assertEquals(0, Cola.nelems());
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




}
