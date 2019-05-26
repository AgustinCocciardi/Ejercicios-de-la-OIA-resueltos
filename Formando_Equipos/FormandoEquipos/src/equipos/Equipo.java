package equipos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Equipo {

	private Integrante[] integrantes;
	private int cantIntegrantes;
	private int cantPreguntas;
	private EquipoAfin equipoAfin;

	public Equipo(Scanner sc) {
		this.cantPreguntas = sc.nextInt();
		this.cantIntegrantes = sc.nextInt();
		sc.nextLine();
		this.integrantes = new Integrante[cantIntegrantes];
		for (int i = 0; i < this.cantIntegrantes; i++) {
			this.integrantes[i] = new Integrante(sc.nextLine());
		}
		this.equipoAfin = new EquipoAfin();
	}

	public void grabarEnElArchivo(PrintWriter pw) {
		calcularAfinidadMaximaYFormarEquipo();
		pw.println(this.equipoAfin.getAfinidad());
		pw.println(this.equipoAfin.getRespuestas());
	}

	private static int calcularAfinidad(int integrantes, int preguntas) {
		return integrantes * (int) Math.pow(preguntas, 2);
	}

	private void calcularAfinidadMaximaYFormarEquipo() {
		int afinidadCalculada;
		int afinidadMaxima = 0;
		for (int i = 1; i < this.cantPreguntas; i++) {
			boolean[] evaluados = new boolean[this.cantIntegrantes];
			int j = 0;
			int integrantes = 0;
			int integrantesFalsos = this.cantIntegrantes;
			int primerFalso = 0;
			boolean afinidadTomada = false;
			boolean primerFalsoHallado = false;
			String respuestasDeAfinidad = "";
			while (integrantesFalsos > 0 && j < this.cantIntegrantes) {
				if (afinidadTomada == false) {
					j = primerFalso + 1;
					integrantes = 1;
					respuestasDeAfinidad = this.integrantes[primerFalso].respuestas(i);
					integrantesFalsos--;
					evaluados[primerFalso] = true;
					afinidadTomada = true;
				}
				if (j < this.cantIntegrantes) {
					if (evaluados[j] == false) {
						if (this.integrantes[j].respuestas(i).equals(respuestasDeAfinidad)) {
							integrantes++;
							integrantesFalsos--;
							evaluados[j] = true;
						} else {
							if (primerFalsoHallado == false) {
								primerFalso = j;
								primerFalsoHallado = true;
							}
						}
					}
				}

				if (j + 1 >= this.cantIntegrantes) {
					afinidadCalculada = calcularAfinidad(integrantes, i);
					if (afinidadCalculada > afinidadMaxima || (afinidadCalculada == afinidadMaxima
							&& respuestasDeAfinidad.length() > this.equipoAfin.getRespuestas().length())) {
						afinidadMaxima = afinidadCalculada;
						this.equipoAfin.setAfinidad(afinidadCalculada);
						this.equipoAfin.setRespuestas(respuestasDeAfinidad);
					}
					if (integrantesFalsos > 0) {
						j = 0;
						afinidadTomada = false;
						primerFalsoHallado = false;
					}
				}
				j++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(new FileReader("equipo.in"));
		PrintWriter salida = new PrintWriter(new FileWriter("equipo.out"));

		Equipo equipo1 = new Equipo(entrada);
		equipo1.grabarEnElArchivo(salida);

		entrada.close();
		salida.close();
	}
}
