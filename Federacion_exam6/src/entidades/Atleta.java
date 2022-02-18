package entidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import utils.Datos;
import utils.Utilidades;
import validaciones.Validaciones;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Atleta extends Participante implements Serializable {

	private static final long serialVersionUID = 1L;
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

	public Atleta() {
		// TODO Auto-generated constructor stub
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

	// Examen 5 Ejercicio 5
	/***
	 * Función que pregunta al usuario por cada uno de los campos para un nuevo
	 * Atleta, los valida y si son correctos devuelve un objeto Atleta completo
	 * 
	 * @return un objeto Atleta completo válido o null si hubo algún error
	 */
	public static Atleta nuevoAtleta() {
		Atleta ret = null;
		long id = -1;
		float altura = 0.0F;
		float peso = 0.0F;
		int elecc = -1;
		DatosPersona dp = null;
		Scanner in;
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del nuevo atleta:");
			in = new Scanner(System.in);
			id = in.nextInt();
			if (id > 0)
				valido = true;
			else
				System.out.println("Valor incorrecto para el identificador.");
		} while (!valido);

		valido = false;
		do {
			System.out.println("Introduzca el peso del nuevo atleta (xx,xx)Kgs:");
			peso = Utilidades.leerFloat();
			valido = Validaciones.validarPeso(peso);
			if (!valido)
				System.out.println("ERROR: El valor introducido para el peso no es válido.");
		} while (!valido);

		valido = false;
		do {
			System.out.println("Introduzca la altura del nuevo atleta (xx,xx)m:");
			altura = Utilidades.leerFloat();
			valido = Validaciones.validarAltura(altura);
			if (!valido)
				System.out.println("ERROR: El valor introducido para la altura no es válido.");
		} while (!valido);

		System.out.println("Introduzca ahora los datos personales:");
		in = new Scanner(System.in);
		dp = DatosPersona.nuevaPersona();

		ret = new Atleta(id, altura, peso, dp);
		return ret;
	}

	/**
	 * Metodo el cual exporta un objeto del tipo Atleta cuando la fecha de
	 * nacimiento es a partir del 01/01/2000, guardandolo en un archivo llamado
	 * "junior.dat"
	 * 
	 * @author Facundo
	 */
	public void exportarBinario() {
		File f = null;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {

			f = new File("junior.dat"); // creo el archivo
			fos = new FileOutputStream(f); // se lo paso a fos para escribir
			oos = new ObjectOutputStream(fos);// se lo paso a oos para que pueda escribir

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			LocalDate fecha = LocalDate.of(2000, 01, 01);
			Atleta a = null;

			for (int i = 0; i < Datos.ATLETAS.length; i++) {
				a = Datos.ATLETAS[i];
				LocalDate c = a.getPersona().getFechaNac();
				if (c.isBefore(fecha)) {
					a = new Atleta();
					a = Datos.ATLETAS[i];
					oos.writeObject(a);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null)
					oos.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/***
	 * Función que devuelve una cadena de caracteres con los datos del atleta con el
	 * siguiente formato: <nombre> “ (” <documentacion> ”) del año
	 * ”<fechaNac.año>’\t’<peso>”Kgs. ”<altura>”m.“
	 */
	@Override
	public String toString() {
		return "" + persona.getNombre() + " (" + persona.getNifnie().mostrar() + ") del año "
				+ persona.getFechaNac().getYear() + "\t" + peso + "Kgs. " + altura + "m.";
	}

}
