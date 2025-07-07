package main; // Define que a classe está no pacote "main"

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;   // Importa a classe ArrayList para armazenar uma lista de objetos
import java.util.Scanner;     // Importa Scanner para entrada de dados via terminal

import model.Financiamento;         // Importa a classe Financiamento do pacote "model"
import model.typeimoveis.Apartamento;
import model.typeimoveis.Casa;
import model.typeimoveis.Terreno;
import util.InterfaceUsuario;       // Importa a classe que lida com entrada e validação dos dados

//TODO adicionar serializarção
//TODO corrigir erro seguro obrigatorio


public class Main {
    public static void main(String[] args) {
        final String ARQUIVO_TEXTO = "financiamentos.txt";
        final String ARQUIVO_SERIAL = "financiamentos.ser";

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
    
            Financiamento financiamento = null;
            switch (opc) {
                case 1:
                    int quantidadeVagasGaragem = interfaceUsuario.quantidadeVagasGaragem();
                    int andarApartamento = interfaceUsuario.andarApartamento();

                    financiamento = new Apartamento(valorImovel, prazoAnosFinanciamento, taxaJuros, quantidadeVagasGaragem, andarApartamento);

                    listaFinanciamentos.add(financiamento);

                    pagamentoMensal = financiamento.pagamentoMensal();
                    pagamentoTotal = financiamento.pagamentoTotal();

                    // Exibe os resultados do financiamento atual
                    System.out.printf("\nO pagamento mensal é de: R$ %.2f%n", pagamentoMensal);
                    System.out.printf("O pagamento total do financiamento é de: R$ %.2f%n", pagamentoTotal);
                    break;
                case 2:
                    double tamanhoAreaConstruida = interfaceUsuario.tamanhoAreaConstruida();
                    double tamanhoTerreno = interfaceUsuario.tamanhoTerreno();

                    financiamento = new Casa(valorImovel, prazoAnosFinanciamento, taxaJuros, tamanhoAreaConstruida, tamanhoTerreno);
                    
                    listaFinanciamentos.add(financiamento);

                    pagamentoMensal = financiamento.pagamentoMensal();
                    pagamentoTotal = financiamento.pagamentoTotal();

                    // Exibe os resultados do financiamento atual
                    System.out.printf("\nO pagamento mensal é de: R$ %.2f%n", pagamentoMensal);
                    System.out.printf("O pagamento total do financiamento é de: R$ %.2f%n", pagamentoTotal);
                    break;
                case 3:
                    String zonaTerreno = interfaceUsuario.zonaTerreno();

                    financiamento = new Terreno(valorImovel, prazoAnosFinanciamento, taxaJuros, zonaTerreno);

                    listaFinanciamentos.add(financiamento);

                    pagamentoMensal = financiamento.pagamentoMensal();
                    pagamentoTotal = financiamento.pagamentoTotal();

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

        // --- 1. Grava em arquivo de texto ---
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_TEXTO))) {
            for (Financiamento f : listaFinanciamentos) {
                pw.println(f.toString());
            }
            System.out.println("\nDados gravados em texto: " + ARQUIVO_TEXTO);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --- 2. Lê e exibe o arquivo de texto ---
        System.out.println("\n--- Lendo " + ARQUIVO_TEXTO + " ---");
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_TEXTO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Lendo array serializado de " + ARQUIVO_SERIAL + " ---");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_SERIAL))) {
            @SuppressWarnings("unchecked")
            ArrayList<Financiamento> lida = (ArrayList<Financiamento>) ois.readObject();
            int cont = 1;
            for (Financiamento f : lida) {
                System.out.printf("Financiamento %d – %s%n", cont++, f.toString());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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
