package entidades;

import java.sql.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import utils.Datos;
import utils.Utilidades;

public class Equipo extends Participante {
	private long idEquipo;
	private int anioinscripcion;
	private Manager manager;
	private Atleta[] atletas;
	private String nombre;

	public Equipo(long id, int anioinscripcion, Manager manager, Atleta[] atletas) {
		super();
		this.idEquipo = id;
		this.anioinscripcion = anioinscripcion;
		this.manager = manager;
		this.atletas = atletas;
	}

	public Equipo(long idParticipante, Equipo e, int dorsal, char calle) {
		super(idParticipante, dorsal, calle);
		this.idEquipo = e.idEquipo;
		this.anioinscripcion = e.anioinscripcion;
		this.manager = e.manager;
		this.atletas = e.atletas;
	}

	@Override
	public long getId() {
		return idEquipo;
	}

	@Override
	public void setId(long id) {
		this.idEquipo = id;
	}

	public int getAnioinscripcion() {
		return anioinscripcion;
	}

	public void setAnioinscripcion(int anioinscripcion) {
		this.anioinscripcion = anioinscripcion;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Atleta[] getAtletas() {
		return atletas;
	}

	public void setAtletas(Atleta[] atletas) {
		this.atletas = atletas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Examen 1 Ejercicio 3
	@Override
	public String toString() {
		String ret = "";
		ret += "EQ" + idEquipo + ". de " + manager.getPersona().getNombre() + " (" + manager.getDireccion() + ") "
				+ atletas.length + " componentes en el equipo:\n";
		for (Atleta a : atletas) {
			ret += a.getId() + ". " + a.getPersona().getNombre() + "("
					+ a.getPersona().getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ") "
					+ " Datos físicos:\t" + a.getPeso() + "Kgs.\t" + a.getAltura() + "m.\n";
		}
		ret += "Valido durante el " + anioinscripcion;
		return ret;
	}

	// ejercicio 1 examen 8 Facundo
	public static Equipo nuevoEquipo() {
		Equipo ret = null;
		Scanner in = new Scanner(System.in);

		long id = -1;
		int anio = 0;
		Manager man = null;
		Atleta atletas[] = null;
		ArrayList<Atleta> atletaAux = new ArrayList<Atleta>();
		String nomb;
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del nuevo Equipo:");
			id = in.nextInt();
			if (id > 0)
				valido = true;
			else
				System.out.println("Valor incorrecto para el identificador.");
		} while (!valido);
		valido = false;

		do {
			LocalDate fecha = LocalDate.now();
			System.out.println("La fecha de inscripcion es correcta?\n" + fecha);
			valido = utils.Utilidades.leerBoolean();
		} while (!valido);
		valido = false;
		// manager
		do {
			long idMan;
			Manager managers[] = Datos.MANAGERS;

			System.out.println("Desea inscribir un manager nuevo?");
			boolean opcion = utils.Utilidades.leerBoolean();
			if (opcion) {
				man = Manager.nuevoManager();
				idMan = man.getId();
			} else {
				System.out.println("Seleccione el id del manager en la lista:");
				for (Manager m : managers) {
					System.out.print("id:" + m.getId() + "Nombre:" + m.getPersona().getNombre());
				}
				idMan = in.nextLong();
			}
			for (Manager m : managers) {
				if (m.getId() == idMan) {
					man = m;
				}
			}
		} while (!valido);
		valido = false;

		// AGREGAR ATLETAS
		do {
			Atleta atletasDatos[] = Datos.ATLETAS;
			int nro = 0;
			System.out.print("cuantos Atletas tiene el Equipo");
			nro = in.nextInt();

			Atleta aux = null;
			long idAtleta = 0;
			System.out.print("el Atleta ya esta registrado? pulse N para registrar uno nuevo");
			boolean registrado = utils.Utilidades.leerBoolean();

			if (!registrado) {
				for (int i = 0; i < nro; i++) {
					Atleta atletaUno = Atleta.nuevoAtleta();
					atletaAux.add(atletaUno);
				}
			} else {
				System.out.println("Seleccione el id del Atleta en la lista:");
				for (Atleta a : atletas) {
					System.out.print("id:" + a.getId() + "Nombre:" + a.getPersona().getNombre());
				}
				idAtleta = in.nextLong();
			}
			for (Atleta a : atletasDatos) {
				if(a.getId() == idAtleta) {
					aux = a;
					atletaAux.add(aux);
				}
			}
			System.out.println("Son los siguientes datos correctos?\n"+ aux.getPersona().toString());
			if(nro>5) {
				valido = true;
			}else {
				System.out.println("Desea añadir otro Alteta?");
				boolean otro = utils.Utilidades.leerBoolean();
				if(!otro);
				atletas = atletaAux.toArray(atletas);
				valido = false;
			}
		} while (!valido);
		valido = false;

		do {
			System.out.println("Introduzca el nombre del nuevo Equipo");
			nomb = in.nextLine();
			valido = true;
		} while (!valido);
		valido = false;

		ret = new Equipo(id, anio, man, atletas);
		return null;
	}

}
