package entidades;

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

	public static Manager nuevoManager() {
		Scanner in = new Scanner(System.in);
		Manager ret = null;
		DatosPersona dp = null;
		boolean valido = false, validoid = false;
		long id = -1;
		String telefono = null, direccion = null;

		do {
			System.out.println("Introduzca el id del nuevo colegiado:");
			id = in.nextInt();
			if (id > 0)
				validoid = true;
			else
				System.out.println("Valor incorrecto para el identificador.");
		} while (!validoid);

		do {
			System.out.println("Introduzca el telefono del nuevo Manager:");
			telefono = in.nextLine();
			valido = validaciones.Validaciones.validarTelefono(telefono);
			System.out.println("Introduzca la direccion del nuevo Manager:");
			direccion = in.nextLine();
		} while (!valido);

		System.out.println("Introduzca ahora los datos personales:");
		dp = DatosPersona.nuevaPersona();
		ret = new Manager(id, telefono, direccion, dp);
		return ret;
	}

	@Override
	public String toString() {
		return "id=" + this.id + "nombre" + persona.getNombre() + "(" + persona.getNifnie() + ")" + " del año"
				+ persona.getFechaNac() + ". Tfno:" + telefono + ", Tfno2: " + persona.getTelefono();
	}

}
