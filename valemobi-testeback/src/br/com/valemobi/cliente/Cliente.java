package br.com.valemobi.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import br.com.valemobi.jdbc.Conexao;

/*classe para representar o objeto Cliente e comportar 
os métodos pertinentes às operações relacionada à ele*/

public class Cliente {
	private int id_customer;
	private String cpf_cnpj;
	private String nm_customer;
	private boolean is_active;
	private double vl_total;
	public int getId_customer() {
		return id_customer;
	}
	public void setId_customer(int id_customer) {
		this.id_customer = id_customer;
	}
	public String getCpf_cnpj() {
		return cpf_cnpj;
	}
	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}
	public String getNm_customer() {
		return nm_customer;
	}
	public void setNm_customer(String nm_customer) {
		this.nm_customer = nm_customer;
	}
	public boolean isIs_active() {
		return is_active;
	}
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	public double getVl_total() {
		return vl_total;
	}

	/*como estamos lidando com dinheiro um "set" para vl_total não seria seguro
	 portanto foi criado o método deposita*/ 

	public void deposita (double x) {
		this.vl_total += x;
	}

	//método de inserção de clientes
	public void inserirCliente(Cliente cliente){
		//faz a conexão com o banco
		Connection conexao = new Conexao().getConnection();
		try {
			//Cria o prepared statement
			String sql = "insert into tb_customer_account " +
					"(cpf_cnpj,nm_customer,is_active,vl_total) " +
					"values (?,?,?,?)";
			PreparedStatement stmt = conexao.prepareStatement(sql);

			//preenche os valores
			stmt.setString(1, cliente.getCpf_cnpj());
			stmt.setString(2, cliente.getNm_customer());
			stmt.setBoolean(3, cliente.isIs_active());
			stmt.setDouble(4, cliente.getVl_total());

			//executa
			stmt.execute();
			stmt.close();

			//fecha a conexão
			conexao.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mediaSaldo (){

		//variaveis auxiliares para o calculo da soma
		double soma = 0;
		double media = 0;

		//Faz a conexao com o banco
		Connection conexao = new Conexao().getConnection();
		try{
			//Array de clientes para reunir os clientes trazidos pela query
			ArrayList<Cliente> arrayCliente = new ArrayList<Cliente>();

			/*cria o prepared statement com a query restringindo os registros à  
			clientes com mais 560 em saldo e com ID entre 1500 e 2700*/
			PreparedStatement stmt = conexao.prepareStatement("select * from tb_customer_account where vl_total > 560 and id_customer between 1500 and 2700");

			// executa a query
			ResultSet rs = stmt.executeQuery();



			//laço para percorrer os registros encontrados adicionando-os no array de cliente
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setNm_customer(rs.getString("nm_customer"));
				cliente.setCpf_cnpj(rs.getString("cpf_cnpj"));
				cliente.setId_customer(rs.getInt("id_customer"));
				cliente.deposita(rs.getDouble("vl_total"));
				cliente.setIs_active(rs.getBoolean("is_active"));
				arrayCliente.add(cliente); 
			}

			if(arrayCliente.size()>0){
				//ordena o array do maior para o menor saldo
				Collections.sort(arrayCliente, new ComparadorCliente());

				//percorre o array somando os valores
				for(int i = 0; i < arrayCliente.size();i++){
					soma += arrayCliente.get(i).getVl_total();
				}

				//calcula a média dos saldos encontrados
				media = soma/arrayCliente.size();

				//formata e imprime a média calculada
				DecimalFormat formato = new DecimalFormat("#.##");
				System.out.print("Média: "+formato.format(media)+"\n");
				System.out.print("================================================="+"\n");

				//imprime o array de cliente já ordenado por saldo
				for (int i = 0; i < arrayCliente.size(); i++){
					System.out.print("ID: "+arrayCliente.get(i).getId_customer()+"\n");
					System.out.print("NOME: "+arrayCliente.get(i).getNm_customer()+"\n");
					System.out.print("CPF/CNPJ: "+arrayCliente.get(i).getCpf_cnpj()+"\n");
					System.out.print("SALDO: "+arrayCliente.get(i).getVl_total()+"\n");
					System.out.print("ATIVO?: "+arrayCliente.get(i).isIs_active()+"\n");
					System.out.print("================================================="+"\n");
				}
			}else{
				System.out.print("Média: Nenhum registro dentro dos requisitos especificados");
			}
			//fecha o result statement, prepared statement e a conexao com o banco
			rs.close();
			stmt.close();
			conexao.close();

		} catch(SQLException e){
			e.printStackTrace();
		}
	}
}




