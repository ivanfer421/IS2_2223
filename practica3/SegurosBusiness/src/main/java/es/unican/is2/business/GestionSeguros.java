package es.unican.is2.business;

import es.unican.is2.common.Cliente;
import es.unican.is2.common.Seguro;
import es.unican.is2.dao.IClientesDAO;
import es.unican.is2.dao.ISegurosDAO;
import es.unican.is2.excepciones.OperacionNoValida;
import es.unican.is2.iman.IGestionClientes;
import es.unican.is2.iman.IGestionSeguros;
import es.unican.is2.iman.IInfoSeguros;

public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {

	private Cliente cliente;
	private Seguro seguro;
	
	public GestionSeguros(IClientesDAO daoContribuyentes, ISegurosDAO daoVehiculos) {
		
	}

	public Cliente cliente(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	public Seguro seguro(String matricula) {
		// TODO Auto-generated method stub
		return null;
	}

	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida {
		// TODO Auto-generated method stub
		return null;
	}

	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida {
		// TODO Auto-generated method stub
		return null;
	}

	public Cliente nuevoCliente(Cliente c) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cliente bajaCliente(String dni) throws OperacionNoValida {
		// TODO Auto-generated method stub
		return null;
	}
}
