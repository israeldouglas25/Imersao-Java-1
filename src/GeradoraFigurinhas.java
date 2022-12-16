import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception{
        // fazer leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // criar nova imagem em memoria com transparencia e tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 170;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova (em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 100, null);

        // configurar a fonte
        var fonte = new Font("Impact", Font.BOLD, 32);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // escreve a msg na imagem
        graphics.drawString("TOP", 85, novaAltura - 30);

        // escreve a imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));


    }

}
