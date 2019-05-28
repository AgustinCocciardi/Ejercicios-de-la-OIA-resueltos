package nombres;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Campamento {

	private int cantidadExploradores;
	private int topRepetidos;
	private HashMap<String,Integer> nombres = new HashMap<String,Integer>();
	private TreeMap<Integer,String> repetidos = new TreeMap<Integer,String>();
	
	public Campamento(Scanner entrada) {
		this.cantidadExploradores = entrada.nextInt();
		this.topRepetidos = entrada.nextInt();
		entrada.nextLine();
		for(int i = 0 ; i < this.cantidadExploradores; i++ ) {
			String nombre = entrada.nextLine();
			if(this.nombres.containsKey(nombre)) {
				int cantidad = this.nombres.get(nombre);
				this.nombres.put(nombre, cantidad+1);
			}
			else
				this.nombres.put(nombre, 1);
		}
	}
	
	public void resolver(PrintWriter salida) {
		for( Entry<String,Integer> nombre : nombres.entrySet() ) {
			repetidos.put(nombre.getValue(), nombre.getKey());
		}
		for(int i = 0; i < this.topRepetidos && this.repetidos.isEmpty() == false; i++) {
			salida.println(this.repetidos.get(this.repetidos.lastKey()) + " " + this.repetidos.lastKey());
			this.repetidos.remove(this.repetidos.lastKey());
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(new FileReader("nombres.in"));
		Campamento campamento = new Campamento(entrada);
		entrada.close();
		PrintWriter salida = new PrintWriter(new FileWriter("nombres.out"));
		campamento.resolver(salida);
		salida.close();
	}

}
