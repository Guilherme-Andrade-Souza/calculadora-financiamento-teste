package model.typeimoveis;

import model.Financiamento;

public class Terreno extends Financiamento {
    private String zonaTerreno; 

    public Terreno(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, String zonaTerreno) {
        super(valorImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.zonaTerreno = zonaTerreno;
    }

    @Override
    public double getTaxaJurosAnual(){
        double novaTaxaJuros = super.getTaxaJurosAnual() + 0.02;
        
        return novaTaxaJuros; 
    }

    public String getZonaTerreno(){
        return zonaTerreno;
    }
}
