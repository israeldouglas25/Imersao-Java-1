import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String apiKey = "k_t6g1hx9p";
        String url = "https://imdb-api.com/en/API/Top250Movies/" + apiKey;
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);

        // extrair só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        // System.out.println(listaDeFilmes.size());
        // System.out.println(listaDeFilmes.get(0));

        var geradora = new GeradoraFigurinhas();

        for(int i=0; i < 5; i++){
            Map<String, String> filme = listaDeFilmes.get(i);

            String urlImagem = filme.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "saida/" + titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);
            System.out.println(filme.get("title"));
        }

        // exibir os dados
        // for (Map<String, String> filme : listaDeFilmes) {

        // String urlImagem = filme.get("image");
        // String titulo = filme.get("title");

        // InputStream inputStream = new URL(urlImagem).openStream();
        // String nomeArquivo = titulo + ".png";

        // geradora.cria(inputStream, nomeArquivo);

        // System.out.println("\u001b[1m\u001b[31m\u001b[42mFILME\u001b[m");
        // System.out.println("\u001b[1mNota\u001b[m: " + titulo);
        // System.out.println();
        // }
    }
}
