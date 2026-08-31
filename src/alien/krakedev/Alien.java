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
	private double precioTotal = 0;
	
	
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
	
	public double getPrecioTotal() {
        return precioTotal;
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
	    
	    this.calcularPrecioTotal(); // Inicializa el precio total con el cuerpo
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
		System.out.println("Precio Total: $" + precioTotal);
		System.out.println("---------------------------");
	}
	
	public boolean agregarBrazos(int brazos) {
        if (this.numeroBrazos + this.numeroPies + brazos <= 10) {
        	this.numeroBrazos = this.numeroBrazos + brazos;
        	this.calcularPrecioTotal();
            return true;
        }
        return false;
    }

    public boolean agregarPiernas(int piernas) {
        if (this.numeroBrazos + this.numeroPies + piernas <= 10) {
            this.numeroPies = this.numeroPies + piernas;
            this.calcularPrecioTotal();
            return true;
        }
        return false;
    }
    
    public boolean agregarOjos(int ojos) {
        int limiteOjos;

        // Determinar el límite según el tamaño del Alien
        if (this.tamanio <= 10) {
            limiteOjos = 3;
        } else if (this.tamanio <= 20) {
            limiteOjos = 5;
        } else {
            limiteOjos = 7;
        }

        // Validar si la cantidad acumulada no excede el límite
        if (this.numeroOjos + ojos <= limiteOjos) {
            this.numeroOjos = this.numeroOjos + ojos;
            this.calcularPrecioTotal();
            return true;
        }
        return false;
    }
    
    public void calcularPrecioTotal() {
        int totalExtremidades = this.numeroBrazos + this.numeroPies;
        this.precioTotal = this.precioCuerpo 
                + (totalExtremidades * this.precioExtremidad) 
                + (this.numeroOjos * this.precioOjo);
    }
}
