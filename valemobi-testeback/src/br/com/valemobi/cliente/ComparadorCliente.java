package br.com.valemobi.cliente;

import java.util.Comparator;

public class ComparadorCliente implements Comparator<Cliente> {

	//compara os clientes com base no saldo ordenando do maior para o menor
	public int compare(Cliente c1, Cliente c2) {
		if (c1.getVl_total() < c2.getVl_total()) return +1;
		else if (c1.getVl_total() > c2.getVl_total()) return -1;
		else return 0;
	}

}
