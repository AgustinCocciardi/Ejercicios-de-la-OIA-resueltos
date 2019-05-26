package paquete;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Mesada implements Comparable<Mesada> {
	private int ancho;
	private int largo;
	private int puedeApilar;
	
	public Mesada() {
		this.ancho=0;
		this.largo=0;
		this.puedeApilar=0;
	}
	
	public Mesada(int ancho, int largo) {
		this.ancho=ancho;
		this.largo=largo;
		this.puedeApilar=0;
	}
	
	public boolean puedeApilar(Mesada mesada) {
		return (mesada.ancho <= this.ancho && mesada.largo <= this.largo) ||
			   (mesada.ancho <= this.largo && mesada.largo <= this.ancho);
	}
	
	public void cambiarParametroPA() {
		this.puedeApilar=1;
	}
	
	public boolean estaApilada() {
		return this.puedeApilar!=0;
	}
	
	@Override
	public int compareTo(Mesada mesada) {
		if(this.ancho > mesada.ancho)
			return -1;
		if(this.ancho < mesada.ancho)
			return 1;
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner (new FileReader("mesadas3.in"));
		int cantidadMesadas= sc.nextInt();
		Mesada mesadas[] = new Mesada[cantidadMesadas];
		int cantidadPilas= 0;
		int ancho, largo;
		for(int i=0; i< cantidadMesadas; i++) {
			ancho=sc.nextInt();
			largo=sc.nextInt();
			if(ancho>=largo)
				mesadas[i]= new Mesada(ancho,largo);
			else
				mesadas[i]= new Mesada(largo,ancho);
		}
		sc.close();
		
		Arrays.sort(mesadas);
		
		for (int i=0; i< cantidadMesadas; i++) {
			if(!mesadas[i].estaApilada()) {
				mesadas[i].cambiarParametroPA();
				cantidadPilas++;
				for(int j=i+1; j<cantidadMesadas; j++) {
					if(!mesadas[j].estaApilada()) {
						if(mesadas[i].puedeApilar(mesadas[j])) {
							mesadas[j].cambiarParametroPA();
						}
					}
				}
			}	
		}
		
		PrintWriter pf = new PrintWriter (new FileWriter("mesadas3.out"));
		pf.print(cantidadPilas);
		pf.close();
		
		
	}

	

}
