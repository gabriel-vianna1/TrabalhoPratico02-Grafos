import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        
        String path = "dados/teste.txt";
  
        System.out.println("Inciando testes e pré-processamento (Floyd-Warshal)");

        InstanceReader reader = new InstanceReader();
        InstanceData dadosDaInstancia; 

        try {
            // Tenta ler o arquivo, construir o grafo E rodar o Floyd-Warshall (Já roda dentro do readInstance )
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

    
        // B. Verifica a Matriz de Distância
        double[][] matrix = dadosDaInstancia.getMatriz();

        if (matrix == null || matrix.length != dadosDaInstancia.getV()) {
            System.err.println("ERRO: Matriz está nula ou com tamanho incorreto!");
        }

        ApproximateSolver as = new ApproximateSolver();

        long startTime = System.nanoTime();

        Solution solucaoAproximada = as.solve(dadosDaInstancia);

        long endTime = System.nanoTime();

        System.out.println("Solução encontrada com método de aproximação: " + solucaoAproximada.toString());

        long durationInNanos = endTime - startTime;

        double durationInMilliseconds = durationInNanos / 1_000_000.0;
        double durationInSeconds = durationInNanos / 1_000_000_000.0;

        System.out.println(String.format(
    "Tempo de execução (Aproximado): %.4f ms (ou %.6f segundos)", 
    durationInMilliseconds, 
    durationInSeconds
    ));
        
        
        
    }

    
}
