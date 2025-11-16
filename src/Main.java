import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        String path = "dados/pmed36.txt";

        InstanceReader reader = new InstanceReader();
        InstanceData instancia;

        try {
            instancia = reader.readInstance(path);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + path);
            return;
        } catch (Exception e) {
            System.err.println("Erro durante leitura/processamento:");
            e.printStackTrace();
            return;
        }

        if (instancia.getMatriz() == null) {
            System.err.println("Erro: matriz inválida.");
            return;
        }

        ApproximateSolver approx = new ApproximateSolver();
        long t1 = System.nanoTime();
        Solution solAprox = approx.solve(instancia);
        long t2 = System.nanoTime();

        double aproxMs = (t2 - t1) / 1_000_000.0;
        double aproxSec = (t2 - t1) / 1_000_000_000.0;

        System.out.println("Método aproximado:");
        System.out.println(solAprox);
        System.out.println(String.format("Tempo: %.4f ms (%.6f s)", aproxMs, aproxSec));

        ExactSolver exact = new ExactSolver();
        long t3 = System.nanoTime();
        Solution solExata = exact.solve(instancia);
        long t4 = System.nanoTime();

        double exactMs = (t4 - t3) / 1_000_000.0;
        double exactSec = (t4 - t3) / 1_000_000_000.0;

        System.out.println("\nMétodo exato (força bruta):");
        System.out.println(solExata);
        System.out.println(String.format("Tempo: %.4f ms (%.6f s)", exactMs, exactSec));
    }
}
