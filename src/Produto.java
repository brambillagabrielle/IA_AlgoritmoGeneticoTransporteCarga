/*
 * Classe que representa os produtos que serão parte da carga do caminhão
 */
public class Produto {

    private String nome;
    private Double peso;
    private Double valor;
    
    public Produto(String nome, Double peso, Double valor) {
        this.nome = nome;
        this.peso = peso;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
}