package paquete;

public class Luchador {
	
	private int peso;
	private int altura;
	private int cantidadDominados;
	
	public Luchador(int peso, int altura) {
		this.peso = peso;
		this.altura = altura;
		this.cantidadDominados = 0;
	}
	
	public void aumentaCantidadDominados() {
		this.cantidadDominados++;
	}
	
	public int obtenerCantidadDominados() {
		return this.cantidadDominados;
	}
	
	public boolean domina(Luchador luchador) {
		if(this.altura > luchador.altura && this.peso > luchador.peso) {
			return true;
		}
		if(this.altura == luchador.altura && this.peso > luchador.peso) {
			return true;
		}
		if(this.peso == luchador.peso && this.altura > luchador.altura) {
			return true;
		}
		return false;
	}
	
}
