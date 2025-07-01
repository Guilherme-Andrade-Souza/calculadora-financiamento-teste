package model.typeimoveis;

import model.Financiamento;

public class Apartamento extends Financiamento{

    public Apartamento(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual) {
        super(valorImovel, prazoFinanciamentoAnos, taxaJurosAnual);
    }

    @Override
    public double pagamentoTotal(){
        double taxaJurosMensal = getTaxaJurosAnual() * 12;
        int tempoFinanciamentoMensal = getPrazoFinanciamentoAnos() * 12;

        double fator = Math.pow(1 + taxaJurosMensal, tempoFinanciamentoMensal);
        
        return (getValorImovel() * fator) / fator - 1;
    }
}
