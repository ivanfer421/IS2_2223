package es.unican.is2.gestionBanco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Credito extends Tarjeta {
	
	private double mCredito;
	private List<Movimiento> mMovimientosMensuales;
	private List<Movimiento> mhistoricoMovimientos;
	
	
	// WMC + 1
	public Credito(String numero, String titular, CuentaAhorro c, double credito) {
		super(numero, titular, c);
		mCredito = credito;
		mMovimientosMensuales = new LinkedList<Movimiento>();
		mhistoricoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisión del 5%.
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
		m.setF(now);
		m.setC("Retirada en cajero automático");
		x += x * 0.05; // Añadimos una comisión de un 5%
		m.setI(-x);
		
		if (getGastosAcumulados()+x > mCredito) // WMC + 1		CCog + 1
			throw new saldoInsuficienteException("Crédito insuficiente");
		else {
			mMovimientosMensuales.add(m);
		}
	}

	// WMC + 1
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (x<0) // WMC + 1		CCog + 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > mCredito) // WMC + 1		CCog + 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Compra a crédito en: " + datos);
		m.setI(-x);
		mMovimientosMensuales.add(m);
	}
	
	// WMC + 1
    public double getGastosAcumulados() {
		double r = 0.0;
		// WMC + 1
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) {	// CCog + 1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		return -r;
	}
	
	
    // WMC + 1
	public LocalDate getCaducidadCredito() {
		return this.mCuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Método que se invoca automáticamente el día 1 de cada mes
	 */
	// WMC + 1
	public void liquidar() {
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setF(now);
		liq.setC("Liquidación de operaciones tarjeta crédito");
		double r = 0.0;
		// WMC + 1
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) {	// CCog + 1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		liq.setI(r);
	
		// WMC + 1
		if (r != 0)		// CCog + 1
			mCuentaAsociada.addMovimiento(liq);
		
		mhistoricoMovimientos.addAll(mMovimientosMensuales);
		mMovimientosMensuales.clear();
	}

	// WMC + 1
	public List<Movimiento> getMovimientosUltimoMes() {
		return mMovimientosMensuales;
	}
	
	// WMC + 1
	public Cuenta getCuentaAsociada() {
		return mCuentaAsociada;
	}
	
	// WMC + 1
	public List<Movimiento> getMovimientos() {
		return mhistoricoMovimientos;
	}

}