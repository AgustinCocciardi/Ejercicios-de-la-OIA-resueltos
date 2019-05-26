package paquete;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Vendedora {

	private int cantidadVentas; /// indica las ventas de cada vendedora
	private int[] importes; /// el importe de cada una de las ventas de cada vendedora
	private boolean enConcurso; /// si es true, la vendedora sigue en el concurso. si es false, quedo eliminada
	private int numeroVendedora; /// el numero de cada vendedora. se construye con una variable estatica
	private int importeMaximo; /// el importe maximo de un conjunto de N ventas consecutivas
	private static int numeroVendedoraActual = 1;
	private static int ventasConsideradas; ///variable estatica que guarda el numero de ventas consideradas para el concurso

	/// constructor parametrizado de la clase Vendedora. recibe las ventas y sus importes
	public Vendedora(int cantidadVentas, int[] importes) {
		this.cantidadVentas = cantidadVentas;
		this.importes = importes;
		this.enConcurso = true;
		this.numeroVendedora = numeroVendedoraActual;
		numeroVendedoraActual++;
		this.importeMaximo = 0;
	}

	/// este metodo fija el valor del importe maximo a las concursantes a partir de importes parciales 
	public void fijaImporteMaximo(int[] importesParciales) {
		if(this.enConcurso) {
			int importeFinal = importesParciales[0];
			for (int i = 1; i < importesParciales.length; i++) {
				if (importesParciales[i] > importeFinal) {
					importeFinal = importesParciales[i];
				}
			}
			this.importeMaximo = importeFinal;
		}
		else {
			this.importeMaximo= 0;
		}
	}

	/// retira a una vendedora en caso de que no pueda seguir en el concurso
	public void retiraDelConcurso() {
		this.enConcurso = false;
	}

	/// me dice si una vendedora esta en concurso
	public boolean estaEnConcurso() {
		return this.enConcurso;
	}
	
	///me dice cuantos importes parciales va a tener una vendedora
	public int dameCantidadDeImportesParciales(int numeroVentasConsecutivas) {
		return this.cantidadVentas - numeroVentasConsecutivas + 1;
	}

	/// calcula el importe
	public int importe(int inicio, int numeroVentasConsecutivas) {
		int total = 0;
		for (int i = inicio; i < (numeroVentasConsecutivas + inicio); i++) {
			total += this.importes[i];
		}
		return total;
	}

	///genera el array de importes parciales
	public void generaImportesParciales(int[] importesParciales, int cantidadImportesParciales, int numeroVentasConsecutivas) {
		for (int i = 0; i < cantidadImportesParciales; i++) {
			importesParciales[i] = this.importe(i, numeroVentasConsecutivas);
		}
	}

	public static int resolver(Vendedora vendedora[], int numeroVentasConsecutivas) {
		// Comprobamos si hay concursantes y si los hay sacamos el numero maximo de ventas
		int numeroConcursantes = 0, maximaCantidadVentas = 0;
		ventasConsideradas=numeroVentasConsecutivas;
		for (int i = 0; i < vendedora.length; i++) {
			if (vendedora[i].cantidadVentas >= numeroVentasConsecutivas) {
				numeroConcursantes++;
			} else {
				vendedora[i].retiraDelConcurso();
			}
			if (vendedora[i].cantidadVentas > maximaCantidadVentas) {
				maximaCantidadVentas = vendedora[i].cantidadVentas;
			}
		}
		if (numeroConcursantes == 0) {
			return -1;
		}
		//// Si no hay concursantes, salgo. Si hay concursantes, obtengo el numero
		//// maximo de ventas

		/// Revisa si puede determinar a una ganadora o si el concurso queda en empate 
		while (numeroVentasConsecutivas <= maximaCantidadVentas) { 
			int i = 0;
			///obtendra la suma de importes mas altas de una vendedora o la retirará del concurso
			while (i < vendedora.length) { 
				if (vendedora[i].estaEnConcurso()) {
					if (vendedora[i].cantidadVentas >= numeroVentasConsecutivas) {
						int[] importesParciales = new int[(vendedora[i]
								.dameCantidadDeImportesParciales(numeroVentasConsecutivas))];
						vendedora[i].generaImportesParciales(importesParciales,
								vendedora[i].dameCantidadDeImportesParciales(numeroVentasConsecutivas),numeroVentasConsecutivas);
						vendedora[i].fijaImporteMaximo(importesParciales);
					} else {
						vendedora[i].retiraDelConcurso();
					}
				}
				i++;
			}

			///obtiene el importe mayor registrado en N ventas consecutivas
			int maximoValor = 0, vendedorasConcursando = 0;
			for (int j = 0; j < vendedora.length; j++) {
				if (vendedora[j].importeMaximo > maximoValor) {
					maximoValor = vendedora[j].importeMaximo;
				}
			}

			///revisa cuantas vendedoras llegaron a ese importe mayor 
			for (int h = 0; h < vendedora.length; h++) {
				if (vendedora[h].importeMaximo == maximoValor) {
					vendedorasConcursando++;
				} else {
					vendedora[h].retiraDelConcurso();
				}
			}

			///si esto se cumple, se devuelve el numero de la ganadora
			if (vendedorasConcursando == 1) {
				for (int k = 0; k < vendedora.length; k++) {
					if (vendedora[k].estaEnConcurso()) {
						return k;
					}
				}
			}

			///si no se cumple, el concurso continua, pero con las vendedoras concursantes
			numeroVentasConsecutivas++; 
			ventasConsideradas++;
		}

		return -2; ///si llegó hasta acá es porque no se puede desempatar
	}

	public static void main(String[] args) throws IOException {
		///lectura de archivo
		Scanner sc = new Scanner(new FileReader("premio7.in"));
		int cantidadVendedoras = sc.nextInt();
		int ventasDeVendedora;
		Vendedora vendedoras[] = new Vendedora[cantidadVendedoras];
		for (int i = 0; i < cantidadVendedoras; i++) {
			ventasDeVendedora = sc.nextInt();
			int[] importesVendedora = new int[ventasDeVendedora];
			for (int j = 0; j < ventasDeVendedora; j++) {
				importesVendedora[j] = sc.nextInt();
			}
			vendedoras[i] = new Vendedora(ventasDeVendedora, importesVendedora);
		}
		int numeroVentasConsecutivas = sc.nextInt();
		sc.close();

		///este metodo nos devolvera el procedimiento a realizar
		int resultado = resolver(vendedoras, numeroVentasConsecutivas);

		///escritura de archivo segun lo que nos dio el resultado
		PrintWriter pw = new PrintWriter(new FileWriter("premio7.out"));
		if (resultado == -2) {
			pw.println("No se puede desempatar");
		} else if (resultado == -1) {
			pw.println("No hay ganadoras");
		} else {
			pw.println(vendedoras[resultado].numeroVendedora);
			pw.println(ventasConsideradas + " " + vendedoras[resultado].importeMaximo);
		}
		pw.close();
	}

}
