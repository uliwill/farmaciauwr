package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;

public class FornecedoresDAO {
	public void salvar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}

	public void excluir(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();
	}

	public void editar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();
	}

	public Fornecedores buscaPorCodigo(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, f.getCodigo());

		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;

		if (resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}

		return retorno;
	}

	public static void main(String[] args) {
		// SALVAR
//		Fornecedores f1 = new Fornecedores();
//		f1.setDescricao("DESCRICAO 2");
//
//		Fornecedores f2 = new Fornecedores();
//		f2.setDescricao("DESCRICAO 3");
//
//		FornecedoresDAO fdao = new FornecedoresDAO();
//		try {
//			fdao.salvar(f1);
//			fdao.salvar(f2);
//			System.out.println("Salvo com sucesso!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("Erro ao salvar!");
//		}

		// EXCLUIR
//		Fornecedores f1 = new Fornecedores();
//		f1.setCodigo(2L);
//
//		FornecedoresDAO fdao = new FornecedoresDAO();
//
//		try {
//			fdao.excluir(f1);
//
//			System.out.println("Deletado com sucesso!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("Erro ao deletar!");
//		}

		// EDITAR
//		Fornecedores f1 = new Fornecedores();
//		f1.setCodigo(1L);
//		f1.setDescricao("Ulisses");
//
//		FornecedoresDAO fdao = new FornecedoresDAO();
//
//		try {
//			fdao.editar(f1);
//			System.out.println("Editado com sucesso!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("Erro ao editar!");
//		}
		
		// BUSCAR
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(1L);

		Fornecedores f2 = new Fornecedores();
		f2.setCodigo(4L);

		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			Fornecedores f3 = fdao.buscaPorCodigo(f1);
			Fornecedores f4 = fdao.buscaPorCodigo(f2);
			System.out.println("Resultado 1: " + f3);
			System.out.println("Resultado 2: " + f4);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao efetuar busca!");
		}

	}
}
