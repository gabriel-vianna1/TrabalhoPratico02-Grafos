import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InstanceReader {

    public InstanceData readInstance(String path) throws FileNotFoundException{

        File file = new File(path);
        int numVertices;
        int k;
        Graph grafo;


        try (Scanner scan = new Scanner(file)) {
            numVertices = scan.nextInt();
            int numArestas = scan.nextInt();
            k = scan.nextInt();

            grafo = new Graph(numVertices);

            while(scan.hasNextInt()){
                // Vai ler todas as arestas u -> v e guardar seus pesos
                int u = scan.nextInt();

              /*   if(!scan.hasNextInt()){
                    break;
                }*/

                int v = scan.nextInt();

                /*if(!scan.hasNextDouble()) {
                  break; 
                }*/

                double peso = scan.nextDouble();
                
                grafo.addDirectEdge(u, v, peso);
            }
        }

        //Transforma o grafo em uma matriz de distâncias mínimas
        grafo.runFloydWarshall();

        double[][] matriz = grafo.getMatrizDisntancia();

        return new InstanceData(numVertices, k, matriz);
    }
    
}
