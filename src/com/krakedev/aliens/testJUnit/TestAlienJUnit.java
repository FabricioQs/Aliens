package com.krakedev.aliens.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import alien.krakedev.Alien;

public class TestAlienJUnit {

	@Test
    public void testTamanioMenorAlMinimo() {
        Alien alien = new Alien(2, "Verde"); // Menor a 5, debe ajustarse a 5
        
        assertEquals(5, alien.getTamanio());
        assertEquals(1.0, alien.getPrecioCuerpo(), 0.01);      // 5 * 0.20 = 1.0
        assertEquals(0.5, alien.getPrecioExtremidad(), 0.01);  // 5 * 0.10 = 0.5
        assertEquals(0.25, alien.getPrecioOjo(), 0.01);        // 5 * 0.05 = 0.25
    }

    @Test
    public void testTamanioMayorAlMaximo() {
        Alien alien = new Alien(45, "Rojo"); // Mayor a 30, debe ajustarse a 30
        
        assertEquals(30, alien.getTamanio());
        assertEquals(6.0, alien.getPrecioCuerpo(), 0.01);      // 30 * 0.20 = 6.0
        assertEquals(3.0, alien.getPrecioExtremidad(), 0.01);  // 30 * 0.10 = 3.0
        assertEquals(1.5, alien.getPrecioOjo(), 0.01);         // 30 * 0.05 = 1.5
    }
    
    @Test
    public void testTamanioValidoYPrecios() {
        Alien alien = new Alien(20, "Azul");
        
        assertEquals(20, alien.getTamanio());
        assertEquals("Azul", alien.getColor());
        assertEquals(4.0, alien.getPrecioCuerpo(), 0.01);      // 20 * 0.20 = 4.0
        assertEquals(2.0, alien.getPrecioExtremidad(), 0.01);  // 20 * 0.10 = 2.0
        assertEquals(1.0, alien.getPrecioOjo(), 0.01);         // 20 * 0.05 = 1.0
    }
    
    @Test
    public void testAgregarExtremidadesValidoYLimite() {
        Alien alien = new Alien(15, "Verde");

        // Caso válido
        boolean brazosOk = alien.agregarBrazos(4);
        assertEquals(true, brazosOk);
        assertEquals(4, alien.getNumeroBrazos());

        // Caso límite (4 brazos + 6 piernas = 10 total)
        boolean piernasOk = alien.agregarPiernas(6);
        assertEquals(true, piernasOk);
        assertEquals(6, alien.getNumeroPies());
    }

    @Test
    public void testExcederLimiteExtremidades() {
        Alien alien = new Alien(15, "Verde");
        alien.agregarBrazos(6);

        // Intento exceder: 6 + 5 = 11 > 10 (debe fallar y mantener en 0 las piernas)
        boolean resultadoExceso = alien.agregarPiernas(5);
        assertEquals(false, resultadoExceso);
        assertEquals(0, alien.getNumeroPies());
        assertEquals(6, alien.getNumeroBrazos());
    }
    
    @Test
    public void testFlujoCompletoPrecioTotalAlienPequeno() {
        // Alien tamaño 8 cm (rango 5-10 cm -> max 3 ojos)
        Alien alien = new Alien(8, "Verde");
        
        // Precio base inicial: cuerpo = 8 * 0.20 = 1.6
        assertEquals(1.6, alien.getPrecioTotal(), 0.01);
        
        // Agregar 2 brazos (extremidad = 8 * 0.10 = 0.8) -> +1.6
        assertTrue(alien.agregarBrazos(2));
        assertEquals(3.2, alien.getPrecioTotal(), 0.01);

        // Agregar 2 ojos (ojo = 8 * 0.05 = 0.4) -> +0.8
        assertTrue(alien.agregarOjos(2));
        assertEquals(4.0, alien.getPrecioTotal(), 0.01);

        // Intentar agregar 2 ojos más (2 + 2 = 4 > 3 máx permitido) -> debe fallar y no cambiar precio
        assertFalse(alien.agregarOjos(2));
        assertEquals(2, alien.getNumeroOjos());
        assertEquals(4.0, alien.getPrecioTotal(), 0.01);
    }

    @Test
    public void testRestriccionesOjosPorTamanio() {
        // Alien tamaño 15 cm (>10 a 20 cm -> max 5 ojos)
        Alien alienMediano = new Alien(15, "Azul");
        assertTrue(alienMediano.agregarOjos(5));
        assertFalse(alienMediano.agregarOjos(1)); // Excede

        // Alien tamaño 25 cm (>20 a 30 cm -> max 7 ojos)
        Alien alienGrande = new Alien(25, "Rojo");
        assertTrue(alienGrande.agregarOjos(7));
        assertFalse(alienGrande.agregarOjos(1)); // Excede
    }

    @Test
    public void testRestriccionExtremidadesYPrecio() {
        // Alien tamaño 20 cm (cuerpo = 4.0, extremidad = 2.0, ojo = 1.0)
        Alien alien = new Alien(20, "Amarillo");

        assertTrue(alien.agregarBrazos(4));
        assertTrue(alien.agregarPiernas(6)); // Total 10 extremidades
        assertFalse(alien.agregarPiernas(1)); // Excede 10 -> false

        // Total: 4.0 + (10 * 2.0) = 24.0
        assertEquals(24.0, alien.getPrecioTotal(), 0.01);
    }
}
