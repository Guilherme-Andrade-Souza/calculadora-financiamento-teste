package model.typeimoveis;

import model.Financiamento;

public class Terreno extends Financiamento {
    public Terreno(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual) {
        super(valorImovel, prazoFinanciamentoAnos, taxaJurosAnual);
    }

    @Override
    public double getTaxaJurosAnual(){
        double novaTaxaJuros = getTaxaJurosAnual() + 0.02;
        
        return novaTaxaJuros; 
    }
}
