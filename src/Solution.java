import java.util.List;

public class Solution {

    private List<Integer> centros;
    private double raio;
    
    public Solution(List<Integer> centros, double raio){
        this.centros = centros;
        this.raio = raio;
    }

    public double getRaio(){
        return raio;
    }

    public List<Integer> getCentros(){
        return centros;
    }

    @Override
    public String toString() {

        String raioStr = String.format("%.2f", this.raio);
        String centroStr = this.centros.toString();
        
        return "Solucao (Raio: " + raioStr + ", Centros (0-indexado): " + centroStr + ")";
    }

    
}
