import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        
        String path = "dados/teste.txt";
  
        System.out.println("Inciando testes e pré-processamento (Floyd-Warshal)");

        InstanceReader reader = new InstanceReader();
        InstanceData dadosDaInstancia; 

        try {
            // Tenta ler o arquivo, construir o grafo E rodar o Floyd-Warshall
            dadosDaInstancia = reader.readInstance(path);
            
            System.out.println("Leitura e processamento concluídos com sucesso!");

        } catch (FileNotFoundException e) {
            System.err.println("ERRO FATAL: Arquivo não encontrado no caminho: " + path);
            e.getMessage();
            return; // Encerra o programa se não achar o arquivo
        } catch (Exception e) {
            System.err.println("ERRO INESPERADO durante a leitura/processamento:");
            e.getMessage();
            return;
        }

        // ----------------------------------------------------
        // 3. VERIFICAÇÃO DOS RESULTADOS
        // ----------------------------------------------------
        System.out.println("\n--- Verificando Dados Carregados ---");

        // A. Verifica o cabeçalho
        // Para pmed1.txt, esperamos V=100 e k=5
        System.out.println("V (Vértices): " + dadosDaInstancia.getV() + " (Esperado para pmed1: 100)");
        System.out.println("k (Centros): " + dadosDaInstancia.getK() + " (Esperado para pmed1: 5)");

        // B. Verifica a Matriz de Distância
        double[][] matrix = dadosDaInstancia.getMatriz();

        if (matrix == null || matrix.length != dadosDaInstancia.getV()) {
            System.err.println("ERRO: Matriz está nula ou com tamanho incorreto!");
            return;
        }

        System.out.println("Verificando sanidade da matriz (" + matrix.length + "x" + matrix.length + ")...");

        // C. Checa a diagonal (distância de um nó para ele mesmo)
        System.out.println("Distância [0][0]: " + matrix[0][0] + " (Esperado: 0.0)");
        int ultimo = dadosDaInstancia.getV() - 1;
        System.out.println("Distância ["+ultimo+"]["+ultimo+"]: " + matrix[ultimo][ultimo] + " (Esperado: 0.0)");

        // D. Checa alguns valores (só para ver se NÃO são 0 ou Infinito)
        System.out.println("Distância [0][1]: " + matrix[0][1]);
        System.out.println("Distância [1][0]: " + matrix[1][0]);
        System.out.println("Distância [5][8]: " + matrix[5][8]);

        // E. Checa se o grafo é conexo (se não houver infinitos, o F-W funcionou)
        boolean achouInfinito = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == Double.POSITIVE_INFINITY) {
                    System.err.println("AVISO: Infinito encontrado em [" + i + "][" + j + "]. O grafo pode ser desconexo.");
                    achouInfinito = true;
                    break;
                }
            }
            if (achouInfinito) break;
        }

        if (!achouInfinito) {
            System.out.println("VERIFICADO: Matriz não contém infinitos. Grafo é conexo.");
        }

        System.out.println("\n--- Teste de Leitura Concluído ---");
        
    }

    
}
