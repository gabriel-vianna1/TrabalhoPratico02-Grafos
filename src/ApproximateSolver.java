
import java.util.ArrayList;
import java.util.List;

public class ApproximateSolver implements IsSolver{

    @Override
    public Solution solve(InstanceData instancia) {

     List<Integer> centros = new ArrayList<Integer>();
     
     //Inicializa o primeiro vértice do grafo como sendo o primeiro dos centros
     centros.add(0);

     for(int i = 0; i < (instancia.getK() - 1); i++){

        double maiorDistancia = -1;
        int verticeMaiorDistancia = -1;

        for(int j = 0; j < instancia.getV(); j++){
        
         double distanciaMinima_do_j = Double.POSITIVE_INFINITY;
         
            for(int centro : centros){
                double dist = instancia.getMatriz()[j][centro];

                if (dist < distanciaMinima_do_j) {
                    distanciaMinima_do_j = dist;
                    }
             }     
             
             if(distanciaMinima_do_j > maiorDistancia){
                maiorDistancia = distanciaMinima_do_j;
                verticeMaiorDistancia = j;
             }
        }

        centros.add(verticeMaiorDistancia);
     }
  
     double raio = calcularRaio(centros, instancia);

     return new Solution(centros, raio);
     
 }
    

   private double calcularRaio(List<Integer> centros, InstanceData instancia){
 
    double raioFinal = 0.0;

    for(int i = 0; i < instancia.getV(); i++){
        double distanciaMinima_do_v = Double.POSITIVE_INFINITY;

        for(int centro : centros){
            double dist = instancia.getMatriz()[i][centro];

            if(dist < distanciaMinima_do_v){
                distanciaMinima_do_v = dist;
            }
        }

        if (distanciaMinima_do_v > raioFinal) {
            raioFinal = distanciaMinima_do_v;
        }
    }

    return raioFinal;
}  

}
