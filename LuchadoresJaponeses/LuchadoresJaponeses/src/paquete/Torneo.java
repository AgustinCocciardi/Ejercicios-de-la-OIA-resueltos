package paquete;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Torneo {
	private int cantidadLuchadores;
	private Luchador luchadores[];

	public Torneo(int cantidadLuchadores) {
		this.cantidadLuchadores = cantidadLuchadores;
		this.luchadores = new Luchador[cantidadLuchadores];
	}

	public void resolver() {
		if (this.cantidadLuchadores > 1) {
			for (int i = 0; i < this.cantidadLuchadores - 1; i++) {
				for (int j = i + 1; j < this.cantidadLuchadores; j++) {
					if (this.luchadores[i].domina(this.luchadores[j])) {
						this.luchadores[i].aumentaCantidadDominados();
					}
					if (this.luchadores[j].domina(this.luchadores[i])) {
						this.luchadores[j].aumentaCantidadDominados();
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		String path = "sumo5";
		Scanner sc = new Scanner(new FileReader(path + ".in"));
		int cantidadLuchadores = sc.nextInt();
		Torneo torneo = new Torneo(cantidadLuchadores);
		for (int i = 0; i < cantidadLuchadores; i++) {
			int peso = sc.nextInt();
			int altura = sc.nextInt();
			torneo.luchadores[i] = new Luchador(peso, altura);
		}
		sc.close();

		torneo.resolver();
		
		PrintWriter pw = new PrintWriter(new FileWriter(path + ".out"));
		for(int i=0; i<cantidadLuchadores; i++) {
			pw.println(torneo.luchadores[i].obtenerCantidadDominados());
		}
		pw.close();
	}
}
