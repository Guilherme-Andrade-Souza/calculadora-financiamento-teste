package model.typeimoveis;

import model.Financiamento;

public class Apartamento extends Financiamento{
    private int quantidadeVagasGaragem;
    private int andarApartamento;

    public Apartamento(double valorImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, int quantidadeVagasGaragem, int andarApartamento) {
        super(valorImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.quantidadeVagasGaragem = quantidadeVagasGaragem;
        this.andarApartamento = andarApartamento;
    }

    @Override
    public double pagamentoTotal(){
        double taxaJurosMensal = getTaxaJurosAnual() * 12;
        int tempoFinanciamentoMensal = getPrazoFinanciamentoAnos() * 12;

        double fator = Math.pow(1 + taxaJurosMensal, tempoFinanciamentoMensal);
        
        return (getValorImovel() * fator) / fator - 1;
    }
}
