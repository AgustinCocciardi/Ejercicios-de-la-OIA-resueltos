package equipos;

public class Integrante {
	
	private String respuestas;
	
	public Integrante(String respuestas){
		this.respuestas = respuestas;
	}
	
	public String respuestas(int numeroRespuestas){
		return this.respuestas.substring(0, numeroRespuestas);
	}
	
}
