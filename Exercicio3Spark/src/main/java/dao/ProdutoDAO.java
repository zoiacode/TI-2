package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDAO {

    private final String url = "jdbc:postgresql://localhost:5432/exercicio3";
    private final String user = "postgres";
    private final String password = "ti2cc";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void inserir(Produto produto) {
        String sql = "INSERT INTO Produto(nome, preco, quantidade) VALUES(?,?,?)";
        try (Connection conn = conectar();
                PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, produto.getNome());
            pst.setDouble(2, produto.getPreco());
            pst.setInt(3, produto.getQuantidade());
            pst.executeUpdate();
            System.out.println("Produto inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Produto";
        try (Connection conn = conectar();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Produto(rs.getInt("id"), rs.getString("nome"),
                        rs.getDouble("preco"), rs.getInt("quantidade")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizar(Produto produto) {
        String sql = "UPDATE Produto SET nome=?, preco=?, quantidade=? WHERE id=?";
        try (Connection conn = conectar();
                PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, produto.getNome());
            pst.setDouble(2, produto.getPreco());
            pst.setInt(3, produto.getQuantidade());
            pst.setInt(4, produto.getId());
            pst.executeUpdate();
            System.out.println("Produto atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM Produto WHERE id=?";
        try (Connection conn = conectar();
                PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Produto excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
