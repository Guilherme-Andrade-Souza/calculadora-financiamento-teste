package main; // Define que a classe está no pacote "main"

import java.util.ArrayList;   // Importa a classe ArrayList para armazenar uma lista de objetos
import java.util.Scanner;     // Importa Scanner para entrada de dados via terminal

import model.Financiamento;         // Importa a classe Financiamento do pacote "model"
import model.typeimoveis.Apartamento;
import model.typeimoveis.Casa;
import model.typeimoveis.Terreno;
import util.InterfaceUsuario;       // Importa a classe que lida com entrada e validação dos dados

//TODO execessão taxa casa
//TODO implementar no main para quando ser perguntando o tipo do financiamento
//TODO adicionar serializarção

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

        double pagamentoMensal;
        double pagamentoTotal;

        char opcfin;
        do {
            // Solicita e lê o valor do imóvel
            double valorImovel = interfaceUsuario.valorImovel();
            
            // Solicita e lê o prazo do financiamento (em anos)
            int prazoAnosFinanciamento = interfaceUsuario.prazoFinanciamentoAnos();
        
            // Solicita e lê a taxa de juros anual
            double taxaJuros = interfaceUsuario.taxaJurosAnual();
    
            System.out.println("\n1 - Apartamento");
            System.out.println("2 - Casa");
            System.out.println("3 - Terreno");
            System.out.print("Qual tipo de financiamento você deseja: ");
            int opc = scanner.nextInt();        
    
            switch (opc) {
                case 1:
                    int quantidadeVagasGaragem = interfaceUsuario.quantidadeVagasGaragem();
                    int andarApartamento = interfaceUsuario.andarApartamento();

                    Financiamento financiamentoApartamento = new Apartamento(valorImovel, prazoAnosFinanciamento, taxaJuros, quantidadeVagasGaragem, andarApartamento);

                    listaFinanciamentos.add(financiamentoApartamento);

                    pagamentoMensal = financiamentoApartamento.pagamentoMensal();
                    pagamentoTotal = financiamentoApartamento.pagamentoTotal();

                    // Exibe os resultados do financiamento atual
                    System.out.printf("\nO pagamento mensal é de: R$ %.2f%n", pagamentoMensal);
                    System.out.printf("O pagamento total do financiamento é de: R$ %.2f%n", pagamentoTotal);
                    break;
                case 2:
                    double tamanhoAreaConstruida = interfaceUsuario.tamanhoAreaConstruida();
                    double tamanhoTerreno = interfaceUsuario.tamanhoTerreno();

                    Financiamento financiamentoCasa = new Casa(valorImovel, prazoAnosFinanciamento, taxaJuros, tamanhoAreaConstruida, tamanhoTerreno);
                    
                    listaFinanciamentos.add(financiamentoCasa);

                    pagamentoMensal = financiamentoCasa.pagamentoMensal();
                    pagamentoTotal = financiamentoCasa.pagamentoTotal();

                    // Exibe os resultados do financiamento atual
                    System.out.printf("\nO pagamento mensal é de: R$ %.2f%n", pagamentoMensal);
                    System.out.printf("O pagamento total do financiamento é de: R$ %.2f%n", pagamentoTotal);
                    break;
                case 3:
                    String zonaTerreno = interfaceUsuario.zonaTerreno();

                    Financiamento financiamentoTerreno = new Terreno(valorImovel, prazoAnosFinanciamento, taxaJuros, zonaTerreno);

                    listaFinanciamentos.add(financiamentoTerreno);

                    pagamentoMensal = financiamentoTerreno.pagamentoMensal();
                    pagamentoTotal = financiamentoTerreno.pagamentoTotal();

                    // Exibe os resultados do financiamento atual
                    System.out.printf("\nO pagamento mensal é de: R$ %.2f%n", pagamentoMensal);
                    System.out.printf("O pagamento total do financiamento é de: R$ %.2f%n", pagamentoTotal);
                    break;
                default:
                    break;
            }

            while(true){
                System.out.print("Deseja simular mais algum financiamento(s/n)?");
                String entrada = scanner.nextLine().trim().toLowerCase();

                if (entrada.equals("s") || entrada.equals("S")) {
                    opcfin = 's';
                    break;
                } else if (entrada.equals("n") || entrada.equals("N")) {
                    opcfin = 'n';
                    break;
                } else {
                    System.out.println("Entrada inválida. Digite apenas 's' para sim ou 'n' para não.");
                }
            }
        } while (opcfin == 's');

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
