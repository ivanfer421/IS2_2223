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

	private IClientesDAO clientes;
	private ISegurosDAO seguros;

	public GestionSeguros(IClientesDAO daoContribuyentes, ISegurosDAO daoVehiculos) {
		clientes = daoContribuyentes;
		seguros = daoVehiculos;
	}

	@Override
	public Cliente cliente(String dni) throws OperacionNoValida {
		if (clientes.cliente(dni) == null) {
			throw new OperacionNoValida("No se existe ningun cliente con ese DNI");
		}
		return clientes.cliente(dni);
	}

	@Override
	public Seguro seguro(String matricula) throws OperacionNoValida {
		if (seguros.seguro(matricula) == null) {
			throw new OperacionNoValida("No se existe ningun seguro con esa matricula");
		}
		return seguros.seguro(matricula);
	}

	@Override
	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida {
		if ((clientes.cliente(dni) == null) || (seguros.seguro(s.getMatricula()) == null)) {
			throw new OperacionNoValida("No se ha podido anhadir el nuevo seguro");
		}
		seguros.creaSeguro(s);
		return null;
	}

	@Override
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida {
		Cliente c = clientes.cliente(dni);
		Seguro s = seguros.seguro(matricula);
		if ((c == null) || (s == null) || (c.getSeguros().contains(s))) {
			throw new OperacionNoValida("No se ha podido dar de baja al seguro");
		}
		seguros.eliminaSeguro(matricula);
		return null;
	}

	@Override
	public Cliente nuevoCliente(Cliente c) throws OperacionNoValida {
		if (clientes.cliente(c.getDni()) != null) {
			throw new OperacionNoValida("No se ha podido anhadir el nuevo cliente");
		}
		clientes.creaCliente(c);
		return null;
	}

	@Override
	public Cliente bajaCliente(String dni) throws OperacionNoValida {
		if ((clientes.cliente(dni) == null) || (clientes.cliente(dni).getSeguros().isEmpty())) {
			throw new OperacionNoValida("No se ha podido dar de baja al cliente");
		}
		return null;
	}
}
