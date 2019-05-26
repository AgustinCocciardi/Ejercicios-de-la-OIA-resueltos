package paquete;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Procesadora {
	
	private ArrayList<Lata> latas = new ArrayList<Lata>();
	private Lata[] latasVec;
	private Secuencia[] secuencia;
	
	public void agregarLata(char etiqueta) {
		this.latas.add(new Lata(etiqueta));
	}
	
	private void llenarVectorYBorrarLista() {
		this.latasVec = new Lata[this.latas.size()];
		this.latasVec = this.latas.toArray(new Lata[this.latas.size()]);
		this.latas.clear();
	}
	
	private void obtenerSecuencias() {
		int inicio = 0, fin, numSecuencias=0, i;
		boolean esGarbanzo = false;
		Stack<Secuencia> pilaSecuencias = new Stack<Secuencia>(); 
		for(i=0; i< this.latasVec.length; i++) {
			if(latasVec[i].obtenerEtiqueta() == 'G' && esGarbanzo == false) {
				esGarbanzo = true;
				inicio = i;
			}
			if(latasVec[i].obtenerEtiqueta() != 'G' && esGarbanzo == true) {
				esGarbanzo = false;
				fin = i-1;
				pilaSecuencias.push(new Secuencia(inicio,fin));
				numSecuencias++;
			}
		}
		
		i=0;
		this.secuencia = new Secuencia[numSecuencias];
		while(i < numSecuencias) {
			this.secuencia[i] = new Secuencia(pilaSecuencias.pop());
			i++;
		}
	}
	
	public void resolver(PrintWriter salida) {
		this.llenarVectorYBorrarLista();
		this.obtenerSecuencias();
		Arrays.sort(this.secuencia);
		salida.println(this.latasVec.length);
		salida.println(this.secuencia[0].obtenerLongitud());
		salida.println(this.secuencia[1].obtenerLongitud());
		salida.println(Secuencia.obtenerDistancia(this.secuencia[0], this.secuencia[1]));
	}
	
	public static void main(String[] args) throws IOException {
		Procesadora procesadora = new Procesadora();
		FileReader entrada = new FileReader("latas.in");
		char etiqueta = (char) entrada.read();
		while(etiqueta != 'F') {
			procesadora.agregarLata(etiqueta);
			entrada.read();
			etiqueta = (char) entrada.read();
		}
		entrada.close();
		
		PrintWriter salida = new PrintWriter(new FileWriter("latas.out"));
		procesadora.resolver(salida);
		salida.close();
	}
	
}
