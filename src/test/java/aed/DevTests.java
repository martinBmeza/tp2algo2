/* Pongo aca los tests que sumo para las clases nuevas. */
package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;

public class DevTests {

    @Test
    void crearColaDoble(){
        ColaDoble Cola = new ColaDoble();
        assertNotNull(Cola);
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
        assertEquals(1, Cola.desencolarAntiguedad().timestamp);
        assertEquals(1452, Cola.desencolarRedito().gananciaNeta);
        assertEquals(334, Cola.desencolarRedito().gananciaNeta);
        assertEquals(333, Cola.desencolarRedito().gananciaNeta);

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
        assertNotNull(Cola);
        assertEquals(1, Cola.desencolarAntiguedad().timestamp);
        assertEquals(1452, Cola.desencolarRedito().gananciaNeta);
        assertEquals(334, Cola.desencolarRedito().gananciaNeta);
        assertEquals(333, Cola.desencolarRedito().gananciaNeta);

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
        assertEquals(2, Cola.proximo().value.getID());
        Cola.borrar_ciudad(ciudades[3]);
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
        assertEquals(2, Cola.desencolar().getID());
    }

    @Test
    void encolarDesencolarColaPrioridadCiudades(){
        
        Ciudad[] ciudades = new Ciudad[] {
            new Ciudad(0, 500, 200, 100),
            new Ciudad(1, 200, 100, 100),
            new Ciudad(2, 300, 300, 100),
            new Ciudad(3, 50, 400, 100),
        };
        ArrayList<Ciudad> ciudades_seq = new ArrayList<Ciudad>();
        for (Ciudad c : ciudades){
            ciudades_seq.add(c);
        } 
        ColaPrioridadCiudades Cola = new ColaPrioridadCiudades(ciudades_seq);
        assertEquals(0, Cola.desencolar().getID());
        assertEquals(2, Cola.desencolar().getID());
        assertEquals(1, Cola.desencolar().getID());
        assertEquals(3, Cola.desencolar().getID());
        assertEquals(null, Cola.desencolar());
        for (Ciudad c : ciudades){
            Cola.encolar(c);
        }
        assertEquals(0, Cola.desencolar().getID());
        assertEquals(2, Cola.desencolar().getID());
        assertEquals(1, Cola.desencolar().getID());
        assertEquals(3, Cola.desencolar().getID());
        assertEquals(null, Cola.desencolar());
    }

    @Test
    void borrarCiudad(){
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
        Cola.borrar_ciudad(ciudades[3]);
        Cola.encolar(new Ciudad(3, 1300, 300, 100));
        assertEquals(3, Cola.desencolar().getID());
    }
}
