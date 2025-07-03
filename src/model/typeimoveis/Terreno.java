package model.typeimoveis;

import model.Financiamento;

public class Terreno extends Financiamento {
    private String tipoImovel; 

    public Terreno(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, String tipoImovel) {
        super(valorImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.tipoImovel = tipoImovel;
    }

    @Override
    public double getTaxaJurosAnual(){
        double novaTaxaJuros = super.getTaxaJurosAnual() + 0.02;
        
        return novaTaxaJuros; 
    }

    public String getTipoImovel(){
        return tipoImovel;
    }
}
