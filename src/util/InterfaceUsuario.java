package util; 
// Define que esta classe pertence ao pacote 'util', voltado para utilitários do sistema

import java.util.InputMismatchException;
import java.util.Scanner;
// Importações necessárias para leitura de dados via console e tratamento de exceções

public class InterfaceUsuario {
    
    // Scanner para capturar a entrada do usuário
    private final Scanner scanner = new Scanner(System.in);

    // Instância de ValidaDado usada para aplicar regras de validação nos inputs
    private final ValidaDado validaDado = new ValidaDado();

    // Solicita e valida o valor do imóvel digitado pelo usuário
    public double valorImovel() {
        while (true) {
            System.out.print("\nInsira o valor do imóvel: ");
            String entrada = scanner.next().replace(",", "."); // Permite usar vírgula ou ponto

            try {
                double valor = Double.parseDouble(entrada); // Tenta converter para double

                if (validaDado.validaValorImovel(valor)) {
                    return valor;
                } else {
                    System.out.println("Valor inválido. O valor deve ser positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Use apenas números, ex: 250000.00 ou 250000,00");
            }
        }
    }

    // Solicita e valida o prazo do financiamento em anos
    public int prazoFinanciamentoAnos() {
        while (true) {
            System.out.print("\nInsira o prazo de financiamento em anos: ");
            try {
                int prazo = scanner.nextInt(); // Lê valor inteiro

                if (validaDado.validaTempoFinanciamento(prazo)) {
                    return prazo;
                } else {
                    System.out.println("Prazo inválido. Insira um valor dentro dos limites permitidos.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro, ex: 15");
                scanner.next(); // Limpa entrada inválida
            }
        }
    }

    // Solicita e valida a taxa de juros anual
    public double taxaJurosAnual() {
        while (true) {
            System.out.print("\nInsira a taxa de juros anual (ex: 0,08 ou 0.08 para 8%): ");
            String entrada = scanner.next().replace(",", ".");

            try {
                double taxa = Double.parseDouble(entrada);

                if (validaDado.validaTaxa(taxa)) {
                    return taxa;
                } else {
                    System.out.println("Taxa inválida. Deve estar dentro de uma faixa aceitável.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número decimal, como 0.08 ou 0,08.");
            }
        }
    }

    // Solicita e valida a quantidade de vagas de garagem
    public int quantidadeVagasGaragem() {
        while (true) {
            System.out.print("\nInsira a quantidade de vagas disponíveis: ");
            try {
                int vagas = scanner.nextInt();
                if (vagas >= 0) {
                    return vagas;
                } else {
                    System.out.println("Quantidade inválida. O valor deve ser zero ou positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro, ex: 1, 2, 3...");
                scanner.next(); // Limpa entrada inválida
            }
        }       
    }

    // Solicita e valida o andar do apartamento
    public int andarApartamento() {
        while (true) {
            System.out.print("\nInsira o andar do apartamento: ");
            try {
                int andar = scanner.nextInt();
                if (andar >= 0) {
                    return andar;
                } else {
                    System.out.println("Andar inválido. O valor deve ser zero ou positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro, ex: 0, 1, 2...");
                scanner.next(); // Limpa entrada inválida
            }
        }
    }

    // Solicita e valida a área construída (em metros quadrados)
    public double tamanhoAreaConstruida() {
        while (true) {
            System.out.print("\nInsira a área construída (em m²): ");
            String entrada = scanner.next().replace(",", ".");

            try {
                double area = Double.parseDouble(entrada);
                if (area > 0) {
                    return area;
                } else {
                    System.out.println("Área inválida. O valor deve ser positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número decimal, ex: 150.5 ou 150,5");
            }
        }
    }

    // Solicita e valida o tamanho total do terreno (em metros quadrados)
    public double tamanhoTerreno() {
        while (true) {
            System.out.print("\nInsira o tamanho total do terreno (em m²): ");
            String entrada = scanner.next().replace(",", ".");

            try {
                double terreno = Double.parseDouble(entrada);
                if (terreno > 0) {
                    return terreno;
                } else {
                    System.out.println("Tamanho inválido. O valor deve ser positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número decimal, ex: 300.0 ou 300,0");
            }
        }
    }

    // Solicita e valida o tipo da zona do terreno (apenas valores específicos são aceitos)
    public String zonaTerreno() {
        while (true) {
            try {
                System.out.print("\nInforme o tipo da zona do terreno (residencial, comercial, industrial ou mista): ");
                String entrada = scanner.nextLine().trim().toLowerCase();

                switch (entrada) {
                    case "residencial":
                    case "comercial":
                    case "industrial":
                    case "mista":
                        return entrada; // Retorna apenas se for uma opção válida
                    default:
                        System.out.println("Tipo inválido. Digite uma das opções válidas: residencial, comercial, industrial ou mista.");
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro ao ler a entrada. Tente novamente.");
            }
        }
    }
}
