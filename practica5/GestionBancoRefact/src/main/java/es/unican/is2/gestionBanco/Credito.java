package es.unican.is2.gestionBanco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Credito extends Tarjeta {
	
	private double Credito;
	private List<Movimiento> movimientosMensuales;
	private List<Movimiento> historicoMovimientos;
	
	
	// WMC + 1
	public Credito(String numero, String titular, CuentaAhorro c, double credito) {
		super(numero, titular, c);
		Credito = credito;
		movimientosMensuales = new LinkedList<Movimiento>();
		historicoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	// WMC + 1
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (x<0) // WMC + 1		CCog + 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto("Retirada en cajero autom�tico");
		x += x * 0.05; // A�adimos una comisi�n de un 5%
		m.setImporte(-x);
		
		if (getGastosAcumulados()+x > Credito) // WMC + 1		CCog + 1
			throw new saldoInsuficienteException("Cr�dito insuficiente");
		else {
			movimientosMensuales.add(m);
		}
	}

	// WMC + 1
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (x<0) // WMC + 1		CCog + 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > Credito) // WMC + 1		CCog + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setFecha(now);
		m.setConcepto("Compra a cr�dito en: " + datos);
		m.setImporte(-x);
		movimientosMensuales.add(m);
	}
	
	// WMC + 1
    public double getGastosAcumulados() {
		double r = 0.0;
		for (int i = 0; i < this.movimientosMensuales.size(); i++) {	// WMC+1 CCog + 1
			Movimiento m = (Movimiento) movimientosMensuales.get(i);
			r += m.getImporte();
		}
		return -r;
	}
	
	
    // WMC + 1
	public LocalDate getCaducidadCredito() {
		return this.cuentaAsociada.getCaducidadCredito();
	}

	/**
	 * M�todo que se invoca autom�ticamente el d�a 1 de cada mes
	 */
	// WMC + 1
	public void liquidar() {
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setFecha(now);
		liq.setConcepto("Liquidaci�n de operaciones tarjeta cr�dito");
		double r = 0.0;
		for (int i = 0; i < this.movimientosMensuales.size(); i++) {	// WMC+1 CCog + 1
			Movimiento m = (Movimiento) movimientosMensuales.get(i);
			r += m.getImporte();
		}
		liq.setImporte(r);

		if (r != 0)		// WMC+1 CCog + 1
			cuentaAsociada.addMovimiento(liq);
		
		historicoMovimientos.addAll(movimientosMensuales);
		movimientosMensuales.clear();
	}

	// WMC + 1
	public List<Movimiento> getMovimientosUltimoMes() {
		return movimientosMensuales;
	}
	
	// WMC + 1
	public List<Movimiento> getMovimientos() {
		return historicoMovimientos;
	}

}