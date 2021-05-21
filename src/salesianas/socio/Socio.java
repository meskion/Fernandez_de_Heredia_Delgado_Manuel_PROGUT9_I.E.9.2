package salesianas.socio;

public class Socio {
	private Integer nSocio, edad, estatura;
	private String nombre, localidad;
	
	public Socio(Integer nSocio,String nombre, Integer edad, Integer estatura, String localidad) {
		super();
		this.nSocio = nSocio;
		this.edad = edad;
		this.estatura = estatura;
		this.nombre = nombre;
		this.localidad = localidad;
	}

	public Integer getnSocio() {
		return nSocio;
	}

	public void setnSocio(Integer nSocio) {
		this.nSocio = nSocio;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getEstatura() {
		return estatura;
	}

	public void setEstatura(Integer estatura) {
		this.estatura = estatura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	
	
	
}
