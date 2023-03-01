import java.util.ArrayList;
import java.util.List;

/*
 * Classe que representa os indivíduos que carregam os cromossomos que
 * são formam a resolução do problema
 */
public class Individuo implements Comparable<Individuo> {

    private List pesos = new ArrayList();
    private List valores = new ArrayList();
    private Double cargaMaxima;
    private Double notaAvaliacao;
    private Double cargaUsada;
    private int geracao;
    private List cromossomo = new ArrayList();

    /*
     * Método construtor, as variáveis serão iniciadas junto com o valor
     * dos cromossomos (representando uma possível solução do problema), tendo
     * 50% de chance de não conter o produto na carga (0) e 50% de chance de conter (1)
     */
    public Individuo(List pesos, List valores, Double cargaMaxima) {

        this.pesos = pesos;
        this.valores = valores;
        this.cargaMaxima = cargaMaxima;
        this.notaAvaliacao = 0.0;
        this.cargaUsada = 0.0;
        this.geracao = 0;

        for (int i = 0; i < this.pesos.size(); i++) {

            if (java.lang.Math.random() < 0.5)
                this.cromossomo.add("0");
            else
                this.cromossomo.add("1");

        }

    }

    /*
     * Método de avaliação/fitness avalia a solução gerada, somando os valores
     * dos produtos contidos na carga e verificando se está de acordo
     * com as especificações (não ultrapassar o valor máximo da carga)
     */
    public void avaliacao() {

        Double nota = 0.0;
        Double somaPesos = 0.0;
        for (int i = 0; i < cromossomo.size(); i++) {

            if (this.cromossomo.get(i).equals("1")) {
                nota += (Double) this.valores.get(i);
                somaPesos += (Double) this.pesos.get(i);
            }

        }

        if (somaPesos > this.cargaMaxima)
            nota = 1.0;

        this.notaAvaliacao = nota;
        this.cargaUsada = somaPesos;

    }

    /*
     * Método sobrescrito do Comparable, será usado para ordenar os indivíduos
     * a partir da nota de avaliação obtida
     */
    @Override
    public int compareTo(Individuo o) {
        
        if (this.notaAvaliacao > o.getNotaAvaliacao())
            return -1;
        
        if (this.notaAvaliacao < o.getNotaAvaliacao())
            return 1;
        
        return 0;

    }

    /*
     * Método que irá fazer o crossover/reprodução de dois indivíduos para a
     * próxima geração, misturando os cromossomos a partir de um ponto de corte,
     * gerando 2 filhos com os genes cruzados
     */
    public List crossover (Individuo outroIndividuo) {

        int corte = (int) Math.round(Math.random() * this.cromossomo.size());

        List filho1 = new ArrayList<>();
        filho1.addAll(this.cromossomo.subList(0, corte));
        filho1.addAll(outroIndividuo.getCromossomo().subList(corte, this.cromossomo.size()));

        List filho2 = new ArrayList<>();
        filho2.addAll(outroIndividuo.getCromossomo().subList(0, corte));
        filho2.addAll(this.cromossomo.subList(corte, this.cromossomo.size()));

        List<Individuo> filhos = new ArrayList<>();
        filhos.add(new Individuo(this.pesos, this.valores, this.cargaMaxima));
        filhos.add(new Individuo(this.pesos, this.valores, this.cargaMaxima));

        filhos.get(0).setCromossomo(filho1);
        filhos.get(0).setGeracao(this.geracao + 1);
        filhos.get(1).setCromossomo(filho2);
        filhos.get(1).setGeracao(this.geracao + 1);

        return filhos;

    }

    /*
     * Método para realizar a mutação em um indíviduo para gerar variedade
     * nos cromossomos/soluções, tendo que alcançar uma chance pequena de mutação
     * para ser alterado algum dos seus genes (inverter)
     */
    public Individuo mutacao(Double taxaMutacao) {

        for (int i = 0; i < this.cromossomo.size(); i++) {

            if (Math.random() < taxaMutacao) {

                if (this.cromossomo.get(i).equals("0"))
                    this.cromossomo.set(i, "1");
                else
                    this.cromossomo.set(i, "0");

            }

        }

        return this;

    }

    public List getPesos() {
        return pesos;
    }

    public void setPesos(List pesos) {
        this.pesos = pesos;
    }

    public List getValores() {
        return valores;
    }

    public void setValores(List valores) {
        this.valores = valores;
    }

    public Double getCargaMaxima() {
        return cargaMaxima;
    }

    public void setCargaMaxima(Double cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }

    public Double getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(Double notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

    public Double getCargaUsada() {
        return cargaUsada;
    }

    public void setCargaUsada(Double cargaUsada) {
        this.cargaUsada = cargaUsada;
    }

    public int getGeracao() {
        return geracao;
    }

    public void setGeracao(int geracao) {
        this.geracao = geracao;
    }

    public List getCromossomo() {
        return cromossomo;
    }

    public void setCromossomo(List cromossomo) {
        this.cromossomo = cromossomo;
    }
    
}
