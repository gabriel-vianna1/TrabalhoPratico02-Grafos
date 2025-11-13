import java.util.Arrays;

public class Graph {
 
    private final int numVertices;
    private final int[][] matriz;

    public Graph(int v){
        this.numVertices = v;
        this.matriz = new int[v][v];

        for (int i = 0; i < v; i++) {
            Arrays.fill(this.matriz[i], Integer.MAX_VALUE);
            
            this.matriz[i][i] = 0;
        }
    }

//Método que vai ajustar os índices da matriz e dos arquivos que serão lidos
public void addDirectEdge(int u, int v, int cost) {
    int u_indice = u - 1; 
    int v_indice = v - 1; 

    this.matriz[u_indice][v_indice] = cost; 
    this.matriz[v_indice][u_indice] = cost; 
}

//Vai rodar o método de Floyd Warshal para montar uma matriz de distâncias de maneira 'in-place'
public void runFloydWarshall() {
        System.out.println("Iniciando Floyd-Warshall para " + numVertices + " vértices...");
        
        for (int k = 0; k < numVertices; k++) { // k = vértice intermediário
            for (int i = 0; i < numVertices; i++) { // i = vértice de origem
                for (int j = 0; j < numVertices; j++) { // j = vértice de destino

                    // Caminho i -> k -> j
                    int indirectPath = matriz[i][k] + matriz[k][j];

                    // Se o caminho passando por k for mais curto
                    if (indirectPath < matriz[i][j]) {
                        matriz[i][j] = indirectPath;
                    }
                }
            }
            
            // Log de progresso
            // if ((k + 1) % 100 == 0) {
            //     System.out.println("...Floyd-Warshall completou " + (k + 1) + "/" + numVertices);
            // }
        }
        System.out.println("Floyd-Warshall concluído.");
    }
   
    public int[][] getMatrizDisntancia(){
        return this.matriz; 
    }

    public int getNumVertices(){
        return this.numVertices;
    }

}
