package entidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import utils.Datos;
import validaciones.Validaciones;

public class Manager {
	private long id;
	private String telefono;
	private String direccion;

	private DatosPersona persona;

	public Manager(long id, String telefono, String direccion) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.persona = Datos.buscarPersonaPorId(id);
	}

	public Manager(long id, String telefono, String direccion, DatosPersona dp) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.persona = dp;
	}

	public Manager() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public DatosPersona getPersona() {
		return this.persona;
	}

	// Examen 5 Ejercicio 4
	/***
	 * Función que pregunta al usuario por cada uno de los campos para un nuevo
	 * Manager, los valida y si son correctos devuelve un objeto Manager completo
	 * 
	 * @return un objeto Manager completo válido o null si hubo algún error
	 */
	public static Manager nuevoManager() {
		Manager ret = null;
		long id = -1;
		String telefono = "";
		String direccion = "";
		DatosPersona dp = null;
		Scanner in;
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del nuevo mánager:");
			in = new Scanner(System.in);
			id = in.nextInt();
			if (id > 0)
				valido = true;
			else
				System.out.println("Valor incorrecto para el identificador.");
		} while (!valido);

		valido = false;
		do {
			in = new Scanner(System.in);
			System.out.println("Introduzca el telefono de empresa del nuevo mánager");
			telefono = in.nextLine();
			valido = Validaciones.validarTelefono(telefono);
			if (!valido)
				System.out.println("ERROR: El valor introducido para el teéfono no es válido.");
		} while (!valido);

		valido = false;
		do {
			in = new Scanner(System.in);
			System.out.println("Introduzca la dirección del nuevo mánager:");
			direccion = in.next();
			valido = Validaciones.validarDireccion(direccion);
			if (!valido)
				System.out.println("ERROR: El valor introducido para la dirección no es válido.");
		} while (!valido);

		System.out.println("Introduzca ahora los datos personales:");
		in = new Scanner(System.in);
		dp = DatosPersona.nuevaPersona();

		ret = new Manager(id, telefono, direccion, dp);
		return ret;
	}

	/**
	 * @return Orden:
	 *         DatosPersona.id|DatosPersona.nombre|DatosPersona.documentacion|DatosPersona.fechaNac|
	 *         DatosPersona.telefono|Manager.id|Manager.telefono|Manager.direccion
	 * @author Facundo
	 */
	public String data() {
		String ret = "";
		ret = this.persona.getId() + "|" + this.getPersona().getNombre() + "|" + this.getPersona().getNifnie().mostrar()
				+ "|" + this.getPersona().getFechaNac() + "|" + this.getPersona().getTelefono() + "|" + this.getId()
				+ "|" + this.getTelefono() + "|" + this.getDireccion();
		return ret;
	}

	/**
	 * Metodo el cual exporta un objeto del tipo Manager Completo, guardandolo en un
	 * archivo llamado "manager.txt" de forma ordenada por linea
	 * 
	 * @author Facundo
	 */
	public void exportarManager() {
		System.out.println("Guardando datos en Manager.txt...");

		File fOut = null;
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(fw = new FileWriter(fOut = new File("managers.txt")));
			for (int i = 0; i < Datos.MANAGERS.length; i++) {
				Manager m = new Manager();
				m = Datos.MANAGERS[i];
				bw.write(m.data() + "\n");
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/***
	 * Función que devuelve una cadena de caracteres con los datos del mánager en el
	 * siguiente formato: <idManager> <nombre> ” (” <documentacion> ”) del año ”
	 * <fechaNac.año> ” Tfno1: <Manager.telefono>” ,Tfno2: ” <DatosPersona.telefono>
	 */
	@Override
	public String toString() {
		return "" + id + ". " + persona.getNombre() + " (" + persona.getNifnie().mostrar() + ") del año "
				+ persona.getFechaNac().getYear() + " Tfno1: " + telefono + " , Tfno2:" + persona.getTelefono() + "]";
	}

}
