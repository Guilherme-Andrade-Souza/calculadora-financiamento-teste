package model.typeimoveis;

import model.Financiamento;

public class Casa extends Financiamento{
    private static final double VALOR_SEGURO_OBRIGATORIO = 80.00;


    public Casa(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual) {
        super(valorImovel, prazoFinanciamentoAnos, taxaJurosAnual);
    }

    @Override
    public double pagamentoMensal(){
        return super.pagamentoMensal() + VALOR_SEGURO_OBRIGATORIO;
    }

}
