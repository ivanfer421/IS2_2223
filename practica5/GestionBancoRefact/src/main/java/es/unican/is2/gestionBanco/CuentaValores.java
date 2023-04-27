package es.unican.is2.gestionBanco;

import java.util.LinkedList;
import java.util.List;

public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	// WMC+1
	public CuentaValores(String numCuenta) {
		super(numCuenta);
		valores = new LinkedList<Valor>();
	}
	
	// WMC+1
	public List<Valor> getValores() {
		return valores;
	}
	
	// WMC+1
	public boolean anhadeValor(Valor valor) {
		for (Valor v:valores) {		// CCog+1
			if (v.getEntidad().equals(valor.getEntidad()))		// CCog+2
				return false;
		}
		valores.add(valor);
		return true;
	}
	
}
