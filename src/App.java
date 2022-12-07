import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class App {
    /**
     * @param args
     * @throws IOException
     * @throws MalformedURLException
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        String apiKey = "k_t6g1hx9p";
        String url = "https://imdb-api.com/en/API/Top250Movies/" + apiKey;
        ExtratorConteudo extrator = new ExtratorConteudoImdb();

        // buscar imgens da nasa
        // String url = "https://api.nasa.gov/planetary/apod?api_key=n6mPjOHkLFL3Ry9GlZ22FMC6fKo1JItHpNjRLjF4&start_date=2022-11-15&end_date=2022-11-20";
        // ExtratorConteudo extrator = new ExtratorConteudoNasa();

        var http = new ClientHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extrairConteudos(json);


        var geradora = new GeradoraFigurinhas();

        for(int i=0; i < 5; i++){
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);
            System.out.println(conteudo.getTitulo());
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
