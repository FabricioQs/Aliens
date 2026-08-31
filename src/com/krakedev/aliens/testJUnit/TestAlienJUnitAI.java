package com.krakedev.aliens.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import alien.krakedev.Alien;

public class TestAlienJUnitAI {
	
	@Test
    public void testLimiteInferiorExacto() {
        // Límite exacto mínimo permitido: 5
        Alien alien = new Alien(5, "Amarillo");
        assertNotNull(alien);
        assertEquals(5, alien.getTamanio());
        assertEquals("Amarillo", alien.getColor());
        assertEquals(1.0, alien.getPrecioCuerpo(), 0.001);
        assertEquals(0.5, alien.getPrecioExtremidad(), 0.001);
        assertEquals(0.25, alien.getPrecioOjo(), 0.001);
    }

    @Test
    public void testLimiteSuperiorExacto() {
        // Límite exacto máximo permitido: 30
        Alien alien = new Alien(30, "Morado");
        assertNotNull(alien);
        assertEquals(30, alien.getTamanio());
        assertEquals("Morado", alien.getColor());
        assertEquals(6.0, alien.getPrecioCuerpo(), 0.001);
        assertEquals(3.0, alien.getPrecioExtremidad(), 0.001);
        assertEquals(1.5, alien.getPrecioOjo(), 0.001);
    }

    @Test
    public void testValoresExtremosNegativosYCero() {
        // Casos anómalos: valores <= 0 deben ajustarse al mínimo 5
        Alien alienNegativo = new Alien(-10, "Negro");
        assertEquals(5, alienNegativo.getTamanio());
        
        Alien alienCero = new Alien(0, "Blanco");
        assertEquals(5, alienCero.getTamanio());
    }

    @Test
    public void testValoresInicialesExtremidadesYOjos() {
        // Al instanciar, los contadores de partes deben arrancar en 0
        Alien alien = new Alien(15, "Gris");
        assertEquals(0, alien.getNumeroBrazos());
        assertEquals(0, alien.getNumeroPies());
        assertEquals(0, alien.getNumeroOjos());
    }

    @Test
    public void testFlujoExtremoCalculoPrecioYLimites() {
        // Alien al límite máximo de tamaño: 30 cm
        Alien alien = new Alien(30, "Púrpura");
        
        // Base: cuerpo = 30 * 0.20 = 6.0
        assertEquals(6.0, alien.getPrecioTotal(), 0.001);

        // Extremidad = 3.0, Ojo = 1.5
        // Agregar 5 brazos y 5 piernas (10 extremidades en total = límite exacto)
        assertTrue(alien.agregarBrazos(5));
        assertTrue(alien.agregarPiernas(5));
        assertFalse(alien.agregarBrazos(1)); // Rechazado por exceder límite global de 10

        // Tamaño >20 a 30 cm -> límite de 7 ojos
        assertTrue(alien.agregarOjos(4));
        assertTrue(alien.agregarOjos(3)); // Total 7 ojos (límite alcanzado)
        assertFalse(alien.agregarOjos(1)); // Rechazado por exceder límite de ojos

        // Precio Total: 6.0 (cuerpo) + (10 * 3.0) + (7 * 1.5) = 6.0 + 30.0 + 10.5 = 46.5
        assertEquals(46.5, alien.getPrecioTotal(), 0.001);
    }

    @Test
    public void testInmutabilidadDePrecioAlFallarOperaciones() {
        Alien alien = new Alien(10, "Negro"); // Rango 5-10 cm -> max 3 ojos
        
        // Precio base: cuerpo = 10 * 0.20 = 2.0
        assertEquals(2.0, alien.getPrecioTotal(), 0.001);

        // Intentar agregar 4 ojos (límite superado)
        assertFalse(alien.agregarOjos(4));
        
        // El precio y los contadores no deben alterarse
        assertEquals(0, alien.getNumeroOjos());
        assertEquals(2.0, alien.getPrecioTotal(), 0.001);

        // Intentar agregar 11 brazos (límite superado)
        assertFalse(alien.agregarBrazos(11));
        assertEquals(0, alien.getNumeroBrazos());
        assertEquals(2.0, alien.getPrecioTotal(), 0.001);
    }

    @Test
    public void testRangosMediosDeOjos() {
        // Tamaño 18 cm (>10 a 20 cm -> max 5 ojos)
        Alien alien = new Alien(18, "Cian");

        assertTrue(alien.agregarOjos(3));
        assertTrue(alien.agregarOjos(2)); // Total 5 ojos (válido)
        assertFalse(alien.agregarOjos(1)); // 6to ojo (inválido)

        assertEquals(5, alien.getNumeroOjos());
    }    
}
