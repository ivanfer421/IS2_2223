package es.unican.is2.gestionBanco;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	// WMC + 1
	public Debito(String numero, String titular, CuentaAhorro c) {
		super(numero, titular, c);
	}
	
	// WMC + 1
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) {		// WMC + 1	CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Retirada en cajero automático", x);
		saldoDiarioDisponible-=x;
	}
	
	// WMC + 1
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) {		// WMC + 1	CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	// WMC + 1
	public LocalDate getCaducidadDebito() {
		return this.mCuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	public void restableceSaldo() {		// WMC + 1
		saldoDiarioDisponible = mCuentaAsociada.getLimiteDebito();
	}
	
	// WMC + 1
	public CuentaAhorro getCuentaAsociada() {
		return mCuentaAsociada;
	}

}