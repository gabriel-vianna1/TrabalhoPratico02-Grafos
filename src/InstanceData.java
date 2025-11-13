public class InstanceData {

    private final int v; // número de vértices
    private final int k; // k, para o trabalho dos k-centros
    private final int[][] matriz; // matriz de distânicas

    public InstanceData(int v, int k, int[][] matriz){
        this.v = v;
        this.k = k;
        this.matriz = matriz;
    }

    public int getV(){
        return v;
    }

    public int getK(){
        return k;
    }

    public int[][] getMatriz(){
        return matriz;
    }
    
}
