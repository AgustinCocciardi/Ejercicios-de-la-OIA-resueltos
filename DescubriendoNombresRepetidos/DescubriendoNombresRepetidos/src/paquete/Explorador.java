package paquete;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Explorador implements Comparable <Explorador>{

	private String nombre;
	private int repeticiones;
	
	public Explorador(String nombre) {
		this.nombre=nombre;
		this.repeticiones=1;
	}
	
	public Explorador(Explorador explorador) {
		this.nombre= explorador.nombre;
		this.repeticiones= explorador.repeticiones;
	}
	
	public void aumentaRepeticiones() {
		this.repeticiones++;
	}
	
	public static int estaRepetido(String nombre, Explorador []exploradores, int enUso) {
		for(int i=0; i<enUso; i++) {
			if(nombre.equals(exploradores[i].nombre)) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public int compareTo(Explorador explorador) {
		if(this.repeticiones > explorador.repeticiones) {
			return -1;
		}
		if(this.repeticiones < explorador.repeticiones ) {
			return 1;
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		int exploradoresTotales, topRepetidos, enUso=1, repetido;
		String nombre, path="nombres3";
		Scanner sc = new Scanner(new FileReader(path+ ".in"));
		exploradoresTotales= sc.nextInt();
		topRepetidos= sc.nextInt();
		if(exploradoresTotales>topRepetidos) {
			sc.nextLine();
			Explorador[] exploradores = new Explorador[exploradoresTotales];
			nombre=sc.nextLine();
			exploradores[0] = new Explorador(nombre);
			for(int i=1; i<exploradoresTotales; i++) {
				nombre=sc.nextLine();
				repetido=estaRepetido(nombre,exploradores,enUso);
				if(repetido==-1) {
					exploradores[enUso]= new Explorador(nombre);
					enUso++;
				}
				else {
					exploradores[repetido].aumentaRepeticiones();
				}
			}
			sc.close();
			
			Explorador []exploradoresFinal = new Explorador[enUso];
			for(int i=0; i<exploradoresFinal.length; i++) {
				exploradoresFinal[i]= new Explorador(exploradores[i]);
			}
			
			Arrays.sort(exploradoresFinal);
			
			if(enUso<topRepetidos) {
				topRepetidos=enUso;
			}
			
			PrintWriter pw = new PrintWriter(new FileWriter(path+ ".out"));
			for(int i=0; i<topRepetidos; i++) {
				pw.println(exploradoresFinal[i].nombre + " " + exploradoresFinal[i].repeticiones);
			}
			pw.close();
		}
		else {
			sc.close();
			PrintWriter pw = new PrintWriter(new FileWriter(path+ ".out"));
			pw.println("Es imposible realizar un top con " + topRepetidos + " nombres debido a que solo hay " + exploradoresTotales + " chicos en el campamento");
			pw.close();
		}
		
		
	}

	

}
