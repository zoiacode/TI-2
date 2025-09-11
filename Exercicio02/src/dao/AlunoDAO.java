package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;

public class AlunoDAO {

    private final String url = "jdbc:postgresql://localhost:5432/exercicio2";
    private final String user = "postgres";
    private final String password = "ti2cc";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO Aluno(nome, idade, curso) VALUES(?,?,?)";
        try (Connection conn = conectar();
                PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, aluno.getNome());
            pst.setInt(2, aluno.getIdade());
            pst.setString(3, aluno.getCurso());
            pst.executeUpdate();
            System.out.println("Aluno inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> listar() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM Aluno";
        try (Connection conn = conectar();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getString("curso")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE Aluno SET nome=?, idade=?, curso=? WHERE id=?";
        try (Connection conn = conectar();
                PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, aluno.getNome());
            pst.setInt(2, aluno.getIdade());
            pst.setString(3, aluno.getCurso());
            pst.setInt(4, aluno.getId());
            pst.executeUpdate();
            System.out.println("Aluno atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM Aluno WHERE id=?";
        try (Connection conn = conectar();
                PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Aluno excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
