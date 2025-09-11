package principal;

import static spark.Spark.*;
import com.google.gson.Gson;
import dao.ProdutoDAO;
import model.Produto;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();
        Gson gson = new Gson();

        // Configurar pasta de arquivos estáticos (HTML, CSS, JS)
        staticFiles.location("/public");

        // Rota para listar todos os produtos em JSON
        get("/produtos", (req, res) -> {
            res.type("application/json");
            List<Produto> produtos = dao.listar();
            return gson.toJson(produtos);
        });

        // Rota para inserir um produto via POST
        post("/produtos", (req, res) -> {
            Produto p = gson.fromJson(req.body(), Produto.class);
            dao.inserir(p);
            res.status(201);
            return "Produto inserido com sucesso!";
        });

        // Rota para atualizar um produto via PUT
        put("/produtos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Produto p = gson.fromJson(req.body(), Produto.class);
            p.setId(id);
            dao.atualizar(p);
            return "Produto atualizado com sucesso!";
        });

        // Rota para excluir um produto via DELETE
        delete("/produtos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            dao.excluir(id);
            return "Produto excluído com sucesso!";
        });
    }
}
