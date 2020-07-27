package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanProduto produto) {

		try {

			String sql = "insert into produto (nome, quantidade,valor) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produto.getNome());
			insert.setString(2, produto.getQuantidade());
			insert.setString(3, produto.getValor());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<BeanProduto> listar() throws Exception {
		List<BeanProduto> listar = new ArrayList<BeanProduto>();

		String sql = "select * from produto";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanProduto bean_produto = new BeanProduto();
			bean_produto.setId(resultSet.getLong("id"));
			bean_produto.setNome(resultSet.getString("nome"));
			bean_produto.setQuantidade(resultSet.getString("quantidade"));
			bean_produto.setValor(resultSet.getString("valor"));
			listar.add(bean_produto);
		}

		return listar;
	}

	public void delete(String id) {

		try {
			String sql = "delete from produto where id = '" + id + "'";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public BeanProduto consultar(String id) throws Exception {
		String sql = "select * from produto where id='" + id + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			BeanProduto bean_produto = new BeanProduto();
			bean_produto.setId(resultSet.getLong("id")); 
			bean_produto.setNome(resultSet.getString("nome"));
			bean_produto.setQuantidade(resultSet.getString("quantidade"));
			bean_produto.setValor(resultSet.getString("valor"));
			return bean_produto;
		}

		return null;
	}
	
	
	public boolean validarNome(String nome) throws Exception {
		String sql = "select count(1) as qtd from produto where nome='" + nome + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			
			return resultSet.getInt("qtd") <= 0;/*Return true*/
		}

		return false;
	}


	public void atualizar(BeanProduto produto) {
		try {
			String sql = "update produto set nome = ?, quantidade = ?, valor = ?  where id = "
					+ produto.getId();

			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setString(2, produto.getQuantidade());
			preparedStatement.setString(3, produto.getValor());
			
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}


}
