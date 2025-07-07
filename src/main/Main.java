package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import model.AumentoMaiorDoQueJurosException;
import model.Financiamento;
import model.typeimoveis.Apartamento;
import model.typeimoveis.Casa;
import model.typeimoveis.Terreno;
import util.InterfaceUsuario;

//Arrumar o bug to para tudo casa
//Adicionar o toSring para cada sub classe

public class Main {
    public static void main(String[] args) {
        final String ARQUIVO_TEXTO = "financiamentos.txt";
        final String ARQUIVO_SERIAL = "financiamentos.ser";

        ArrayList<Financiamento> listaFinanciamentos = new ArrayList<>();
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        Scanner scanner = new Scanner(System.in);

        double valorTotalImoveis = 0;
        double valorTotalPagamento = 0;

        double pagamentoMensal;
        double pagamentoTotal;

        
        char opcfin = 0;
        do {
            try {
                double valorImovel = interfaceUsuario.valorImovel();
                int prazoAnosFinanciamento = interfaceUsuario.prazoFinanciamentoAnos();
                double taxaJuros = interfaceUsuario.taxaJurosAnual();
            
                System.out.println("\n1 - Apartamento");
                System.out.println("2 - Casa");
                System.out.println("3 - Terreno");
                System.out.print("Qual tipo de financiamento você deseja: ");
                int opc = scanner.nextInt();
                scanner.nextLine(); // consumir quebra de linha
            
                Financiamento financiamento = null;
            
                switch (opc) {
                    case 1:
                        int vagas = interfaceUsuario.quantidadeVagasGaragem();
                        int andar = interfaceUsuario.andarApartamento();
                        financiamento = new Apartamento(valorImovel, prazoAnosFinanciamento, taxaJuros, vagas, andar);
                        break;
                    case 2:
                        double area = interfaceUsuario.tamanhoAreaConstruida();
                        double terreno = interfaceUsuario.tamanhoTerreno();
                        financiamento = new Casa(valorImovel, prazoAnosFinanciamento, taxaJuros, area, terreno);
                        break;
                        case 3:
                        String zona = interfaceUsuario.zonaTerreno();
                        financiamento = new Terreno(valorImovel, prazoAnosFinanciamento, taxaJuros, zona);
                        break;
                    default:
                    System.out.println("Opção inválida.");
                }
                
                if (financiamento != null) {
                    listaFinanciamentos.add(financiamento);
                    
                    pagamentoMensal = financiamento.pagamentoMensal();
                    pagamentoTotal = financiamento.pagamentoTotal();
                    
                    System.out.printf("\nO pagamento mensal é de: R$ %.2f%n", pagamentoMensal);
                    System.out.printf("O pagamento total do financiamento é de: R$ %.2f%n", pagamentoTotal);
                }
            
            } catch (AumentoMaiorDoQueJurosException e) {
                System.out.println("Erro: " + e.getMessage());
                continue;
            }
            
            while (true) {
                System.out.print("Deseja simular mais algum financiamento(s/n)? ");
                String entrada = scanner.nextLine().trim().toLowerCase();

                if (entrada.equals("s")) {
                    opcfin = 's';
                    break;
                } else if (entrada.equals("n")) {
                    opcfin = 'n';
                    break;
                } else {
                    System.out.println("Entrada inválida. Digite apenas 's' para sim ou 'n' para não.");
                }
            }
        } while (opcfin == 's');

        // Acumuladores
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

        // --- 2. Serializa o ArrayList ---
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_SERIAL))) {
            oos.writeObject(listaFinanciamentos);
            System.out.println("\nArrayList serializado em: " + ARQUIVO_SERIAL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --- 3. Lê e exibe o arquivo de texto ---
        System.out.println("\nConteúdo de " + ARQUIVO_TEXTO + ":");
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_TEXTO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --- 4. Lê e exibe o arquivo serializado ---
        File arquivoSerial = new File(ARQUIVO_SERIAL);
        if (arquivoSerial.exists()) {
            System.out.println("\nConteúdo serializado de " + ARQUIVO_SERIAL + ":");
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoSerial))) {
                @SuppressWarnings("unchecked")
                ArrayList<Financiamento> lida = (ArrayList<Financiamento>) ois.readObject();
                int cont = 1;
                for (Financiamento f : lida) {
                    System.out.printf("Financiamento %d – %s%n", cont++, f.toString());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("\nArquivo serializado não encontrado.");
        }

        // Exibe todos os financiamentos registrados na sessão atual
        System.out.println("\nLista de financiamentos cadastrados:");
        int contador = 1;
        for (Financiamento h : listaFinanciamentos) {
            System.out.printf("Financiamento %d – %s%n", contador++, h.toString());
        }

        System.out.printf("\nTotal dos imóveis: R$ %.2f | Total dos financiamentos: R$ %.2f%n",
                valorTotalImoveis, valorTotalPagamento);

        scanner.close();
    }
}
