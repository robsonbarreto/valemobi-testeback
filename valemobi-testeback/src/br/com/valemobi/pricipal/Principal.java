package br.com.valemobi.pricipal;

import br.com.valemobi.cliente.Cliente;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Vetor com o nome dos clientes
		String nmCliente[] = {"Felipe Mendonça", "Edgar Araújo","Fernando de Melo","Junior Santos", "Jośe Alvez", 
				"Paulo Silva", "Fernando Marinho", "Eliseu Costa Neto", "Valmir Santos Filho", "Paola Lima",
				"Paula Siqueira", "Fernanda Abreu", "Julia Costa", "Leandra Souza Aguiar", "Maria Oliveira",
				"Cimento S/A", "Confeitaria doce sonho", "Bom preço supermercado", "Lar móveis", "FarMelhor farmácia"};

		//Vetor com o CPF/CNPJ dos clientes
		String cpfcnpjCliente[] = {"85236922313", "97676676100", "96175988760", "74581863562","40223256668",
				"17427880331","34752128543", "89052916276", "10610262807","47153957898",
				"87614242343","42856782841", "71902874188", "44601221625", "38326877859",
				"86285514000156", "14744408000182", "47235633000142", "45429382000101", "92746733000198"};

		//Vetor com o status (ativo/inativo) de cada cliente
		boolean statusCliente[] = {true, true, true, true, false, true, true, false, false, true,
				true, true, false, false, false, false, true, true, true, true};

		//Vetor com o saldo de cada cliente

		double saldoCliente[] = {1565.00, 3055.00, 1955.10, 8035.25, 9802.00, 
				3025.00, 500.00, 965.00, 200.00, 100.00,
				1000.00, 0.00, 800.00, 150.00, 900.00,
				30000.00, 10000.00, 500000.00, 100000.00, 35000.00};





		for(int i = 0; i < nmCliente.length; i++){
			//cria o cliente com base no numero atual do contador
			Cliente cliente = new Cliente();
			cliente.setNm_customer(nmCliente[i]);
			cliente.setCpf_cnpj(cpfcnpjCliente[i]);
			cliente.setIs_active(statusCliente[i]);
			cliente.deposita(saldoCliente[i]);

			//insere o cliente no banco
			cliente.inserirCliente(cliente);
		}

		//executa o método referente à média salarial
		Cliente cliente = new Cliente();
		cliente.mediaSaldo();

	}

}
