package paquete;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class Numero {

	private TreeMap<Integer, Integer> numeros = new TreeMap<Integer, Integer>();
	private int n;
	private int k;
	
	public Numero(Scanner entrada) {
		this.n = entrada.nextInt();
		this.k = entrada.nextInt();
		for(int i = 0; i < this.n; i++) {
			int valor = entrada.nextInt();
			this.numeros.put(valor, valor);
		}
	}
	
	public void resolver(PrintWriter salida) {
		Iterator<Integer> iterador = this.numeros.keySet().iterator();
		int i=0;
		salida.println(this.k);
		while(i < this.k) {
			salida.println(iterador.next());
			i++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(new FileReader("numeros.in"));
		Numero numeros = new Numero(entrada);
		entrada.close();
		PrintWriter salida = new PrintWriter(new FileWriter("numeros.out"));
		numeros.resolver(salida);
		salida.close();
	}

}
