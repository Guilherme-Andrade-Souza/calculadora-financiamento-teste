package model.typeimoveis;

import model.AumentoMaiorDoQueJurosException;
import model.Financiamento;

public class Casa extends Financiamento{
    private static final double VALOR_SEGURO_OBRIGATORIO = 80.00;
    private double tamanhoAreaConstruida;
    private double tamanhoTerreno;

    public Casa(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, double tamanhoAreaConstruida, double tamanhoTerreno) {
        super(valorImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.tamanhoAreaConstruida = tamanhoAreaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    @Override
    public double pagamentoMensal() {
        double parcelaBase = super.pagamentoMensal();
        double taxaJurosMensal = getTaxaJurosAnual() / 12 / 100;
        double valorJuros = getValorImovel() * taxaJurosMensal;

        if (VALOR_SEGURO_OBRIGATORIO > (valorJuros / 2)) {
            throw new AumentoMaiorDoQueJurosException (
                "O valor do seguro obrigatório (R$80,00) excede a metade dos juros mensais (" + (valorJuros / 2) + ")"
            );
        }

        return parcelaBase + VALOR_SEGURO_OBRIGATORIO;
    }

    public double getTamanhoAreaConstruida(){
        return tamanhoAreaConstruida;
    }

    public double getTamanhoTerreno(){
        return tamanhoTerreno;
    }
}
