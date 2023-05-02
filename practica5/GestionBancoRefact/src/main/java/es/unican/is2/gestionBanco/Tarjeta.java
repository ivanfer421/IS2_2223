package es.unican.is2.gestionBanco;

public abstract class Tarjeta {
	
	protected String numero, titular;		
	protected CuentaAhorro cuentaAsociada;

	// WMC+1
	public Tarjeta(String numero, String titular, CuentaAhorro c) {
		this.numero = numero;
		this.titular = titular;
		this.cuentaAsociada = c;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException;		// WMC+1

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double x)		// WMC+1
			throws saldoInsuficienteException, datoErroneoException;
	
	
	// WMC + 1
	public Cuenta getCuentaAsociada() {
		return cuentaAsociada;
	}
}