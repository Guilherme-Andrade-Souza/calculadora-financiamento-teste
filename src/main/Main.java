package main; // Define que a classe está no pacote "main"

import java.util.ArrayList;   // Importa a classe ArrayList para armazenar uma lista de objetos
import java.util.Scanner;     // Importa Scanner para entrada de dados via terminal

import model.Financiamento;         // Importa a classe Financiamento do pacote "model"
import model.typeimoveis.Apartamento;
import model.typeimoveis.Casa;
import model.typeimoveis.Terreno;
import util.InterfaceUsuario;       // Importa a classe que lida com entrada e validação dos dados

public class Main {
    public static void main(String[] args) {
        // Cria uma lista para armazenar todos os financiamentos registrados
        ArrayList<Financiamento> listaFinanciamentos = new ArrayList<>();

        // Instancia a interface responsável por ler e validar os dados do usuário
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        // Scanner usado para ler o "s/n" do usuário sobre novo financiamento
        Scanner scanner = new Scanner(System.in);

        // Variáveis acumuladoras para os totais dos imóveis e dos pagamentos
        double valorTotalImoveis = 0;
        double valorTotalPagamento = 0;

        // Solicita e lê o valor do imóvel
        double valorImovel = interfaceUsuario.valorImovel();
        
        // Solicita e lê o prazo do financiamento (em anos)
        int anosFinanciamento = interfaceUsuario.prazoFinanciamentoAnos();
    
        // Solicita e lê a taxa de juros anual
        double taxaJuros = interfaceUsuario.taxaJurosAnual();

        double tamanhoAreaConstruida = interfaceUsuario.tamanhoAreaConstruida();

        double tamanhoTerreno = interfaceUsuario.tamanhoTerreno();
        
        // Cria o financiamento com os dados lidos
        Financiamento financiamento = new Casa(valorImovel, anosFinanciamento, taxaJuros, tamanhoAreaConstruida, tamanhoTerreno);
        
        // Calcula o pagamento mensal e total com base nos métodos da classe
        double pagamentoMensal = financiamento.pagamentoMensal();
        double pagamentoTotal = financiamento.pagamentoTotal();
        
        // Adiciona o financiamento à lista
        listaFinanciamentos.add(financiamento);
        listaFinanciamentos.add(new Casa(50000.00, 20, 0.02, 32.53, 100.00));
        listaFinanciamentos.add(new Apartamento(400600.00, 25, 0.06, 30, 3));
        listaFinanciamentos.add(new Apartamento(900000.00, 15, 0.08, 5, 10));
        listaFinanciamentos.add(new Terreno(100000.00, 10, 0.02, "comercial"));

        // Exibe os resultados do financiamento atual
        System.out.printf("\nO pagamento mensal é de: R$ %.2f%n", pagamentoMensal);
        System.out.printf("O pagamento total do financiamento é de: R$ %.2f%n", pagamentoTotal);

        // Acumula os totais de valor dos imóveis e dos pagamentos
        for (Financiamento f : listaFinanciamentos) {
            valorTotalImoveis += f.getValorImovel();
            valorTotalPagamento += f.pagamentoTotal();
        }
        // Fecha o scanner após o uso
        scanner.close();

        // Exibe todos os financiamentos registrados
        System.out.println("Lista de financiamentos:");
        int contador = 1;
        for (Financiamento h : listaFinanciamentos) {
            System.out.printf("Financiamento %d – %s%n", contador, h.toString());
            contador++;
        }

        // Exibe o total acumulado dos imóveis e do valor financiado
        System.out.printf("Total de todos os imóveis: R$ %.2f | Total de todos financiamentos: R$ %.2f%n",
            valorTotalImoveis, valorTotalPagamento);
    }   
}
