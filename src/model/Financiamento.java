package model; // Define que esta classe está no pacote "model"

public abstract class Financiamento {
    // Atributos privados que representam os dados essenciais de um financiamento
    private double valorImovel;              // Valor total do imóvel
    private int prazoFinanciamentoAnos;      // Prazo de financiamento em anos
    private double taxaJurosAnual;           // Taxa de juros anual (em decimal, ex: 0.1 para 10%)

    // Construtor da classe que inicializa os atributos
    public Financiamento(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual){
        this.valorImovel = valorImovel;
        this.prazoFinanciamentoAnos = prazoFinanciamentoAnos;
        this.taxaJurosAnual = taxaJurosAnual; 
    }

    // Métodos getters para acessar os atributos privados
    public double getValorImovel(){
        return valorImovel;
    }

    public int getPrazoFinanciamentoAnos(){
        return prazoFinanciamentoAnos;
    }

    public double getTaxaJurosAnual(){
        return taxaJurosAnual;
    }

    // Calcula o valor do pagamento mensal
    public double pagamentoMensal(){
        // Fórmula simplificada: valor da parcela base vezes acréscimo de juros mensal
        // Exemplo: (valor / número total de parcelas) * (1 + juros mensal)
        return (valorImovel / (prazoFinanciamentoAnos * 12)) * (1 + (taxaJurosAnual / 12));
    }

    // Calcula o valor total pago ao final do financiamento
    public double pagamentoTotal(){
        // Multiplica o valor da parcela pelo total de meses
        return pagamentoMensal() * (prazoFinanciamentoAnos * 12);
    }

    // Representação textual do objeto Financiamento
    @Override
    public String toString() {
        // Formata a saída com duas casas decimais
        return String.format("Valor do Imóvel: R$%.2f | Pagamento Total: R$%.2f%n",
            valorImovel, pagamentoTotal());
    }
}
