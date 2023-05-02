package es.unican.is2.gestionBanco;

import java.time.LocalDate;

public class Cuenta {
	
	private String numCuenta;
	private LocalDate FechaDeCaducidadTarjetaDebito;
	private LocalDate FechaDeCaducidadTarjetaCredito;
	
	// WMC + 1
	public Cuenta(String numCuenta, LocalDate date, LocalDate date2) {
		this.numCuenta = numCuenta;
		this.FechaDeCaducidadTarjetaDebito = date;
		this.FechaDeCaducidadTarjetaCredito = date2;
	}
	
	// WMC + 1
	public Cuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	
	// WMC + 1
	public String getNumCuenta() {
		return numCuenta;
	}
	
	// WMC+1 
	public LocalDate getCaducidadDebito() {
		return this.FechaDeCaducidadTarjetaDebito;
	}

	// WMC+1 
	public LocalDate getCaducidadCredito() {
		return this.FechaDeCaducidadTarjetaCredito;
	}
	
}
