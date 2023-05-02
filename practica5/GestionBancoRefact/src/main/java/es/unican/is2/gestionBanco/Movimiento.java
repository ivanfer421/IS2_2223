package es.unican.is2.gestionBanco;

import java.time.LocalDateTime;

public class Movimiento {
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	// WMC+1
	public double getImporte() {
		return importe;
	}

	// WMC+1
	public void setImporte(double newImporte) {
		importe = newImporte;
	}
	
	// WMC+1
	public String getConcepto() {
		return concepto;
	}

	// WMC+1
	public void setConcepto(String newConcepto) {
		concepto = newConcepto;
	}

	// WMC+1
	public LocalDateTime getFecha() {
		return fecha;
	}

	// WMC+1
	public void setFecha(LocalDateTime newFecha) {
		fecha = newFecha;
	}
	
}