package es.unican.is2.gestionBanco;

/**
 * Clase que representa un valor en bolsa (paquete de acciones). 
 * Cada valor contiene un número de acciones 
 * de una de las entidades del IBEX 35
 */
public class Valor {
	
	private String entidad;	
	private int numAcciones;
	private double cotizacion;
	
	// WMC+1
	public Valor(String entidad, int numAcciones, double cotizacionActual) {
		this.entidad = entidad;
		this.numAcciones = numAcciones;
		this.cotizacion = cotizacionActual;
	}
	
	// WMC+1
	public int getNumValores() {
		return numAcciones;
	}

	// WMC+1
	public void setNumValores(int numValores) {
		this.numAcciones = numValores;
	}

	// WMC+1
	public double getCotizacion() {
		return cotizacion;
	}
	
	// WMC+1
	public void setCotizacion(double cotizacion) {
		this.cotizacion = cotizacion;
	}

	// WMC+1
	public String getEntidad() {
		return entidad;
	}

	@Override
	public boolean equals(Object obj) {		// WMC+1
		Valor other = (Valor)obj;
		return (entidad.equals(other.entidad) & numAcciones==other.numAcciones);

	}
	
	

}