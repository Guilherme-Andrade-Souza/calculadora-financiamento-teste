package model.typeimoveis;

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
    public double pagamentoMensal(){
        return super.pagamentoMensal() + VALOR_SEGURO_OBRIGATORIO;
    }

}
