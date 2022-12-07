import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoImdb implements ExtratorConteudo {

    public List<Conteudo> extrairConteudos(String json){

        // extrair só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        // popular a lista de conteudos
        for (Map<String, String>  atributo : listaAtributos) {
            String titulo = atributo.get("title");
            String urlImagem = atributo.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");

            var conteudo = new Conteudo(titulo, urlImagem);
            
            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
