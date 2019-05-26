package paquete;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Vagon {
	
	private int agresividadMaximaTolerada;
	private int cantidadVagones;
	private int agresividadMaximaVagon;
	private Animal[] animales;
	
	public Vagon(Scanner entrada) {
		this.cantidadVagones = 0;
		this.agresividadMaximaVagon = 0;
		int especies = entrada.nextInt();
		this.agresividadMaximaTolerada = entrada.nextInt();
		this.animales = new Animal[especies];
		for(int i = 0; i < especies; i++) {
			this.animales[i] = new Animal(entrada.next(), entrada.nextInt(), entrada.nextInt());
		}
	}
	
	public void aumentarVagones() {
		this.cantidadVagones++;
	}
	
	public void setAgresividadMaximaVagon(int agresividad) {
		this.agresividadMaximaVagon = agresividad;
	}
	
	public void resolver(PrintWriter salida) {
		int animales = this.animales.length;
		if(animales == 0)
			salida.println(" ");
		Arrays.sort(this.animales);
		Stack<Animal> pilaAnimal = new Stack<Animal>();
		for (int i = 0; i < animales; i++) {
			pilaAnimal.push(this.animales[i]);
		}
		Animal animal1 = new Animal(pilaAnimal.pop());
		this.aumentarVagones();
		while(pilaAnimal.isEmpty() == false) {
			Animal animal2 = new Animal(pilaAnimal.pop());
			int agresividadCalculada = animal1.diferenciaAgresividad(animal2);
			if( agresividadCalculada <= this.agresividadMaximaTolerada ) {
				if(agresividadCalculada > this.agresividadMaximaVagon)
					this.setAgresividadMaximaVagon(agresividadCalculada);
			}
			else {
				this.aumentarVagones();
				animal1 = animal2;
			}
		}
		salida.println(this.cantidadVagones + " " + this.agresividadMaximaVagon);
	}
	
	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(new FileReader("vagones3.in"));
		Vagon vagon = new Vagon(entrada);
		entrada.close();
		PrintWriter salida = new PrintWriter(new FileWriter("vagones3.out"));
		vagon.resolver(salida);
		salida.close();
	}
}	
