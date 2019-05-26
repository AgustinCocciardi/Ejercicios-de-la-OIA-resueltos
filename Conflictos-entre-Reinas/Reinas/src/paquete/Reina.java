package paquete;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Reina {
	public int fila;
	public int columna;
	public int conflictos;
	public int numero;
	public int[] reinas = new int[8]; 
	
	public Reina(int fila, int columna, int numero) {
		this.fila= fila;
		this.columna= columna;
		this.conflictos= 0;
		this.numero= numero;
		for(int i=0; i<8; i++)
			this.reinas[i]=0;
	}
	
	public static void ordenarConflictos(int vector[], int cantElem) {
		int aux=0;
		for(int i=0; i<cantElem-1; i++) {
			for(int j=i+1; j<cantElem; j++) {
				if(vector[i]>vector[j]) {
					aux= vector[i];
					vector[i]= vector[j];
					vector[j]=aux;
				}
			}
		}
	}
	
	public static void resolver(Reina reinas[], int tablaAjedrez[][]) {
		int inicio, fin, reinaActual=0, i, j, k, numero; 
		int cambio;
		while(reinaActual < reinas.length) {
			inicio=reinas[reinaActual].fila;
			fin=reinas[reinaActual].columna;
			k=0;
			
			///Reviso hacia arriba 
			if(inicio != 0) {
				i=inicio;
				j=fin;
				cambio=0;
				while(cambio==0 && i>=1) {
					if(tablaAjedrez[i-1][j] == 0)
						i--;
					else {
						cambio=1;
						numero= tablaAjedrez[i-1][j];
						reinas[reinaActual].reinas[k]= numero;
						reinas[reinaActual].conflictos++;
						k++;
					}
				}
			}
			
			///Reviso hacia abajo
			if(inicio != tablaAjedrez.length-1) {
				i=inicio;
				j=fin;
				cambio=0;
				while(cambio==0 && i<tablaAjedrez.length-1) {
					if(tablaAjedrez[i+1][j] == 0)
						i++;
					else {
						cambio=1;
						numero= tablaAjedrez[i+1][j];
						reinas[reinaActual].reinas[k]= numero;
						reinas[reinaActual].conflictos++;
						k++;
					}
				}
			}
			
			///Reviso hacia izquierda
			if(fin != 0) {
				i=inicio;
				j=fin;
				cambio=0;
				while(cambio ==0 && j>=1) {
					if(tablaAjedrez[i][j-1] == 0)
						j--;
					else {
						cambio=1;
						numero= tablaAjedrez[i][j-1];
						reinas[reinaActual].reinas[k]= numero;
						reinas[reinaActual].conflictos++;
						k++;
					}
				}
			}
			
			///Reviso hacia derecha
			if(fin != tablaAjedrez.length-1) {
				i = inicio;
				j=fin;
				cambio=0;
				while(cambio ==0 && j<tablaAjedrez.length-1) {
					if(tablaAjedrez[i][j+1] ==0 )
						j++;
					else {
						cambio=1;
						numero= tablaAjedrez[i][j+1];
						reinas[reinaActual].reinas[k]=numero;
						reinas[reinaActual].conflictos++;
						k++;
					}
				}
			}
			
			///Reviso diagonal izquierda arriba 
			if(inicio != 0 && fin!= 0) {
				i=inicio;
				j=fin;
				cambio=0;
				while(cambio ==0 && i>0 && j>0) {
					if(tablaAjedrez[i-1][j-1]==0) {
						i--;
						j--;
					}
					else {
						cambio=1;
						numero= tablaAjedrez[i-1][j-1];
						reinas[reinaActual].reinas[k]= numero;
						reinas[reinaActual].conflictos++;
						k++;
					}
				}
			}
			
			///Reviso diagonal izquierda abajo
			if(inicio != tablaAjedrez.length-1 && fin != 0) {
				i=inicio;
				j=fin;
				cambio=0;
				while(cambio ==0 && i<tablaAjedrez.length-1 && j>1 ) {
					if(tablaAjedrez[i+1][j-1]==0) {
						i++;
						j--;
					}
					else {
						cambio=1;
						numero= tablaAjedrez[i+1][j-1];
						reinas[reinaActual].reinas[k]= numero;
						reinas[reinaActual].conflictos++;
						k++;
					}
				}
			}
			
			///Reviso diagonal derecha arriba
			if(inicio != 0 && fin != tablaAjedrez.length) {
				i=inicio;
				j=fin;
				cambio=0;
				while(cambio == 0 && i>=1 && j<=tablaAjedrez.length-2 ) {
					if(tablaAjedrez[i-1][j+1] == 0) {
						i--;
						j++;
					}
					else {
						cambio=1;
						numero= tablaAjedrez[i-1][j+1];
						reinas[reinaActual].reinas[k]= numero;
						reinas[reinaActual].conflictos++;
						k++;
					}
				}
			}
			
			///Reviso diagonal derecha abajo
			if(inicio != tablaAjedrez.length && fin != tablaAjedrez.length) {
				i=inicio;
				j=fin;
				cambio=0;
				while( cambio ==0 && i<=tablaAjedrez.length-2 && j<=tablaAjedrez.length-2) {
					if(tablaAjedrez[i+1][j+1] ==0) {
						i++;
						j++;
					}
					else {
						cambio=1;
						numero= tablaAjedrez[i+1][j+1];
						reinas[reinaActual].reinas[k]= numero;
						reinas[reinaActual].conflictos++;
						k++;
					}
				}
			}
			
			reinaActual++;
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner (new FileReader("reinas2.in"));
		int tablero= sc.nextInt(), fila, columna;
		int cantidadReinas= sc.nextInt();
		int  tablaAjedrez[][] = new int [tablero][tablero];
		Reina reinas[] = new Reina[cantidadReinas];
		for(int i=0; i<tablero; i++)
			for(int j=0; j<tablero; j++)
				tablaAjedrez[i][j]=0;
		for(int i=0; i< cantidadReinas; i++) {
			fila= sc.nextInt();
			columna=sc.nextInt();
			tablaAjedrez[fila-1][columna-1]= i+1;
			reinas[i] = new Reina(fila-1, columna-1, i+1);
		}
		sc.close();
		
		resolver(reinas,tablaAjedrez);
		
		PrintWriter pw = new PrintWriter (new FileWriter("reinas2.out"));
		for(int a=0; a<cantidadReinas; a++) {
			pw.print(reinas[a].conflictos + " ");
			if(reinas[a].conflictos>0) {
				if(reinas[a].conflictos>1) {
					ordenarConflictos(reinas[a].reinas, reinas[a].conflictos);
				}
				int b=0;
				while(b<reinas[a].conflictos && reinas[a].reinas[b] != 0) {
					pw.print(reinas[a].reinas[b] + " ");
					b++;
				}
					
			}
			pw.print('\n');
		}
			
			
		pw.close();
	}

	

}
