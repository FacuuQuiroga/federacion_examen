package entidades;

import java.sql.Array;
import java.util.Comparator;
import java.util.LinkedList;

import utils.Datos;

public class ComparadorMedallas implements Comparator<Metal> {

	// se ordenen según su valor de pureza
	@Override
	public int compare(Metal o1, Metal o2) {
		if (o1.getPureza() == o2.getPureza())
			return 0;
		else if (o1.getPureza() > o2.getPureza())
			return 1;
		else
			return 0;
	}

	public static void mostrarOrdenadas() {

		LinkedList<Metal> metales;
		metales = new LinkedList<Metal>();

		for (Oro o : Datos.OROS) {
			metales.add(o);
		}
		for (Plata p : Datos.PLATAS) {
			metales.add(p);
		}
		for (Bronce b : Datos.BRONCES) {
			metales.add(b);
		}
		System.out.print("los metales ordenados por su pureza son \n" + metales.toString());
	}
}
