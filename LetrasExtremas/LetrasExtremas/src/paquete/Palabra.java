package paquete;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Palabra {

	char letraExtrema;
	int apariciones;
	
	public Palabra(char letra) {
		this.letraExtrema=letra;
		this.apariciones=0;
	}
	
	public char dameLetra() {
		return this.letraExtrema;
	}
	
	public int dameApariciones() {
		return this.apariciones;
	}
	
	public void aumentaApariciones() {
		this.apariciones++;
	}
	
	public static boolean aparece(char[] letrasAGrabar, char letra) {
		for (int i=0; i<letrasAGrabar.length; i++) {
			if(letra==letrasAGrabar[i]) {
				return true;
			}
		}
		return false;
	}
	
	public static int maxRepeticiones(Palabra[] letras) {
		int max=0;
		for(int i=0; i<letras.length; i++) {
			if(letras[i].apariciones>max) {
				max=letras[i].apariciones;
			}
		}
		return max;
	}
	
	public static int letrasMasRepetidas(Palabra []letras, int maxRepeticiones) {
		int total=0;
		for (int i=0; i<letras.length; i++) {
			if(letras[i].apariciones==maxRepeticiones) {
				total++;
			}
		}
		return total;
	}
	
	public static void resolver(String []palabras, Palabra []letras, PrintWriter pw) {
		for(int i=0; i<palabras.length; i++) {
			char letra;
			letra = palabras[i].charAt(0);
			letras[(int)(letra-97)].aumentaApariciones();
			letra = palabras[i].charAt(palabras[i].length()-1);
			letras[(int)(letra-97)].aumentaApariciones();
		}
		
		int maxRepeticiones=maxRepeticiones(letras); 
		int letrasTotales= letrasMasRepetidas(letras,maxRepeticiones);
		
		char[] letrasAGrabar = new char[letrasTotales]; 
		int a=0;
		for(int i=0; i<letras.length; i++) {
			if(letras[i].apariciones==maxRepeticiones) {
				letrasAGrabar[a] = letras[i].letraExtrema;
				a++;
			}
		}

		for(int i=0; i<letrasAGrabar.length; i++) {
			if(i<letrasAGrabar.length-1) {
				pw.print(letrasAGrabar[i] + " ");
			}
			else {
				pw.println(letrasAGrabar[i]);
			}
		}
		
		for(int i=0; i<palabras.length; i++) {
			if(aparece(letrasAGrabar,palabras[i].charAt(0)) || aparece(letrasAGrabar,palabras[i].charAt(palabras[i].length()-1))){
				pw.println(palabras[i]);
			}
		}

	}
	
	public static void main(String[] args) throws IOException {
		Palabra[] letras = new Palabra[26];
		for(int i=0; i<letras.length; i++) {
			letras[i] = new Palabra((char)(97+i));
		}
		
		int cantidadPalabras; 
		Scanner sc = new Scanner(new FileReader("extremas4.in"));
		cantidadPalabras=sc.nextInt();
		sc.nextLine();
		String[] palabras = new String[cantidadPalabras];
		for(int i=0; i<cantidadPalabras; i++) {
			palabras[i]= new String(sc.nextLine());
		}
		sc.close();
		
		PrintWriter pw= new PrintWriter(new FileWriter("extremas4.out"));
		
		resolver(palabras,letras, pw);
		
		pw.close();
	}

}
