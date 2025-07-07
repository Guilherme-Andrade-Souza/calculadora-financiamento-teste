package model; // Indica que a classe pertence ao pacote "model"

import java.io.Serializable; // Permite que objetos dessa classe sejam serializados

// Classe abstrata base para diferentes tipos de financiamento (Casa, Apartamento, Terreno, etc.)
public abstract class Financiamento implements Serializable {

    // Atributos principais de qualquer financiamento
    private double valorImovel;              // Valor do imóvel
    private int prazoFinanciamentoAnos;      // Prazo do financiamento em anos
    private double taxaJurosAnual;           // Taxa de juros anual (em percentual, ex: 0.1 = 10%)

    // Construtor que inicializa os atributos básicos
    public Financiamento(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual) {
        this.valorImovel = valorImovel;
        this.prazoFinanciamentoAnos = prazoFinanciamentoAnos;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    // Getters para acesso aos atributos encapsulados
    public double getValorImovel() {
        return valorImovel;
    }

    public int getPrazoFinanciamentoAnos() {
        return prazoFinanciamentoAnos;
    }

    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }

    /**
     * Calcula o valor da parcela mensal do financiamento.
     * Fórmula usada: (valor total / total de meses) * (1 + juros mensal)
     * Nota: Esta é uma fórmula simplificada, não utiliza o sistema de amortização PRICE.
     */
    public double pagamentoMensal() {
        double jurosMensal = taxaJurosAnual / 12;
        int totalMeses = prazoFinanciamentoAnos * 12;
        return (valorImovel / totalMeses) * (1 + jurosMensal);
    }

    /**
     * Calcula o valor total a ser pago durante todo o financiamento.
     * Multiplica o valor da parcela mensal pela quantidade de meses.
     */
    public double pagamentoTotal() {
        return pagamentoMensal() * (prazoFinanciamentoAnos * 12);
    }

    /**
     * Retorna uma string com informações básicas do financiamento.
     * Pode ser sobrescrito por subclasses para incluir detalhes específicos.
     */
    @Override
    public String toString() {
        return String.format("Valor do Imóvel: R$%.2f | Pagamento Total: R$%.2f%n",
                valorImovel, pagamentoTotal());
    }
}
