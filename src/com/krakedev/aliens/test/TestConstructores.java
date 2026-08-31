package com.krakedev.aliens.test;

import alien.krakedev.Alien;

public class TestConstructores {

	public static void main(String[] args) {
		
		System.out.println("=== ALIEN 1: Ajuste de tamaño superior y flujo de partes ===");
        // Tamaño 35 -> se ajusta a 30 cm automáticamente
        Alien a1 = new Alien(35, "Verde");
        a1.imprimir(); // Muestra precio inicial (solo cuerpo = $6.0)

        // Agregamos partes
        a1.agregarBrazos(4);
        a1.agregarPiernas(4);
        a1.agregarOjos(5);
        System.out.println("\n--- Después de agregar extremidades y ojos ---");
        a1.imprimir();

        System.out.println("\n=== ALIEN 2: Ajuste de tamaño inferior y prueba de límites ===");
        // Tamaño 2 -> se ajusta a 5 cm automáticamente
        Alien a2 = new Alien(2, "Rojo");
        a2.imprimir();

        // Alien de 5 cm: máximo 3 ojos y máximo 10 extremidades
        boolean agregoOjosOk = a2.agregarOjos(4); // Debe fallar porque el límite es 3
        System.out.println("\n¿Pudo agregar 4 ojos?: " + agregoOjosOk);

        a2.agregarOjos(2); // Válido
        a2.agregarBrazos(2); // Válido
        a2.agregarPiernas(2); // Válido
        
        System.out.println("\n--- Estado final Alien 2 ---");
        a2.imprimir();

	}

}
