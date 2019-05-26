package paquete;

public class Animal implements Comparable<Animal> {
	
	private String nombre;
	private int nivelAgresividad;
	private int cantidad;
	
	public Animal(String nombre, int nivelAgresividad, int cantidad) {
		this.nombre = nombre;
		this.nivelAgresividad = nivelAgresividad;
		this.cantidad = cantidad;
	}

	public Animal(Animal animal) {
		this.cantidad = animal.cantidad;
		this.nombre = animal.nombre;
		this.nivelAgresividad = animal.nivelAgresividad;
	}

	public int diferenciaAgresividad(Animal animal) {
		return this.nivelAgresividad - animal.nivelAgresividad;
	}
	
	@Override
	public int compareTo(Animal animal) {
		return this.nivelAgresividad - animal.nivelAgresividad;
	}

}
