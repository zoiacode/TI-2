import java.net.http.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class Main {
    public static void main(String[] args) throws Exception {
        String endpoint = System.getenv("AZURE_AI_ENDPOINT");
        String key = System.getenv("AZURE_AI_KEY");

        if (endpoint == null || key == null) {
            System.err.println("ERRO: Defina as variáveis de ambiente AZURE_AI_ENDPOINT e AZURE_AI_KEY");
            System.exit(1);
        }

        String url = endpoint + "/text/analytics/v3.1/sentiment";
        String body = """
        {
          "documents": [
            { "id": "1", "language": "pt", "text": "Estou muito feliz com o resultado do projeto!" },
            { "id": "2", "language": "pt", "text": "O serviço foi lento e me deixou frustrado." }
          ]
        }
        """;

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(30))
                .header("Ocp-Apim-Subscription-Key", key)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body:\n" + response.body());
    }
}
