import java.util.ArrayList;
import java.util.List;

public class ExactSolver implements IsSolver {

    private static final long LIMITE_COMBINACOES = 120_000_000L;

    private double melhorRaio;
    private List<Integer> melhoresCentros;
    private double[][] matriz;

    @Override
    public Solution solve(InstanceData instancia) {

        int n = instancia.getV();
        int k = instancia.getK();
        matriz = instancia.getMatriz();

        long totalComb = combinacoes(n, k);

        if (totalComb == Long.MAX_VALUE || totalComb > LIMITE_COMBINACOES) {
            System.out.println(
                "Método exato desabilitado: C(" + n + "," + k + ") = " + totalComb +
                " combinações (acima do limite)."
            );
            return new Solution(new ArrayList<>(), -1);
        }

        melhorRaio = Double.POSITIVE_INFINITY;
        melhoresCentros = null;

        gerarCombinacoes(n, k);

        return new Solution(melhoresCentros, melhorRaio);
    }


    /* ======================================================
       Combinação C(n, k) com overflow controlado
       ====================================================== */
    private long combinacoes(int n, int k) {
        if (k > n) return 0;
        if (k > n - k) k = n - k;

        long res = 1;

        for (int i = 1; i <= k; i++) {
            long mult = n - k + i;

            if (res > Long.MAX_VALUE / mult)
                return Long.MAX_VALUE;

            res *= mult;
            res /= i;

            if (res > LIMITE_COMBINACOES)
                return Long.MAX_VALUE;
        }

        return res;
    }


    /* ======================================================
       Geração de combinações em streaming (sem estourar memória)
       ====================================================== */
    private void gerarCombinacoes(int n, int k) {
        backtrack(new ArrayList<>(), 0, n, k);
    }

    private void backtrack(List<Integer> atual, int inicio, int n, int k) {

        if (atual.size() == k) {
            double raio = calcularRaio(atual);

            if (raio < melhorRaio) {
                melhorRaio = raio;
                melhoresCentros = new ArrayList<>(atual);
            }

            return;
        }

        for (int i = inicio; i < n; i++) {
            atual.add(i);
            backtrack(atual, i + 1, n, k);
            atual.remove(atual.size() - 1);
        }
    }


    /* ======================================================
       Cálculo do raio
       ====================================================== */
    private double calcularRaio(List<Integer> centros) {
        double raio = 0.0;
        int n = matriz.length;

        for (int v = 0; v < n; v++) {
            double menor = Double.POSITIVE_INFINITY;

            for (int c : centros) {
                double d = matriz[v][c];
                if (d < menor) menor = d;
            }

            if (menor > raio) raio = menor;
        }

        return raio;
    }
}
