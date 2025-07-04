package util; // Define que esta classe faz parte do pacote "util"

public class ValidaDado {

    // Valida se o valor do imóvel é positivo
    public boolean validaValorImovel(double valorImovel){
        // Se o valor for maior que zero, é válido
        if (valorImovel > 0) {
            return true;
        } else {
            return false;
        }
    }

    // Valida se o prazo do financiamento (em anos) está dentro do limite aceitável
    public boolean validaTempoFinanciamento(int prazoFinanciamentoAnos){
        // Aceita apenas prazos entre 1 e 40 anos (inclusive)
        if (prazoFinanciamentoAnos > 0 && prazoFinanciamentoAnos <= 40) {
            return true;
        } else {
            return false;
        }
    } 

    // Valida se a taxa de juros anual está dentro do intervalo aceitável
    public boolean validaTaxa(double taxaJurosAnual){
        // A taxa deve estar entre 0.0 e 1.0 (100%)
        // Ex: 0.1 → 10%, 0.12 → 12%, etc.
        if (taxaJurosAnual > 0.0 && taxaJurosAnual <= 1.0) {
            return true;
        } else {
            return false;
        }
    }
}
