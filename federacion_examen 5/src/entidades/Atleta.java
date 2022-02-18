package entidades;

import java.util.Scanner;

import utils.Datos;
import utils.Utilidades;

public class Atleta extends Participante {
	private long idAtleta;
	private float altura;
	private float peso;

	private DatosPersona persona;

	public Atleta(long id, int dorsal, char calle, long idAtleta, float altura, float peso) {
		super(id, dorsal, calle);
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = Datos.buscarPersonaPorId(id);
	}

	public Atleta(long id, int dorsal, char calle, long idAtleta, float altura, float peso, DatosPersona dp) {
		super(idAtleta, dorsal, calle);
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = dp;
	}

	public Atleta(long idAtleta, float altura, float peso, DatosPersona dp) {
		super();
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = dp;
	}

	public Atleta(long idParticipante, Atleta a, int dorsal, char calle) {
		super(idParticipante, dorsal, calle);
		this.idAtleta = a.idAtleta;
		this.altura = a.altura;
		this.peso = a.peso;
		this.persona = Datos.buscarPersonaPorId(a.idAtleta);
	}

	@Override
	public long getId() {
		return idAtleta;
	}

	@Override
	public void setId(long id) {
		this.idAtleta = id;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public DatosPersona getPersona() {
		return this.persona;
	}

	/**
	 * Funcion que crea un objeto del tipo Atleta con todos sus datos
	 * 
	 * @return Objeto del tipo Alteta completo.
	 * @author Facu
	 */
	public static Atleta nuevoAtleta() {
		Scanner in = new Scanner(System.in);
		Atleta ret = null;
		DatosPersona dp = null;
		long id = -1;
		float altura = 0, peso = 0;
		boolean valido = false, validoId = false;

		do {
			System.out.println("Introduzca el id del nuevo Atleta:");
			id = in.nextLong();
			if (id > 0)
				validoId = true;
			else
				System.out.println("Valor incorrecto para el identificador.");
		} while (!validoId);

		System.out.println("Introduzca la altura del nuevo Atleta:");
		altura = Utilidades.leerFloat();
		System.out.println("Introduzca el peso del nuevo Atleta:");
		peso = Utilidades.leerFloat();

		System.out.println("Introduzca los datos del nuevo Atleta:");
		dp = DatosPersona.nuevaPersona();
		ret = new Atleta(id, altura, peso, dp);
		return ret;
	}
	/**
	 * metodo toString sobrescrito para que devuelva:
	 * "nombre “ (” documentacion ”) del año ”fechaNac.año’\t’"peso"”Kgs. ”"altura"+”m.“
	 *  @author Facu
	 */
	@Override
	public String toString() {
		return persona.getNombre() + "(" + persona.getNifnie() + ")" + " del año " + persona.getFechaNac() + "\t" + peso
				+ "kgs. " + altura + " m.";
	}

}
