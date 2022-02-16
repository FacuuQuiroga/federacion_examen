package entidades;

public class Palmares<T, S> {
	private T t;// cualquier tipo de medalla
	private S s;// puede ser un Atleta o un Equipo
	private long id;
	private Prueba prueba;
	private long explicaciones;

	public Palmares() {
	}

	public Palmares(T t, S s, long id, Prueba p, Long ex) {
		this.t = t;
		this.s = s;
		this.id = id;
		this.prueba = p;
		this.explicaciones = ex;
	}

	/**
	 * 
	 * @return  el id del propio objeto Palmarés, seguido los datos de la medalla a
	 *         partir de su método toString(), seguido del nombre de la prueba y su
	 *         fecha y lugar de celebración, seguido del dorsal y calle con que
	 *         participó y, en función de si el participante es Atleta o Equipo,
	 *         mostrar los datos de la/s persona/s que ganaron esa medalla en dicha
	 *         prueba, a partir del método toString() del campo persona asociado/s,
	 *         según el caso.
	 * @author Facundo
	 */
	public static void mostrar() {


		
		
		
		
		
		
		System.out.println("nada");
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public S getS() {
		return s;
	}

	public void setS(S s) {
		this.s = s;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Prueba getPrueba() {
		return prueba;
	}

	public void setPrueba(Prueba prueba) {
		this.prueba = prueba;
	}

	public long getExplicaciones() {
		return explicaciones;
	}

	public void setExplicaciones(long explicaciones) {
		this.explicaciones = explicaciones;
	}

	@Override
	public String toString() {
		return "Palmares [t=" + t + ", s=" + s + ", id=" + id + ", prueba=" + prueba + ", explicaciones="
				+ explicaciones + "]";
	}

}
