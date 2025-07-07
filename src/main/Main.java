// Pacote principal
package main;

// Importações necessárias
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Importações de classes do seu projeto
import model.AumentoMaiorDoQueJurosException;
import model.Financiamento;
import model.typeimoveis.Apartamento;
import model.typeimoveis.Casa;
import model.typeimoveis.Terreno;
import util.InterfaceUsuario;

// Classe principal
public class Main {
    public static void main(String[] args) {
        // Constantes com os nomes dos arquivos
        final String ARQUIVO_TEXTO = "financiamentos.txt";
        final String ARQUIVO_SERIAL = "financiamentos.ser";

        // Lista que armazenará os financiamentos
        ArrayList<Financiamento> listaFinanciamentos = new ArrayList<>();
        
        // Interface para capturar dados do usuário
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        Scanner scanner = new Scanner(System.in);

        // Variáveis acumuladoras para somar os valores
        double valorTotalImoveis = 0;
        double valorTotalPagamento = 0;

        // Variáveis de controle para os cálculos
        double pagamentoMensal;
        double pagamentoTotal;

        // Controle da repetição
        char opcfin = 0;

        // Loop de simulação de financiamentos
        do {
            try {
                // Captura os dados básicos do financiamento
                double valorImovel = interfaceUsuario.valorImovel();
                int prazoAnosFinanciamento = interfaceUsuario.prazoFinanciamentoAnos();
                double taxaJuros = interfaceUsuario.taxaJurosAnual();
            
                // Menu de escolha do tipo de financiamento
                System.out.println("\n1 - Apartamento");
                System.out.println("2 - Casa");
                System.out.println("3 - Terreno");
                System.out.print("Qual tipo de financiamento você deseja: ");
                int opc = scanner.nextInt();
                scanner.nextLine(); // Consome quebra de linha
            
                Financiamento financiamento = null;
            
                // Criação do financiamento de acordo com o tipo selecionado
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
                
                // Se o financiamento for válido, calcula os pagamentos e armazena
                if (financiamento != null) {
                    listaFinanciamentos.add(financiamento);
                    
                    pagamentoMensal = financiamento.pagamentoMensal();
                    pagamentoTotal = financiamento.pagamentoTotal();
                    
                    System.out.printf("\nO pagamento mensal é de: R$ %.2f%n", pagamentoMensal);
                    System.out.printf("O pagamento total do financiamento é de: R$ %.2f%n", pagamentoTotal);
                }

            // Trata exceção personalizada para a classe Casa
            } catch (AumentoMaiorDoQueJurosException e) {
                System.out.println("Erro: " + e.getMessage());
                continue; // Volta ao início do loop
            }

            // Verifica se o usuário quer simular outro financiamento
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
        } while (opcfin == 's'); // Repete enquanto o usuário quiser

        // Soma o valor de todos os imóveis e pagamentos
        for (Financiamento f : listaFinanciamentos) {
            valorTotalImoveis += f.getValorImovel();
            valorTotalPagamento += f.pagamentoTotal();
        }

        // --- Grava os dados em um arquivo de texto (formato legível) ---
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_TEXTO))) {
            for (Financiamento f : listaFinanciamentos) {
                pw.println(f.toString()); // Grava cada financiamento em uma linha
            }
            System.out.println("\nDados gravados em texto: " + ARQUIVO_TEXTO);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --- Serializa a lista de financiamentos para um arquivo binário ---
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_SERIAL))) {
            oos.writeObject(listaFinanciamentos); // Salva o ArrayList inteiro
            System.out.println("\nArrayList serializado em: " + ARQUIVO_SERIAL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --- Lê e exibe o conteúdo do arquivo de texto ---
        System.out.println("\nConteúdo de " + ARQUIVO_TEXTO + ":");
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_TEXTO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --- Lê e exibe os dados serializados do arquivo .ser ---
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

        // Exibe novamente todos os financiamentos registrados na sessão atual
        System.out.println("\nLista de financiamentos cadastrados:");
        int contador = 1;
        for (Financiamento h : listaFinanciamentos) {
            System.out.printf("Financiamento %d – %s%n", contador++, h.toString());
        }

        // Mostra os totais finais
        System.out.printf("\nTotal dos imóveis: R$ %.2f | Total dos financiamentos: R$ %.2f%n",
                valorTotalImoveis, valorTotalPagamento);

        scanner.close(); // Fecha o scanner para liberar recurso
    }
}
