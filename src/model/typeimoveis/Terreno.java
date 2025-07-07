// Define o pacote onde a classe está localizada
package model.typeimoveis;

// Importa a classe base Financiamento
import model.Financiamento;

// A classe Terreno herda da classe Financiamento
public class Terreno extends Financiamento {

    // Atributo específico da classe Terreno: a zona onde o terreno está localizado (ex: urbana, rural, etc.)
    private String zonaTerreno;

    // Construtor da classe Terreno
    public Terreno(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, String zonaTerreno) {
        // Chama o construtor da superclasse para inicializar os atributos comuns
        super(valorImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        // Inicializa o atributo específico da subclasse
        this.zonaTerreno = zonaTerreno;
    }

    // Sobrescreve o método getTaxaJurosAnual para aplicar um acréscimo específico para terrenos
    @Override
    public double getTaxaJurosAnual() {
        // Aplica um aumento fixo de 2% (0.02) à taxa de juros anual original
        double novaTaxaJuros = super.getTaxaJurosAnual() + 0.02;
        return novaTaxaJuros;
    }

    // Getter para o atributo zonaTerreno
    public String getZonaTerreno() {
        return zonaTerreno;
    }

    // Representação textual do objeto Terreno, útil para salvar ou exibir os dados
    @Override
    public String toString() {
        return String.format(
            "%.2f;%.2f;%d;zona_terreno=%s",
            getValorImovel(), getTaxaJurosAnual(), // Usa o getTaxaJurosAnual sobrescrito, com o acréscimo de 2%
            getPrazoFinanciamentoAnos(), zonaTerreno
        );
    }
}
