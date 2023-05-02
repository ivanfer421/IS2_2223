package es.unican.is2.gestionBanco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> movimientos;
	private double limiteDebito;

	// WMC+1 
	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) throws datoErroneoException {
		super(numCuenta, date, date2);
		movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	// WMC+1 
	public void ingresar(double x) throws datoErroneoException {
		ingresaSaldoAux(null, x);
	}

	// WMC+1 
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		retiraSaldoAux(null, x);
	}

	// WMC+1 
	public void ingresar(String concepto, double x) throws datoErroneoException {
		ingresaSaldoAux(concepto, x);
	}
	
	// WMC+1 
	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException {
		retiraSaldoAux(concepto, x);
	}
		
	// WMC+1 
	public void ingresaSaldoAux(String concepto, double x) throws datoErroneoException {
		if (x <= 0)		// WMC+1 	CCog+1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		if (concepto == null) {		// WMC+1	CCog+1
			m.setConcepto("Ingreso en efectivo");
		} else {
			m.setConcepto(concepto);
		}
		m.setImporte(x);
		this.movimientos.add(m);
	}
	
	// WMC+1
	public void retiraSaldoAux(String concepto, double x) throws saldoInsuficienteException, datoErroneoException {
		if (getSaldo() < x)		// WMC+1 	CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0)		// WMC+1 	CCog+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		if (concepto == null) { // WMC+1 	CCog+1
			m.setConcepto("Retirada de efectivo");
		} else {
			m.setConcepto(concepto);
		}
		m.setImporte(-x);
		this.movimientos.add(m);
	}
	

	// WMC+1 
	public double getSaldo() {
		double r = 0.0;
		for (int i = 0; i < this.movimientos.size(); i++) {	// WMC+1 	CCog+1
			Movimiento m = (Movimiento) movimientos.get(i);
			r += m.getImporte();
		}
		return r;
	}

	// WMC+1 
	public void addMovimiento(Movimiento m) {
		movimientos.add(m);
	}

	// WMC+1 
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	// WMC+1 
	public double getLimiteDebito() {
		return limiteDebito;
	}

}