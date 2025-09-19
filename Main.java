import java.io.File;

public class Main {
    public static void main(String[] args) {
        Labirinto labirinto = new Labirinto();

        String pastaRaiz = "."; // diretório atual

        File folder = new File(pastaRaiz);

        File[] arquivosTxt = folder.listFiles((dir, nome) -> nome.toLowerCase().endsWith(".txt"));

        if (arquivosTxt == null || arquivosTxt.length == 0) {
            System.out.println("Nenhum arquivo .txt encontrado na pasta raiz.");
            return;
        }

        for (File arquivo : arquivosTxt) {
            if (arquivo.getName().toLowerCase().contains("readme")) {
                continue; // pula arquivos README
            }

            System.out.println("\n== Labirinto do arquivo: " + arquivo.getName() + " ==");

            labirinto.criaLabirinto(arquivo.getPath());
            labirinto.imprimeLabirinto();

            boolean temCaminho = labirinto.percorreLabirinto();

            if (temCaminho) {
                System.out.println("Existe caminho possível até a saída!");
            } else {
                System.out.println("Não existe caminho possível.");
            }
        }
    }
}
