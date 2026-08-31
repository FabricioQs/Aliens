package alien.krakedev;

public class Alien {
	
	private int tamanio;
	private String color;
	private int numeroOjos;
	private int numeroBrazos;
	private int numeroPies;
	private double precioExtremidad;
	private double precioOjo;
	private double precioCuerpo;
	
	
	public int getTamanio() {
		return tamanio;
	}

	public String getColor() {
		return color;
	}

	public int getNumeroOjos() {
		return numeroOjos;
	}


	public int getNumeroBrazos() {
		return numeroBrazos;
	}

	public int getNumeroPies() {
		return numeroPies;
	}

	public double getPrecioExtremidad() {
		return precioExtremidad;
	}

	public double getPrecioOjo() {
		return precioOjo;
	}

	public double getPrecioCuerpo() {
		return precioCuerpo;
	}

	public Alien(int tamanio, String color) {
	    // 1. Validación y ajuste automático del tamaño (rango 5 a 30)
	    if (tamanio < 5) {
	        this.tamanio = 5;
	    } else if (tamanio > 30) {
	        this.tamanio = 30;
	    } else {
	        this.tamanio = tamanio;
	    }

	    // 2. Asignación del color
	    this.color = color;

	    // 3. Cálculo de precios base a partir del tamaño ajustado
	    this.precioCuerpo = this.tamanio * 0.20;      // 20%
	    this.precioExtremidad = this.tamanio * 0.10;  // 10%
	    this.precioOjo = this.tamanio * 0.05;         // 5%
	}

	public void imprimir() {
		System.out.println("----- DATOS DEL ALIEN -----");
		System.out.println("Tamaño: " + tamanio + " cm");
		System.out.println("Color: " + color);
		System.out.println("Número de Ojos: " + numeroOjos);
		System.out.println("Número de Brazos: " + numeroBrazos);
		System.out.println("Número de Pies: " + numeroPies);
		System.out.println("Precio Extremidad: $" + precioExtremidad);
		System.out.println("Precio Ojo: $" + precioOjo);
		System.out.println("Precio Cuerpo: $" + precioCuerpo);
		System.out.println("---------------------------");
	}
}
