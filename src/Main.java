import java.util.ArrayList;
import java.util.List;

/*
 * Classe com o método principal para estabelecer a instância do problema 
 * e iniciar a execução da resolução
 */
public class Main {

    public static void main(String args[]) {

        List<Produto> produtos = new ArrayList();
        produtos.add(new Produto("Produto1", 50.0, 100.0));
        produtos.add(new Produto("Produto2", 40.0, 20.0));
        produtos.add(new Produto("Produto3", 40.0, 140.0));
        produtos.add(new Produto("Produto4", 3.0, 112.0));
        produtos.add(new Produto("Produto5", 2.0, 115.0));
        produtos.add(new Produto("Produto6", 60.0,200.0));
        produtos.add(new Produto("Produto7", 40.0, 5.0));
        produtos.add(new Produto("Produto8", 20.0, 10.0));

        List nomes = new ArrayList();
        List pesos = new ArrayList();
        List valores = new ArrayList();
        for (Produto p : produtos) {
            nomes.add(p.getNome());
            pesos.add(p.getPeso());
            valores.add(p.getValor());

        }

        Double capacidadeMaxima = 150.0;
        int tamanhoPopulacao = 20;
        Double taxaMutacao = 0.01;
        int numeroGeracoes = 100;

        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanhoPopulacao);
        List resultado = ag.resolverProblema(taxaMutacao, numeroGeracoes, pesos, valores, capacidadeMaxima);

        System.out.println(" Produtos selecionados na solução: ");
        for (int i = 0; i < produtos.size(); i++) {

            if (resultado.get(i).equals("1"))
                System.out.println(
                    " - Nome: " + produtos.get(i).getNome()
                    + "\n   - Valor: R$" + produtos.get(i).getValor()
                    + "\n   - Peso: " + produtos.get(i).getPeso() + "KG"
                );

        }

    }
    
}
