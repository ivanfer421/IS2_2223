package es.unican.is2.gestionBanco;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	public String nombre;
	public Direccion direccion;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

    // WMC + 1
 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) {  
		this.nombre = titular;
		this.direccion.calle = calle;
		this.direccion.zip = zip;
		this.direccion.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
 	// WMC + 1
	public void cambiaDireccion(String calle, String zip, String localidad) {
		this.direccion.calle = calle;
		this.direccion.zip = zip;
		this.direccion.localidad = localidad;
	}
	
	// WMC + 1
	public void anhadeCuenta(Cuenta c) {
		Cuentas.add(c);
	}
	
	// WMC + 1
	public double getSaldoTotal() {
		double total = 0.0;
		for (Cuenta c: Cuentas) {  // WMC + 1	CCog + 1
			if (c instanceof CuentaAhorro) { // WMC + 1		CCog + 2
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  { // WMC + 1	CCog + 2
				calculaSaldoCuenta(c);
			}
		}
		return total;
	}
	
	// WMC + 1
	private double calculaSaldoCuenta(Cuenta c) {
		double t = 0.0;
		for (Valor v: ((CuentaValores) c).getValores()) { // WMC + 1	CCog + 1
			t += v.getCotizacion()*v.getNumValores();
		}
		return t;
	}
	
	// WMC + 1
	public String getNombre() {
		return nombre;
	}

	// WMC + 1
	public String getCalle() {
		return direccion.calle;
	}

	// WMC + 1
	public String getZip() {
		return direccion.zip;
	}

	// WMC + 1
	public String getLocalidad() {
		return direccion.localidad;
	}

	// WMC + 1
	public String getTelefono() {
		return telefono;
	}

	// WMC + 1
	public String getDni() {
		return dni;
	}
	
	
	
}