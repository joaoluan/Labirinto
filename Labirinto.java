import java.io.*;
import java.util.*;

public class Labirinto {
    private char[][] labirinto;
    private int linhas;
    private int colunas;
    private boolean[][] visitado;

    public void criaLabirinto(String filename) {
        List<char[]> linhasLab = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhasLab.add(linha.toCharArray());
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + filename);
            e.printStackTrace();
        }

        linhas = linhasLab.size();
        colunas = 0;
        for (char[] linha : linhasLab) {
            if (linha.length > colunas) {
                colunas = linha.length;
            }
        }

        labirinto = new char[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            Arrays.fill(labirinto[i], ' '); // preencher com espaço para evitar lixo
            char[] linha = linhasLab.get(i);
            System.arraycopy(linha, 0, labirinto[i], 0, linha.length);
        }

        visitado = new boolean[linhas][colunas];
    }

    public void imprimeLabirinto() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print(labirinto[i][j]);
            }
            System.out.println();
        }
    }

    public boolean percorreLabirinto() {
        for (int i = 0; i < linhas; i++)
            Arrays.fill(visitado[i], false);
        return percorreLabirintoRec(0, 0);
    }

    private boolean percorreLabirintoRec(int x, int y) {
        // Verifica limites do array
        if (x < 0 || y < 0 || x >= linhas || y >= colunas) return false;
        // Verifica se já visitado ou se é parede
        if (visitado[x][y] || labirinto[x][y] == 'X') return false;

        // Marca como visitado
        visitado[x][y] = true;

        // Se achou o destino
        if (labirinto[x][y] == 'D') return true;

        // Tenta todos os movimentos possíveis (cima, baixo, esquerda, direita)
        if (percorreLabirintoRec(x - 1, y)) return true;
        if (percorreLabirintoRec(x + 1, y)) return true;
        if (percorreLabirintoRec(x, y - 1)) return true;
        if (percorreLabirintoRec(x, y + 1)) return true;

        // Se nenhum caminho válido, volta atrás (backtracking)
        return false;
    }
}
