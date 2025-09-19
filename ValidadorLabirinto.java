public class ValidadorLabirinto {
    public static void main(String[] args) {
        testarLabirinto("labirinto1.txt", true);
        testarLabirinto("labirinto2.txt", true);
        testarLabirinto("labirinto3.txt", false);
        testarLabirinto("labirinto4.txt", false);
    }

    private static void testarLabirinto(String nomeArquivo, boolean esperado) {
        Labirinto lab = new Labirinto();
        lab.criaLabirinto(nomeArquivo);
        boolean resultado = lab.percorreLabirinto();

        if (resultado == esperado) {
            System.out.println("[" + nomeArquivo + "] Resultado esperado: " + esperado + " => OK");
        } else {
            System.out.println("[" + nomeArquivo + "] Resultado esperado: " + esperado + " => FALHOU");
        }
    }
}