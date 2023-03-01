import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Classe que controla os aspectos do Algoritmo Genético e sua população
 */
public class AlgoritmoGenetico {

    private int tamanhoPopulacao;
    private List<Individuo> populacao = new ArrayList();
    private Individuo geracao;
    private Individuo melhorSolucao;

    public AlgoritmoGenetico(int tamanhoPopulacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    /*
     * Método que inicializa a população, chamando o método para criação
     * de indivíduos
     */
    public void inicializaPopulacao(List peso, List valor, Double capacidadeMaxima) {

        for (int i = 0; i < tamanhoPopulacao; i++)
            this.populacao.add(new Individuo(peso, valor, capacidadeMaxima));

        this.melhorSolucao = this.populacao.get(0);

    }

    /*
     * Método para encontrar o melhor indivíduo/solução, comparando as notas de avaliação
     */
    public void melhorIndividuo(Individuo individuo) {

        if (individuo.getNotaAvaliacao() > this.melhorSolucao.getNotaAvaliacao())
            this.melhorSolucao = individuo;

    }

    /*
     * Método para ordenar a população, ordenando de maior para menor as notas
     * dos indivíduos (melhor indivíduo estáno topo, no índice 0)
     */
    public void ordenaPopulacao() {
        Collections.sort(this.populacao);
    }

    /*
     * Método que soma as avaliação de uma população, sendo usado para seleção do
     * indivíduo pai
     */
    public Double somaAvaliacoes() {

        Double soma = 0.0;
        for (Individuo i : this.populacao)
            soma += i.getNotaAvaliacao();

        return soma;

    }

    /*
     * Método que seleciona os indivíduos que serão os pais da próxima geração,
     * através de um método de Roleta Viciada (seleciona indivíduos aleatórios e
     * não só os melhores)
     */
    public int selecionaPai(Double somaAvaliacao) {

        int pai = -1;
        Double valorSorteado = Math.random() * somaAvaliacao;
        Double soma = 0.0;

        int i = 0;
        while (i < this.populacao.size() && soma < valorSorteado) {

            soma += this.populacao.get(i).getNotaAvaliacao();
            pai += 1;
            i += 1;

        }

        return pai;

    }

    /*
     * Método para visualizar as informações sobre o melhor indivíduo de uma
     * geração
     */
    public void visualizaGeracao() {

        Individuo melhor = this.populacao.get(0);

        System.out.println("\n\n* MELHOR SOLUÇÃO DA GERAÇÃO: *");
        System.out.println(
            " Geração: " + this.melhorSolucao.getGeracao()
            + "\n Valor total: R$" + this.melhorSolucao.getNotaAvaliacao()
            + "\n Capacidade usada: " + this.melhorSolucao.getCargaUsada() + "KG (de " + this.melhorSolucao.getCargaMaxima() + "KG)"
            + "\n Cromossomo: " + this.melhorSolucao.getCromossomo()
        );

    }

    /*
     * Método que resolve o problema com o Algoritmo Genético, usando os métodos criados
     * para atingir uma solução final para o problema
     */
    public List resolverProblema(Double taxaMutacao, int numeroGeracoes, List pesos, List valores, Double capacidadeMaxima) {

        this.inicializaPopulacao(pesos, valores, capacidadeMaxima);

        for (Individuo i : this.populacao)
            i.avaliacao();

        this.ordenaPopulacao();
        this.visualizaGeracao();

        for (int gen = 0; gen < numeroGeracoes; gen++) {

            Double somaAvaliacao = this.somaAvaliacoes();
            List<Individuo> novaPopulacao = new ArrayList();

            for (int j = 0; j < this.populacao.size() / 2; j++) {

                int pai1 = this.selecionaPai(somaAvaliacao);
                int pai2 = this.selecionaPai(somaAvaliacao);

                List<Individuo> filhos = this.getPopulacao().get(pai1).crossover(this.populacao.get(pai2));
                novaPopulacao.add(filhos.get(0).mutacao(taxaMutacao));
                novaPopulacao.add(filhos.get(1).mutacao(taxaMutacao));

            }

            this.setPopulacao(novaPopulacao);

            for (Individuo i : this.populacao)
                i.avaliacao();

            this.ordenaPopulacao();
            this.visualizaGeracao();
            Individuo melhor = this.populacao.get(0);
            this.melhorIndividuo(melhor);

        }

        System.out.println("\n\n** MELHOR SOLUÇÃO DO PROBLEMA: **");
        System.out.println(
            " Geração: " + this.melhorSolucao.getGeracao()
            + "\n Valor total: R$" + this.melhorSolucao.getNotaAvaliacao()
            + "\n Capacidade usada: " + this.melhorSolucao.getCargaUsada() + "KG (de " + this.melhorSolucao.getCargaMaxima() + "KG)"
            + "\n Cromossomo: " + this.melhorSolucao.getCromossomo()
        );

        return this.melhorSolucao.getCromossomo();

    }

    public int getTamanhoPopulacao() {
        return tamanhoPopulacao;
    }

    public void setTamanhoPopulacao(int tamanhoPopulacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    public List<Individuo> getPopulacao() {
        return populacao;
    }

    public void setPopulacao(List<Individuo> populacao) {
        this.populacao = populacao;
    }

    public Individuo getGeracao() {
        return geracao;
    }

    public void setGeracao(Individuo geracao) {
        this.geracao = geracao;
    }

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public void setMelhorSolucao(Individuo melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }
    
}
