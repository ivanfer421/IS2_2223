package es.unican.is2.dao;




import java.util.List;

import es.unican.is2.aseguradora.Aseguradora;
import es.unican.is2.common.Cliente;



public class ClientesDAO implements IClientesDAO {

	private Aseguradora aseguradora;

	public ClientesDAO() {
		aseguradora = Aseguradora.creaAseguradora();
	}

	@Override
	public Cliente creaCliente(Cliente c) {
		aseguradora = Aseguradora.creaAseguradora();
		if (aseguradora.getClientes().contains(c))
			return null;
		aseguradora.getClientes().add(c);
		Aseguradora.flush(aseguradora);
		return cliente(c.getDni());
	}

	@Override
	public Cliente cliente(String dni) {
		aseguradora = Aseguradora.creaAseguradora();
		for (Cliente c: aseguradora.getClientes()) {
			if (c.getDni().equals(dni)) {
				return c;
			}
		}
		return null;
	}


	@Override
	public Cliente actualizaCliente(Cliente nuevo) {
		aseguradora = Aseguradora.creaAseguradora();
		if (aseguradora.getClientes().contains(nuevo)) {
			aseguradora.getClientes().remove(nuevo);
			aseguradora.getClientes().add(nuevo);
			Aseguradora.flush(aseguradora);
			return nuevo;
		}
		return null;
	}


	@Override
	public Cliente eliminaCliente(String dni) {
		aseguradora = Aseguradora.creaAseguradora();
		Cliente c = cliente(dni);
		if (c!=null) {
			aseguradora.getClientes().remove(c);
			Aseguradora.flush(aseguradora);
			return c;
		}
		return null;
	}


	@Override
	public List<Cliente> clientes() {
		aseguradora = Aseguradora.creaAseguradora();
		return aseguradora.getClientes();
	}

}
