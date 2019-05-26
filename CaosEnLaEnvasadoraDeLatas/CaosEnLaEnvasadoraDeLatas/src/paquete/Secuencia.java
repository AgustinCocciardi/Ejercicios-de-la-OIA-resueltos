package paquete;

public class Secuencia implements Comparable<Secuencia>{
	
	private int inicioSecuencia;
	private int finSecuencia;
	private int longitud;
	
	public Secuencia(int inicio, int fin) {
		this.inicioSecuencia = inicio;
		this.finSecuencia = fin;
		this.longitud = fin - inicio + 1;
	}
	
	public Secuencia(Secuencia secuencia) {
		this.inicioSecuencia = secuencia.inicioSecuencia;
		this.finSecuencia = secuencia.finSecuencia;
		this.longitud = secuencia.finSecuencia - secuencia.inicioSecuencia + 1;
	}

	public int obtenerLongitud() {
		return this.longitud;
	}
	
	public static int obtenerDistancia(Secuencia sec1, Secuencia sec2) {
		if(sec2.finSecuencia > sec1.finSecuencia) 
			return sec2.inicioSecuencia - sec1.finSecuencia - 1;
		return sec1.inicioSecuencia - sec2.finSecuencia - 1;
	}
	
	@Override
	public int compareTo(Secuencia secuencia) {
		return secuencia.longitud - this.longitud;
	}
	
}
