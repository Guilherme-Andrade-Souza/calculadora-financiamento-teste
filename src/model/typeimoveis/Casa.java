// Pacote onde a classe está localizada
package model.typeimoveis;

// Importa a exceção personalizada e a classe base Financiamento
import model.AumentoMaiorDoQueJurosException;
import model.Financiamento;

// A classe Casa estende (herda de) Financiamento
public class Casa extends Financiamento {

    // Valor fixo do seguro obrigatório para casas
    private static final double VALOR_SEGURO_OBRIGATORIO = 80.00;

    // Atributos específicos de uma casa
    private double tamanhoAreaConstruida;
    private double tamanhoTerreno;

    // Construtor que recebe os dados do financiamento e os atributos específicos
    public Casa(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual,
                double tamanhoAreaConstruida, double tamanhoTerreno) {
        // Chama o construtor da superclasse Financiamento
        super(valorImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.tamanhoAreaConstruida = tamanhoAreaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    // Sobrescreve o método pagamentoMensal para incluir o valor do seguro obrigatório
    @Override
    public double pagamentoMensal() {
        // Calcula o valor da parcela base do financiamento usando a lógica da superclasse
        double parcelaBase = super.pagamentoMensal();

        // Calcula a taxa de juros mensal
        double taxaJurosMensal = getTaxaJurosAnual() / 12 / 100;

        // Calcula o valor total dos juros sobre o imóvel
        double valorJuros = getValorImovel() * taxaJurosMensal;

        // Se o valor do seguro for maior que metade dos juros, lança exceção personalizada
        if (VALOR_SEGURO_OBRIGATORIO > (valorJuros / 2)) {
            throw new AumentoMaiorDoQueJurosException(
                "O valor do seguro obrigatório (R$80,00) excede a metade dos juros mensais (" + (valorJuros / 2) + ")"
            );
        }

        // Retorna o valor total da parcela com o acréscimo do seguro
        return parcelaBase + VALOR_SEGURO_OBRIGATORIO;
    }

    // Getter para o tamanho da área construída
    public double getTamanhoAreaConstruida() {
        return tamanhoAreaConstruida;
    }

    // Getter para o tamanho do terreno
    public double getTamanhoTerreno() {
        return tamanhoTerreno;
    }

    // Representação textual do objeto Casa, usada por exemplo para salvar em arquivos
    @Override
    public String toString() {
        return String.format(
            "%.2f;%.2f;%d;area_construida=%.2f m2;tamanho_terreno=%.2f m2",
            getValorImovel(), getTaxaJurosAnual(),
            getPrazoFinanciamentoAnos(), tamanhoAreaConstruida, tamanhoTerreno
        );
    }
}
