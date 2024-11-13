/* Pongo aca los tests que sumo para las clases nuevas. */
package aed;

import static org.junit.jupiter.api.Assertions.*;

//import java.util.ArrayList;
//import java.util.Arrays;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;

public class DevTests {

    @Test
    void crearCP(){
        // CdeP
        CdeP<Integer> Cola = new CdeP<Integer>();
        assertNotNull(Cola);
        assertEquals(0, Cola.nelems());
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
    }
}
