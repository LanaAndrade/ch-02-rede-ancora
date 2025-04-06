package integracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelos.Produto;

public class ProdutoDAO {

	public void inserirProduto(Produto produto, Connection conn) throws SQLException {
	    String sql = "INSERT INTO produto (nome, descricao, preco, estoque) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, produto.getNome());
	        stmt.setString(2, produto.getDescricao());
	        stmt.setDouble(3, produto.getPreco());
	        stmt.setInt(4, produto.getEstoque());
	        stmt.executeUpdate();

	        System.out.println("Produto " + produto.getNome() + " inserido com sucesso!");
	    } catch (SQLException e) {
	        System.out.println(">>> ERRO ao inserir produto: " + produto.getNome());
	        e.printStackTrace(); 
	        throw e;
	    }
	}
    
    public void listarProdutos(Connection conn) throws SQLException {
        String sql = "SELECT nome FROM produto";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            System.out.println("\nProdutos cadastrados no banco:");
            while (rs.next()) {
                String nome = rs.getString("nome");
                System.out.println("- " + nome);
            }
        }
    }

}
