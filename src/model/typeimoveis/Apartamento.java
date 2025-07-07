// Pacote onde a classe está localizada
package model.typeimoveis;

// Importa a classe base Financiamento
import model.Financiamento;

// A classe Apartamento herda de Financiamento
public class Apartamento extends Financiamento {

    // Atributos específicos da classe Apartamento
    private int quantidadeVagasGaragem;
    private int andarApartamento;

    // Construtor da classe Apartamento
    public Apartamento(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual,
                       int quantidadeVagasGaragem, int andarApartamento) {
        // Chama o construtor da superclasse Financiamento
        super(valorImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        // Inicializa os atributos específicos do apartamento
        this.quantidadeVagasGaragem = quantidadeVagasGaragem;
        this.andarApartamento = andarApartamento;
    }

    // Sobrescreve o método pagamentoTotal para aplicar fórmula de cálculo própria (tentativa da PRICE)
    @Override
    public double pagamentoTotal() {
        // Erro: taxaJurosAnual está sendo multiplicada por 12, quando deveria ser dividida para obter a taxa mensal
        double taxaJurosMensal = getTaxaJurosAnual() * 12;

        // Converte o prazo de financiamento de anos para meses
        int tempoFinanciamentoMensal = getPrazoFinanciamentoAnos() * 12;

        // Calcula o fator de juros composto
        double fator = Math.pow(1 + taxaJurosMensal, tempoFinanciamentoMensal);

        // Erro: a fórmula abaixo sempre retorna o valor do imóvel original (PRICE incorreto)
        // Fórmula correta da prestação PRICE: PMT = P * [i*(1+i)^n] / [(1+i)^n - 1]
        return (getValorImovel() * fator) / fator - 1;
    }

    // Getter para quantidade de vagas de garagem
    public int getQuantidadeVagasGaragem() {
        return quantidadeVagasGaragem;
    }

    // Getter para o andar do apartamento
    public int getAndarApartamento() {
        return andarApartamento;
    }

    // Retorna uma representação em texto do financiamento de apartamento
    @Override
    public String toString() {
        return String.format(
            "%.2f;%.2f;%d;vagas=%d;andar=%d",
            getValorImovel(), getTaxaJurosAnual(),
            getPrazoFinanciamentoAnos(), quantidadeVagasGaragem, andarApartamento
        );
    }
}
